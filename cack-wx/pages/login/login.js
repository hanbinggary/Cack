// pages/login/login.js
import Toast from '../../miniprogram_npm/@vant/weapp/toast/toast';

const app = getApp();

Page({
  onLogin: function () {
    wx.getUserProfile({
      desc: '用于完善用户信息',
      lang: 'zh_CN',
      success: function ({
        userInfo
      }) {
        wx.login({
          timeout: 5000,
          success: function ({
            code
          }) {
            wx.request({
              url: 'http://localhost:8081/cack/wx/login',
              method: 'POST',
              data: {
                code,
                ...userInfo
              },
              success: function ({
                data
              }) {
                const {
                  code,
                  message,
                  result
                } = data;
                if (code === 200) {
                  wx.setStorageSync('userInfo', {
                    id: result,
                    ...userInfo
                  });
                  // 设置为全局数据
                  app.globalData.userInfo = wx.getStorageSync('userInfo');
                  wx.switchTab({
                    url: '/pages/index/index'
                  });
                } else if (code === 400) {
                  Toast.fail({
                    message: message,
                    selector: '#van-toast'
                  });
                } else {
                  Toast.fail({
                    message: '登录失败，请重试',
                    selector: '#van-toast'
                  });
                }
              }
            })
          }
        })

      }
    })
  }
})