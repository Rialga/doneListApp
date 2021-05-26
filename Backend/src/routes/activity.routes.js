module.exports = (app) => {
  const activity = require('../controllers/activity.controller');

  const router = require('express').Router();
  router.post('/', activity.create);
  router.get('/', activity.allData);

  app.use('/api/activity', router);
};
