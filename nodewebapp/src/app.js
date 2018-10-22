const port = process.env.PORT || 3000

const express = require('express');
const bodyParser = require('body-parser');
const sqlite = require ('sqlite3').verbose();

const app = express();

app.use( bodyParser.urlencoded({extended: true}) );
app.use('/static',express.static(__dirname + '/html'));
app.set('views',__dirname + '/views');
app.set('view engine','ejs');

const db = new sqlite.Database('notes.sqlite',err => {
			if (err) {
				console.err('Error opening database: '+err);
			}
			console.log("Database opened");
		});

//Database initialization url
app.get('/initdb', (req, res) => {
	db.serialize(()=> {
		db.run(' DROP TABLE IF EXISTS notes' )
		db.run(` CREATE TABLE IF NOT EXISTS notes (
			id INTEGER PRIMARY KEY AUTOINCREMENT, 
			title TEXT, 
			text TEXT, 
			hidden INTEGER)`)
		db.run(`INSERT INTO notes ('title','text','hidden') VALUES
			('first','Hello world',0),
			('second','Hidden',1);`)
	})
	res.redirect('/');
})

app.get('/', (req, res) => {
	let notes=[];
	let query="SELECT * FROM notes";
	if (req.query.id) {
		query+=` WHERE id= ${req.query.id}`;
	}
	db.all(query,[], (err,  rows ) => {
		if (!err) 
			rows.forEach( note => {
				notes.push(note);
			})
		res.render('index',{notes:notes});
	})
})

app.post('/', (req, res) => {
	let query=`INSERT INTO notes ('title','text','hidden') VALUES`
			+`('${req.body.title}','${req.body.text}',0);`;
	db.run(query,[], err => {
		if (err)
			console.log('Insert error '+err);
		res.redirect('/');
	})
})

const server=app.listen(port, () =>  {
  console.log(`Running at Port ${port}`);
});

function closeResources (code) {
	console.log("Server shutdown");
	db.close();
	server.close();
}
process.on('SIGTERM', closeResources);
process.on('SIGINT', closeResources); //Detect Ctrl+c
