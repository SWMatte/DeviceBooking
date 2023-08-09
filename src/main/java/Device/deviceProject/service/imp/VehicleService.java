package Device.deviceProject.service.imp;

import Device.deviceProject.models.Vehicle;
import Device.deviceProject.repositories.VehicleRepository;
import Device.deviceProject.service.iService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService implements iService<Vehicle> {
@Autowired
VehicleRepository vehicleRepository;


    @Override
    public List<Vehicle> findAll() {
        return null;
    }

    @Override
    public void add(Vehicle element) throws Exception {
        vehicleRepository.save(element);

    }

    @Override
    public void remove(int id) throws Exception {
        vehicleRepository.deleteById(id);
    }

    @Override
    public void update(Vehicle element) throws Exception {

    }
}
