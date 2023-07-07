package com.example.BusReservation.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int BusId;
    private String BusNumber;
    private String BusDriver;

    public int getBusId() {
        return BusId;
    }

    public void setBusId(int busId) {
        BusId = busId;
    }

    public String getBusNumber() {
        return BusNumber;
    }

    public void setBusNumber(String busNumber) {
        BusNumber = busNumber;
    }

    public String getBusDriver() {
        return BusDriver;
    }

    public void setBusDriver(String busDriver) {
        BusDriver = busDriver;
    }

    public String getBusCapacity() {
        return BusCapacity;
    }

    public void setBusCapacity(String busCapacity) {
        BusCapacity = busCapacity;
    }

    private String BusCapacity;

    public void setId(int id) {
    }
}

