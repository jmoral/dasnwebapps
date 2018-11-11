<%@page import="javax.servlet.http.HttpServletResponse"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <body>
		<a href="<%= response.encodeURL("accion/")%>">acceso anticsrf</a>
		
	</ul>
</body>
</html>