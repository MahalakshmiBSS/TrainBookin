package com.example.train.DTO;

public class UserSeatDTO {
    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String seat;

    public UserSeatDTO(String firstName, String lastName, String email, String seat) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.seat = seat;
    }

    public UserSeatDTO() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }
}
