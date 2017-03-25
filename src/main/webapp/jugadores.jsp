<%-- 
    Document   : jugadores
    Created on : Mar 19, 2017, 9:04:15 AM
    Author     : bernardo
--%>

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
        <title>JSP Page</title>
    </head>
    <body>
        <ul>
            <li><a href='index.jsp'>Equipos</a></li>
            <li><a href='jugadores.jsp'>Jugadores</a></li>
            <li><a href='partidos.jsp'>Partidos</a></li>
        </ul>
        <h1>${mensAler}</h1>
        <form method="POST" action="JugadoresServ" name="Demo">
            <label>código: &ensp;</label><input readonly type="text" name="codi" id="codi" value="${codi}"><br>
            <label>Equipo:&ensp; </label><select id="codiEqui" name="codiEqui">
                <option value="" disabled>Seleccione un equipo</option>
                <%
                    int indi = 0;
                    for(Equipos temp : new EquiposCtrl().consTodo())
                    {
                %> 
                <option value="<%=temp.getCodiEqui()%>" ${estCmbEqui}><%=temp.getNombEqui()%></option>
                <%
                    indi++;
                    }
                %>
            </select><br>
            <label>Nombre:  </label><input type="text" name="nomb" id="nomb" value="${nomb}"><br>
            <label>Edad: &ensp;&ensp; </label><input type="text" name="edad" id="edad" value="${edad}"><br>
            <label>Altura:&ensp;&ensp;</label><input type="text" name="altu" id="altu" value="${altu}"><br>
            <label>Peso: &ensp;&ensp;&ensp;</label><input type="text" name="peso" id="peso" value="${peso}"><br>
            <input ${estGuar} type="submit" name="btonJuga" value="Guardar">
            <input ${estModi} type="submit" name="btonJuga" value="Modificar">
        </form>
        <h1>La Tabla</h1>
        `<form method="POST" action="JugadoresServ" name="Tabl">
            <table border="1" width="500">
            <tr>
                <th>Cons</th>
                <th>Equipo</th>
                <th>Nombre</th>
                <th>Edad</th>
                <th>Altura</th>
                <th>Peso</th>
            </tr>
            <%
                for(Jugadores temp : new JugadoresCtrl().consTodo())
                { 
            %>
            <tr>
                <td><input type="radio" name="codiJugaRadi" value="<%=temp.getCodiJuga()%>"></td>
                <td><%=temp.getNombEqui()%></td>
                <td><%=temp.getNombJuga()%></td>
                <td><%=temp.getEdadJuga()%></td>
                <td><%=temp.getAltuJuga()%></td>
                <td><%=temp.getPesoJuga()%></td>
            </tr>
            <%
                }
            %>
        </table>
        <input type="submit" name="btonJuga" value="Consultar">
        <input ${estElim} type="submit" onclick="return confirm('¿Desea proceder con la actualización del registro?');" name="btonJuga" value="Eliminar">
        <input type="submit" name="btonJuga" value="Nuevo">
        </form>
    </body>
</html>
