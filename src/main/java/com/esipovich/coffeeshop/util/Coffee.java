package com.esipovich.coffeeshop.util;

import java.util.*;

public class Coffee {
    private static Map<String, Double> coffeePrices = new HashMap<>();

    static {
        coffeePrices.put("americano", 15.00);
        coffeePrices.put("espresso", 10.00);
        coffeePrices.put("latte", 11.00);
        coffeePrices.put("macchiato", 12.50);
        coffeePrices.put("mocha", 8.00);
        coffeePrices.put("lungo", 10.20);
        coffeePrices.put("3 in 1", 5.50);
        coffeePrices.put("capuchino", 6.50);
        coffeePrices.put("melicano", 7.80);
    }

    public static List getCoffeeKinds(){
        return new ArrayList<>(coffeePrices.keySet());
    }

    public static double getCoffeePrice(String coffeeKind){
        return coffeeKind == null ? 0 : coffeePrices.get(coffeeKind);
    }
}
