module.exports = (app) => {
  const router = require('express').Router();
  app.use('/api/auth', router);
};
