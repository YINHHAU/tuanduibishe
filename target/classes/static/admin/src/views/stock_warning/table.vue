<template>
  <div class="inventory-alert-container">
    <!-- 列表页面 -->
    <div v-if="currentView === 'list'">
      <div class="operation-bar">
        <el-button type="primary" @click="showAddDialog">添加预警</el-button>
      </div>

      <el-table :data="alertList" border style="width: 100%">
        <el-table-column prop="goodsName" label="商品名称"></el-table-column>
        <el-table-column prop="currentStock" label="当前库存"></el-table-column>
        <el-table-column prop="threshold" label="预警阈值"></el-table-column>
        <el-table-column label="预警状态">
          <template slot-scope="scope">
            <el-tag :type="scope.row.status | statusTypeFilter">
              {{ scope.row.status | statusTextFilter }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="220">
          <template slot-scope="scope">
            <el-button size="mini" @click="showDetail(scope.row)">详情</el-button>
            <el-button size="mini" type="warning" @click="editAlert(scope.row)">编辑</el-button>
            <el-button size="mini" type="danger" @click="deleteAlert(scope.row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <!-- 添加/编辑弹窗 -->
      <el-dialog :title="dialogTitle" :visible.sync="dialogVisible" width="30%">
        <el-form :model="form" label-width="100px">
          <el-form-item label="商品名称">
            <el-input v-model="form.goodsName"></el-input>
          </el-form-item>
          <el-form-item label="预警阈值">
            <el-input-number v-model="form.threshold" :min="0" :step="10" controls-position="right"></el-input-number>
          </el-form-item>
          <el-form-item label="备注">
            <el-input type="textarea" :rows="3" placeholder="请输入备注信息" v-model="form.remark">
            </el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="submitForm">确 定</el-button>
        </div>
      </el-dialog>
    </div>

    <!-- 详情页面 -->
    <div v-if="currentView === 'detail'" class="detail-container">
      <div class="back-btn">
        <el-button icon="el-icon-arrow-left" @click="currentView = 'list'">返回列表</el-button>
      </div>

      <el-card class="detail-card">
        <div slot="header">
          <span>预警详情 - {{ detailData.goodsName }}</span>
        </div>
        <el-descriptions :column="2" border>
          <el-descriptions-item label="商品名称">{{ detailData.goodsName }}</el-descriptions-item>
          <el-descriptions-item label="当前库存">{{ detailData.currentStock }}</el-descriptions-item>
          <el-descriptions-item label="预警阈值">{{ detailData.threshold }}</el-descriptions-item>
          <el-descriptions-item label="状态">
            <el-tag :type="detailData.status | statusTypeFilter">
              {{ detailData.status | statusTextFilter }}
            </el-tag>
          </el-descriptions-item>
          <el-descriptions-item label="创建时间">{{ detailData.createTime }}</el-descriptions-item>
          <el-descriptions-item label="最后更新时间">{{ detailData.updateTime }}</el-descriptions-item>
          <el-descriptions-item label="备注" :span="2">{{ detailData.remark }}</el-descriptions-item>
        </el-descriptions>
      </el-card>
    </div>
  </div>
</template>

<script>
const STORAGE_KEY = 'inventory-alert-data'
export default {
  filters: {
    statusTypeFilter(status) {
      const map = {
        0: 'success',   // 正常
        1: 'warning',  // 预警中
        2: 'danger'     // 缺货
      }
      return map[status] || 'info'
    },
    statusTextFilter(status) {
      const map = {
        0: '正常',
        1: '预警中',
        2: '缺货'
      }
      return map[status] || '未知状态'
    }
  },

  data() {
    return {
      currentView: 'list',
      dialogVisible: false,
      dialogType: 'add', // add/edit
      form: {
        id: null,
        goodsName: '',
        threshold: 100,
        remark: ''
      },
      alertList: [
        {
          id: 1,
          goodsName: '电子产品-A1',
          currentStock: 150,
          threshold: 200,
          status: 0,
          createTime: '2023-08-01 10:00',
          updateTime: '2023-08-05 14:30',
          remark: '常规商品库存'
        },
        {
          id: 2,
          goodsName: '包装材料-B2',
          currentStock: 45,
          threshold: 50,
          status: 1,
          createTime: '2023-08-02 09:00',
          updateTime: '2023-08-06 16:20',
          remark: '易耗品需及时补货'
        }
      ],
      detailData: {}
    }
  },

  computed: {
    dialogTitle() {
      return this.dialogType === 'add' ? '添加预警设置' : '编辑预警设置'
    }
  },

  methods: {
    loadFromLocalStorage() {
      const data = localStorage.getItem(STORAGE_KEY)
      this.alertList = data ? JSON.parse(data) : this.getDefaultData()
    },
    saveToLocalStorage() {
      localStorage.setItem(STORAGE_KEY, JSON.stringify(this.alertList))
    },
    getDefaultData() {
      return [
        {
          id: 1,
          goodsName: '商品名称1',
          currentStock: 150,
          threshold: 200,
          status: 0,
          createTime: '2023-08-01 10:00',
          updateTime: '2023-08-05 14:30',
          remark: '常规商品库存'
        },
        {
          id: 2,
          goodsName: '商品名称2',
          currentStock: 45,
          threshold: 50,
          status: 1,
          createTime: '2023-08-02 09:00',
          updateTime: '2023-08-06 16:20',
          remark: '易耗品需及时补货'
        },
        {
          id: 3,
          goodsName: '商品名称3',
          currentStock: 0,
          threshold: 100,
          status: 2,
          createTime: '2023-08-03 11:00',
          updateTime: '2023-08-07 15:10',
          remark: '库存不足，需立即补货'
        },
        {
          id: 4,
          goodsName: '商品名称4',
          currentStock: 300,
          threshold: 250,
          status: 0,
          createTime: '2023-08-04 12:00',
          updateTime: '2023-08-08 17:00',
          remark: '库存充足，无需补货'
        },
        {
          id: 5,
          goodsName: '商品名称5',
          currentStock: 80,
          threshold: 150,
          status: 1,
          createTime: '2023-08-05 13:00',
          updateTime: '2023-08-09 18:30',
          remark: '库存接近阈值，需注意补货'
        }
      ]
    },
    showDetail(row) {
      this.$router.push({ name: 'stock_warning_view', params: { id: row.id } })
    },
    showAddDialog() {
      this.dialogType = 'add'
      this.form = {
        id: null,
        goodsName: '',
        threshold: 100,
        remark: ''
      }
      this.dialogVisible = true
    },

    editAlert(row) {
      this.dialogType = 'edit'
      this.form = { ...row }
      this.dialogVisible = true
    },

    submitForm() {
      // 修改后的提交方法
      if (this.dialogType === 'add') {
        const newItem = {
          ...this.form,
          id: Date.now(),
          currentStock: 0,
          status: 2,
          createTime: new Date().toISOString(),
          updateTime: new Date().toISOString()
        }
        this.alertList.push(newItem)
        this.$message.success('添加预警成功')

      } else {
        // 编辑逻辑
        this.$message.success('更新预警成功')
      }
      this.saveToLocalStorage()
      this.dialogVisible = false
    },

    deleteAlert(id) {
      this.alertList = this.alertList.filter(item => item.id !== id)
      this.saveToLocalStorage()
      this.$message.success('删除成功')
    }
  },
  created() {
    this.loadFromLocalStorage()
  },
}
</script>

<style scoped>
.inventory-alert-container {
  padding: 20px;
}

.operation-bar {
  margin-bottom: 20px;
}

.detail-container {
  padding: 20px;
}

.back-btn {
  margin-bottom: 20px;
}

.detail-card {
  max-width: 800px;
  margin: 0 auto;
}

.el-descriptions {
  margin-top: 20px;
}

.el-tag {
  margin-right: 10px;
}
</style>