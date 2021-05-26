module.exports = (sequelize, DataTypes) => {
  const User = sequelize.define('user', {
    nama: {
      type: DataTypes.STRING(26),
      allowNull: false,
    },
    email: {
      type: DataTypes.STRING(30),
      unique: true,
      allowNull: false,
    },
    password: {
      type: DataTypes.STRING,
      allowNull: false,
    },
  },
  {
    freezeTableName: true,
  });

  return User;
};
