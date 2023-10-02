// require("dotenv-safe").config();

const express  = require('express');
const httpProxy = require('express-http-proxy');
const router = express.Router();

const autenticacaoServiceProxy = httpProxy('http://localhost:8081');

router.post('/api/v1/auth/login', (req, res, next) => {
    autenticacaoServiceProxy(req, res, next);
});

module.exports = router;