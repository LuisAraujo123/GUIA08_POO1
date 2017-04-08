/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.modelo;


/**
 *
 * @author bernardo
 */
public class Jugadores {
    private int codiJuga;
    private int codiEqui;
    private String nombJuga;
    private String nombEqui;
    private String edadJuga;
    private int altuJuga;
    private String pesoJuga;
    private byte[] fotoJuga;

    public Jugadores() {
        
    }

    public Jugadores(int codiJuga, int codiEqui, String nombJuga, String nombEqui, String edadJuga, int altuJuga, String pesoJuga, byte[] fotoJuga) {
        this.codiJuga = codiJuga;
        this.codiEqui = codiEqui;
        this.nombJuga = nombJuga;
        this.nombEqui = nombEqui;
        this.edadJuga = edadJuga;
        this.altuJuga = altuJuga;
        this.pesoJuga = pesoJuga;
        this.fotoJuga = fotoJuga;
    }

    
    public byte[] getFotoJuga() {
        return fotoJuga;
    }

    public void setFotoJuga(byte[] fotoJuga) {
        this.fotoJuga = fotoJuga;
    }
       
    public int getCodiJuga() {
        return codiJuga;
    }

    public void setCodiJuga(int codiJuga) {
        this.codiJuga = codiJuga;
    }

    public int getCodiEqui() {
        return codiEqui;
    }

    public void setCodiEqui(int codiEqui) {
        this.codiEqui = codiEqui;
    }

    public String getNombJuga() {
        return nombJuga;
    }

    public void setNombJuga(String nombJuga) {
        this.nombJuga = nombJuga;
    }

    public String getNombEqui() {
        return nombEqui;
    }

    public void setNombEqui(String nombEqui) {
        this.nombEqui = nombEqui;
    }

    public String getEdadJuga() {
        return edadJuga;
    }

    public void setEdadJuga(String edadJuga) {
        this.edadJuga = edadJuga;
    }

    public int getAltuJuga() {
        return altuJuga;
    }

    public void setAltuJuga(int altuJuga) {
        this.altuJuga = altuJuga;
    }

    public String getPesoJuga() {
        return pesoJuga;
    }

    public void setPesoJuga(String pesoJuga) {
        this.pesoJuga = pesoJuga;
    }

    @Override
    public String toString() {
        return this.nombJuga;
    }
}
