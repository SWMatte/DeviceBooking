package Device.deviceProject.service.imp;

import Device.deviceProject.models.Device;
import Device.deviceProject.repositories.DeviceRepository;
import Device.deviceProject.service.iService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceService implements  iService<Device> {
@Autowired
DeviceRepository deviceRepository;


    @Override
    public List<Device> findAll() {
        return deviceRepository.findAll();
    }

    @Override
    public void add(Device element) throws Exception {
        deviceRepository.save(element);
    }

    @Override
    public void remove(int id) throws Exception {
        deviceRepository.deleteById(id);
    }

    @Override
    public void update(Device element) throws Exception {

    }
}
