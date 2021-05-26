module.exports = (app) => {
  const activity = require('../controllers/activity.controller');
  const { authJwt } = require('../middleware');

  const router = require('express').Router();

  router.post('/', [authJwt.verifyToken], activity.create);
  router.get('/', [authJwt.verifyToken], activity.allData);

  app.use('/api/activity', router, (req, res, next) => {
    res.header(
      'Access-Control-Allow-Headers',
      'x-access-token, Origin, Content-Type, Accept',
    );
    next();
  });
};
