package Device.deviceProject.controller;

import Device.deviceProject.models.Subscription;
import Device.deviceProject.response.ResponseHandler;
import Device.deviceProject.service.iService;
import Device.deviceProject.service.imp.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SubscriptionController {


    @Autowired
    iService<Subscription> abbonamentoService;

    @Autowired
    SubscriptionService subscriptionService;


    @PostMapping("/subscription")
    public ResponseEntity<Object> add(@RequestBody Subscription element) {
        try {
            abbonamentoService.add(element);


            return ResponseHandler.generateMessage("ok", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.generateMessage(e.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }

    @GetMapping("/subscription")
    public ResponseEntity<Object> getAll(   ) {
        try {
            abbonamentoService.findAll();
            return ResponseHandler.generateMessage("ok", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.generateMessage("No", HttpStatus.BAD_REQUEST);

        }
    }

    @DeleteMapping("/subscription/{id}")
    public void removeSubscription(@PathVariable int id  ) throws Exception {
        abbonamentoService.remove(id);
    }

    @PostMapping("/subscriptionexpired")
    public void removeSubscriptionExpired(   ) {
        subscriptionService.expiredSubscription();
    }
}
