<template>
<div>
  <h1>查询taco</h1>
  <input type="text" v-model="id">
  <input type="button" @click="search" value="查询">
  <h3>{{msg}}</h3>
</div>
</template>

<script>
export default {
  name: "Search",
  data(){
    return{
      id:"",
      url:"http://localhost:8080/design/",
      msg:""
    }
  },
  methods:{
    search(){
      this.$http.get(this.url+this.id).then(res=>{
        if(res.status == 200){
          this.msg = res.data;
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
