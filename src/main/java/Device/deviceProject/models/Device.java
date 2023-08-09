package Device.deviceProject.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Device {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int idDevice;

private String nameDevice;
private String codeDevice;

private boolean licence;

@JsonIgnore
@OneToOne(mappedBy = "device", cascade = CascadeType.ALL)
private Subscription subscription;


    public int getIdDevice() {
        return idDevice;
    }

    public void setIdDevice(int idDevice) {
        this.idDevice = idDevice;
    }

    public String getNameDevice() {
        return nameDevice;
    }

    public void setNameDevice(String nameDevice) {
        this.nameDevice = nameDevice;
    }

    public String getCodeDevice() {
        return codeDevice;
    }

    public void setCodeDevice(String codeDevice) {
        this.codeDevice = codeDevice;
    }

    public boolean isLicence() {
        return licence;
    }

    public void setLicence(boolean licence) {
        this.licence = licence;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }
}
