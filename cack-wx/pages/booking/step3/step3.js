// pages/booking/step3/step3.js
const app = getApp();

Page({
  data: {
    times: []
  },
  onLoad: function (options) {
    this.getTimes(options.date);
  },
  onShow: function () {
    app.globalData.booking.couponId = -1;
  },
  getTimes: function (date) {
    const that = this;
    wx.request({
      url: 'http://localhost:8081/cack/wx/booking/time/query/time/date',
      method: 'GET',
      data: {
        date
      },
      success: function ({
        data
      }) {
        const {
          code,
          result
        } = data;
        if (code === 200) {
          if (result) {
            result.map(time => time.selected = false);
            result[0].selected = true;
            app.globalData.booking.timeId = result[0].id;
            that.setData({
              times: result
            })
          }
        }
      }
    })
  },
  // 选择时间段
  onSelectedTime: function ({
    currentTarget
  }) {
    const {
      id
    } = currentTarget.dataset;
    this.data.times.map(item => {
      if (item.id === id) {
        item.selected = true;
      } else {
        item.selected = false;
      }
    });
    this.setData({
      times: this.data.times
    })
    app.globalData.booking.timeId = id;
  },
  // 取消
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
  // 下一步
  onNext: function () {
    wx.navigateTo({
      url: '/pages/booking/step4/step4'
    })
  }
})