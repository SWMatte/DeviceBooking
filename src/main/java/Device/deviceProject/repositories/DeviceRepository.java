package Device.deviceProject.repositories;

import Device.deviceProject.models.Device;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface DeviceRepository extends JpaRepository<Device,Integer> {

    @Transactional
    @Modifying
    @Query("UPDATE Device u SET u.licence=?1 WHERE u.idDevice=?2")
    public void updateLicence(boolean licence, int idDevice);
}
