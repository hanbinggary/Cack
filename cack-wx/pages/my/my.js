// pages/my/my.js
const app = getApp();

Page({
  data: {
    userInfo: app.globalData.userInfo,
    couponTotal: 0,
    vehicleTotal: 0,
    notifyTotal: 0
  },
  onShow: function () {
    if (this.data.userInfo && this.data.userInfo.id) {
      this.getCouponTotal();
      this.getVehicleTotal();
      this.getNotifyTotal();
    }
  },
  onPullDownRefresh: function () {
    if (this.data.userInfo && this.data.userInfo.id) {
      this.getCouponTotal();
      this.getVehicleTotal();
      this.getNotifyTotal();
    }
  },
  getCouponTotal: function () {
    const that = this;
    wx.request({
      url: `http://localhost:8081/cack/wx/coupon/count/usable/${this.data.userInfo.id}`,
      method: 'GET',
      success: function ({
        data
      }) {
        if (data.code === 200) {
          that.setData({
            couponTotal: data.result
          })
        }
      }
    })
  },
  getVehicleTotal: function () {
    const that = this;
    wx.request({
      url: `http://localhost:8081/cack/wx/vehicle/count/${this.data.userInfo.id}`,
      method: 'GET',
      success: function ({
        data
      }) {
        if (data.code === 200) {
          that.setData({
            vehicleTotal: data.result
          })
        }
      }
    })
  },
  getNotifyTotal: function () {
    const that = this;
    wx.request({
      url: `http://localhost:8081/cack/wx/notify/count/unread/${this.data.userInfo.id}`,
      method: 'GET',
      success: function ({
        data
      }) {
        if (data.code === 200) {
          that.setData({
            notifyTotal: data.result
          })
        }
      }
    })
  },
  onGoPage: function ({
    currentTarget
  }) {
    const path = currentTarget.dataset.path;
    wx.navigateTo({
      url: `/pages/${path}/${path}`,
    })
  }
})