package Device.deviceProject.controller;

import Device.deviceProject.models.Device;
import Device.deviceProject.response.ResponseHandler;
import Device.deviceProject.service.iService;
import Device.deviceProject.service.imp.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeviceController {

    @Autowired
    iService<Device> deviceService;

    @PostMapping("/device")
    public ResponseEntity<Object> add(@RequestBody Device element) throws Exception {
        try {
            deviceService.add(element);
            return ResponseHandler.generateMessage("ok", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.generateMessage(e.getMessage(), HttpStatus.BAD_REQUEST);

        }

    }

    @GetMapping("/device")
    public ResponseEntity<Object> getAll() {
        try {
            return ResponseHandler.generateResponse("ok", HttpStatus.OK, deviceService.findAll());

        } catch (Exception e) {
            return ResponseHandler.generateMessage("No", HttpStatus.BAD_REQUEST);

        }
    }

}
