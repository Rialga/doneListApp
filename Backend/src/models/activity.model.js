module.exports = (sequelize, DataTypes) => {
  const Activity = sequelize.define('activity', {
    activity_name: {
      type: DataTypes.STRING(30),
      allowNull: false,
    },
  },
  {
    freezeTableName: true,
  });

  return Activity;
};
