<%-- 
    Document   : index
    Created on : 29 janv. 2015, 13:25:03
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
        <h1>Hello World!</h1>
        <p>Il est <%=new java.util.Date()%> </p>
        <%-- appel de la servlet par son nom à la soumission (input type submit) du form--%>
        <form action="HelloControlerServlet">  
            Texte à afficher: <input type="text" name="texte" value="" size="20" /><br/>
            Nombre de repetition: <input type="text" name="nombre" value="" size="3"> <br/>
            Affichage: <br/> 
            <%-- RADIO BOUTON CHOIX FORMAT--%>
            HTML <input type="radio" name="format" value="HTML" />
            PNG <input type="radio" name="format" value="PNG" /> <br/>
            <img src="Compteur">
            <input type="submit" value="soumettre"/>
        </form>
    </body>
</html>

