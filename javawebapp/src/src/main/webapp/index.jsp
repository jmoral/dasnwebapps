<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <body>
        <h1>DASN 2018 -evaluación de la pila de desarrollo</h1>
		<h2>autores</h2>
		mjnd0001, jrmc0002
		<h2>Detalle de la pila</h2>
		linux, java, tomcat
		<h2>ejemplos</h2>
	<ul>
		<li><a href="notes/initdb">Inicialización de la base de datos</a></li>
		<li><a href="notes_corregido/xssdb">inclusión en la base de datos de un xss permanente</a></li>
		<li><a href="notes_corregido/?id=A">validación de parametros GET</a></li>
		<p>Se verifica que el parametro id es un numero con Pattern.matches y se controla la excepción NumberFormatException</p>
		<p>No se permite pasar un id no númerico</p>
		<li><a href="notes_corregido/">validación de datos del formulario con POST</a></li>
		<p>Se escapan las entradas de texto del usuario con StringEscapeUtils.escapeHtml4</p>
		<li><a href="notes/?id=1 or id=2">sql injection. ejemplo 1</a></li>
		<p>Este prueba debería devolver un solo registro, pero devuelve 2 por injección de SQL</p>
		<li><a href="notes_corregido/?id=1 or id=2">ejemplo 1 corregido</a></li>
		<p>La misma prueba anterior pero usamos prepareStatement</p>
		<li><a href="no_existe">ocultamiento de información de errores</a></li>
		<p>Llamamos a una ruta que no existe y ocultamos la información del error con una página genérica</p>
		<li><a href="notes_corregido/">XSS</a></li>
		<p>Para ataques xss aplicamos los filtros de <a href="https://www.owasp.org/index.php/Content_Security_Policy">owasp</a></p>
		<p>aseguramos que la cookie de sesion es solo http</p>
		<p>escapamos los valores devueltos con fn:escapeXml</p>
	</ul>
</body>
</html>