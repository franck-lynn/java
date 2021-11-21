package base.general.rmi.rmi03.common;

import java.io.Serializable;

public class Product implements Serializable{
    private String description;
    private double price;
    private Warehouse location;
    
    public Product(String description, double price){
        this.description = description;
        this.price = price;
    }
    public String getDescription() {
        return description;
    }
    public double getPrice() {
        return price;
    }
    public Warehouse getLocation() {
        return location;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setLocation(Warehouse location) {
        this.location = location;
    }
}
