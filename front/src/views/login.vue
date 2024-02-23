<script setup>
  import { reactive, ref } from 'vue'
  import { useRouter } from 'vue-router'

  const router = useRouter()

  const formData = reactive({ 
    username: '', 
    password: '' 
  })

  const loginForm = ref()

  const loginRules = reactive({
    username: [
      { required: true, message: '请输入用户名', trigger: 'blur'}
    ],
    password: [
      { required: true, message: '请输入密码', trigger: 'blur'}
    ]
  })

  // 登录逻辑
  const login = () => {
    if (!loginForm) return 
    console.log(loginForm.value)
    loginForm.value.validate((valid, fields) => {
      if (valid) {
        //todo: 登录逻辑
        console.log('login successful')
        router.push('/list')
      }
    })
  }

</script>

<template>
  <div class="login-container">
    <el-form ref="loginForm" :model="formData" :rules="loginRules" status-icon label-position="left" label-width="80px">
      <el-form-item label="用户名" prop="username">
        <el-input v-model="formData.username"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input type="password" v-model="formData.password"></el-input>
      </el-form-item>
      <el-button type="primary" @click="login">登录</el-button>
    </el-form>
  </div>
</template>

<style scoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
}
</style>