package com.epam.reshetnev.spring.core.domain.form;

public class BookForm {

    private String name;

    private Integer seat;

    public BookForm() {
    }

    public BookForm(String name, Integer seat) {
        this.name = name;
        this.seat = seat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSeat() {
        return seat;
    }

    public void setSeat(Integer seat) {
        this.seat = seat;
    }

}
