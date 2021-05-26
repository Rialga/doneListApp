const express = require('express');
const bodyParser = require('body-parser');
const cors = require('cors');

const corsOptions = {
  origin: 'http://localhost:5000',
};

const app = express();
app.use(cors(corsOptions));

app.use(bodyParser.urlencoded({ extended: true }));

app.use(bodyParser.json());

app.get('/', (req, res) => {
  res.send('This is Server Side');
});

require('./src/routes/auth.routes')(app);
require('./src/routes/activity.routes')(app);

const port = process.env.PORT || 5000;
app.listen(port, () => {
  console.log(`Server berjalan pada http://localhost:${port}`);
});
