package Device.deviceProject.repositories;

import Device.deviceProject.models.Vehicle;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface VehicleRepository extends JpaRepository<Vehicle,Integer> {



    Vehicle findByPlate(String plate);
    Vehicle findBySubscriptionAssociated(int subscriptionAssociated);

    @Transactional
    @Modifying
    @Query("UPDATE Vehicle u SET u.statusExpirated=?1  WHERE u.subscriptionAssociated=?2")
    public void updateSubscriptionExpirated ( String statusExpirated ,int subscriptionAssociated);
}
