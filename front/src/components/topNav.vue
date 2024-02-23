<script setup>
import { useRouter } from 'vue-router'

const router = useRouter()

const onBack = () => {
  router.go(-1)
}
</script>

<template>
  <el-page-header @back="onBack"></el-page-header>
</template>