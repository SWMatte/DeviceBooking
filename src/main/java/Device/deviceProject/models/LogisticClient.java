package Device.deviceProject.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;


import java.util.List;

@Entity
public class LogisticClient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idLogistic;
    private String name;
    private String cfLogistic;

    private boolean enableEmail;
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinTable(name = "clientLogistic_subscription",
            joinColumns = @JoinColumn(name = "idLogistic", referencedColumnName = "idLogistic"),
            inverseJoinColumns = @JoinColumn(name = "idSubscription", referencedColumnName = "idSubscription"))
    private List<Subscription> listSubscription;


    @JsonIgnore
    @OneToMany(mappedBy = "logisticCompany")
    private List<Vehicle> vehicles;


    @JsonIgnore
    @OneToMany(mappedBy = "idCompany")
    private List<ClientSub> subscriptionAssigned;


    public int getIdLogistic() {
        return idLogistic;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCfLogistic() {
        return cfLogistic;
    }

    public void setIdLogistic(int idLogistic) {
        this.idLogistic = idLogistic;
    }

    public void setCfLogistic(String cfLogistic) {
        this.cfLogistic = cfLogistic;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public List<ClientSub> getSubscriptionAssigned() {
        return subscriptionAssigned;
    }

    public void setSubscriptionAssigned(List<ClientSub> subscriptionAssigned) {
        this.subscriptionAssigned = subscriptionAssigned;
    }

    public boolean isEnableEmail() {
        return enableEmail;
    }

    public void setEnableEmail(boolean enableEmail) {
        this.enableEmail = enableEmail;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Subscription> getListSubscription() {
        return listSubscription;
    }

    public void setListSubscription(List<Subscription> listSubscription) {
        this.listSubscription = listSubscription;
    }






}
