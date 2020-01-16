package com.company.students.model;

public class Country {
    private int id;
    private String name;
    private int peopleId;

    public Country() {
    }

    public Country(int id, String name, int peopleId) {
        this.id = id;
        this.name = name;
        this.peopleId = peopleId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPeopleId() {
        return peopleId;
    }

    public void setPeopleId(int peopleId) {
        this.peopleId = peopleId;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", peopleId=" + peopleId +
                '}';
    }
}
