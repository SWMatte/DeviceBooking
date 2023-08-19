package Device.deviceProject.repositories;

import Device.deviceProject.models.LogisticClient;
import Device.deviceProject.models.Subscription;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LogisticClientRepository extends JpaRepository<LogisticClient,Integer> {

    public LogisticClient findByCfLogistic(String cf);


    @Transactional
    @Modifying
    @Query("UPDATE LogisticClient u SET u.listSubscription=?1 WHERE u.idLogistic=?2")
    public void updateListSubscription(List<Subscription> subscription, int idLogistic);

    @Query("SELECT u FROM LogisticClient u WHERE u.enableEmail=true")
    List<LogisticClient> listCompanies();
}
