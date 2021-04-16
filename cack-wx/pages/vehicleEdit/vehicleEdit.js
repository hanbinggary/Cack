// pages/vehicleAdd/vehicleAdd.js
import {
  formatTime
} from '../../utils/util';
import Toast from '../../miniprogram_npm/@vant/weapp/toast/toast';

const app = getApp();

Page({
  data: {
    userId: app.globalData.userInfo.id,
    currentId: 0,
    plateNo: '',
    odometer: 0,
    type: '',
    useCharacter: '',
    engineNo: '',
    vin: '',
    plateNoErrorMsg: '',
    engineNoErrorMsg: '',
    vinErrorMsg: '',
    registerDate: formatTime(Date.now()),
    showType: false,
    vehicleTypes: [],
    showUseCharacter: false,
    vehicleUseCharacters: [],
    showRegisterDate: false,
    currentDate: new Date().getTime(),
    minDate: new Date(2000, 1, 1).getTime(),
    formatter(type, value) {
      if (type === 'year') {
        return `${value}年`;
      } else if (type === 'month') {
        return `${value}月`;
      }
      return `${value}日`;
    },
  },
  onLoad: function (options) {
    this.setData({
      currentId: options.id
    });
    this.getVehicle();
    this.getVehicleTypes();
    this.getVehicleUseCharacters();
  },
  getVehicle: function () {
    const that = this;
    wx.request({
      url: `http://localhost:8081/cack/wx/vehicle/${this.data.currentId}`,
      method: 'GET',
      success: function ({
        data
      }) {
        const {
          code,
          result
        } = data;
        if (code === 200) {
          that.setData({
            plateNo: result.plateNo,
            odometer: result.odometer,
            type: result.vehicleType,
            useCharacter: result.useCharacter,
            vin: result.vin,
            engineNo: result.engineNo,
            registerDate: result.registerDate,
            currentDate: new Date(result.registerDate).getTime()
          });
        }
      }
    })
  },
  getVehicleTypes: function () {
    const that = this;
    wx.request({
      url: 'http://localhost:8081/cack/wx/vehicle/type',
      method: 'GET',
      success: function ({
        data
      }) {
        if (data.code === 200) {
          that.setData({
            vehicleTypes: data.result
          })
        }
      }
    })
  },
  getVehicleUseCharacters: function () {
    const that = this;
    wx.request({
      url: 'http://localhost:8081/cack/wx/vehicle/use/character',
      method: 'GET',
      success: function ({
        data
      }) {
        if (data.code === 200) {
          that.setData({
            vehicleUseCharacters: data.result
          })
        }
      }
    })
  },
  // 点击车辆类型
  onClickType: function () {
    this.setData({
      showType: true
    })
  },
  onCancelType: function () {
    this.setData({
      showType: false
    });
  },
  onSelectType: function ({
    detail
  }) {
    this.setData({
      type: detail.name,
      showType: false
    })
  },
  // 点击使用性质
  onClickUseCharacter: function () {
    this.setData({
      showUseCharacter: true
    })
  },
  onCancelUseCharacter: function () {
    this.setData({
      showUseCharacter: false
    })
  },
  onSelectUseCharacter: function ({
    detail
  }) {
    this.setData({
      useCharacter: detail.name,
      showUseCharacter: false
    })
  },
  // 点击注册日期
  onClickRegisterDate: function () {
    this.setData({
      showRegisterDate: true
    })
  },
  onCloseRegisterDate: function () {
    this.setData({
      showRegisterDate: false
    })
  },
  onConfirmRegisterDate: function ({
    detail
  }) {
    this.setData({
      registerDate: formatTime(detail),
      showRegisterDate: false
    })
  },
  // 修改总里程数
  onChangeOdometer: function ({
    detail
  }) {
    this.setData({
      odometer: detail
    })
  },
  onChangePlateNo: function ({
    detail
  }) {
    if (detail.length === 0) {
      this.setData({
        plateNoErrorMsg: '车牌号不能为空'
      });
    } else {
      this.setData({
        plateNoErrorMsg: ''
      });
    }
  },
  onChangeEngineNo: function ({
    detail
  }) {
    if (detail.length === 0) {
      this.setData({
        engineNoErrorMsg: '发动机代码不能为空'
      });
    } else {
      this.setData({
        engineNoErrorMsg: ''
      });
    }
  },
  onChangeVin: function ({
    detail
  }) {
    if (detail.length === 0) {
      this.setData({
        vinErrorMsg: '车辆识别代码不能为空'
      });
    } else {
      this.setData({
        vinErrorMsg: ''
      });
    }
  },
  // 点击修改按钮
  onUpdateBtn: function () {
    this.setData({
      plateNoErrorMsg: '',
      engineNoErrorMsg: '',
      vinErrorMsg: ''
    });
    if (this.data.plateNo.length === 0) {
      this.setData({
        plateNoErrorMsg: '车牌号不能为空'
      })
    } else if (this.data.engineNo.length === 0) {
      this.setData({
        engineNoErrorMsg: '发动机代码不能为空'
      })
    } else if (this.data.vin.length === 0) {
      this.setData({
        vinErrorMsg: '车辆识别代码不能为空'
      })
    } else {
      this.handleUpdateVehicle();
    }
  },
  // 添加车辆
  handleUpdateVehicle: function () {
    wx.request({
      url: 'http://localhost:8081/cack/wx/vehicle',
      method: 'PUT',
      data: {
        id: this.data.currentId,
        plateNo: this.data.plateNo,
        odometer: this.data.odometer,
        useCharacter: this.data.useCharacter,
        vehicleType: this.data.type,
        vin: this.data.vin,
        engineNo: this.data.engineNo,
        registerDate: this.data.registerDate,
        userId: this.data.userId
      },
      success: function ({
        data
      }) {
        if (data.code === 200) {
          Toast.success({
            message: "成功修改车辆",
            selector: '#van-toast',
            onClose: () => {
              wx.navigateBack({
                delta: 1,
              })
            }
          })

        } else {
          Toast.fail({
            message: '修改车辆失败',
            selector: '#van-toast'
          })
        }
      }
    })
  }
})