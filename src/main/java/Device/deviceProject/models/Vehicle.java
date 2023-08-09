package Device.deviceProject.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Vehicle {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idVehicle;

 private String nameVehicle;
 private String plate;

 private LocalDate assicuration; // sara' poi un entita' ? per gestire le revisioni

 @ManyToOne
 @JoinColumn(name = "idLogistic")
 private LogisticClient logisticCompany;

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



}