package Device.deviceProject.service.imp;

import Device.deviceProject.models.Device;
import Device.deviceProject.models.DurationSubscription;
import Device.deviceProject.models.Subscription;
import Device.deviceProject.models.LogisticClient;
import Device.deviceProject.repositories.SubscriptionRepository;
import Device.deviceProject.repositories.DeviceRepository;
import Device.deviceProject.repositories.LogisticClientRepository;
import Device.deviceProject.service.iService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    @Override
    public List<LogisticClient> findAll() {
        return logisticClientRepository.findAll();
    }

    @Override
    public void add(LogisticClient element) throws Exception {
        // list subscriptions and list of device linked of the subscriptions
        List<Subscription> listsub = element.getListSubscrition().stream().map(sub -> subscriptionRepository.findById(sub.getIdSubscription()).orElseThrow()).collect(Collectors.toList());
        List<Device> listDevice = listsub.stream()
                .map(sub -> deviceRepository.findById(sub.getDevice().getIdDevice()).orElseThrow()).collect(Collectors.toList());

        if (listsub != null) {
            if (
                // licence of device have to be available && subscription have to be available
                    listDevice.stream()
                            .allMatch(device -> device.isLicence() == false) &&
                            listsub.stream()
                                    .allMatch(sub -> sub.isAvailable() == true)
            ) {

                element.setListSubscrition(listsub);

                listsub.forEach(sub -> {
                    int idSub = sub.getIdSubscription();
                    LocalDate dataActivation = LocalDate.now();
                    subscriptionRepository.updateDateActivation(LocalDate.now(), idSub);
                    int idDevice = sub.getDevice().getIdDevice();
                    deviceRepository.updateLicence(true, idDevice);

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
                });
                logisticClientRepository.save(element);
            } else {
                System.out.println("ALCUNI DEVICE NON SONO DISPONIBILI");
            }
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
