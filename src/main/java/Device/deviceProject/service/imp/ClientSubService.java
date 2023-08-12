package Device.deviceProject.service.imp;

import Device.deviceProject.models.ClientSub;
import Device.deviceProject.repositories.ClientSubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientSubService    {
    @Autowired
    ClientSubRepository clientSubRepository;

    public List<ClientSub> getAll(){

        return clientSubRepository.trovaTutto();

    }

}
