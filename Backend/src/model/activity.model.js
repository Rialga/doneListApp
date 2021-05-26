module.exports = (sequelize, DataTypes) => {
  const Activity = sequelize.define('activity', {
    activity_name: {
      type: DataTypes.STRING(30),
      unique: true,
      allowNull: false,
    },
  },
  {
    freezeTableName: true,
  });

  return Activity;
};
