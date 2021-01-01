import Vue from 'vue'
import Router from 'vue-router'
import Search from "../components/search/SearchOne";
import Home from "../components/home/Home";

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/search',
      name: 'Search',
      component: Search
    },
    {
      path: '/home',
      component: Home,
      name: 'Home'
    }
  ]
})
