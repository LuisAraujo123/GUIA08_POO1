<%@page import="java.util.Base64"%>
<%@page import="com.sv.udb.controlador.EquiposCtrl"%>
<%@page import="com.sv.udb.modelo.Equipos"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%-- language maneja el idioma actual --%>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.me.jsp.bundle.idiomas" />
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel='stylesheet' href='webjars/Materialize/0.96.0/dist/css/materialize.min.css'>
        <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <script type="text/javascript" src="webjars/jquery/2.1.1/jquery.min.js"></script>
        <script type="text/javascript" src="webjars/Materialize/0.96.0/dist/js/materialize.min.js"></script>
        <title>INTERNACIONALIZACION</title>
    </head>
    <body>
        <ul>
            <li><a href='index.jsp'>Equipos</a></li>
            <li><a href='jugadores.jsp'>Jugadores</a></li>
            <li><a href='partidos.jsp'>Partidos</a></li>
        </ul>
        <div class="row container z-depth-5">
             <form class="col s12 m6 offset-m3">
                <fmt:message key="label.select_language" />: 
                <select id="language" name="language" class="default-browser" onchange="submit();">
                   <option value="es_SV" 
                     <c:if test="${language=='es_SV'}">selected</c:if>>
                     <fmt:message key="label.spanish" />
                   </option>
                   <option value="en_US" 
                     <c:if test="${language=='en_US'}">selected</c:if>>
                     <fmt:message key="label.english" />
                   </option>
                </select>
            </form>
            <h1 class="col s12 m6 offset-m3">${mensAler}</h1>
            <form method="POST" action="EquiposServ" name="Demo" class="col s12 m6 offset-m3" enctype="multipart/form-data">
            <label><fmt:message key="label.codi" />:</label>
            <input readonly type="text" name="codi" id="codi" value="${codi}"><br>
            <label><fmt:message key="label.name" />:</label><input type="text" name="nomb" id="nomb" value="${nomb}"><br>
            <label><fmt:message key="label.desc" />:</label><input type="text" name="desc" id="desc" value="${desc}"><br>
            <div class="file-field input-field">
                <div class="btn">
                  <span><i class="material-icons">image</i></span>
                  <input type="file" id="imag" name="imag" value="${imag}">
                </div>
                <div class="file-path-wrapper">
                  <input class="file-path validate" type="text">
                </div>
            </div>
            <input ${estGuar} type="submit" name="btonEqui" value="<fmt:message key="button.guar" />">
            <input ${estModi} type="submit" name="btonEqui" value="<fmt:message key="button.modi" />">
        </form>
        <h1 class="col s12 m6 offset-m3"><fmt:message key="table.titu" /></h1>
        `<form method="POST" action="EquiposServ" name="Tabl" class="col s12 m6 offset-m3">
            <table class="highlight" border="1" width="500">
            <tr>
                <th>Cons</th>
                <th><fmt:message key="label.name" /></th>
                <th><fmt:message key="label.desc" /></th>
                <th>Logo</th>
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
        <input type="submit" name="btonEqui" value="<fmt:message key="button.cons" />">
        <input ${estElim} type="submit" onclick="return confirm('¿Desea proceder con la actualización del registro?');" name="btonEqui" value="<fmt:message key="button.elim" />">
        <input type="submit" name="btonEqui" value="<fmt:message key="button.nuev" />">
        </form>
        </div>
    </body>
     <script>
        $(document).ready(function() {
            $('select').material_select();
          });
    </script>
</html>
