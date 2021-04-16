// pages/orderDetail/orderDetail.js
import Dialog from '../../miniprogram_npm/@vant/weapp/dialog/dialog';
import Toast from '../../miniprogram_npm/@vant/weapp/toast/toast';

const app = getApp();

Page({
  data: {
    userId: app.globalData.userInfo.id,
    currentId: 0,
    booking: {}
  },
  onLoad: function (options) {
    this.setData({
      currentId: options.id
    });
    this.getOrderDetail(options.id);
  },
  getOrderDetail: function (id) {
    const that = this;
    wx.request({
      url: `http://localhost:8081/cack/wx/booking/${id}`,
      method: 'GET',
      success: function ({
        data
      }) {
        const {
          code,
          result
        } = data;
        if (code === 200) {
          if (result.status === 4) {
            result.statusText = "已取消";
            result.statusMsg = "您的订单已取消，将不会安排车检";
          } else if (result.status === 3) {
            result.statusText = "已完成";
            result.statusMsg = "您的订单已完成，顺利完成车辆年检";
          } else if (result.status === 2) {
            result.statusText = "车检中";
            result.statusMsg = "您的车辆在进行车检中，请耐心等待...";
          } else if (result.status === 1) {
            result.statusText = "待车检";
            result.statusMsg = "您的车辆已经成功预约车检";
          } else if (result.status === 0) {
            result.statusText = "强制取消";
            result.statusMsg = result.cancelReason;
          }

          that.setData({
            booking: result
          })
        }
      }
    })
  },
  // 点击取消预约按钮
  onCancelBooking: function () {
    Dialog.confirm({
        title: '取消预约',
        message: '您确定取消当前车检预约吗？',
      })
      .then(() => {
        wx.request({
          url: `http://localhost:8081/cack/wx/booking/${this.data.currentId}/${this.data.userId}/cancel`,
          method: 'PUT',
          success: function ({
            data
          }) {
            if (data.code === 200) {
              Toast.success({
                message: '取消成功',
                selector: '#van-toast'
              });
              wx.navigateBack({
                delta: 1,
              })
            }
          }
        })
      }).catch(() => {});
  }
})