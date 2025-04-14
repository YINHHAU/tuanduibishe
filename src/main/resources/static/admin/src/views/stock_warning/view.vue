<template>
  <div class="goods-detail">
    <el-button type="primary" icon="el-icon-arrow-left" @click="$router.push({ name: 'stock_warning_table' })">
      返回列表
    </el-button>
    <el-card v-if="goodsData" class="detail-card">
      <div slot="header">
        <span>商品预警详情 - {{ goodsData.goodsName }}</span>
        <el-tag :type="goodsData.status | statusTypeFilter" style="margin-left: 15px">
          {{ goodsData.status | statusTextFilter }}
        </el-tag>
      </div>

      <el-descriptions :column="2" border>
        <el-descriptions-item label="商品ID">{{ goodsData.id }}</el-descriptions-item>
        <el-descriptions-item label="商品名称">{{ goodsData.goodsName }}</el-descriptions-item>
        <el-descriptions-item label="当前库存">
          <span :class="stockClass">{{ goodsData.currentStock }}</span>
        </el-descriptions-item>
        <el-descriptions-item label="预警阈值">{{ goodsData.threshold }}</el-descriptions-item>
        <el-descriptions-item label="创建时间">{{ formatTime(goodsData.createTime) }}</el-descriptions-item>
        <el-descriptions-item label="最后更新时间">{{ formatTime(goodsData.updateTime) }}</el-descriptions-item>
        <el-descriptions-item label="备注" :span="2">
          {{ goodsData.remark || '无备注信息' }}
        </el-descriptions-item>
      </el-descriptions>
    </el-card>

    <el-empty v-else description="未找到商品信息" image="/static/images/empty-box.png"></el-empty>
  </div>
</template>

<script>
import dayjs from 'dayjs'

export default {
  props: {
    id: {
      type: [Number, String],
      required: true
    }
  },

  data() {
    return {
      goodsData: {
        createTime:"2023-08-02 09:00",
        currentStock:45,
        goodsName:"商品名称2",
        id:2,
        remark:"易耗品需及时补货",
        status:1,
        threshold:50,
        updateTime:"2023-08-06 16:20"
      }
    }
  },

  computed: {
    stockClass() {
      if (!this.goodsData) return ''
      return {
        'warning-stock': this.goodsData.currentStock <= this.goodsData.threshold,
        'danger-stock': this.goodsData.currentStock <= 0
      }
    }
  },

  created() {
    this.loadGoodsDetail()
  },

  methods: {
    loadGoodsDetail() {
      const data = JSON.parse(localStorage.getItem('inventory-alert-data')) || this.goodsData
      const targetId = typeof this.$route.params.id === 'string' ? parseInt(this.$route.params.id) : this.$route.params.id
      this.goodsData = data.find(item => item.id === targetId) || null

      if (!this.goodsData) {
        this.$message.error('未找到该商品的预警信息')
      }
    },

    formatTime(isoString) {
      return dayjs(isoString).format('YYYY-MM-DD HH:mm:ss')
    }
  },

  filters: {
    statusTypeFilter(status) {
      const map = { 0: 'success', 1: 'warning', 2: 'danger' }
      return map[status] || 'info'
    },
    statusTextFilter(status) {
      const map = { 0: '正常', 1: '预警中', 2: '缺货' }
      return map[status] || '未知状态'
    }
  },

  watch: {
    '$route.params.id'(newVal) {
      if (newVal) {
        this.id = typeof newVal === 'string' ? parseInt(newVal) : newVal
        this.loadGoodsDetail()
      }
    }
  }
}
</script>

<style scoped>
.goods-detail {
  padding: 20px;
  max-width: 1200px;
  margin: 0 auto;
}

.detail-card {
  margin-top: 20px;
}

.warning-stock {
  color: #e6a23c;
  font-weight: bold;
}

.danger-stock {
  color: #f56c6c;
  font-weight: bold;
}
</style>