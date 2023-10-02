const express  = require('express');
const httpProxy = require('express-http-proxy');
const router = express.Router();
const verifyJWT = require('./auth/verifyJWT');

const monitoramentoServiceProxy = httpProxy('http://localhost:8082');

router.use((req, res, next) => {
    console.log("Called: ", req.path);
    next();
});

router.post('/incidentes', verifyJWT, (req, res, next) => {
  monitoramentoServiceProxy(req, res, next);
});

router.get('/incidentes', verifyJWT, (req, res, next) => {
  monitoramentoServiceProxy(req, res, next);
});

module.exports = router;