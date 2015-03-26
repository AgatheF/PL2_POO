$(document).ready(function () {
    // mise en place des onglets (tabs)
    $("#tabs").tabs();

// pour chaque balise form 
    $("form").on("submit", function (e) {
        e.preventDefault();
        e.stopPropagation();
    });

    // cache les images pdf au demarrage
    $("#pdfConsosSemaine").hide();
    $("#pdfConsosProgrammeur").hide();

    // associe un gestionnaire d'évenements au champ de saisie du 
    // numéro de semaine
    // des que valeur de l'imput du noSemaine j'actualise le tableau resultats
    $("#numSemInput").on("change", function (e) {
        e.preventDefault();
        e.stopPropagation();
//HTML: chargement du tableau (id du div destination).load("url de la servlet a execute,met a jour l affichage du formulaire, function=autres actions a realiser apres le load)
        $("#tabConsosSemaine").load("consosSemaine", $("#semainesForm").serialize(),
                function () {
                    //PDF: 
                    $("#pdfConsosSemaine a").attr("href", "consosSemaine?format=pdf&numSem=" + $("#numSemInput").val());
                    //montre le lien sur le pdf dans le div pdfConsosSemaine
                    $("#pdfConsosSemaine").show();
                });
    });
    
    // associe un gestionnaire d'évenements au champ de saisie du 
    // numéro de programmeur
    $("#idProg").on("change", function (e) {
        e.preventDefault();
        e.stopPropagation();
        $("#tabConsosProgrammeur").load("consosProgrammeur", $("#programmeurForm").serialize(),
                function () {
                    $("#pdfConsosProgrammeur a").attr("href", "consosProgrammeur?format=pdf&idProg=" + $("#idProg").val());
                    $("#pdfConsosProgrammeur").show();
                });
    });
    
    // associe un gestionnaire d'évenements au champ de saisie du 
    // numéro de bureau
    $("#noBureau").on("change", function (e) {
        e.preventDefault();
        e.stopPropagation();
        $("#tabConsosBureau").load("consosBureau", $("#bureauForm").serialize(),
                function () {
                    $("#pdfConsosBureau a").attr("href", "consosBureau?format=pdf&noBureau=" + $("#noBureau").val());
                    $("#pdfConsosBureau").show();
                });
    });
});


