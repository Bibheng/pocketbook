<script setup>
import bottomNav from '../components/bottomNav.vue'
import topNav from '../components/topNav.vue'
</script>

<template>
  <div>
    <!-- <el-header class="header"><top-nav></top-nav></el-header> -->
    <el-main><router-view></router-view></el-main>
    <!-- <el-footer><bottom-nav></bottom-nav></el-footer> -->
  </div>
</template>

<style scoped>
  .header {
    height: 20px
  }
</style>