var jwt = require('jsonwebtoken');

module.exports = function verifyJWT(req, res, next){
    const authHeader = req.headers['authorization'];

    if (!authHeader || !authHeader.startsWith('Bearer ')) {
      return res.status(401).json({ auth: false, message: 'No token provided.' });
    }

    const token = authHeader.replace('Bearer ', '');

    console.log(token)
  
    if (!token) {
      return res.status(401).json({ auth: false, message: 'No token provided.' });
    }
    
    jwt.verify(token, process.env.SECRET, function(err, decoded) {
      if (err) return res.status(500).send({ auth: false, message: 'Failed to authenticate token.' });
      
      req.userId = decoded.id;
      next();
    });
}