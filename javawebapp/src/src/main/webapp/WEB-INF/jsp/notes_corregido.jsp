<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    </head>
    <body>
        <h1>Notes</h1>
        <ul>
            <c:forEach var="row" items="${notes}">
                <li>
                Id: <a href='?id=${fn:escapeXml(row.id)}'>${fn:escapeXml(row.id)}</a> Title: ${fn:escapeXml(row.title)}
                 Message: ${fn:escapeXml(row.text)} 
                </li>
            </c:forEach>
        </ul>
        <form method="POST" >
            <input name="title" placename="Title"/>
            <input name="text" placename="Text"/> 
            <input type="CHECKBOX" name="hidden" value="1">
            <input type="submit" name="Enviar"/>
        </form>
    </body>
</html>
