<%-- 
    Document   : jugadores
    Created on : Mar 19, 2017, 9:04:15 AM
    Author     : bernardo
--%>

<%@page import="java.util.Base64"%>
<%@page import="com.sv.udb.controlador.EquiposCtrl"%>
<%@page import="com.sv.udb.modelo.Equipos"%>
<%@page import="com.sv.udb.modelo.Equipos"%>
<%@page import="com.sv.udb.controlador.JugadoresCtrl"%>
<%@page import="com.sv.udb.modelo.Jugadores"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel='stylesheet' href='webjars/Materialize/0.96.0/dist/css/materialize.min.css'>
        <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <script type="text/javascript" src="webjars/jquery/2.1.1/jquery.min.js"></script>
        <script type="text/javascript" src="webjars/Materialize/0.96.0/dist/js/materialize.min.js"></script>
        <title>JSP Page</title>
    </head>
    <body>
        <ul>
            <li><a href='index.jsp'>Equipos</a></li>
            <li><a href='jugadores.jsp'>Jugadores</a></li>
            <li><a href='partidos.jsp'>Partidos</a></li>
        </ul>
        <div class="row container z-depth-5">
            <h1 class="col s12 m6 offset-m3">${mensAler}</h1>
            <form method="POST" action="JugadoresServ" name="Demo" class="col s12 m6 offset-m3" enctype="multipart/form-data">
                <label>código: &ensp;</label><input readonly type="text" name="codi" id="codi" value="${codi}"><br>
                <select id="codiEqui" name="codiEqui" required> 
                    <option value="" disabled selected>Seleccione un equipo</option>
                    <%
                        int indi = 0;
                        for(Equipos temp : new EquiposCtrl().consTodo())
                        {
                            
                    %> 
                    <option value="<%=temp.getCodiEqui()%>"><%=temp.getNombEqui()%></option>
                    <%
                        indi++;
                        }
                    %>
                </select>
                <label>Equipo:&ensp; </label>
                <br>
                <label>Nombre:  </label><input type="text" name="nomb" id="nomb" value="${nomb}"><br>
                <label>Edad: &ensp;&ensp; </label><input type="text" name="edad" id="edad" value="${edad}"><br>
                <label>Altura:&ensp;&ensp;</label><input type="text" name="altu" id="altu" value="${altu}"><br>
                <label>Peso: &ensp;&ensp;&ensp;</label><input type="text" name="peso" id="peso" value="${peso}"><br>
                <div class="file-field input-field">
                    <div class="btn">
                      <span><i class="material-icons">image</i></span>
                      <input type="file" id="imag" name="imag" value="${imag}">
                    </div>
                    <div class="file-path-wrapper">
                      <input class="file-path validate" type="text">
                    </div>
                </div>
                <input ${estGuar} type="submit" name="btonJuga" value="Guardar">
                <input ${estModi} type="submit" name="btonJuga" value="Modificar">
            </form>
            <h1 class="col s12 m6 offset-m3">La Tabla</h1>
            `<form method="POST" action="JugadoresServ" name="Tabl" class="col s12 m6 offset-m3">
                <table border="1" width="500">
                <tr>
                    <th>Cons</th>
                    <th>Equipo</th>
                    <th>Nombre</th>
                    <th>Edad</th>
                    <th>Altura</th>
                    <th>Peso</th>
                    <th>Foto</th>
                </tr>
                <%
                    for(Jugadores temp : new JugadoresCtrl().consTodo())
                    { 
                        byte[] photo = temp.getFotoJuga();
                        String bphoto = Base64.getEncoder().encodeToString(photo);
                %>
                <tr>
                    <td><p><input type="radio" name="codiJugaRadi" class='with-gap' id="<%=temp.getCodiEqui()%>" value="<%=temp.getCodiJuga()%>"><label for="<%=temp.getCodiEqui()%>"></label></p></td>
                    <td><%=temp.getNombEqui()%></td>
                    <td><%=temp.getNombJuga()%></td>
                    <td><%=temp.getEdadJuga()%></td>
                    <td><%=temp.getAltuJuga()%></td>
                    <td><%=temp.getPesoJuga()%></td>
                    <td><img src="data:image/*;base64,<%=bphoto%>" class="materialboxed" width="100" height="100"></td>
                </tr>
                <%
                    }
                %>
            </table>
        <input type="submit" name="btonJuga" value="Consultar">
        <input ${estElim} type="submit" onclick="return confirm('¿Desea proceder con la actualización del registro?');" name="btonJuga" value="Eliminar">
        <input type="submit" name="btonJuga" value="Nuevo">
        </form>
        </div>
        <script>
        $(document).ready(function() {
            $('select').material_select();
          });
    </script>
    </body>
</html>
