package com.esipovich.coffeeshop.model;

import com.esipovich.coffeeshop.util.DateTimeUtil;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "coffeeorder")
public class CoffeeOrder {

    @Id
    //стратегия AUTO вызывает exception, если id - auto-increment
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String coffeeKind;

    private double quantity;

    private double cost;

    private Date deliveryTimeFrom;

    private Date deliveryTimeTo;

    public CoffeeOrder() {
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

    public Date getDeliveryTimeFrom() {
        return deliveryTimeFrom;
    }

    public void setDeliveryTimeFrom(Date deliveryTimeFrom) {
        this.deliveryTimeFrom = deliveryTimeFrom;
    }

    public Date getDeliveryTimeTo() {
        return deliveryTimeTo;
    }

    public void setDeliveryTimeTo(Date deliveryTimeTo) {
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
    //не читаемо!
    @Override
    public String toString() {
        return "KIND: " + coffeeKind + ", QUANTITY: " + quantity +
               " g., DELIVERY: " + (deliveryTimeFrom == null ? "pickup" : "from " +
                DateTimeUtil.convertDateToString(deliveryTimeFrom) + " to " +
                DateTimeUtil.convertDateToString(deliveryTimeTo)) +
                ",  TOTAL COST: " + cost + "$";
    }

}
