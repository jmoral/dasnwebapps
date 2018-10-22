<?php
	$dsn = "sqlite:/tmp/notes.sqlite";
	$dbuser = "usuario";
	$dbpass = "secreto";

	$db = new PDO($dsn, $dbuser, $dbpass);

	// Set errormode to exceptions
	$db->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

	if ($_SERVER['REQUEST_METHOD'] === 'POST') {
		$query = "INSERT INTO notes ('title','text','hidden') VALUES (" .
				"'${_POST['title']}','${_POST['text']}',0 );";
		$db->exec($query);
		header("Location: " . $_SERVER['REQUEST_URI']);
		exit();
	}
	$query="SELECT * FROM notes";
	if ($_GET['id']) {
		$result = $db->query($query." WHERE id=  ${_GET['id']}");
	} else {
		$result = $db->query($query);
	}
?>
<html>
    <head>
    </head>
    <body>
        <h1>Notes</h1>
        <ul>
            <?php
            foreach ($result as $row) {
                echo "<li>";
                echo "Id: ";
                echo "<a href='index.php?id=${row['id']}'>${row['id']}</a>";
                echo " Title: " . $row['title'];
                echo " Message: " . $row['text'];
                echo " Hidden: " . $row['hidden'];
                echo "</li>";
            }
            ?>
        </ul>
        <form method="POST" >
            <input name="title" placename="Title"/>
            <input name="text" placename="Text"/> 
            <input type="CHECKBOX" name="hidden" value="1">
            <input type="submit" name="Enviar"/>
        </form>
    </body>
</html>
