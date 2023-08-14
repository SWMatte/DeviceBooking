package Device.deviceProject.DTO;



import java.time.LocalDate;

public class VehicleDTO {
    private int idVehicle;
    private LocalDate assicuration;
    private String nameVehicle;
    private String plate;
    private String statusExpirated;
    private int idLogistic;
    private String cfLogistic;
    private String nameLogistic;
    private int idSubscription;
    private boolean available;
    private LocalDate dateActivation;
    private LocalDate dateFinish;
    private String duration;
    private double price;


    public VehicleDTO(int idVehicle, LocalDate assicuration, String nameVehicle, String plate, String statusExpirated,
                      int idLogistic, String cfLogistic, String nameLogistic, int idSubscription, boolean available,
                      LocalDate dateActivation, LocalDate dateFinish, String duration, double price
                     ) {
        this.idVehicle = idVehicle;
        this.assicuration = assicuration;
        this.nameVehicle = nameVehicle;
        this.plate = plate;
        this.statusExpirated = statusExpirated;
        this.idLogistic = idLogistic;
        this.cfLogistic = cfLogistic;
        this.nameLogistic = nameLogistic;
        this.idSubscription = idSubscription;
        this.available = available;
        this.dateActivation = dateActivation;
        this.dateFinish = dateFinish;
        this.duration = duration;
        this.price = price;

    }

    public VehicleDTO() {
    }


    public int getIdVehicle() {
        return idVehicle;
    }

    public void setIdVehicle(int idVehicle) {
        this.idVehicle = idVehicle;
    }

    public LocalDate getAssicuration() {
        return assicuration;
    }

    public void setAssicuration(LocalDate assicuration) {
        this.assicuration = assicuration;
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

    public String getStatusExpirated() {
        return statusExpirated;
    }

    public void setStatusExpirated(String statusExpirated) {
        this.statusExpirated = statusExpirated;
    }

    public int getIdLogistic() {
        return idLogistic;
    }

    public void setIdLogistic(int idLogistic) {
        this.idLogistic = idLogistic;
    }

    public String getCfLogistic() {
        return cfLogistic;
    }

    public void setCfLogistic(String cfLogistic) {
        this.cfLogistic = cfLogistic;
    }

    public String getNameLogistic() {
        return nameLogistic;
    }

    public void setNameLogistic(String nameLogistic) {
        this.nameLogistic = nameLogistic;
    }

    public int getIdSubscription() {
        return idSubscription;
    }

    public void setIdSubscription(int idSubscription) {
        this.idSubscription = idSubscription;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
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

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
