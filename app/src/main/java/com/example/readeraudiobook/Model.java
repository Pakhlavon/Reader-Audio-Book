package com.example.readeraudiobook;

public class Model {
    int id;
    String name, descr_uz;

    public Model() {
    }

    public Model(int id, String name, String descr_uz) {
        this.id = id;
        this.name = name;
        this.descr_uz = descr_uz;
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

    public String getDescr_uz() {
        return descr_uz;
    }

    public void setDescr_uz(String descr_uz) {
        this.descr_uz = descr_uz;
    }
}
