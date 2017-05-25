package com.esipovich.coffeeshop.dao;

import com.esipovich.coffeeshop.model.CoffeeOrder;
import java.util.List;

public interface CoffeeOrderDao {
    void save(CoffeeOrder coffeeOrder);
    void update(CoffeeOrder coffeeOrder);
    void delete(CoffeeOrder coffeeOrder);
    List<CoffeeOrder> getAll();
}
