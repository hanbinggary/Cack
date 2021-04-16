// pages/booking/step4/step4.js
import Toast from '../../../miniprogram_npm/@vant/weapp/toast/toast';
const app = getApp();

Page({
  data: {
    coupons: [],
    bookingLoading: false
  },
  onLoad: function () {
    this.getCoupons();
  },
  getCoupons: function () {
    const that = this;
    wx.request({
      url: `http://localhost:8081/cack/wx/coupon/unused/${app.globalData.userInfo.id}`,
      method: 'GET',
      success: function ({
        data
      }) {
        if (data.code === 200) {
          const coupons = data.result;
          if (coupons) {
            coupons.map(coupon => coupon.selected = false);
            that.setData({
              coupons
            })
          }
        }
      }
    })
  },
  // 选择优惠券
  onSelectedCoupon: function ({
    currentTarget
  }) {
    const {
      id
    } = currentTarget.dataset;
    this.data.coupons.map(coupon => {
      if (coupon.id === id) {
        coupon.selected = true;
      } else {
        coupon.selected = false;
      }
    });
    app.globalData.booking.couponId = id;
    this.setData({
      coupons: this.data.coupons
    });
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
  // 点击预约
  onBooking: function () {
    const that = this;
    this.setData({
      bookingLoading: true
    });
    this.onBookingLoading();
    wx.request({
      url: 'http://localhost:8081/cack/wx/booking',
      method: 'POST',
      data: {
        ...app.globalData.booking,
        userId: app.globalData.userInfo.id
      },
      success: function ({
        data
      }) {
        if (data.code === 200) {
          that.setData({
            bookingLoading: false
          });
          Toast.success({
            message: '预约成功',
            selector: '#van-toast',
            onClose: () => {
              wx.switchTab({
                url: '/pages/index/index'
              })
            }
          })
        } else if (data.code === 400 || data.code === 500) {
          Toast.fail({
            message: data.message,
            selector: '#van-toast',
          });
        }

      }
    })
  },
  // 预约加载中
  onBookingLoading: function () {
    const that = this;
    Toast.loading({
      duration: 0, // 持续展示 toast
      forbidClick: true,
      message: '预约中...',
      selector: '#van-toast',
    });
    const timerID = setInterval(() => {
      if (!that.data.bookingLoading) {
        clearInterval(timerID);
        Toast.clear();
      }
    }, 500);
  }
})