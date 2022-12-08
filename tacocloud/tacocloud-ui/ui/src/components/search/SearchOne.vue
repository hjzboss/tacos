<template>
  <div>
    <h1>查询taco</h1>
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item label="ID">
        <el-input v-model="id" placeholder="请输入id号"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="search">查询</el-button>
      </el-form-item>
    </el-form>

    <el-table
      :data="taco"
      style="width: 100%">
      <el-table-column
        prop="id"
        label="ID"
        width="180">
      </el-table-column>
      <el-table-column
        prop="name"
        label="名字"
        width="180">
      </el-table-column>
      <el-table-column
        prop="ingredients"
        label="配料">
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  name: "Search",
  data() {
    return {
      id: "",
      url: "http://localhost:8080/design/",
      taco: []
    }
  },
  methods: {
    search() {
      this.$http.get(this.url).then(res => {
        if (res.status === 200) {
          console.log(res.data);
          this.taco = res.data;
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
