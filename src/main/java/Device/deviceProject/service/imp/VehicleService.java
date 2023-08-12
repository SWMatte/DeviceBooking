package Device.deviceProject.service.imp;

import Device.deviceProject.DTO.VehicleDTO;
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
import java.util.stream.Collectors;

@Service
public class VehicleService implements iService<Vehicle> {
    @Autowired
    VehicleRepository vehicleRepository;

    @Autowired
    ClientSubRepository clientSubRepository;
    @Autowired
    SubscriptionRepository subscriptionRepository;

    @Autowired
    LogisticClientRepository logisticClientRepository;



    public List<VehicleDTO> getAll() {
        List<Vehicle> listaVeicoli = vehicleRepository.findAll();

       List<VehicleDTO> veicoliDTO= listaVeicoli.stream().map(
                vehicle -> {
                    LogisticClient azienda= logisticClientRepository.findById(vehicle.getLogisticCompany().getIdLogistic()).orElseThrow();
                    return new VehicleDTO(vehicle.getIdVehicle(), vehicle.getNameVehicle(), vehicle.getPlate(), vehicle.getAssicuration(), vehicle.getStatusExpirated(), azienda, vehicle.getSubscriptionAssociated());
                }
        ).collect(Collectors.toList());

        return veicoliDTO;

    }

    @Override
    public List<Vehicle> findAll() {
        return null;
    }

    @Override
    public void add(Vehicle element) throws Exception {
        List<ClientSub> listSubscription = clientSubRepository.subscriptionByIdCompany(element.getLogisticCompany().getIdLogistic());
        Subscription subscriptionAssociated = subscriptionRepository.findById(element.getSubscriptionAssociated()).orElseThrow();
        if (listSubscription != null) {
            if (subscriptionAssociated.isAvailable()) {

                listSubscription.forEach(sub -> {
                    {

                        if (element.getSubscriptionAssociated() == sub.getIdSub().getIdSubscription()) {
                            element.setSubscriptionAssociated(element.getSubscriptionAssociated());

                            clientSubRepository.updateUsed(true, element.getSubscriptionAssociated());
                            clientSubRepository.updateStatus("ACTIVATED", element.getSubscriptionAssociated());
                            vehicleRepository.save(element);
                            vehicleRepository.updateSubscriptionExpirated("ACTIVE", element.getIdVehicle());

                        }
                    }
                });
            }else{
                System.out.println("abbonamento scaduto");
            }
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

    public List<Vehicle> vehicleActive( ) {
        return vehicleRepository.findAll().stream().filter(vehicle -> vehicle.getStatusExpirated().equals("ACTIVE")).collect(Collectors.toList());

    }


}
