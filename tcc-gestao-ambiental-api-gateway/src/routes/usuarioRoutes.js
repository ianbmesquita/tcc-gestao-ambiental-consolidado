// require("dotenv-safe").config();

const express  = require('express');
const httpProxy = require('express-http-proxy');
const router = express.Router();
const verifyJWT = require('../auth/verifyJWT');

const autenticacaoServiceProxy = httpProxy('http://localhost:8081');

router.get('/api/v1/users', verifyJWT, (req, res, next) => {
    autenticacaoServiceProxy(req, res, next);
  });
  
  router.post('/api/v1/users', verifyJWT, (req, res, next) => {
    autenticacaoServiceProxy(req, res, next);
  });
  
  router.put('/api/v1/users/:id', verifyJWT, (req, res, next) => {
    autenticacaoServiceProxy(req, res, next);
  });
  
  router.delete('/api/v1/users/:id', verifyJWT, (req, res, next) => {
    autenticacaoServiceProxy(req, res, next);
  });

  module.exports = router;