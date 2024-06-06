package com.example.ridergame.model;

public class UserScore {
    private String id;
    private String email;
    private String firstName;
    private String lastName;
    private long score;

    public UserScore(String id, String email, String firstName, String lastName, int score) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public UserScore() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public long getScore() {
        return score;
    }

/*    public void setScore(String score) {
        this.score = Integer.parseInt(score);
    }*/

    public void setScore(long score) {
        this.score = score;
    }
}
