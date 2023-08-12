package Device.deviceProject.models;

import jakarta.persistence.*;

@Entity
public class ClientSub {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne( )
    @JoinColumn(name = "idLogistic")
    private LogisticClient idCompany;

    @ManyToOne( )
    @JoinColumn(name = "idSubscription")
    private Subscription idSub;


    private boolean isUsed;

    private String status;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LogisticClient getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(LogisticClient idCompany) {
        this.idCompany = idCompany;
    }

    public Subscription getIdSub() {
        return idSub;
    }

    public void setIdSub(Subscription idSub) {
        this.idSub = idSub;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        status = status;
    }
}
