import Vue from 'vue';
import VueRouter from 'vue-router';
import index from '../views/index.vue';
import login from '../views/login.vue';
import forgot from '../views/forgot.vue';
import register from '../views/register.vue';
Vue.use(VueRouter)

const routes = [
	// 主页
	{
		path: '/',
		name: 'index',
		component: index,
		meta: {
			index: 0,
			title: '首页'
		}
	},

	// 登录
	{
		path: '/login',
		name: 'login',
		component: login,
		meta: {
			index: 0,
			title: '登录'
		}
	},
	// 注册
	{
		path: '/register',
		name: 'register',
		component: register,
		meta: {
			index: 0,
			title: '注册'
		}
	},

	// 忘记密码
	{
		path: '/forgot',
		name: "forgot",
		component: forgot,
		meta: {
			index: 0,
			title: '忘记密码'
		}
	},

	// 修改密码
	{
		path: '/user/password',
		name: "password",
		component: () => import("../views/user/password.vue"),
		meta: {
			index: 0,
			title: '修改密码'
		}
	},

	// 视频播放页
	{
		path: "/media/video",
		name: "video",
		component: () => import('../views/media/video.vue'),
		meta: {
			index: 0,
			title: "视频"
		}
	},

	// 音频播放页
	{
		path: "/media/audio",
		name: "audio",
		component: () => import('../views/media/audio.vue'),
		meta: {
			index: 0,
			title: "音频"
		}
	},
	// 普通用户路由
	{
		path: '/ordinary_users/table',
		name: 'ordinary_users_table',
		component: () => import('../views/ordinary_users/table.vue')
	},
	{
		path: '/ordinary_users/view',
		name: 'ordinary_users_view',
		component: () => import('../views/ordinary_users/view.vue')
	},
	// 部门经理路由
	{
		path: '/division_manager/table',
		name: 'division_manager_table',
		component: () => import('../views/division_manager/table.vue')
	},
	{
		path: '/division_manager/view',
		name: 'division_manager_view',
		component: () => import('../views/division_manager/view.vue')
	},
	// 员工档案信息路由
	{
		path: '/employee_profile_information/table',
		name: 'employee_profile_information_table',
		component: () => import('../views/employee_profile_information/table.vue')
	},
	{
		path: '/employee_profile_information/view',
		name: 'employee_profile_information_view',
		component: () => import('../views/employee_profile_information/view.vue')
	},
	// 上班打卡路由
	{
		path: '/clock_in/table',
		name: 'clock_in_table',
		component: () => import('../views/clock_in/table.vue')
	},
	{
		path: '/clock_in/view',
		name: 'clock_in_view',
		component: () => import('../views/clock_in/view.vue')
	},
	// 迟到人员登记路由
	{
		path: '/late_personnel_registration/table',
		name: 'late_personnel_registration_table',
		component: () => import('../views/late_personnel_registration/table.vue')
	},
	{
		path: '/late_personnel_registration/view',
		name: 'late_personnel_registration_view',
		component: () => import('../views/late_personnel_registration/view.vue')
	},
	// 迟到警示路由
	{
		path: '/late_warning/table',
		name: 'late_warning_table',
		component: () => import('../views/late_warning/table.vue')
	},
	{
		path: '/late_warning/view',
		name: 'late_warning_view',
		component: () => import('../views/late_warning/view.vue')
	},
	// 工资条路由
	{
		path: '/pay_slip/table',
		name: 'pay_slip_table',
		component: () => import('../views/pay_slip/table.vue')
	},
	{
		path: '/pay_slip/view',
		name: 'pay_slip_view',
		component: () => import('../views/pay_slip/view.vue')
	},
	// 通知栏路由
	{
		path: '/notification_bar/table',
		name: 'notification_bar_table',
		component: () => import('../views/notification_bar/table.vue')
	},
	{
		path: '/notification_bar/view',
		name: 'notification_bar_view',
		component: () => import('../views/notification_bar/view.vue')
	},

	// 用户路由
	{
		path: '/user/table',
		name: 'user_table',
		component: () => import('../views/user/table.vue'),
		meta: {
			index: 0,
			title: '用户列表'
		}
	},
	{
		path: '/user/view',
		name: 'user_view',
		component: () => import('../views/user/view.vue'),
		meta: {
			index: 0,
			title: '用户详情'
		}
	},
	{
		path: '/user/info',
		name: 'user_info',
		component: () => import('../views/user/info.vue'),
		meta: {
			index: 0,
			title: '个人信息'
		}
	},
	// 用户组路由
	{
		path: '/user_group/table',
		name: 'user_group_table',
		component: () => import('../views/user_group/table.vue'),
		meta: {
			index: 0,
			title: '用户组列表'
		}
	},
	{
		path: '/user_group/view',
		name: 'user_group_view',
		component: () => import('../views/user_group/view.vue'),
		meta: {
			index: 0,
			title: '用户组详情'
		}
	}
]

const router = new VueRouter({
	mode: 'history',
	base: process.env.BASE_URL,
	routes
})

router.beforeEach((to, from, next) => {
	let token = to.query.token;
	if (token){
		$.db.set("token",token,120);
	}
	next();
})

router.afterEach((to, from, next) => {
	let title = "海纳部门人事管理系统-admin";
	document.title = title;
})

export default router
