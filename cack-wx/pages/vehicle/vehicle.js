// pages/vehicle/vehicle.js
const app = getApp();

Page({
  data: {
    userId: app.globalData.userInfo.id,
    vehicles: [],
    loading: false
  },
  onShow: function () {
    this.getVehicles();
  },
  getVehicles: function () {
    const that = this;
    this.setData({
      loading: true
    })
    wx.request({
      url: `http://localhost:8081/cack/wx/vehicle/all/${that.data.userId}`,
      success: function ({
        data
      }) {
        const {
          code,
          result
        } = data;
        if (code === 200) {
          that.setData({
            vehicles: result,
            loading: false
          })
        }
      }
    })
  },
  onViewDetail: function (e) {
    wx.navigateTo({
      url: `/pages/vehicleDetail/vehicleDetail?id=${e.currentTarget.dataset.id}`
    });
  },
  // 添加车辆登记
  onAdd: function () {
    wx.navigateTo({
      url: '/pages/vehicleAdd/vehicleAdd',
    })
  }
})