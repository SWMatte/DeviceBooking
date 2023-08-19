package Device.deviceProject.service.imp;

import Device.deviceProject.models.Subscription;
import Device.deviceProject.models.Device;
import Device.deviceProject.models.DurationSubscription;
import Device.deviceProject.repositories.ClientSubRepository;
import Device.deviceProject.repositories.SubscriptionRepository;
import Device.deviceProject.repositories.DeviceRepository;
import Device.deviceProject.repositories.VehicleRepository;
import Device.deviceProject.service.iService;
import Device.deviceProject.service.iSubscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubscriptionService implements iService<Subscription>, iSubscription {
    @Autowired
    SubscriptionRepository subscriptionRepository;
    @Autowired
    DeviceRepository deviceRepository;
    @Autowired
    ClientSubRepository clientSubRepository;

    @Autowired
    VehicleRepository vehicleRepository;

    @Override
    public List<Subscription> findAll() {
        return subscriptionRepository.findAll();
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
            deviceRepository.updateAssociated(true,d.getIdDevice());
        } else {
            throw new IllegalArgumentException();
        }

    }

    @Override
    public void expiredSubscription() {
        // filter subscription for not available anymore
        List<Subscription> subExpired = subscriptionRepository.findAll()
                .stream()
                .filter(subscription -> subscription.getDateActivation()!=null && subscription.getDateFinish()!=null)
                .filter(sub -> sub.getDateActivation().isAfter(sub.getDateFinish()) && sub.isAvailable())
                .collect(Collectors.toList());

        // for each subscription not available updated the other tables device,clientSub,subscription and vehicle
        subExpired.forEach(x ->{
            deviceRepository.updateAssociated(false,x.getDevice().getIdDevice());
            clientSubRepository.updateStatus("EXPIRATED",x.getIdSubscription());
            subscriptionRepository.updateAvailableAndDeviceAndStatus(false,null,"EXPIRATED" ,x.getIdSubscription());
            vehicleRepository.updateSubscriptionExpirated("EXPIRATED",x.getIdSubscription());
        } );

    }

    @Override
    public void notificationExpiration() {

    }

    @Override
    public void remove(int id) throws Exception {
        subscriptionRepository.deleteById(id);
    }

    @Override
    public void update(Subscription element) throws Exception {

    }
}
