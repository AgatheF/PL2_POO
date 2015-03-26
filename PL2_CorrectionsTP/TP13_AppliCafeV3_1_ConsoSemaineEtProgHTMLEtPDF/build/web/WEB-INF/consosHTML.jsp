<%-- 
    Document   : consosHTML
    Created on : 7 févr. 2015, 10:01:45
    Author     : Philippe Genoud
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Servlet ConsosCafesServlet</title>
        <link rel="stylesheet" type="text/css" href="styles/cafe.css">
    </head>
    <body>
        <div id="wrapper">
            <header>
                <h3 id="header"><img src="images/coffeeSmall.png" alt="logo avec une tasse de café"/>
                    ${titre}
                </h3>
            </header>
            <jsp:include page="/htmlTable"/>
            <a href="index.jsp">choisir une autre sélection</a>
        </div>
    </body>
</html>
