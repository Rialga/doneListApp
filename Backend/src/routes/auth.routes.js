module.exports = (app) => {
  const auth = require('../controllers/auth.controller');

  const router = require('express').Router();
  router.post('/signUp', auth.signUp);

  app.use('/api/auth', router);
};
