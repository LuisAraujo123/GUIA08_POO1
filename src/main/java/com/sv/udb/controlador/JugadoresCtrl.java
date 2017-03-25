/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import com.sv.udb.modelo.Jugadores;
import com.sv.udb.recursos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author bernardo
 */
public class JugadoresCtrl {
    
    public boolean guar(Jugadores obje){
        boolean resp = false;
        Connection cn = new Conexion().getConn();
        try {
            PreparedStatement cmd = cn.prepareStatement("INSERT INTO Jugadores VALUES(NULL, ?, ?, ?, ?, ?)");
            cmd.setInt(1, obje.getCodiEqui());
            cmd.setString(2, obje.getNombJuga());
            cmd.setString(3, obje.getEdadJuga());
            cmd.setInt(4, obje.getAltuJuga());
            cmd.setString(5, obje.getPesoJuga());
            cmd.executeUpdate();
            resp = true;
            
        } catch (Exception ex) {
            System.err.println("Error: " + ex.getMessage());
        }
        finally 
        {
            try 
            {
                if (cn!=null)
                {
                    if (!cn.isClosed())
                    {
                        cn.close();
                    }
                }
            } 
            catch (Exception e) 
            {
                System.err.println("Error: " + e.getMessage());
            }
        }
        return resp;
    }
    
    public boolean upda(Jugadores obje){
        boolean resp = false;
        Connection cn = new Conexion().getConn();
        try {
            PreparedStatement cmd = cn.prepareStatement("update jugadores set codi_equi = ?, nomb_juga = ?, edad_juga = ?, altu_juga = ?, peso_juga = ? where codi_juga = ?;");
            cmd.setInt(1, obje.getCodiEqui());
            cmd.setString(2, obje.getNombJuga());
            cmd.setString(3, obje.getEdadJuga());
            cmd.setInt(4, obje.getAltuJuga());
            cmd.setString(5, obje.getPesoJuga());
            cmd.setInt(6, obje.getCodiJuga());
            cmd.executeUpdate();
            resp = true;
            
        } catch (Exception ex) {
            System.err.println("Error: " + ex.getMessage());
        }
        finally 
        {
            try 
            {
                if (cn!=null)
                {
                    if (!cn.isClosed())
                    {
                        cn.close();
                    }
                }
            } 
            catch (Exception e) 
            {
                System.err.println("Error: " + e.getMessage());
            }
        }
        return resp;
    }
    
    public boolean dele(Jugadores obje){
        boolean resp = false;
        Connection cn = new Conexion().getConn();
        try {
            PreparedStatement cmd = cn.prepareStatement("delete from jugadores where codi_juga = ?");
            cmd.setInt(1, obje.getCodiJuga());
            cmd.executeUpdate();
            resp = true;
            
        } catch (Exception ex) {
            System.err.println("Error: " + ex.getMessage());
        }
        finally 
        {
            try 
            {
                if (cn!=null)
                {
                    if (!cn.isClosed())
                    {
                        cn.close();
                    }
                }
            } 
            catch (Exception e) 
            {
                System.err.println("Error: " + e.getMessage());
            }
        }
        return resp;
    }
    
    public List<Jugadores> consTodo(){
        List<Jugadores> resp = new ArrayList<>();
        Connection cn = new Conexion().getConn();
        try {
            PreparedStatement cmd = cn.prepareStatement("select codi_juga, jugadores.codi_equi, nomb_juga, nomb_equi, edad_juga, altu_juga, peso_juga from jugadores,equipos where jugadores.codi_equi = equipos.codi_equi;");
            ResultSet rs = cmd.executeQuery();
            while (rs.next())
            {
                resp.add(new Jugadores(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7)));
            }
        } catch (Exception ex) {
            System.err.println("Error: " + ex.getMessage());
            
        }
        finally 
        {
            try 
            {
                if (cn!=null)
                {
                    if (!cn.isClosed())
                    {
                        cn.close();
                    }
                }
            } 
            catch (Exception e) 
            {
                System.err.println("Error: " + e.getMessage());
            }
        }
        return resp;
    }
    
    public Jugadores consUno(int id){
        Jugadores resp = null;
        Connection cn = new Conexion().getConn();
        try {
            PreparedStatement cmd = cn.prepareStatement("select codi_juga, jugadores.codi_equi, nomb_juga, nomb_equi, edad_juga, altu_juga, peso_juga from jugadores,equipos where jugadores.codi_equi = equipos.codi_equi and codi_juga = ?");
            cmd.setInt(1, id);
            ResultSet rs = cmd.executeQuery();
            while (rs.next())
            {
                resp = (new Jugadores(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7)));
            }
        } catch (Exception ex) {
            System.err.println("Error: " + ex.getMessage());
        }
        finally 
        {
            try 
            {
                if (cn!=null)
                {
                    if (!cn.isClosed())
                    {
                        cn.close();
                    }
                }
            } 
            catch (Exception e) 
            {
                System.err.println("Error: " + e.getMessage());
            }
        }
        return resp;
    }
}
