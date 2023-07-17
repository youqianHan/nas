import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";
Vue.use(VueRouter);

const routes = [
    {
        path: "*", //输错路由回首页
        redirect: "/404",
        hidden: true,
        meta: {
			title: "输错路由回首页",
            keepAlive: false,
            level: 0 //判断是否缓存 0为不缓存 1为缓存 2为详情页
        }
    },
    {
        path: "/", //默认路由
        redirect: "/login",
        hidden: true,
        meta: {
			title: "默认路由",
            keepAlive: false,
            level: 0
        }
    },
    //登录
    {
        path: "/login",
        name: "login",
        component: r => require.ensure([], () => r(require("../views/login/Login.vue")), "Login"),
        hidden: true,
        meta: {
			title: "登录",
            keepAlive: false,
            level: 0
        }
    },
    //404
    {
        path: "/404",
        name: "notFound",
        component: r => require.ensure([], () => r(require("../views/error/NotFound.vue")), "NotFound"),
        hidden: true,
        meta: {
			title: "404",
            keepAlive: false,
            level: 0
        }
    },
    {
        path: "/home",
        name: "home",
        component: Home,
        redirect: '/summarys/index',
        hidden: true,
        meta: {
			title: "首页",
            keepAlive: false,
            level: 0
        }
    },
    {
        path: "/home",
        component: Home,
        iconCls: "fa el-icon-s-data",
		hidden: false,
		meta: {
			title: "框架简介",
            keepAlive: false,
            level: 0
		},
        children: [
            {
                path: "/summarys/index",
                component: () => import("../views/summarys/index.vue"),
                name: "summarys",
				hidden: false,
                meta: {
					title: "框架简介",
					keepAlive: false,
					level: 0
                }
            }
        ]
    },
    {
        path: "/home",
        component: Home,
        iconCls: "fa el-icon-s-data",
		hidden: false,
		meta: {
			title: "nas盘管理",
            keepAlive: false,
            level: 0
		},
        children: [
			{
				path: "/nas/nasList",
				component: () => import("../views/nas/nasList.vue"),
				name: "nasList",
				hidden: false,
				meta: {
					title: "nas盘列表",
					keepAlive: false,
					level: 0
				}
			},
			{
				path: "/nas/nasList/nasDetail",
				component: () => import("../views/nas/nasDetail.vue"),
				name: "nasDetail",
				hidden: true,
				meta: {
					title: "邮件发送记录",
					parentPath: "/nas/nasList",
					parentTitle: "列表",
					keepAlive: false,
					level: 0
				}
			}
        ]
    },
    {
        path: "/home",
        component: Home,
        iconCls: "fa el-icon-s-data",
		hidden: false,
		meta: {
			title: "用户管理",
            keepAlive: false,
            level: 0
		},
        children: [
			{
				path: "/users/userssList",
				component: () => import("../views/useradmin/usersList.vue"),
				name: "usersList",
				hidden: false,
				meta: {
					title: "用户列表",
					keepAlive: false,
					level: 0
				}
			},
			{
				path: "/users/userssList/userDetail",
				component: () => import("../views/useradmin/usersDetail.vue"),
				name: "usersDetail",
				hidden: true,
				meta: {
					title: "详情",
					parentPath: "/userssList/userssList",
					parentTitle: "用户列表",
					keepAlive: false,
					level: 0
				}
			}
        ]
    },
    {
        path: "/home",
        component: Home,
        iconCls: "fa el-icon-s-data",
		hidden: false,
		meta: {
			title: "运维管理",
            keepAlive: false,
            level: 0
		},
        children: [
            {
                path: "/formValidation/index",
                component: () => import("../views/formValidation/index.vue"),
                name: "formValidation",
				hidden: false,
                meta: {
					title: "部署备份",
					keepAlive: false,
					level: 0
                }
            }
        ]
    },
]

const router = new VueRouter({
    // mode: "history",
    base: process.env.BASE_URL,
    routes
});

//全局路由守卫、路由拦截
// router.beforeEach((to, from, next) => {
//     const isLogin = localStorage.token ? true : false;
//     if (to.path === "/login" || to.path === "/forgotPassword") {
//         next();
//     } else {
//         isLogin ? next() : next("/");
//     }
// });

export default router;
