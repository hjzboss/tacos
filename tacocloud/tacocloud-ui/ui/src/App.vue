<template>
  <div id="app">
    <el-container>
      <el-header>
        <el-menu
          :default-active="activeIndex"
          class="el-menu-demo"
          mode="horizontal"
          @select="handleSelect"
          background-color="#545c64"
          text-color="#fff"
          active-text-color="#ffd04b">
          <el-menu-item index="/home">主页</el-menu-item>
          <el-menu-item index="/search">订单查询</el-menu-item>
          <el-menu-item index="/recent">最近的设计</el-menu-item>
          <el-menu-item index="/order">创建订单</el-menu-item>
        </el-menu>
      </el-header>
      <el-main><router-view/></el-main>
    </el-container>

  </div>
</template>

<script>
export default {
  name: 'App',
  data() {
    return {
      activeIndex: '/home',
    };
  },
  methods: {
    handleSelect(key, keyPath) {
        this.$router.push(key);
    }
  }
}
</script>

<style>
</style>
