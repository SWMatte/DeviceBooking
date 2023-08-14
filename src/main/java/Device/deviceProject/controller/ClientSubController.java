package Device.deviceProject.controller;

import Device.deviceProject.models.ClientSub;
import Device.deviceProject.service.imp.ClientSubService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClientSubController {

    @Autowired
    ClientSubService clientSubService;


    @GetMapping("clientSub")
    public List<ClientSub> findAll() {
        return clientSubService.findAll();
    }


}
