// pages/order/order.js

const app = getApp();

Page({
  data: {
    userId: app.globalData.userInfo.id,
    allOrders: [],
    currentStatus: -1,
    loading: false,
    pagination: {
      current: 1,
      pageSize: 5,
      total: 0,
      pages: 0
    }
  },
  onLoad: function () {
    this.getOrders();
  },
  onShow: function () {
    this.setData({
      pagination: {
        current: 1,
        pageSize: 5,
        total: 0,
        pages: 0
      }
    });
    this.getOrders();
  },
  // 触底刷新
  onReachBottom: function () {
    if (this.data.pagination.current < this.data.pagination.pages) {
      const that = this;
      wx.request({
        url: `http://localhost:8081/cack/wx/booking/${this.data.userId}/all`,
        method: 'GET',
        data: {
          status: this.data.currentStatus,
          pageNum: this.data.pagination.current,
          pageSize: this.data.pagination.pageSize
        },
        success: function ({
          data
        }) {
          const {
            code,
            result
          } = data;
          if (code === 200) {
            that.setData({
              allOrders: [...that.data.allOrders, ...result.list],
              pagination: {
                ...that.data.pagination,
                pages: result.pages,
                current: that.data.pagination.current + 1
              }
            })
          }
        }
      })
    }
  },
  // 下拉刷新
  onPullDownRefresh: function () {
    this.setData({
      pagination: {
        current: 1,
        pageSize: 5,
        total: 0,
        pages: 0
      }
    }, function () {
      this.getOrders();
    })
  },
  getOrders: function () {
    this.setData({
      loading: true
    });
    const that = this;
    wx.request({
      url: `http://localhost:8081/cack/wx/booking/${this.data.userId}/all`,
      method: 'GET',
      data: {
        status: this.data.currentStatus,
        pageNum: this.data.pagination.current,
        pageSize: this.data.pagination.pageSize
      },
      success: function ({
        data
      }) {
        const {
          code,
          result
        } = data;
        if (code === 200) {
          that.setData({
            allOrders: result.list,
            pagination: {
              ...that.data.pagination,
              pages: result.pages
            },
            loading: false
          })
        }
      }
    })
  },
  onClickTabs: function ({
    detail
  }) {
    const {
      name
    } = detail;
    let status;
    if (name === 'all') {
      status = -1;
    } else if (name === 'waiting') {
      status = 1;
    } else if (name === 'checking') {
      status = 2;
    }
    this.setData({
      currentStatus: status,
      pagination: {
        current: 1,
        pageSize: 5,
        total: 0,
        pages: 0
      }
    }, function () {
      this.getOrders()
    })
  },
  onClickOrder: function ({
    currentTarget
  }) {
    const {
      id
    } = currentTarget.dataset;
    wx.navigateTo({
      url: `/pages/orderDetail/orderDetail?id=${id}`,
    })
  }
})