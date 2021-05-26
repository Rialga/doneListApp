const db = require('../models');

const Activity = db.activity;

exports.allData = (req, res) => {
  const userId = 1;

  Activity.findAll({ where: { user_id: userId } })
    .then((data) => {
      res.status(200).send({
        message: 'success mendapatkan data',
        data,
      });
    })
    .catch((err) => {
      res.status(500).send({
        message:
            err.message || 'Tidak Dapat Mengambil data Activity.',
      });
    });
};

exports.create = (req, res) => {
  const userId = 1;

  if (!req.body.activity_name) {
    res.status(400).send({
      message: 'Konten Tidak Boleh Kosong!',
    });
  } else {
    const dataRequest = {
      activity_name: req.body.activity_name,
      user_id: userId,
    };

    Activity.create(dataRequest)
      .then((data) => {
        res.status(200).send({
          message: 'success add Activity',
          data,
        });
      })
      .catch((err) => {
        res.status(500).send({
          message:
            err.message || 'Kesalahan Dalam Menambahan Activity.',
        });
      });
  }
};
