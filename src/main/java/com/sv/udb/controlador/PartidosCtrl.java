/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import com.sv.udb.modelo.Partidos;
import com.sv.udb.recursos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bernardo
 */
public class PartidosCtrl {
    public boolean guar(Partidos obje){
        boolean resp = false;
        Connection cn = new Conexion().getConn();
        try {
            PreparedStatement cmd = cn.prepareStatement("INSERT INTO Partidos VALUES(NULL, ?, ?, ?, ?, ?, ?, ?)");
            cmd.setInt(1, obje.getCodiEqui1());
            cmd.setInt(2, obje.getCodiEqui2());
            cmd.setInt(3, obje.getMarc1());
            cmd.setInt(4, obje.getMarc2());
            cmd.setDate(5, obje.getFech());
            cmd.setTime(6, obje.getHora());
            cmd.setString(7, obje.getLuga());
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
    
    public boolean upda(Partidos obje){
        boolean resp = false;
        Connection cn = new Conexion().getConn();
        try {
            PreparedStatement cmd = cn.prepareStatement("update Partidos set codi_equi1 = ?, codi_equi2 = ?, Marcador1 = ?, Marcador2 = ?, Fecha = ?, Hora = ?, Lugar = ? where codi_part = ?;");
            cmd.setInt(1, obje.getCodiEqui1());
            cmd.setInt(2, obje.getCodiEqui2());
            cmd.setInt(3, obje.getMarc1());
            cmd.setInt(4, obje.getMarc2());
            cmd.setDate(5, obje.getFech());
            cmd.setTime(6, obje.getHora());
            cmd.setString(7, obje.getLuga());
            cmd.setInt(8, obje.getCodiPart());
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
    
    public boolean dele(Partidos obje){
        boolean resp = false;
        Connection cn = new Conexion().getConn();
        try {
            PreparedStatement cmd = cn.prepareStatement("delete from Partidos where codi_part = ?");
            cmd.setInt(1, obje.getCodiPart());
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
    
    public List<Partidos> consTodo(){
        List<Partidos> resp = new ArrayList<>();
        Connection cn = new Conexion().getConn();
        try {
            PreparedStatement cmd = cn.prepareStatement("select codi_part, Partidos.codi_equi1, Partidos.codi_equi2, Equipos.nomb_equi, Marcador1, Marcador2, Fecha, Hora, Lugar from Partidos, Equipos where Partidos.codi_equi2 = Equipos.codi_equi;");
            ResultSet rs = cmd.executeQuery();
            PreparedStatement cmd2 = cn.prepareStatement("select nomb_equi from Partidos, Equipos where Equipos.codi_equi = Partidos.codi_equi1;");
            ResultSet rs2 = cmd2.executeQuery();
            while (rs.next() && rs2.next())
            {
                System.out.println(rs.getString(4));
                resp.add(new Partidos(rs.getInt(1), rs.getInt(2), rs2.getString(1), rs.getInt(3), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getDate(7), rs.getTime(8), rs.getString(9)));
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
