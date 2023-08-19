package Device.deviceProject.service.imp;

import Device.deviceProject.models.*;
import Device.deviceProject.repositories.ClientSubRepository;
import Device.deviceProject.repositories.SubscriptionRepository;
import Device.deviceProject.repositories.LogisticClientRepository;
import Device.deviceProject.service.iService;
import Device.deviceProject.service.iServiceClientLogistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class LogisticClientService implements iService<LogisticClient>, iServiceClientLogistic {
    @Autowired
    LogisticClientRepository logisticClientRepository;

    @Autowired
    SubscriptionRepository subscriptionRepository;

    @Autowired
    ClientSubRepository clientSubRepository;

    @Autowired
    SendEmailService sendEmailService;

    @Override
    public List<LogisticClient> findAll() {
        return logisticClientRepository.findAll();
    }

    @Override
    public void add(LogisticClient element) throws Exception {
        LogisticClient existingCompany = logisticClientRepository.findByCfLogistic(element.getCfLogistic()); // if company already exist use the company on the DB

        getListSubscription(element); // use the method to get a list

        if (getListSubscription(element).size() == 0) {
            System.out.println("GLI ABBONAMENTI NON SONO ASSEGNABILI");
        } else {
            if (existingCompany != null) {
                existingCompany.getListSubscription().addAll(getListSubscription(element));
                setSubscriptionAndTable(getListSubscription(element), existingCompany);

                logisticClientRepository.save(existingCompany);
            } else {
                element.setListSubscription(getListSubscription(element));
                logisticClientRepository.save(element);
                setSubscriptionAndTable(getListSubscription(element), element);  // use the method to update the table ClientSub and update subscription date

            }
        }


    }

    public List<Subscription> getListSubscription(LogisticClient element) { //  based on the client's subscription list, search subscriptions
        List<Subscription> listSubscription = element.getListSubscription().stream()
                .map(subscription -> {
                    try {
                        return subscriptionRepository.findById(subscription.getIdSubscription())
                                .orElseThrow(() -> new IllegalArgumentException());
                    } catch (IllegalArgumentException e) {

                        System.out.println("Subscription with ID " + subscription.getIdSubscription() + " not found.");
                        return null;
                    }

                })
                .filter(Objects::nonNull) // remove id subscription not valide
                .filter(Subscription::isAvailable)
                .collect(Collectors.toList());

        return listSubscription;
    }

    public void setSubscriptionAndTable(List<Subscription> listSubscription, LogisticClient element) {


        listSubscription.forEach(sub -> {   // for each subscription in according to the duration set the date activation and finish
            if (sub.isAvailable()) {
                int idSub = sub.getIdSubscription();
                LocalDate dataActivation = LocalDate.now();
                subscriptionRepository.updateDateActivation(LocalDate.now(), idSub);

                switch (sub.getDuration()) {
                    case "month":
                        subscriptionRepository.updateDateFinish(dataActivation.plusDays(30), idSub);
                        break;
                    case "annual":
                        subscriptionRepository.updateDateFinish(dataActivation.plusYears(1), idSub);
                        break;
                    case "biweekly":
                        subscriptionRepository.updateDateFinish(dataActivation.plusWeeks(2), idSub);
                        break;
                    case "week":
                        subscriptionRepository.updateDateFinish(dataActivation.plusWeeks(1), idSub);
                        break;

                }
                ClientSub clientsub = new ClientSub();
                clientsub.setIdSub(sub);
                clientsub.setIdCompany(element);
                clientSubRepository.save(clientsub);
            }
        });

    }


    @Override
    public void remove(int id) throws Exception {
        logisticClientRepository.deleteById(id);
    }

    @Override
    public void update(LogisticClient element) throws Exception {

    }

    @Override
    @Scheduled(cron = "0 0 * * * ?")
    public void sendEmail() {
        List<LogisticClient> companiesWithEnabledEmail = logisticClientRepository.listCompanies(); // Recupera le aziende con enableEmail = true

        Map<LogisticClient, List<Subscription>> mapCompanyAndSubscription = new HashMap<>(); // crea la mappa con aziende - abbonamenti

        LocalDate currentDate = LocalDate.now();

        for (LogisticClient company : companiesWithEnabledEmail) {

            List<Subscription> subAlmostExpired = company.getListSubscription().stream()      // ottengo la lista di abbonamenti scaduti
                    .filter(sub -> currentDate.plusDays(3).isEqual(sub.getDateFinish()))
                    .collect(Collectors.toList());

            if (!subAlmostExpired.isEmpty()) {  // se quella lista non è vuota riempio la mappa
                mapCompanyAndSubscription.put(company, subAlmostExpired);
            }
        }

        mapCompanyAndSubscription.forEach((company, subscriptions) -> {   // costruisco il messaggio da inviare

            String emailSubject = "Subscription Expiry Notification";  // oggetto dell'email
            StringBuilder emailMessage = new StringBuilder();
            emailMessage.append("Dear ").append(company.getName()).append(",\n\n");
            emailMessage.append("Your subscriptions are expiring soon:\n");

            subscriptions.forEach(sub -> {
                emailMessage.append("- Subscription ID: ").append(sub.getIdSubscription())
                        .append(", Expiry Date: ").append(sub.getDateFinish()).append("\n");

              });

            String recipientEmail = company.getEmail();
            System.out.println(recipientEmail);
            System.out.println(emailSubject);
            System.out.println(emailMessage);
           // sendEmailService.sendSingleEmail(recipientEmail, emailSubject, emailMessage.toString());

        });


    }

    @Override
    public void removeNotifyEmail(int idLogistic) {
        logisticClientRepository.updateEnableEmail(false,idLogistic);
    }
    @Override
    public void enableNotifyEmail(int idLogistic) {
        logisticClientRepository.updateEnableEmail(true,idLogistic);
    }
}
