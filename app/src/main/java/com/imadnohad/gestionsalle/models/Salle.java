package com.imadnohad.gestionsalle.models;

import java.io.Serializable;

public class Salle implements Serializable {
    private int id;
    private String code;
    private String libele;
    public static int Auto_Id = 0;

    public Salle(String code, String libele) {
        this.id = ++Auto_Id;
        this.code = code;
        this.libele = libele;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibele() {
        return libele;
    }

    public void setLibele(String libele) {
        this.libele = libele;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Salle{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", libele='" + libele + '\'' +
                '}';
    }
}
