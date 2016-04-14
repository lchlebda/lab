package com.getbase.restaurant;

public class Meal {

    private final String name;
    private final long id;

    public Meal(String name, long id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }
}
