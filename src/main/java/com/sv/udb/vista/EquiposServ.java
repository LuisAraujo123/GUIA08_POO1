/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.vista;

import com.sv.udb.controlador.EquiposCtrl;
import com.sv.udb.modelo.Equipos;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Base64;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.swing.ImageIcon;

/**
 *
 * @author bernardo
 */

@MultipartConfig
@WebServlet(name = "EquiposServ", urlPatterns = {"/EquiposServ"})
public class EquiposServ extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        boolean esValido = request.getMethod().equals("POST");
        String mens = "";
        System.err.println("Entra al process");
        if(!esValido)
        {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }
        else {
            try{
                String CRUD = request.getParameter("btonEqui");
                if (CRUD.equals("Guardar"))
                {
                    Equipos obje = new Equipos();
                    obje.setNombEqui(request.getParameter("nomb"));
                    obje.setDescEqui(request.getParameter("desc"));
                    System.err.println(request.getPart("imag"));
                    Part filePart = request.getPart("imag");
                    byte[] foto = null;
                    System.err.println(filePart + " esto es");
                    int tamaFoto = (int)filePart.getSize();
                    System.err.println("tomo la imagen");
                    foto = new byte[tamaFoto];
                    try(DataInputStream imagen = new DataInputStream(filePart.getInputStream())) 
                    {
                        imagen.readFully(foto);
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    if (tamaFoto > 0) {
                        obje.setLogoEqui(foto);
                    }
                    System.err.println("Extra");
                    if (new EquiposCtrl().guar(obje))
                    {
                        mens = "Datos guardados";
                        request.setAttribute("estModi", "disabled");
                    }
                    else
                    {
                        mens = "Error al guardar";
                        request.setAttribute("estModi", "disabled");
                    }
                    System.err.println(request.getParameter("imag"));
                    
                    
                    
                }
                else if (CRUD.equals("Consultar"))
                {
                    //El operador unario sirve como if para validar que el radio buton este selecionado, si no lo esta devuelve -1
                    int codi = Integer.parseInt(request.getParameter("codiEquiRadi").isEmpty() ? "-1" : request.getParameter("codiEquiRadi"));
                    Equipos obje = new EquiposCtrl().consUno(codi);
                    if (obje != null)
                    {
                        try {
                            request.setAttribute("codi", obje.getCodiEqui());
                            request.setAttribute("nomb", obje.getNombEqui());
                            request.setAttribute("desc", obje.getDescEqui());
                            System.err.println("bandera de consultar");
                            byte[] photo = obje.getLogoEqui();
                            BufferedImage img = null;
                            img = ImageIO.read(new ByteArrayInputStream(obje.getLogoEqui()));
                            ImageIcon icon = new ImageIcon(img);
                            request.setAttribute("imag", icon);
                            request.setAttribute("estModi", "enable");
                            request.setAttribute("estGuar", "disabled");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        
                    }
                    else
                    {
                        mens = "Error al consultar";
                    }
                }
                else if  (CRUD.equals("Eliminar"))
                {
                    int codi = Integer.parseInt(request.getParameter("codiEquiRadi").isEmpty() ? "-1" : request.getParameter("codiEquiRadi"));
                    if (codi != -1)
                    {
                        Equipos obje = new Equipos();
                        obje.setCodiEqui(codi);
                        if (new EquiposCtrl().dele(obje))
                        {
                            mens = "Datos eliminados";
                            request.setAttribute("estModi", "disabled");
                            request.setAttribute("estGuar", "enable");
                            request.removeAttribute("codi");
                            request.removeAttribute("nomb");
                            request.removeAttribute("desc");
                        }
                        else
                        {
                            request.setAttribute("estModi", "disabled");
                            request.setAttribute("estGuar", "enable");
                            request.removeAttribute("codi");
                            request.removeAttribute("nomb");
                            request.removeAttribute("desc");
                            mens = "Error al eliminar";
                        }
                    }
                }
                else if (CRUD.equals("Modificar"))
                {
                    int codi = Integer.parseInt(request.getParameter("codi").isEmpty() ? "-1" : request.getParameter("codi"));
                    if (codi != -1)
                    {
                        Equipos obje = new Equipos();
                        obje.setCodiEqui(codi);
                        obje.setNombEqui(request.getParameter("nomb"));
                        obje.setDescEqui(request.getParameter("desc"));
                        if (new EquiposCtrl().upda(obje))
                        {
                            mens = "Datos modificados";
                            request.setAttribute("estModi", "disabled");
                            request.setAttribute("estGuar", "enable");
                            request.removeAttribute("codi");
                            request.removeAttribute("nomb");
                            request.removeAttribute("desc");
                        }
                        else
                        {
                            request.setAttribute("estModi", "disabled");
                            request.setAttribute("estGuar", "enable");
                            request.removeAttribute("codi");
                            request.removeAttribute("nomb");
                            request.removeAttribute("desc");
                            mens = "Error al modificar";
                        }
                    }                   
                }
                else if (CRUD.equals("Nuevo"))
                {
                    request.removeAttribute("codi");
                    request.removeAttribute("nomb");
                    request.removeAttribute("desc");
                    request.setAttribute("estModi", "disabled");
                    request.setAttribute("estGuar", "enable");
                }
                request.setAttribute("mensAler", mens);
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                
            }
            catch (Exception ex){
                System.err.println(ex.getMessage());
                ex.printStackTrace();
                request.setAttribute("estModi", "disabled");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
            
        }
        
    }
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
