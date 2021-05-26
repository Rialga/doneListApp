module.exports = (app) => {
  const router = require('express').Router();
  app.use('/api/activity', router);
};
