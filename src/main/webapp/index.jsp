<%@page import="com.sv.udb.controlador.EquiposCtrl"%>
<%@page import="com.sv.udb.modelo.Equipos"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

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
        <form method="POST" action="EquiposServ" name="Demo">
            <label>Código:&ensp;&ensp;&ensp;&ensp; </label><input readonly type="text" name="codi" id="codi" value="${codi}"><br>
            <label>Nombre:&ensp;&ensp;&ensp; </label><input type="text" name="nomb" id="nomb" value="${nomb}"><br>
            <label>Descripción: </label><input type="text" name="desc" id="desc" value="${desc}"><br>
            <input ${estGuar} type="submit" name="btonEqui" value="Guardar">
            <input ${estModi} type="submit" name="btonEqui" value="Modificar">
        </form>
        <h1>La Tabla</h1>
        `<form method="POST" action="EquiposServ" name="Tabl">
            <table border="1" width="500">
            <tr>
                <th>Cons</th>
                <th>Nombre</th>
                <th>Descripción</th>
            </tr>
            <%
                for(Equipos temp : new EquiposCtrl().consTodo())
                { 
            %>
            <tr>
                <td><input type="radio" name="codiEquiRadi" value="<%=temp.getCodiEqui()%>"></td>
                <td><%=temp.getNombEqui()%></td>
                <td><%=temp.getDescEqui()%></td>
            </tr>
            <%
                }
            %>
        </table>
        <input type="submit" name="btonEqui" value="Consultar">
        <input ${estElim} type="submit" onclick="return confirm('¿Desea proceder con la actualización del registro?');" name="btonEqui" value="Eliminar">
        <input type="submit" name="btonEqui" value="Nuevo">
        </form>
    </body>
</html>
