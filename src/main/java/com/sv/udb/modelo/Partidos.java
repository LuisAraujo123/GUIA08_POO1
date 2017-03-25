/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.modelo;

import java.sql.Time;
import java.sql.Date;

/**
 *
 * @author bernardo
 */
public class Partidos {
    private int codiPart;
    private int codiEqui1;
    private String nombEqui1;
    private int codiEqui2;
    private String nombEqui2;
    private int marc1;
    private int marc2;
    private Date fech;
    private Time Hora;
    private String luga;

    public Partidos() {
    }

    public Partidos(int codiPart, int codiEqui1, String nombEqui1, int codiEqui2, String nombEqui2, int marc1, int marc2, Date fech, Time Hora, String luga) {
        this.codiPart = codiPart;
        this.codiEqui1 = codiEqui1;
        this.nombEqui1 = nombEqui1;
        this.codiEqui2 = codiEqui2;
        this.nombEqui2 = nombEqui2;
        this.marc1 = marc1;
        this.marc2 = marc2;
        this.fech = fech;
        this.Hora = Hora;
        this.luga = luga;
    } 

    public int getCodiPart() {
        return codiPart;
    }

    public void setCodiPart(int codiPart) {
        this.codiPart = codiPart;
    }

    public int getCodiEqui1() {
        return codiEqui1;
    }

    public void setCodiEqui1(int codiEqui1) {
        this.codiEqui1 = codiEqui1;
    }

    public int getCodiEqui2() {
        return codiEqui2;
    }

    public void setCodiEqui2(int codiEqui2) {
        this.codiEqui2 = codiEqui2;
    }

    public int getMarc1() {
        return marc1;
    }

    public void setMarc1(int marc1) {
        this.marc1 = marc1;
    }

    public int getMarc2() {
        return marc2;
    }

    public void setMarc2(int marc2) {
        this.marc2 = marc2;
    }

    public Date getFech() {
        return fech;
    }

    public void setFech(Date fech) {
        this.fech = fech;
    }

    public Time getHora() {
        return Hora;
    }

    public void setHora(Time Hora) {
        this.Hora = Hora;
    }

    public String getLuga() {
        return luga;
    }

    public void setLuga(String luga) {
        this.luga = luga;
    }

    public String getNombEqui1() {
        return nombEqui1;
    }

    public void setNombEqui1(String nombEqui1) {
        this.nombEqui1 = nombEqui1;
    }

    public String getNombEqui2() {
        return nombEqui2;
    }

    public void setNombEqui2(String nombEqui2) {
        this.nombEqui2 = nombEqui2;
    }

    @Override
    public String toString() {
        return this.nombEqui1;
    }
    
    
    
    
}
