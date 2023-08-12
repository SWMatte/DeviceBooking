package Device.deviceProject.repositories;

import Device.deviceProject.models.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle,Integer> {



    Vehicle findByPlate(String plate);
}
