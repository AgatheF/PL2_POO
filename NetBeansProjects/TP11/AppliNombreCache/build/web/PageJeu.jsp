<%-- 
    Document   : PageJeu
    Created on : 6 févr. 2015, 16:28:49
    Author     : agathe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1> ! Nombre Cache ! </h1>
        <%-- appel de la servlet de sontrole si on envoi un nombre--%>
        <form action="jouer"> 
            <p>  Entrer un nombre entre 0 et 100 </p><br>
            <%--  nombreEssai=parametre de notre nombreInconnu= passe en attribut de 
                la session dans InitPartieServlet--%>
            <p>  Il vous reste ${nombreCache.nbEssaisRestant} essais</p>
            <p>votre suggestion: <input type="text" name="valSaisie" value="" size="3" /></p>
            <p> ${message} </p>
            <input type="submit" value="envoyer"/> <br>
        </form>
        
        <%-- appel de la servlet de demarrage si on rejoue une partie--%>
        <form action="InitPartieServlet"> 
        <input type="submit" value="nouvellePartie"/>
        </form>
    </body>
</html>
