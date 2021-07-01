import Vue from "vue";
import VueRouter from "vue-router";
import UserList from "../views/UserList.vue";
import { auth as store } from "../store/auth.module";
import Login from "../views/Login";
import AdminView from "@/views/AdminView";
import OwnerList from "@/views/OwnerList";
import EmployeeView from "@/views/EmployeeView";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Login",
    component: Login,
  },
  {
    path: "/users",
    name: "Users",
    component: UserList,
    beforeEnter: (to, from, next) => {
      if (store.getters.isAdmin) {
        next();
      } else {
        next({ name: "Items" });
      }
    },
  },
  {
    path: "/animals",
    name: "Animals",
    component: AdminView,
    beforeEnter: (to, from, next) => {
      if (store.state.status.loggedIn) {
        next();
      } else {
        next({ name: "Home" });
      }
    },
  },
  {
    path: "/owners",
    name: "Owners",
    component: OwnerList,
    beforeEnter: (to, from, next) => {
      if (store.state.status.loggedIn) {
        next();
      } else {
        next({ name: "Home" });
      }
    },
  },
  {
    path: "/regularUser",
    name: "Regular User",
    component: EmployeeView,
    beforeEnter: (to, from, next) => {
      if (store.state.status.loggedIn) {
        next();
      } else {
        next({ name: "Home" });
      }
    },
  },
  {
    path: "/about",
    name: "About",
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/About.vue"),
  },
];

const router = new VueRouter({
  routes,
});

export default router;
