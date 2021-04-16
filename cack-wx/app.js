// app.js
App({
  onLaunch() {
    // 判断用户是否登录
    const userInfo = wx.getStorageSync('userInfo');
    if (typeof userInfo === 'string') {
      wx.navigateTo({
        url: '/pages/login/login',
      });
    } else {
      this.globalData.userInfo = userInfo;
      wx.switchTab({
        url: '/pages/index/index',
      })
    }
  },
  globalData: {
    userInfo: null,
    booking: {
      vehicleId: -1,
      date: '',
      timeId: -1,
      couponId: -1
    }
  }
})