<script setup>
  const currentRouter = ''
</script>

<template>
  <div class="bottom-nav">
    <router-link to="/list" :class="{ active: currentRouter === '/list'}">list</router-link>
    <router-link to="/me" :class="{ active: currentRouter === '/me'}">me</router-link>
  </div>
</template>

<style scoped>
  .bottom-nav {
    position: fixed;
    bottom: 0;
    left: 0;
    width: 100%;
    display: flex;
    justify-content: space-around;
    align-items: center;
    background-color: #eee;
    height: 50px;
  }

  .active {
    color: blue;
  }
</style>