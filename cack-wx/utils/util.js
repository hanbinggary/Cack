const formatTime = datetime => {
  const date = new Date(datetime);
  const year = date.getFullYear()
  const month = date.getMonth() + 1
  const day = date.getDate()

  return `${[year, month, day].map(formatNumber).join('-')}`
}

const formatNumber = n => {
  n = n.toString()
  return n[1] ? n : `0${n}`
}

// 根据经纬度计算两点之间的距离，单位KM
function radians(d) {
  return (d * Math.PI) / 180.0;
}

function getDistance(from, to) {
  const fromLongitudeRad = radians(from.longitude);
  const fromLatitudeRad = radians(from.latitude);
  const toLongitudeRad = radians(to.longitude);
  const toLatitudeRad = radians(to.latitude);

  const a = toLatitudeRad - fromLatitudeRad;
  const b = toLongitudeRad - fromLongitudeRad;
  return (
    2 *
    Math.asin(
      Math.sqrt(
        Math.sin(a / 2) * Math.sin(a / 2) +
        Math.cos(fromLatitudeRad) *
        Math.cos(toLatitudeRad) *
        Math.sin(b / 2) *
        Math.sin(b / 2)
      )
    ) *
    6378.137
  ).toFixed(2);
}

module.exports = {
  formatTime,
  getDistance
}