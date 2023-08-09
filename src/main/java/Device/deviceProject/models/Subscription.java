package Device.deviceProject.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSubscription;
    private String duration;
    private LocalDate dateActivation;
    private LocalDate dateFinish;
    private boolean available;
    private double price;

    @JsonIgnore
    @ManyToMany(mappedBy = "listSubscription")
    private List<LogisticClient> listLogisticClient;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idDevice")
    private Device device;

    public int getIdSubscription() {
        return idSubscription;
    }

    public void setIdSubscription(int idSubscription) {
        this.idSubscription = idSubscription;
    }

    public LocalDate getDateActivation() {
        return dateActivation;
    }

    public void setDateActivation(LocalDate dateActivation) {
        this.dateActivation = dateActivation;
    }

    public LocalDate getDateFinish() {
        return dateFinish;
    }

    public void setDateFinish(LocalDate dateFinish) {
        this.dateFinish = dateFinish;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<LogisticClient> getListClient() {
        return listLogisticClient;
    }

    public void setListClient(List<LogisticClient> listLogisticClient) {
        this.listLogisticClient = listLogisticClient;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
