package com.imadnohad.gestionsalle;

import android.app.Application;

import com.imadnohad.gestionsalle.models.Salle;

import java.util.ArrayList;
import java.util.List;

public class SalleApplication extends Application {
    private static List<Salle> salleList = new ArrayList<Salle>();

    public SalleApplication() {
    }

    public static List<Salle> getSalleList() {
        return salleList;
    }

    public static void setSalleList(List<Salle> salleList) {
        SalleApplication.salleList = salleList;
    }
}
