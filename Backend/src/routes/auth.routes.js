module.exports = (app) => {
  const auth = require('../controllers/auth.controller');

  const router = require('express').Router();
  router.post('/signup', auth.signUp);
  router.post('/signin', auth.signIn);

  app.use('/api/auth', router,
    (req, res, next) => {
      res.header(
        'Access-Control-Allow-Headers',
        'x-access-token, Origin, Content-Type, Accept',
      );
      next();
    });
};
