package com.imadnohad.gestionsalle.service;

import android.app.Application;

import com.imadnohad.gestionsalle.dao.IDao;
import com.imadnohad.gestionsalle.models.Salle;

import java.util.ArrayList;
import java.util.List;

public class SalleService implements IDao<Salle> {

    private List<Salle> salles;

    public SalleService() {
        salles = new ArrayList<Salle>();
    }

    public SalleService(List<Salle> _salles) {
        salles = _salles;
    }

    @Override
    public boolean create(Salle o) {
        return salles.add(o);
    }

    @Override
    public boolean delete(Salle o) {
        return salles.remove(o);
    }

    @Override
    public boolean update(Salle o) {
        int index = salles.indexOf(o);
        if (index > -1){
            salles.set(index, o);
            return true;
        }
        return false;
    }

    @Override
    public Salle findById(int id) {
        for(Salle s : salles)
            if(s.getId() == id)
                return s;
        return null;
    }

    @Override
    public List<Salle> findAll() {
        return salles;
    }
}
