package com.example.train.DTO;

public class TicketReceiptDTO {

    private int id;  // ticket_id
    private String departurePlace;
    private String destination;
    private String firstName;  // user.firstName
    private String lastName;   // user.lastName
    private String email;      // user.email
    private double pricePaid;
    private String section;
    private String seat;

    public TicketReceiptDTO() {
    }

    public TicketReceiptDTO(String departurePlace, String destination, String firstName, String lastName, String email, double pricePaid, String section, String seat) {
        this.departurePlace = departurePlace;
        this.destination = destination;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.pricePaid = pricePaid;
        this.section = section;
        this.seat = seat;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
