package Device.deviceProject.controller;

import Device.deviceProject.models.LogisticClient;
import Device.deviceProject.models.Subscription;
import Device.deviceProject.response.ResponseHandler;
import Device.deviceProject.service.iService;
import Device.deviceProject.service.iServiceClientLogistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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

    @PostMapping("/clientEnableEmail")
    public void clientEnableEmail(@RequestParam("idLogistic") int idLogistic)   {
        iServiceClientLogistic.enableNotifyEmail(idLogistic);
    }
    @PostMapping("/clientDisableEmail")
    public void clientDisableEmail(@RequestParam("idLogistic") int idLogistic)   {
        iServiceClientLogistic.removeNotifyEmail(idLogistic);
    }




}
