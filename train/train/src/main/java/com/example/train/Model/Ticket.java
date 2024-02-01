package com.example.train.Model;
// Ticket.java

import jakarta.persistence.*;


@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String departurePlace;
    private String destination;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private double pricePaid;
    private String section;
    private String seat;

    // Constructors
    public Ticket() {
    }

    public Ticket(String departurePlace, String destination, double pricePaid, String seat, String section, User user) {
        this.departurePlace = departurePlace;
        this.destination = destination;
        this.pricePaid = pricePaid;
        this.seat = seat;
        this.section = section;
        this.user = user;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDeparturePlace() {
        return departurePlace;
    }

    public void setDeparturePlace(String departurePlace) {
        this.departurePlace = departurePlace;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getPricePaid() {
        return pricePaid;
    }

    public void setPricePaid(double pricePaid) {
        this.pricePaid = pricePaid;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }
}

