<template>
  <div>
    <el-container>
      <el-header>
        <div align="center">
          下面这些是最近一些顾客自己设计的taco，您可以根据自己的需要参考或者直接预订和其一样的taco
        </div>
      </el-header>

      <el-main>
        <el-row>
          <el-col :span="4" v-for="(o,index) in tacos.slice(0,3)" :offset="1" :key="index" push="4">
            <el-card>
              <img :src="images" class="image">
              <div style="padding: 20px;">
                <span>{{ o.name }}</span>
                <el-divider></el-divider>
                <div class="bottom clearfix">
                  配料
                  <ul>
                    <li v-for="item in o.ingredients">{{ item.name }}</li>
                  </ul>
                  <el-button type="text" class="button">预订这个taco</el-button>
                </div>
              </div>
            </el-card>
          </el-col>
        </el-row>


      </el-main>
      <el-footer>
        <div align="center">
          <el-pagination
            layout="prev, pager, next"
            :total="6"
            :page-sizes="[3,6,9]"
            :page-size="3"
            @current-change="findPage">
          </el-pagination>
        </div>
      </el-footer>
    </el-container>
  </div>
</template>

<style>
.bottom {
  margin-top: 13px;
  line-height: 20px;
}

.button {
  padding: 0;
  float: right;
}

.image {
  width: 100%;
  display: block;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both
}
</style>

<script>
import TacoPhoto from '../../assets/TacoPhoto.jpg'

export default {
  name: "Recent",
  data() {
    return {
      tacos: [],
      images: TacoPhoto
    }
  },
  methods: {
    findPage(page){
      console.log(page);
      if(page == 1){
        this.$http.get("http://localhost:8080/design/recent").then(res => {
          this.tacos = res.data._embedded.tacos;
        })
      }
      else{
          this.tacos = this.tacos.slice(3,5);
      }
    }
  },
  created() {
    this.$http.get("http://localhost:8080/design/recent").then(res => {
      console.log(res);
      this.tacos = res.data._embedded.tacos;
    })
  }
}
</script>

<style scoped>

</style>
