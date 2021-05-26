const bcrypt = require('bcrypt');
const db = require('../models');

const User = db.user;

exports.signUp = (req, res) => {
  const salt = bcrypt.genSaltSync(10);

  if (!req.body.nama || !req.body.email || !req.body.password) {
    res.status(400).send({
      message: 'Konten Tidak Boleh Kosong!',
    });
  } else {
    const dataRequest = {
      nama: req.body.nama,
      email: req.body.email,
      password: bcrypt.hashSync(req.body.password, salt),
    };

    User.create(dataRequest)
      .then((data) => {
        res.status(200).send({
          message: 'success add user',
          data,
        });
      })
      .catch((err) => {
        res.status(500).send({
          message:
            err.message || 'Kesalahan dalam Menambahkan User.',
        });
      });
  }
};
