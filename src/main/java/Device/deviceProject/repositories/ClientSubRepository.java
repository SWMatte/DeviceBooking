package Device.deviceProject.repositories;

import Device.deviceProject.models.ClientSub;
import Device.deviceProject.models.Subscription;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClientSubRepository extends JpaRepository<ClientSub,Integer> {


     @Query("""
    SELECT u FROM ClientSub u WHERE u.idCompany.idLogistic=?1 AND u.isUsed=FALSE
""")
    public List<ClientSub> subscriptionByIdCompany(int idCompany);


    @Transactional
    @Modifying
    @Query("UPDATE ClientSub u SET u.isUsed=?1 WHERE u.idSub.idSubscription=?2")
    public void updateUsed(boolean used, int idSubscription);

    @Transactional
    @Modifying
    @Query("UPDATE ClientSub u SET u.status=?1 WHERE u.idSub.idSubscription=?2")
    public void updateStatus(String status, int idSubscription);

}
