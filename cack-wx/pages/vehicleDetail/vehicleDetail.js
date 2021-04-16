// pages/vehicleDetail/vehicleDetail.js
import Dialog from '../../miniprogram_npm/@vant/weapp/dialog/dialog';
import Toast from '../../miniprogram_npm/@vant/weapp/toast/toast';

const app = getApp();

Page({
  data: {
    userId: app.globalData.userInfo.id,
    currentId: 0,
    vehicle: {}
  },
  onLoad: function (options) {
    this.setData({
      currentId: options.id
    });
    this.getVehicle(options.id);
  },
  onShow: function () {
    this.getVehicle(this.data.currentId);
  },
  getVehicle: function (id) {
    const that = this;
    wx.request({
      url: `http://localhost:8081/cack/wx/vehicle/${id}`,
      method: 'GET',
      success: function ({
        data
      }) {
        if (data.code === 200) {
          that.setData({
            vehicle: data.result
          })
        }
      }
    })
  },
  // 点击编辑
  onEdit: function () {
    wx.navigateTo({
      url: `/pages/vehicleEdit/vehicleEdit?id=${this.data.currentId}`
    })
  },
  // 点击删除
  onDelete: function () {
    const that = this;
    Dialog.confirm({
      title: "删除车辆",
      message: `您确定要删除当前车牌为【${this.data.vehicle.plateNo}】的车辆吗？`,
      selector: "#van-dialog"
    }).then(() => {
      wx.request({
        url: `http://localhost:8081/cack/wx/vehicle/${that.data.currentId}`,
        method: "DELETE",
        success: function ({
          data
        }) {
          if (data.code === 200) {
            Toast.success({
              message: "删除成功",
              selector: "#vant-toast",
              onClose: () => {
                wx.navigateBack();
              }
            });
          } else {
            Toast.fail({
              message: "删除失败",
              selector: "#vant-toast"
            });
          }
        }
      })
    }).catch(() => {});
  }
})