// pages/coupon/coupon.js
const app = getApp();

Page({
  data: {
    userInfo: app.globalData.userInfo,
    coupons: []
  },
  onLoad: function (options) {
    this.getCoupons();
  },
  getCoupons: function () {
    const that = this;
    wx.request({
      url: `http://localhost:8081/cack/wx/coupon/unused/${this.data.userInfo.id}`,
      method: 'GET',
      success: function ({
        data
      }) {
        if (data.code === 200) {
          that.setData({
            coupons: data.result
          })
        }
      }
    })
  }
})