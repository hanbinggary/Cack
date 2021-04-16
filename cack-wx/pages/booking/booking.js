// pages/booking/booking.js
const app = getApp();

Page({
  data: {
    userId: app.globalData.userInfo && app.globalData.userInfo.id,
    vehicles: []
  },
  onLoad: function () {
    this.getVehicles();
  },
  onShow: function () {
    app.globalData.booking = {
      vehicleId: -1,
      date: '',
      timeId: -1,
      couponId: -1
    };
  },
  getVehicles: function () {
    const that = this;
    wx.request({
      url: `http://localhost:8081/cack/wx/vehicle/can/booking/${this.data.userId}`,
      method: 'GET',
      success: function ({
        data
      }) {
        console.log('data: ', data);
        if (data.code === 200) {
          const vehicles = data.result;
          if (vehicles && vehicles.length > 0) {
            vehicles.map(vehicle => {
              vehicle.selected = false;
            });
            vehicles[0].selected = true;
            that.setData({
              vehicles
            })
          }
        }
      }
    })
  },
  onSelected: function ({
    currentTarget
  }) {
    const {
      id
    } = currentTarget.dataset;
    this.data.vehicles.map(vehicle => {
      if (vehicle.id === id) {
        vehicle.selected = true;
      } else {
        vehicle.selected = false;
      }
    });
    this.setData({
      vehicles: this.data.vehicles
    })
  },
  // 点击取消
  onCancel: function () {
    app.globalData.booking = {
      vehicleId: -1,
      date: '',
      timeId: -1,
      couponId: -1
    };
    wx.switchTab({
      url: '/pages/index/index'
    })
  },
  // 点击下一步
  onNext: function () {
    this.data.vehicles.some(vehicle => {
      if (vehicle.selected) {
        app.globalData.booking.vehicleId = vehicle.id;
        return true;
      }
      return;
    });
    wx.navigateTo({
      url: '/pages/booking/step2/step2',
    })
  }
})