/* eslint-disable consistent-return */
const jwt = require('jsonwebtoken');
const bcrypt = require('bcryptjs');
const db = require('../models');
const config = require('../../config/auth.config');

const User = db.user;

exports.signIn = (req, res) => {
  if (!req.body.email || !req.body.password) {
    res.status(400).send({
      message: 'Konten Tidak Boleh Kosong!',
    });
  }

  User.findOne({ where: { email: req.body.email } })
    .then((user) => {
      if (!user) {
        res.status(200).send({
          message: 'User Tidak Ditemukan',
        });
      } else {
        const passwordIsValid = bcrypt.compareSync(
          req.body.password,
          user.password,
        );

        if (!passwordIsValid) {
          return res.status(401).send({
            accessToken: null,
            message: 'Password Salah!',
          });
        }

        const token = jwt.sign({ id: user.id }, config.secret, {
          expiresIn: 86400, // 24 hours
        });

        res.status(200).send({
          id: user.id,
          nama: user.nama,
          email: user.email,
          accessToken: token,
        });
      }
    }).catch((err) => {
      res.status(500).send({ message: err.message });
    });
};

exports.signUp = (req, res) => {
  if (!req.body.nama || !req.body.email || !req.body.password) {
    res.status(400).send({
      message: 'Konten Tidak Boleh Kosong!',
    });
  } else {
    const dataRequest = {
      nama: req.body.nama,
      email: req.body.email,
      password: bcrypt.hashSync(req.body.password, 8),
    };

    User.findOne({ where: { email: req.body.email } })
      .then((user) => {
        if (user) {
          res.status(400).send({
            message: 'Failed! Email Sudah Ada!',
          });
        } else {
          User.create(dataRequest)
            .then(() => {
              res.status(200).send({
                message: 'success add user',
              });
            })
            .catch((err) => {
              res.status(500).send({
                message:
                err.message || 'Kesalahan dalam Menambahkan User.',
              });
            });
        }
      });
  }
};
