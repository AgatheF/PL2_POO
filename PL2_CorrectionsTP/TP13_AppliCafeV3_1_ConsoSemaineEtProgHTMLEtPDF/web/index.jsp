<%-- 
    Document   : index
    Created on : 6 févr. 2015, 15:08:51
    Author     : genoud
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Consos Cafés</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="styles/cafe.css">
    </head>
    <body>
        <div id='wrapper'>
            <header>
                <p id="header"><img src="images/coffeeSmall.png" alt="logo avec une tasse de café"/>
                    Consommations des cafés
                </p>
            </header>
            <hr>
            <div>
                <h3>Consommations hebdomadaires</h3>
                <form action="consosSemaine" method="POST">
                    <label for="numSemInput">semaine:&nbsp;</label>
                    <input id="numSemInput" type="week" 
                           name="numSem" value="${param.numSem}" required> ${messageErreur}
                    <br><br>
                    HTML: <input type="radio" name="format" value="HTML" checked/>
                    PDF :<input type="radio" name="format" value="pdf" /><br><br>
                    <input type="submit" value="Envoyer" />
                </form>
            </div>
            <hr>
            <div>
                <h3>Consommations d'un programmeur</h3>
                <form action="consosProgrammeur" method="POST">
                    <label for="idProg"">id du Programmeur:&nbsp;</label>
                    <input id="idProg" type="number" 
                           name="idProg" value="${param.idProg}" required> ${messageErreur}
                    <br><br>
                    HTML: <input type="radio" name="formatP" value="HTML" checked/>
                    PDF :<input type="radio" name="formatP" value="pdf" /><br><br>
                    <input type="submit" value="Envoyer" />
                </form>
            </div>        
        </div>
    </body>
</html>
