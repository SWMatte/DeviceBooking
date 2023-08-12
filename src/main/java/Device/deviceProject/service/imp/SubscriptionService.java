package Device.deviceProject.service.imp;

import Device.deviceProject.models.Subscription;
import Device.deviceProject.models.Device;
import Device.deviceProject.models.DurationSubscription;
import Device.deviceProject.repositories.SubscriptionRepository;
import Device.deviceProject.repositories.DeviceRepository;
import Device.deviceProject.service.iService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubscriptionService implements iService<Subscription> {
    @Autowired
    SubscriptionRepository subscriptionRepository;
    @Autowired
    DeviceRepository deviceRepository;

    @Override
    public List<Subscription> findAll() {
        return null;
    }

    @Override
    public void add(Subscription element) throws Exception {
        Device d = deviceRepository.findById(element.getDevice().getIdDevice()).orElseThrow();
        if (d != null) {
            element.setDevice(d);

            switch (element.getDuration()) {
                case "month":
                    element.setDuration(String.valueOf(DurationSubscription.month));
                    break;
                case "annual":
                    element.setDuration(String.valueOf(DurationSubscription.annual));
                    break;
                case "biweekly":
                    element.setDuration(String.valueOf(DurationSubscription.biweekly));
                    break;
                case "week":
                    element.setDuration(String.valueOf(DurationSubscription.week));
                    break;
            }

            subscriptionRepository.save(element);
        } else {
            throw new IllegalArgumentException();
        }

    }


    @Override
    public void remove(int id) throws Exception {
        subscriptionRepository.deleteById(id);
    }

    @Override
    public void update(Subscription element) throws Exception {

    }
    public void expiredSubscription() {
        List<Subscription> subExpired = subscriptionRepository.findAll()
                .stream()
                .filter(sub -> sub.getDateActivation().isAfter(sub.getDateFinish())).collect(Collectors.toList());


        subExpired.forEach(x ->{
            deviceRepository.updateLicence(false,x.getDevice().getIdDevice());
            subscriptionRepository.deleteById(x.getIdSubscription());

        } );



    }
}
