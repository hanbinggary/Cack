// pages/station/station.js
Page({
  data: {
    station: {}
  },
  onLoad: function () {
    this.getStation();
  },
  getStation: function () {
    const that = this;
    wx.request({
      url: 'http://localhost:8081/cack/wx/station',
      method: 'GET',
      success: function ({
        data
      }) {
        if (data.code === 200) {
          data.result.openingTime = data.result.openingTime.replace('@', '-');
          that.setData({
            station: data.result
          })
        }
      }
    })
  },
  // 点击电话图标
  onMakePhone: function () {
    wx.makePhoneCall({
      phoneNumber: this.data.station.phone
    })
  },
  // 点击定位图标
  onMap: function () {
    const that = this;
    console.log('that.data.station: ', that.data.station);
    wx.openLocation({
      latitude: parseFloat(that.data.station.latitude),
      longitude: parseFloat(that.data.station.longitude),
    })
  }
})