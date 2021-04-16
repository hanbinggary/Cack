// index.js
import Toast from '../../miniprogram_npm/@vant/weapp/toast/toast';
import {
  getDistance
} from '../../utils/util';

const app = getApp();

Page({
  data: {
    userInfo: app.globalData.userInfo,
    name: '重新定位',
    distance: -1,
    readyCheckList: [],
    readyCheckLoading: false,
    readyCheckTotal: 0,
    checkingTotal: 0
  },
  onShow() {
    this.hasLogin(); // 判断是否登录，未登录返回登录页面
    this.getStationInfo();
    if (this.data.userInfo && this.data.userInfo.id) {
      // 获取用户待车检或者车检中信息
      this.getReadyCheckInfo();
      this.getReadyAndCheckingTotal();
    }
  },
  // 下拉刷新
  onPullDownRefresh: function () {
    this.getStationInfo();
    if (this.data.userInfo && this.data.userInfo.id) {
      // 获取用户待车检或者车检中信息
      this.getReadyCheckInfo();
      this.getReadyAndCheckingTotal();
    }
  },
  // 获取用户信息
  hasLogin: function () {
    const userInfo = wx.getStorageSync('userInfo');
    if (typeof userInfo === 'string') {
      Toast.fail({
        message: '未登录，请登录',
        selector: '#van-toast'
      })
      wx.redirectTo({
        url: '/pages/login/login'
      })
    }
  },
  // 获取验车站信息
  getStationInfo: function () {
    this.getStation().then(result => {
      const location = wx.getStorageSync('location');
      if (typeof location === 'string') {
        this.setData({
          name: '重新定位',
          distance: -1
        })
      } else {
        this.setData({
          name: location.name,
          distance: getDistance({
            longitude: result.longitude,
            latitude: result.latitude
          }, {
            longitude: location.longitude,
            latitude: location.latitude
          })
        })
      }
    });
  },
  getStation: function () {
    return new Promise(resolve => {
      let station = wx.getStorageSync('station');
      if (typeof station !== 'string') resolve(station);
      wx.request({
        url: 'http://localhost:8081/cack/wx/station',
        method: 'GET',
        success: function ({
          data
        }) {
          const {
            code,
            result
          } = data;
          if (code === 200) {
            wx.setStorageSync('station', {
              ...result
            });
            resolve(result);
          }
        }
      })
    })
  },
  getReadyAndCheckingTotal: function () {
    const that = this;
    wx.request({
      url: `http://localhost:8081/cack/wx/booking/count/status/ready/checking/${this.data.userInfo.id}`,
      method: 'GET',
      success: function ({
        data
      }) {
        if (data.code === 200) {
          that.setData({
            checkingTotal: data.result.checkingTotal,
            readyCheckTotal: data.result.readyCheckTotal
          })
        }
      }
    })
  },
  // 获取定位信息
  onLocation: function () {
    const that = this;
    wx.chooseLocation({
      success: function (result) {
        if (result.name.length === 0) {
          Toast.fail({
            message: '未选中地址',
            selector: '#van-toast'
          });
        } else {
          wx.setStorageSync('location', {
            addr: result.address,
            name: result.name,
            longitude: result.longitude,
            latitude: result.latitude
          });
          const station = wx.getStorageSync('station');
          that.setData({
            name: result.name,
            distance: getDistance({
              longitude: station.longitude,
              latitude: station.latitude
            }, {
              longitude: result.longitude,
              latitude: result.latitude
            })
          })
        }
      }
    });
  },
  // 获取用户待车检或者车检中信息
  getReadyCheckInfo: function () {
    this.setData({
      readyCheckLoading: true
    })
    const that = this;
    wx.request({
      url: `http://localhost:8081/cack/wx/booking/${this.data.userInfo.id}/ready/check`,
      method: 'GET',
      success: function ({
        data
      }) {
        if (data.code === 200) {
          that.setData({
            readyCheckList: data.result,
            readyCheckLoading: false
          })
        }
      }
    })
  },
  // 点击预约车检按钮
  onBooking: function () {
    wx.navigateTo({
      url: '/pages/booking/booking',
    })
  }
})