package com.zwli.builder;

public class Vehicle {
    private final String brand;
    private final double speed;

    private Vehicle(Builder builder) {
        this.brand = builder.brand;
        this.speed = builder.speed;
    }

    public static class Builder implements ObjBuilder<Vehicle> {
        private String brand;
        private double speed;

        public Builder brand(String brand) {
            this.brand = brand;
            return this;
        }

        public Builder speed(double speed) {
            this.speed = speed;
            return this;
        }

        @Override
        public Vehicle build() {
            return new Vehicle(this);
        }

        public String getBrand() {
            return brand;
        }

        public double getSpeed() {
            return speed;
        }
    }

    public String getBrand() {
        return brand;
    }

    public double getSpeed() {
        return speed;
    }

}
