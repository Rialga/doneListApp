const db = require('../models');

const Activity = db.activity;

exports.allData = (req, res) => {
  const userId = req.query.id;
  const condtion = userId ? { user_id: userId } : null;
  Activity.findAll({ where: condtion })
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
  if (!req.body.activity_name || !req.body.user_id) {
    res.status(400).send({
      message: 'Konten Tidak Boleh Kosong!',
    });
  } else {
    const dataRequest = {
      activity_name: req.body.activity_name,
      user_id: req.body.user_id,
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
