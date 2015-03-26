$(document).ready(function () {
    // mise en place des onglets (tabs)
    $("#tabs").tabs();

    $("form").on("submit", function (e) {
        e.preventDefault();
        e.stopPropagation();
    });

    $("#pdfConsosSemaine").hide();
    $("#pdfConsosProgrammeur").hide();

    // associe un gestionnaire d'évenements au champ de saisie du 
    // numéro de semaine
    $("#numSemInput").on("change", function (e) {
        e.preventDefault();
        e.stopPropagation();
        $("#tabConsosSemaine").load("consosSemaine", $("#semainesForm").serialize(),
                function () {
                    $("#pdfConsosSemaine a").attr("href", "consosSemaine?format=pdf&numSem=" + $("#numSemInput").val());
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
});


