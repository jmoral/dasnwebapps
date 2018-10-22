<?php
	$dsn="sqlite:/tmp/notes.sqlite";
	$dbuser="usuario";
	$dbpass="secreto";

	$db = new PDO( $dsn,$dbuser,$dbpass);

	// Set errormode to exceptions
	$db->setAttribute(PDO::ATTR_ERRMODE, 
		PDO::ERRMODE_EXCEPTION);

	$query="CREATE TABLE IF NOT EXISTS notes (
		id INTEGER PRIMARY KEY AUTOINCREMENT, 
		title TEXT, 
		text TEXT, 
		hidden INTEGER)";
	$db->exec($query);

	$query="INSERT INTO notes ('title','text','hidden') VALUES
		('first','Hello world',0),
		('second','Hidden',1);";
	$db->exec($query);

	header("Location: .");
	exit();
?>
