package com.example.BusReservation.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
public class Passenger {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int passengerId;
    private String passengerName;
    private String passengerAddress;
    private String passengerEmail;
    private String PassengerPhone;

    public void setId(int id) {
    }

    public int getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getPassengerAddress() {
        return passengerAddress;
    }

    public void setPassengerAddress(String passengerAddress) {
        this.passengerAddress = passengerAddress;
    }

    public String getPassengerEmail() {
        return passengerEmail;
    }

    public void setPassengerEmail(String passengerEmail) {
        this.passengerEmail = passengerEmail;
    }

    public String getPassengerPhone() {
        return PassengerPhone;
    }

    public void setPassengerPhone(String passengerPhone) {
        PassengerPhone = passengerPhone;
    }

}
