package Device.deviceProject.controller;

import Device.deviceProject.DTO.VehicleDTO;
import Device.deviceProject.models.Vehicle;
import Device.deviceProject.response.ResponseHandler;
import Device.deviceProject.service.iService;
import Device.deviceProject.service.imp.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VehicleController {

    @Autowired
    iService<Vehicle> IvehicleiService;
    @Autowired
    VehicleService vehicleService;

    @PostMapping("/vehicle")
    public ResponseEntity<Object> add(@RequestBody Vehicle element) {
        try {
            IvehicleiService.add(element);

            return ResponseHandler.generateMessage("ok", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.generateMessage(e.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }

    @GetMapping("/vehicle")
    public ResponseEntity<Object> getAll() {
        try {
            return ResponseHandler.generateResponse("ok", HttpStatus.OK, vehicleService.getAll());
        } catch (Exception e) {
            return ResponseHandler.generateMessage("No", HttpStatus.BAD_REQUEST);

        }
    }

    @GetMapping("/vehicleActive")
    public List<Vehicle> vehicleActive() {
        return vehicleService.vehicleActive();
    }

    @GetMapping("/vehicleallInformation")
    public List<VehicleDTO> allInformation() {
        return vehicleService.allInformation();

    }

    @GetMapping("/vehicleCompany")
    public List<Vehicle> getAllVehicleByCompany(@RequestParam("idCompany") int idCompany){
        return vehicleService.allVehicleByCompany(idCompany);
    }

}
