<script setup>
import { ref, onMounted } from 'vue'
import { CaretBottom, CaretTop, Warning } from '@element-plus/icons-vue'
import { getCardData, getBillList, addBillInfo, delBillInfo } from '../service/list/index'
import { ElMessage, ElMessageBox } from 'element-plus'
import dayjs from 'dayjs'

// 日期筛选框
const currentDay = ref(new Date())
const disabledDate = (time) => {
  return time.getTime() > Date.now()
}
const shortcuts = [
  {
    text: 'Today',
    value: new Date(),
  },
  {
    text: 'Yesterday',
    value: () => {
      const date = new Date()
      date.setTime(date.getTime() - 3600 * 1000 * 24)
      return date
    },
  }
]

// 人员选择器
const numbers = [
  {
    value: '所有',
    label: '所有'
  },
  {
    value: 'gcl',
    label: 'gcl'
  },
  {
    value: 'sz',
    label: 'sz'
  }
]
const number = ref('所有')

// 搜索框调用接口
let cardData = ref({
  currentDayPay: 0,
  compareDayPay: 0,
  currentMonthPay: 0,
  compareMonthPay: 0,
  currentMonthIncome: 0,
  compareMonthIncome: 0
})
let tableData = ref([
  {
    billId: '',
    payDate: '',
    billType: '',
    payer: '',
    payee: '',
    money: '',
    remark: ''
  }
])
const filterMethod = () => {
  let cardParams = {
    date: dayjs(currentDay.value).format('YYYY-MM-DD'),
    relatedBy: number.value == '所有' ? null : number.value
  }
  getCardData(cardParams).then(res => {
    const { data } = res
    cardData.value = data
  })
  let tableParams = {
    payDateBegin: dayjs(currentDay.value).format('YYYY-MM-DD'),
    payDateEnd: dayjs(currentDay.value).format('YYYY-MM-DD'),
    relatedBy: number.value == '所有' ? null : number.value
  }
  getBillList(tableParams).then(res => {
    const { data } = res
    tableData.value = data
  })
}

// 表格校验
const filterPayer = [
  { text: 'gcl', value: 'gcl' },
  { text: 'sz', value: 'sz' }
]
const filterPayerMethod = (value, row) => {
  return row.payer === value
}
const filterPayee = [
  { text: 'gcl', value: 'gcl' },
  { text: 'sz', value: 'sz' }
]
const filterPayeeMethod = (value, row) => {
  return row.payee === value
}

// 修改按钮逻辑
const handleEdit = (index, row) => {
  detailDialogVisible.value = true
  billData.value = row
}

// 删除按钮逻辑
const handleDelete = (index, row) => {
  ElMessageBox.confirm("是否确认删除", "提示").then(() => {
    const delParam = {billId: row.billId}
    delBillInfo(delParam).then(res => {
      filterMethod()
    })
  })
}

// 新增/修改弹窗
const detailDialogVisible = ref(false)

const addDialog = () => {
  detailDialogVisible.value = true
}

let billData = ref({
  billId: '',
  billType: '',
  payer: '',
  payee: '',
  payType: '',
  money: '',
  payDate: ''
})

const add = () => {
  billData.value.payDate = dayjs(currentDay.value).format('YYYY-MM-DD')
  addBillInfo(billData.value).then(res => {
    ElMessageBox.alert('更新成功')
    filterMethod()
  })
  detailDialogVisible.value = false
}

onMounted(() => {
  filterMethod()
})

</script>

<template>
  <el-row :gutter="10">
    <el-col :span="12">
      <el-date-picker v-model="currentDay" type="date" size="small" placeholder="选择日期" :disabled-date="disabledDate"
        :shortcuts="shortcuts" style="width:100%" @change="filterMethod" />
    </el-col>
    <el-col :span="12">
      <el-select v-model="number" size="small" @change="filterMethod" style="width:100%">
        <el-option v-for="item in numbers" :key="item.value" :label="item.label" :value="item.value" />
      </el-select>
    </el-col>
  </el-row>
  <el-row :gutter="10">
    <el-col :span="8">
      <div class="statistic-card">
        <el-statistic :value="cardData.currentDayPay">
          <template #title>
            <div style="display: inline-flex; align-items: center">
              当日支出
            </div>
          </template>
        </el-statistic>
        <div class="statistic-footer">
          <div class="footer-item">
            <span>较昨天</span>
            <span class="green">
              {{ (cardData.compareDayPay * 100).toFixed(2) }}%
              <el-icon>
                <CaretTop />
              </el-icon>
            </span>
          </div>
        </div>
      </div>
    </el-col>
    <el-col :span="8">
      <div class="statistic-card">
        <el-statistic :value="cardData.currentMonthPay">
          <template #title>
            <div style="display: inline-flex; align-items: center">
              当月支出
            </div>
          </template>
        </el-statistic>
        <div class="statistic-footer">
          <div class="footer-item">
            <span>较上月</span>
            <span class="red">
              {{ (cardData.compareMonthPay * 100).toFixed(2) }}%
              <el-icon>
                <CaretBottom />
              </el-icon>
            </span>
          </div>
        </div>
      </div>
    </el-col>
    <el-col :span="8">
      <div class="statistic-card">
        <el-statistic :value="cardData.currentMonthIncome" title="New transactions today">
          <template #title>
            <div style="display: inline-flex; align-items: center">
              当月收入
            </div>
          </template>
        </el-statistic>
        <div class="statistic-footer">
          <div class="footer-item">
            <span>较上月</span>
            <span class="green">
              {{ (cardData.compareMonthIncome * 100).toFixed(2) }}%
              <el-icon>
                <CaretTop />
              </el-icon>
            </span>
          </div>
        </div>
      </div>
    </el-col>
  </el-row>
  <el-row>
    <el-button size="small" @click="addDialog">新增一条</el-button>
  </el-row>
  <el-row :gutter="10">
    <el-table size="small" :data="tableData" stripe fit style="width: 100%">
      <el-table-column prop="payDate" label="时间" width="90" sortable />
      <el-table-column prop="billType" label="类型" width="40" />
      <el-table-column prop="payer" label="付" width="50" :filters="filterPayer" :filter-method="filterPayerMethod" />
      <el-table-column prop="payee" label="收" width="50" :filters="filterPayee" :filter-method="filterPayeeMethod" />
      <el-table-column prop="money" label="金额" width="70" sortable />
      <el-table-column label="操作" width="100" fixed="right">
        <template #default="scope">
          <el-button size="small" @click="handleEdit(scope.$index, scope.row)">U</el-button>
          <el-button size="small" type="danger" @click="handleDelete(scope.$index, scope.row)">D</el-button>
        </template>
      </el-table-column>
    </el-table>
  </el-row>

  <el-dialog v-model="detailDialogVisible" title="操作明细" width="300">
    <el-form ref="addForm" :model="billData" label-position="left" label-width="80px">
      <el-form-item label="账单类型" prop="billType">
        <el-radio-group v-model="billData.billType">
          <el-radio label="01">支出</el-radio>
          <el-radio label="02">收入</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="付款人" prop="payer">
        <el-input v-model="billData.payer"></el-input>
      </el-form-item>
      <el-form-item label="收款人" prop="payee">
        <el-input v-model="billData.payee"></el-input>
      </el-form-item>
      <el-form-item label="交易类型" prop="payType">
        <el-input v-model="billData.payType"></el-input>
      </el-form-item>
      <el-form-item label="金额" prop="money">
        <el-input v-model="billData.money"></el-input>
      </el-form-item>
      <el-button type="primary" @click="add">更新</el-button>
    </el-form>
  </el-dialog>
</template>

<style scoped>
.el-row {
  margin-bottom: 20px;
}

.el-statistic {
  --el-statistic-content-font-size: 20px;
}

.statistic-card {
  height: 100%;
  padding: 2px;
  background-color: var(--el-bg-color-overlay);
}

.statistic-footer {
  display: flex;
  justify-content: space-between;
  align-items: center;
  flex-wrap: wrap;
  font-size: 8px;
  color: var(--el-text-color-regular);
  margin-top: 8px;
}

.statistic-footer .footer-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.statistic-footer .footer-item span:last-child {
  display: inline-flex;
  align-items: center;
  margin-left: 2px;
}

.green {
  color: var(--el-color-success);
}

.red {
  color: var(--el-color-error);
}
</style>