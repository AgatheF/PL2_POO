<%-- 
    Document   : pageJeu.jsp
    Created on : 23 janv. 2013, 11:24:16
    Author     : genoud
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta charset="utf-8"/>
        <link rel="stylesheet" href="styles/nbreCache.css" />
        <title>Nombre caché</title>
    </head>
    <body>
        <div id="wrapper">
            <p>Trouvez le nombre caché entre ${nombreInconnu.borneMin} et ${nombreInconnu.borneMax}<br>
                en ${nombreInconnu.nbEssaisMax} essais maximum.</p>
            <br> 
            <div id="message" class="${styleFinPartie}">
                ${message1}<br>
                ${message2}<br>
            </div>
            <form action="debuterPartie" method="POST">
                <input type="submit" name="rejouer" value="Nouvelle Partie" />
            </form>
        </div>
    </body>
</html>
