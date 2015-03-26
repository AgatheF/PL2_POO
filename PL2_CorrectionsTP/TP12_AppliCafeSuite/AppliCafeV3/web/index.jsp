<!DOCTYPE html>
<!--
Page d'accueil de l'application de consultation des consommations de caf�s.
-->
<html>
    <head>
        <title>Consos Caf�s</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="styles/cafe.css">
    </head>
    <body>
        <div id='wrapper'>
            <img src="images/coffee.png" alt="logo avec une tasse de caf�"/>
            <div>
                <h1>Consommations hebdomadaires des caf�s</h1>
                <form action="consosCafes">
                    <!-- 
                    pour la saisie du num�ro de la semaine on utilise un nouveau
                    type d'input HTML 5 pr�vu � cet effet. Le support n'est pas le m�me
                    selon les navigateurs : sur Firefox cela sera un simple textfield,
                    sur Chrome vous aurez un contr�le plus sophistiqu� qui permettra
                    d'afficher directement un calandrier et de s�lectionner la semaine
                    concern�e sur celui-ci.
                    Pour plus d'infos :
                    Formulaires HTML5 : nouveaux types de champs, tutoriel de Geoffrey C.
                    http://www.alsacreations.com/tuto/lire/1372-formulaires-html5-nouveaux-types-champs-input.html
                    et plus sp�cifiquement pour le type week
                    http://www.alsacreations.com/tuto/lire/1408-formulaire-html5-type-month-week.html
                    -->
                    <label for="numSemInput">semaine:&nbsp;</label><input id="numSemInput" type="week" name="numSem"> ${messageErreur} <br><br> 
                    HTML: <input type="radio" name="affichage" value="HTML" /> PDF :<input type="radio" name="affichage" value="pdf" /><br><br>
                    <input type="submit" value="Envoyer" />
                </form>
            </div>
        </div>
    </body>
</html>
