// pages/booking/step2/step2.js
const app = getApp();

Page({
  data: {
    dates: [],
  },
  onLoad: function () {
    this.getDates();
  },
  getDates: function () {
    const that = this;
    wx.request({
      url: 'http://localhost:8081/cack/wx/booking/time/date',
      method: 'GET',
      success: function ({
        data
      }) {
        if (data.code === 200) {
          const dates = data.result;
          if (dates && dates.length > 0) {
            dates.map(date => {
              date.selected = false;
            });
            if (dates.length > 0) {
              dates[0].selected = true;
            }
            app.globalData.booking.date = dates[0].date;
            that.setData({
              dates
            })
          }
        }
      }
    })
  },
  // 选择日期
  onSelectedDate: function ({
    currentTarget
  }) {
    const {
      date
    } = currentTarget.dataset;
    this.data.dates.map(item => {
      if (item.date === date) {
        item.selected = true;
      } else {
        item.selected = false;
      }
    });
    this.setData({
      dates: this.data.dates
    })
    app.globalData.booking.date = date;
  },
  // 取消选择
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
      url: `/pages/booking/step3/step3?date=${app.globalData.booking.date}`,
    })
  }
})