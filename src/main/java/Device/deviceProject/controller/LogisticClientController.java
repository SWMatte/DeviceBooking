package Device.deviceProject.controller;

import Device.deviceProject.models.LogisticClient;
import Device.deviceProject.models.Subscription;
import Device.deviceProject.response.ResponseHandler;
import Device.deviceProject.service.iService;
import Device.deviceProject.service.iServiceClientLogistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class LogisticClientController {

    @Autowired
    iService<LogisticClient> clientiService;

    @Autowired
    iServiceClientLogistic iServiceClientLogistic;


    @PostMapping("/client")
    public void add(@RequestBody LogisticClient element) throws Exception {
        clientiService.add(element);
    }


    @GetMapping("/client")
    public ResponseEntity<Object> getAll() {
        try {
            return ResponseHandler.generateResponse("ok", HttpStatus.OK, clientiService.findAll());
        } catch (Exception e) {
            return ResponseHandler.generateMessage("No", HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/clientsendEmail")
    public void sendEmail(){
         iServiceClientLogistic.sendEmail();


    }


}
