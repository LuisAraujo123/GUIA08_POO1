/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.vista;

import com.sv.udb.controlador.JugadoresCtrl;
import com.sv.udb.modelo.Jugadores;
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
@WebServlet(name = "JugadoresServ", urlPatterns = {"/JugadoresServ"})
public class JugadoresServ extends HttpServlet {

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
            response.sendRedirect(request.getContextPath() + "/jugadores.jsp");
        }
        else {
            try{
                String CRUD = request.getParameter("btonJuga");
                if (CRUD.equals("Guardar"))
                {
                    Jugadores obje = new Jugadores();
                    obje.setNombJuga(request.getParameter("nomb"));
                    obje.setCodiEqui(Integer.parseInt(request.getParameter("codiEqui")));
                    obje.setEdadJuga(request.getParameter("edad"));
                    obje.setAltuJuga(Integer.parseInt(request.getParameter("altu")));
                    obje.setPesoJuga(request.getParameter("peso"));
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
                        obje.setFotoJuga(foto);
                    }
                    System.err.println("Extra");
                    if (new JugadoresCtrl().guar(obje))
                    {
                        mens = "Datos guardados";
                        request.setAttribute("estModi", "disabled");
                    }
                    else
                    {
                        mens = "Error al guardar";
                        request.setAttribute("estModi", "disabled");
                    }    
                }
                else if (CRUD.equals("Consultar"))
                {
                    //El operador unario sirve como if para validar que el radio buton este selecionado, si no lo esta devuelve -1
                    int codi = Integer.parseInt(request.getParameter("codiJugaRadi").isEmpty() ? "-1" : request.getParameter("codiJugaRadi"));
                    Jugadores obje = new JugadoresCtrl().consUno(codi);
                    if (obje != null)
                    {
                        request.setAttribute("codi", obje.getCodiJuga());
                        request.setAttribute("nomb", obje.getNombJuga());
                        request.setAttribute("codiEqui", obje.getCodiEqui());
                        request.setAttribute("edad", obje.getEdadJuga());
                        request.setAttribute("altu", obje.getAltuJuga());
                        request.setAttribute("peso", obje.getAltuJuga());
                        request.setAttribute("estModi", "enable");
                        request.setAttribute("estGuar", "disabled");
                    }
                    else
                    {
                        mens = "Error al consultar";
                    }
                }
                else if  (CRUD.equals("Eliminar"))
                {
                    int codi = Integer.parseInt(request.getParameter("codiJugaRadi").isEmpty() ? "-1" : request.getParameter("codiJugaRadi"));
                    if (codi != -1)
                    {
                        Jugadores obje = new Jugadores();
                        obje.setCodiJuga(codi);
                        if (new JugadoresCtrl().dele(obje))
                        {
                            mens = "Datos eliminados";
                            request.setAttribute("estModi", "disabled");
                            request.setAttribute("estGuar", "enable");
                            request.removeAttribute("codi");
                            request.removeAttribute("nomb");
                            request.removeAttribute("altu");
                            request.removeAttribute("codiEqui");
                            request.removeAttribute("peso");
                            request.removeAttribute("edad");
                        }
                        else
                        {
                            request.setAttribute("estModi", "disabled");
                            request.setAttribute("estGuar", "enable");
                            request.removeAttribute("codi");
                            request.removeAttribute("nomb");
                            request.removeAttribute("altu");
                            request.removeAttribute("codiEqui");
                            request.removeAttribute("peso");
                            request.removeAttribute("edad");
                            mens = "Error al eliminar";
                        }
                    }
                }
                else if (CRUD.equals("Modificar"))
                {
                    int codi = Integer.parseInt(request.getParameter("codi").isEmpty() ? "-1" : request.getParameter("codi"));
                    if (codi != -1)
                    {
                        Jugadores obje = new Jugadores();
                        obje.setCodiJuga(codi);
                        obje.setNombJuga(request.getParameter("nomb"));
                        obje.setCodiEqui(Integer.parseInt(request.getParameter("codiEqui")));
                        obje.setEdadJuga(request.getParameter("edad"));
                        obje.setAltuJuga(Integer.parseInt(request.getParameter("altu")));
                        obje.setPesoJuga(request.getParameter("peso"));
                        if (new JugadoresCtrl().upda(obje))
                        {
                            mens = "Datos modificados";
                            request.setAttribute("estModi", "disabled");
                            request.setAttribute("estGuar", "enable");
                            request.removeAttribute("codi");
                            request.removeAttribute("nomb");
                            request.removeAttribute("altu");
                            request.removeAttribute("codiEqui");
                            request.removeAttribute("peso");
                            request.removeAttribute("edad");
                        }
                        else
                        {
                            request.setAttribute("estModi", "disabled");
                            request.setAttribute("estGuar", "enable");
                            request.removeAttribute("codi");
                            request.removeAttribute("nomb");
                            request.removeAttribute("altu");
                            request.removeAttribute("codiEqui");
                            request.removeAttribute("peso");
                            request.removeAttribute("edad");
                            mens = "Error al modificar";
                        }
                    }                   
                }
                else if (CRUD.equals("Nuevo"))
                {
                    request.removeAttribute("codi");
                    request.removeAttribute("nomb");
                    request.removeAttribute("altu");
                    request.removeAttribute("codiEqui");
                    request.removeAttribute("peso");
                    request.removeAttribute("edad");
                    request.setAttribute("estModi", "disabled");
                    request.setAttribute("estGuar", "enable");
                }
                request.setAttribute("mensAler", mens);
                request.getRequestDispatcher("/jugadores.jsp").forward(request, response);
                
            }
            catch (Exception ex){
                System.err.println(ex.getMessage());
                ex.printStackTrace();
                request.setAttribute("estModi", "disabled");
                request.getRequestDispatcher("/jugadores.jsp").forward(request, response);
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
