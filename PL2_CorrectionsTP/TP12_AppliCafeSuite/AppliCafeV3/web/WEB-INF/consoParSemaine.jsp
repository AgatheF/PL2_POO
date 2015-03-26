<%-- 
    Document   : consoParSemaine
    Created on : 6 févr. 2015, 12:40:45
    Author     : genoud
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="m2pcci.im2ag.tp11.cafes.model.Programmeur" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Servlet ConsosCafesServlet</title>
        <link rel="stylesheet" type="text/css" href="styles/cafe.css">
    </head>
    <body>
        <div id='wrapper'>
            <img class="icon" src="images/coffee.png" width=100 height=100 alt="logo avec une tasse de café"/>
            <h1>Consommations de café pour<br>la semaine : 2</h1>
            <table id='tabConsos'>
                <thead>
                <th>PROGRAMMEUR</th><th>NBRE TASSES</th>
                </thead>
                <tbody>
                    <%
                         int nbTotalTasses = 0;
                     // recuperation de la list programmeur par le nom "consos" passe en l73 consosCafesServlet
                        List<Programmeur> programmeurs = (List<Programmeur>) request.getAttribute("consos");
                        for (Programmeur p : programmeurs) {
                            nbTotalTasses += p.getNbTasses();
                            out.println("<tr><td>" + p.getNom() + " " + 
                                    p.getPrenom() + "</td><td>" + p.getNbTasses() + "</td></tr>\n");
                        }
                    %>
                </tbody>
                <tfoot>
                    <tr><td>nbre total de tasses </td><td><%=nbTotalTasses%></td></tr>
                </tfoot>
            </table>
            <a href="index.html">chosir une autre semaine</a>
    </body>
</html>
