const db = require('../src/models');

db.sequelize.sync();

db.sequelize.sync({ force: true }).then(() => {
  console.log('Drop and re-sync db.');
});
