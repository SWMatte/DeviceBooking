package Device.deviceProject.repositories;

import Device.deviceProject.DTO.VehicleDTO;
import Device.deviceProject.models.Vehicle;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {


    @Transactional
    @Modifying
    @Query("UPDATE Vehicle u SET u.statusExpirated=?1  WHERE u.subscriptionAssociated=?2")
    public void updateSubscriptionExpirated(String statusExpirated, int subscriptionAssociated);

    @Query(value = """
                SELECT vehicle.*, logistic_client.*, subscription.*
                FROM vehicle
                INNER JOIN subscription ON vehicle.subscription_associated = subscription.id_subscription
                INNER JOIN logistic_client ON vehicle.id_logistic = logistic_client.id_logistic
            """, nativeQuery = true)
    public List<Object[]> allInformation(int sub);

    @Query("""
    SELECT DISTINCT NEW Device.deviceProject.DTO.VehicleDTO
    (v.idVehicle,
    v.assicuration,
    v.nameVehicle,
    v.plate,
    v.statusExpirated,
    lc.idLogistic,
    lc.cfLogistic,
    lc.name,
    s.idSubscription,
    s.available,
    s.dateActivation,
    s.dateFinish,
    s.duration,
    s.price)
    FROM
    Vehicle v
    JOIN
    LogisticClient lc ON v.logisticCompany.idLogistic = lc.idLogistic
    JOIN
    Subscription s ON v.subscriptionAssociated = s.idSubscription 
""")
    public List<VehicleDTO> getAllInfo();

/*
    @Query(value = """
            SELECT vehicle.*, logistic_client.*, subscription.*,device.*
               FROM vehicle
                 INNER JOIN logistic_client ON vehicle.id_logistic = logistic_client.id_logistic
             INNER JOIN subscription ON vehicle.subscription_associated = subscription.id_subscription
              INNER JOIN device on subscription.id_device = device.id_device;
            """, nativeQuery = true)
    List<Object[]> allInfo();
 */


    @Query("SELECT u FROM Vehicle u where u.logisticCompany.idLogistic=?1")
    public List<Vehicle> findAllVehicleByCompany(int idCompany);

}
