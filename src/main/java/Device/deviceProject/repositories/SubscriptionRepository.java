package Device.deviceProject.repositories;

import Device.deviceProject.models.Subscription;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface SubscriptionRepository extends JpaRepository<Subscription,Integer> {

 @Transactional
 @Modifying
 @Query("UPDATE Subscription u SET u.dateActivation=?1 WHERE u.idSubscription=?2")
 public void updateDateActivation(LocalDate data, int idSub);


 @Transactional
 @Modifying
 @Query("UPDATE Subscription u SET u.dateFinish=?1 WHERE u.idSubscription=?2")
 public void updateDateFinish(LocalDate data, int idSub);

 @Transactional
 @Modifying
 @Query("UPDATE Subscription u SET u.available=?1 , u.device.idDevice=?2 WHERE u.idSubscription=?3")
 public void updateAvailableAndDevice (boolean available,Integer idDevice, int idSub);
}
