package Device.deviceProject.service.imp;

import Device.deviceProject.models.*;
import Device.deviceProject.repositories.ClientSubRepository;
import Device.deviceProject.repositories.SubscriptionRepository;
import Device.deviceProject.repositories.DeviceRepository;
import Device.deviceProject.repositories.LogisticClientRepository;
import Device.deviceProject.service.iService;
import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class LogisticClientService implements iService<LogisticClient> {
    @Autowired
    LogisticClientRepository logisticClientRepository;

    @Autowired
    SubscriptionRepository subscriptionRepository;

    @Autowired
    ClientSubRepository clientSubRepository;

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


}
