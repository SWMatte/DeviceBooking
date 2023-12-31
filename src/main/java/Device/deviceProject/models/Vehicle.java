package Device.deviceProject.models;

 import jakarta.persistence.*;
 import javax.validation.constraints.Size;

import java.time.LocalDate;

@Entity
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idVehicle;

    private String nameVehicle;
    @Column(unique = true)
    @Size(min = 2, max = 14)
    private String plate;

    private LocalDate assicuration; // sara' poi un entita' ? per gestire le revisioni

    private String statusExpirated;

    @ManyToOne( )
    @JoinColumn(name = "idLogistic")
     private LogisticClient logisticCompany;

    private int subscriptionAssociated;

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

    public String getStatusExpirated() {
        return statusExpirated;
    }

    public void setStatusExpirated(String statusExpirated) {
        this.statusExpirated = statusExpirated;
    }
}
