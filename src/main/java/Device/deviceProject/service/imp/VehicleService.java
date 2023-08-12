package Device.deviceProject.service.imp;

import Device.deviceProject.models.ClientSub;
import Device.deviceProject.models.LogisticClient;
import Device.deviceProject.models.Subscription;
import Device.deviceProject.models.Vehicle;
import Device.deviceProject.repositories.ClientSubRepository;
import Device.deviceProject.repositories.LogisticClientRepository;
import Device.deviceProject.repositories.SubscriptionRepository;
import Device.deviceProject.repositories.VehicleRepository;
import Device.deviceProject.service.iService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService implements iService<Vehicle> {
    @Autowired
    VehicleRepository vehicleRepository;

    @Autowired
    ClientSubRepository clientSubRepository;


    @Override
    public List<Vehicle> findAll() {
        return null;
    }

    @Override
    public void add(Vehicle element) throws Exception {
        List<ClientSub> listSubscription = clientSubRepository.subscriptionByIdCompany(element.getLogisticCompany().getIdLogistic());
        if (listSubscription != null) {
            listSubscription.forEach(sub -> {
                if (element.getSubscriptionAssociated() == sub.getIdSub().getIdSubscription()) {
                    element.setSubscriptionAssociated(element.getSubscriptionAssociated());

                    clientSubRepository.updateUsed(true, element.getSubscriptionAssociated());
                    clientSubRepository.updateStatus("ACTIVATED", element.getSubscriptionAssociated());
                    vehicleRepository.save(element);

                }
            });

        } else {
            System.out.println("QUESTA AZIENDA NON HA ABBONAMENTI ALL'ATTIVO");
        }


    }

    @Override
    public void remove(int id) throws Exception {
        vehicleRepository.deleteById(id);
    }

    @Override
    public void update(Vehicle element) throws Exception {

    }


}
