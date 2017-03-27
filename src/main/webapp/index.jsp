<%@page import="java.util.Base64"%>
<%@page import="com.sv.udb.controlador.EquiposCtrl"%>
<%@page import="com.sv.udb.modelo.Equipos"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

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
        <div class="row container ">
            <h1 class="col s12 m6 offset-m3">${mensAler}</h1>
            <form method="POST" action="EquiposServ" name="Demo" class="col s12 m6 offset-m3" enctype="multipart/form-data">
            <label>Código:&ensp;&ensp;&ensp;&ensp; </label><input readonly type="text" name="codi" id="codi" value="${codi}"><br>
            <label>Nombre:&ensp;&ensp;&ensp; </label><input type="text" name="nomb" id="nomb" value="${nomb}"><br>
            <label>Descripción: </label><input type="text" name="desc" id="desc" value="${desc}"><br>
            <div class="file-field input-field">
                <div class="btn">
                  <span><i class="material-icons">image</i></span>
                  <input type="file" id="imag" name="imag" value="${imag}">
                </div>
                <div class="file-path-wrapper">
                  <input class="file-path validate" type="text">
                </div>
            </div>
               
            <input ${estGuar} type="submit" name="btonEqui" value="Guardar">
            <input ${estModi} type="submit" name="btonEqui" value="Modificar">
        </form>
        <h1 class="col s12 m6 offset-m3">La Tabla</h1>
        `<form method="POST" action="EquiposServ" name="Tabl" class="col s12 m6 offset-m3">
            <table class="highlight" border="1" width="500">
            <tr>
                <th>Cons</th>
                <th>Nombre</th>
                <th>Descripción</th>
            </tr>
            <%
                for(Equipos temp : new EquiposCtrl().consTodo())
                { 
                    byte[] photo = temp.getLogoEqui();
                    String bphoto = Base64.getEncoder().encodeToString(photo);
            %>
            <tr>
                <td><p><input type="radio" name="codiEquiRadi" class='with-gap' id="<%=temp.getCodiEqui()%>" value="<%=temp.getCodiEqui()%>"><label for="<%=temp.getCodiEqui()%>"></label></p></td>
                <td><%=temp.getNombEqui()%></td>
                <td><%=temp.getDescEqui()%></td>
                <td><img src="data:image/*;base64,<%=bphoto%>" class="materialboxed" width="100" height="100"></td>
            </tr>
            <%
                }
            %>
        </table>
        <input type="submit" name="btonEqui" value="Consultar">
        <input ${estElim} type="submit" onclick="return confirm('¿Desea proceder con la actualización del registro?');" name="btonEqui" value="Eliminar">
        <input type="submit" name="btonEqui" value="Nuevo">
        </form>
        </div>
    </body>
</html>
