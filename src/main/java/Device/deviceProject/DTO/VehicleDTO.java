package Device.deviceProject.DTO;

import Device.deviceProject.models.LogisticClient;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

public class VehicleDTO {

    private int idVehicle;
    private String nameVehicle;

    private String plate;

    private LocalDate assicuration;

    private String statusExpirated;

    private LogisticClient logisticCompany;

    private int subscriptionAssociated;


    public VehicleDTO() {
    }

    public VehicleDTO(int idVehicle, String nameVehicle, String plate, LocalDate assicuration, String statusExpirated, LogisticClient logisticCompany, int subscriptionAssociated) {
        this.idVehicle = idVehicle;
        this.nameVehicle = nameVehicle;
        this.plate = plate;
        this.assicuration = assicuration;
        this.statusExpirated = statusExpirated;
        this.logisticCompany = logisticCompany;
        this.subscriptionAssociated = subscriptionAssociated;
    }


    public int getIdVehicle() {
        return idVehicle;
    }

    public void setIdVehicle(int idVehicle) {
        this.idVehicle = idVehicle;
    }

    public String getNameVehicle() {
        return nameVehicle;
    }

    public void setNameVehicle(String nameVehicle) {
        this.nameVehicle = nameVehicle;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public LocalDate getAssicuration() {
        return assicuration;
    }

    public void setAssicuration(LocalDate assicuration) {
        this.assicuration = assicuration;
    }

    public String getStatusExpirated() {
        return statusExpirated;
    }

    public void setStatusExpirated(String statusExpirated) {
        this.statusExpirated = statusExpirated;
    }

    public LogisticClient getLogisticCompany() {
        return logisticCompany;
    }

    public void setLogisticCompany(LogisticClient logisticCompany) {
        this.logisticCompany = logisticCompany;
    }

    public int getSubscriptionAssociated() {
        return subscriptionAssociated;
    }

    public void setSubscriptionAssociated(int subscriptionAssociated) {
        this.subscriptionAssociated = subscriptionAssociated;
    }
}
