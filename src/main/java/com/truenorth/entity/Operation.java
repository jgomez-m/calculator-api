package com.truenorth.entity;

import javax.persistence.*;

@Entity
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Type type;

    @Column(nullable = false)
    private double cost;

    public Operation(Long id, Type type, double cost) {
        this.id = id;
        this.type = type;
        this.cost = cost;
    }

    public Operation(Type type, double cost) {
        this.type = type;
        this.cost = cost;
    }

    public Operation() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public enum Type {
        ADDITION,
        SUBTRACTION,
        MULTIPLICATION,
        DIVISION,
        SQUARE_ROOT,
        RANDOM_STRING
    }

}