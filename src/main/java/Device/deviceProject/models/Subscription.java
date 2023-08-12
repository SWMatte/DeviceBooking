package Device.deviceProject.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    @ManyToMany(mappedBy = "listSubscription",cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private List<LogisticClient> listLogisticClient;

    @OneToOne()
    @JoinColumn(name = "idDevice")
     private Device device;


    @JsonIgnore
    @OneToMany(mappedBy = "idSub",cascade = CascadeType.REMOVE)
     private List<ClientSub> subscriptionAssigned;


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


    public List<ClientSub> getSubscriptionAssigned() {
        return subscriptionAssigned;
    }

    public void setSubscriptionAssigned(List<ClientSub> subscriptionAssigned) {
        this.subscriptionAssigned = subscriptionAssigned;
    }

    public List<LogisticClient> getListLogisticClient() {
        return listLogisticClient;
    }

    public void setListLogisticClient(List<LogisticClient> listLogisticClient) {
        this.listLogisticClient = listLogisticClient;
    }
}
