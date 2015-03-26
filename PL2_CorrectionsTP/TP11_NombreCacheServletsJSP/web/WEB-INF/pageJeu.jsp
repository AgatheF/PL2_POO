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
            <form action="jouer" method="POST" id="formJouer">votre proposition: 
                <!-- utilisation d'un input HTML5 de type number, qui permet d'effectuer
                     les vérifications côté client si celui-ci supporte HTML5  -->
                <input type="number" name="proposition" step="1" value="${param.proposition}" 
                       min="${nombreInconnu.borneMin}" max="${nombreInconnu.borneMax}"><br> 
                <div id="message" class="perdu">
                    ${message1}<br>
                    ${message2}<br>
                </div>
                <input type="submit" name="jouer" value="Jouer" form="formJouer">&nbsp;
                <input type="submit" name="abandonner" value="Abandonner" form="formAbandon">
            </form>
            <form action="abandonner" method="POST" id="formAbandon"/>
        </div>
    </body>
</html>
