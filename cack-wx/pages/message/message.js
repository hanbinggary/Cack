// pages/message/message.js
const app = getApp();

Page({
  data: {
    userId: app.globalData.userInfo.id,
    notifys: [],
    currentTab: 'unread'
  },
  onShow: function () {
    this.getNotifys();
  },
  getNotifys: function () {
    const that = this;
    wx.request({
      url: `http://localhost:8081/cack/wx/notify/query/${this.data.currentTab}/${this.data.userId}`,
      method: 'GET',
      success: function ({
        data
      }) {
        if (data.code === 200) {
          that.setData({
            notifys: data.result
          })
        }
      }
    })
  },
  onClickTabs: function ({
    detail
  }) {
    this.setData({
      currentTab: detail.name
    }, function () {
      this.getNotifys();
    })
  },
  onNotifyDetail: function ({
    currentTarget
  }) {
    const id = currentTarget.dataset.id;
    if (this.data.currentTab === 'unread') {
      wx.request({
        url: `http://localhost:8081/cack/wx/notify/${id}/readed`,
        method: 'PUT'
      })
    }
    wx.navigateTo({
      url: `/pages/messageDetail/messageDetail?id=${id}`
    })
  }
})