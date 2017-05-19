package com.esipovich.coffeeshop.util;

import java.util.*;

public class Coffee {
    private static Map<String, Double> coffeePrices = new HashMap<>();

    static {
        coffeePrices.put("Americano", 15.00);
        coffeePrices.put("Espresso", 10.00);
        coffeePrices.put("Latte", 11.00);
        coffeePrices.put("Macchiato", 12.50);
        coffeePrices.put("Mocha", 8.00);
        coffeePrices.put("Lungo", 10.20);
        coffeePrices.put("3 in 1", 5.50);
    }

    public static Map<String, Double> getCoffeePrices() {
        return coffeePrices;
    }

    public static List getCoffeeKinds(){
        return new ArrayList<>(coffeePrices.keySet());
    }

    public static double getCoffeePrice(String coffeeKind){
        return coffeePrices.get(coffeeKind);
    }
}
