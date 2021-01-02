import Vue from 'vue'
import Router from 'vue-router'
import Search from "../components/search/SearchOne";
import Home from "../components/home/Home";
import Recent from "../components/search/Recent";

Vue.use(Router)

const originalPush = Router.prototype.push
Router.prototype.push = function push (location) {
  return originalPush.call(this, location).catch(err => err)
}

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
    },
    {
      path: '/recent',
      component: Recent,
      name: 'Recent'
    },
    {
      path: '/',
      redirect: '/home'
    }
  ]
})
