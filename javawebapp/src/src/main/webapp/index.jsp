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
		<li><a href="notes_corregido/?id=A">validación de datos del formulario</a></li>
		<p>Se verifica que el parametro id es un numero con Pattern.matches y se controla la excepción NumberFormatException
			No se permite pasar un id no númerico
		</p>
		<li><a href="notes/?id=1 or id=2">sql injection. ejemplo 1</a></li>
		<p>Este prueba debería devolver un solo registro, pero devuelve 2 por injección de SQL</p>
		<li><a href="notes_corregido1/?id=1 or id=2">ejemplo 1 corregido</a></li>
		<p>La misma prueba anterior pero usamos prepareStatement</p>
		<li><a href="notes/?id=1 or id=2">ejemplo 3 ocultamiento de información de errores</a></li>
	</ul>
</body>
</html>