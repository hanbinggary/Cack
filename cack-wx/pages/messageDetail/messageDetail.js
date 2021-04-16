// pages/messageDetail/messageDetail.js
Page({
  data: {
    currentId: 0,
    notify: {}
  },
  onLoad: function (options) {
    this.setData({
      currentId: options.id
    }, function () {
      this.getNotify();
    })
  },
  getNotify: function () {
    const that = this;
    wx.request({
      url: `http://localhost:8081/cack/wx/notify/${this.data.currentId}`,
      method: 'GET',
      success: function ({
        data
      }) {
        if (data.code === 200) {
          that.setData({
            notify: data.result
          })
        }
      }
    })
  }
})