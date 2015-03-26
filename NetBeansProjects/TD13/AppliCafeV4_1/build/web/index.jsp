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
        <!-- téléchargement de JQuery sur MaxCDN -->
        <script src="//code.jquery.com/jquery-2.1.3.min.js"></script>
        <!-- scripts et css pour jqueryui -->
        <link rel="stylesheet" type="text/css" href="scripts/jqueryui/jquery-ui.min.css">
        <link rel="stylesheet" type="text/css" href="scripts/jqueryui/jquery-ui.structure.min.css">
        <link rel="stylesheet" type="text/css" href="scripts/jqueryui/jquery-ui.theme.min.css">
        <script src="scripts/jqueryui/jquery-ui.min.js"></script>


        <link rel="stylesheet" type="text/css" href="styles/cafe.css">
        <!-- appel du javascript consoscafe.js -->
        <script src="scripts/consoscafe.js"></script>
    </head>
    <body>
        <div id='wrapper'>
            <header>
                <p id="header"><img src="images/coffeeSmall.png"/> Consommations des cafés</p>
            </header>
            <div id="tabs">
                <ul>
                    <li><a href="#tabs-1">Par semaine</a></li>
                    <li><a href="#tabs-2">Par programmeur</a></li>
                    <li><a href="#tabs-3">Par bureau</a></li>
                </ul>
                <div id="tabs-1">
                    <h1>Consommations hebdomadaires des cafés</h1>
                    <form id="semainesForm">
                        <label for="numSemInput">semaine:&nbsp;</label>
                        <input id="numSemInput" type="week" 
                               name="numSem" value="${param.numSem}"> ${messageErreur}
                        <input type="hidden" name="format" value="HTML" checked/>
                    </form>
                    <br><br>
                    <div id="tabConsosSemaine"></div>
                    <p id="pdfConsosSemaine">télécharger la table en pdf 
                        <a href="#" download><img src="images/pdf_icon_32.png"></a>
                        <!-- attribut download ne semble pas être supporté par IE et Safari
                             voir http://caniuse.com/#feat=download
                             le remplacer par target="_blank" -->
                    </p>
                </div>
                <div id="tabs-2">
                    <h1>Consommations hebdomadaires des cafés</h1>
                    <form id="programmeurForm">
                        <label for="idProg">ID Programmeur:&nbsp;</label>
                        <input id="idProg" type="number" 
                               name="idProg"> ${messageErreur}
                        <input type="hidden" name="format" value="HTML" checked/>
                    </form>
                    <br><br>
                    <div id="tabConsosProgrammeur"></div>
                    <p id="pdfConsosProgrammeur">télécharger la table en pdf 
                        <a href="#" download><img src="images/pdf_icon_32.png"></a>
                        <!-- attribut download ne semble pas être supporté par IE et Safari
                             voir http://caniuse.com/#feat=download
                             le remplacer par target="_blank" -->
                    </p>
                </div>
                <div id="tabs-3">
                    <h1>Consommations hebdomadaires des cafés</h1>
                    <form id="bureauForm">
                        <label for="noBureau">Numero du bureau:&nbsp;</label>
                        <input id="noBureau" type="number" 
                               name="noBureau"> ${messageErreur}
                        <input type="hidden" name="format" value="HTML" checked/>
                    </form>
                    <br><br>
                    <div id="tabConsosBureau"></div>
                    <p id="pdfConsosBureau">télécharger la table en pdf 
                        <a href="#" download><img src="images/pdf_icon_32.png"></a>
                        <!-- attribut download ne semble pas être supporté par IE et Safari
                             voir http://caniuse.com/#feat=download
                             le remplacer par target="_blank" -->
                    </p>
                </div>
            </div>
        </div>
    </body>
</html>
