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
import java.util.stream.Collectors;

@Service
public class LogisticClientService implements iService<LogisticClient> {
    @Autowired
    LogisticClientRepository logisticClientRepository;

    @Autowired
    SubscriptionRepository subscriptionRepository;
    @Autowired
    DeviceRepository deviceRepository;

    @Autowired
    ClientSubRepository clientSubRepository;

    @Override
    public List<LogisticClient> findAll() {
        return logisticClientRepository.findAll();
    }

    @Override
    public void add(LogisticClient element) throws Exception {
        LogisticClient existingCompany = logisticClientRepository.findByCfLogistic(element.getCfLogistic()); // if company already exist use the company on the DB

        if (existingCompany != null) {

            List<Subscription> listSubscription = element.getListSubscription().stream().map(x -> subscriptionRepository.findById(x.getIdSubscription()).orElseThrow()).filter(sub->sub.isAvailable()).collect(Collectors.toList());

            if (listSubscription.size() == 0) {
                System.out.println("GLI ABBONAMENTI NON SONO ASSEGNABILI");
            } else {

                existingCompany.getListSubscription().addAll(listSubscription);
            }

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
                    clientsub.setIdCompany(existingCompany);
                    clientsub.setStatus("ACTIVATED");
                    clientSubRepository.save(clientsub);
                } else {
                    System.out.println("ABBONAMENTI SCADUTI");
                }
            });

            logisticClientRepository.save(existingCompany);
        } else {
            //List<Subscription> listSubscription = element.getListSubscription().stream().map(x -> subscriptionRepository.findById(x.getIdSubscription()).orElseThrow()).collect(Collectors.toList());
            List<Subscription> listSubscription = element.getListSubscription().stream().map(x -> subscriptionRepository.findById(x.getIdSubscription()).orElseThrow()).filter(sub->sub.isAvailable()).collect(Collectors.toList());
            if (listSubscription.size() == 0) {
                System.out.println("ABBONAMENTI NON ASSEGNABILI");
            } else {
                element.setListSubscription(listSubscription);
                logisticClientRepository.save(element);

            }

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
                    clientsub.setStatus("ACTIVATED");
                    clientSubRepository.save(clientsub);
                } else {
                    System.out.println("ABBONAMENTI SCADUTI");
                }
            });


        }


    }


    @Override
    public void remove(int id) throws Exception {
        logisticClientRepository.deleteById(id);
    }

    @Override
    public void update(LogisticClient element) throws Exception {

    }


}
