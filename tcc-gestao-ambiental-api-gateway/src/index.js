const helmet = require('helmet');
const cors = require('cors');
const express  = require('express');
const app = express();

const routes   = require('./routes');
const loginRoutes = require('./routes/loginRoutes')
const usuarioRoutes = require('./routes/usuarioRoutes')

var cookieParser = require('cookie-parser');

app.use(cors({ origin: 'http://localhost:3000'}));
app.use(helmet());
app.use(express.urlencoded({ extended: false }));
app.use(express.json());
app.use(cookieParser());


app.use(routes);
app.use('/api/v1/auth/login', loginRoutes)
app.use('/api/v1/users', usuarioRoutes); 

app.listen(3002, () => {
    console.log("Servidor ouvindo na porta 3002.")
});