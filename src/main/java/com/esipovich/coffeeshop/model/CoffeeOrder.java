package com.esipovich.coffeeshop.model;

import com.esipovich.coffeeshop.util.DateTimeUtil;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "coffeeorder")
public class CoffeeOrder {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "coffeeKind")
    private String coffeeKind;

    @Column(name = "quantity")
    private double quantity;

    @Column(name = "cost")
    private double cost;

    @Column(name = "deliveryTimeFrom")
    @Convert(converter = DateTimeUtil.class)
    private LocalDateTime deliveryTimeFrom;

    @Column(name = "deliveryTimeTo")
    @Convert(converter = DateTimeUtil.class)
    private LocalDateTime deliveryTimeTo;

    public CoffeeOrder() {
    }

    public CoffeeOrder(String coffeeKind, double quantity, double cost) {
        this.coffeeKind = coffeeKind;
        this.quantity = quantity;
        this.cost = cost;
    }

    public CoffeeOrder(String coffeeKind, double quantity, double cost, LocalDateTime deliveryTimeFrom, LocalDateTime deliveryTimeTo) {
        this.coffeeKind = coffeeKind;
        this.quantity = quantity;
        this.cost = cost;
        this.deliveryTimeFrom = deliveryTimeFrom;
        this.deliveryTimeTo = deliveryTimeTo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCoffeeKind() {
        return coffeeKind;
    }

    public void setCoffeeKind(String coffeeKind) {
        this.coffeeKind = coffeeKind;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public LocalDateTime getDeliveryTimeFrom() {
        return deliveryTimeFrom;
    }

    public void setDeliveryTimeFrom(LocalDateTime deliveryTimeFrom) {
        this.deliveryTimeFrom = deliveryTimeFrom;
    }

    public LocalDateTime getDeliveryTimeTo() {
        return deliveryTimeTo;
    }

    public void setDeliveryTimeTo(LocalDateTime deliveryTimeTo) {
        this.deliveryTimeTo = deliveryTimeTo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CoffeeOrder that = (CoffeeOrder) o;

        if (id != that.id) return false;
        if (Double.compare(that.quantity, quantity) != 0) return false;
        if (Double.compare(that.cost, cost) != 0) return false;
        if (!coffeeKind.equals(that.coffeeKind)) return false;
        if (!deliveryTimeFrom.equals(that.deliveryTimeFrom)) return false;
        return deliveryTimeTo.equals(that.deliveryTimeTo);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + coffeeKind.hashCode();
        temp = Double.doubleToLongBits(quantity);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(cost);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + deliveryTimeFrom.hashCode();
        result = 31 * result + deliveryTimeTo.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Kind: " + coffeeKind + ", quantity: "
                + quantity + "g., total cost: + " + cost + "$. Delivery: " +
                (deliveryTimeFrom == null ? "pickup" : "from " + deliveryTimeFrom +
                        " to " + deliveryTimeTo);
    }
}
