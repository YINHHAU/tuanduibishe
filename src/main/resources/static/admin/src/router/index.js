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







    // 员工用户路由
    {
        path: '/employee_users/table',
        name: 'employee_users_table',
        component: () => import('../views/employee_users/table.vue'),
        meta: {
            index: 0,
            title: '员工用户列表'
        }
    },
    {
        path: '/employee_users/view',
        name: 'employee_users_view',
        component: () => import('../views/employee_users/view.vue'),
        meta: {
            index: 0,
            title: '员工用户详情'
        }
    },
    // 商品分类路由
    {
        path: '/product_classification/table',
        name: 'product_classification_table',
        component: () => import('../views/product_classification/table.vue'),
        meta: {
            index: 0,
            title: '商品分类列表'
        }
    },
    {
        path: '/product_classification/view',
        name: 'product_classification_view',
        component: () => import('../views/product_classification/view.vue'),
        meta: {
            index: 0,
            title: '商品分类详情'
        }
    },
    // 商品信息路由
    {
        path: '/product_information/table',
        name: 'product_information_table',
        component: () => import('../views/product_information/table.vue'),
        meta: {
            index: 0,
            title: '商品信息列表'
        }
    },
    {
        path: '/product_information/view',
        name: 'product_information_view',
        component: () => import('../views/product_information/view.vue'),
        meta: {
            index: 0,
            title: '商品信息详情'
        }
    },
    // 供应商信息路由
    {
        path: '/supplier_information/table',
        name: 'supplier_information_table',
        component: () => import('../views/supplier_information/table.vue'),
        meta: {
            index: 0,
            title: '供应商信息列表'
        }
    },
    {
        path: '/supplier_information/view',
        name: 'supplier_information_view',
        component: () => import('../views/supplier_information/view.vue'),
        meta: {
            index: 0,
            title: '供应商信息详情'
        }
    },
    // 无人车信息路由
    {
        path: '/unmanned_vehicle_information/table',
        name: 'unmanned_vehicle_information_table',
        component: () => import('../views/unmanned_vehicle_information/table.vue'),
        meta: {
            index: 0,
            title: '无人车信息列表'
        }
    },
    {
        path: '/unmanned_vehicle_information/view',
        name: 'unmanned_vehicle_information_view',
        component: () => import('../views/unmanned_vehicle_information/view.vue'),
        meta: {
            index: 0,
            title: '无人车信息详情'
        }
    },
    // 无人车类型路由
    {
        path: '/unmanned_vehicle_type/table',
        name: 'unmanned_vehicle_type_table',
        component: () => import('../views/unmanned_vehicle_type/table.vue'),
        meta: {
            index: 0,
            title: '无人车类型列表'
        }
    },
    {
        path: '/unmanned_vehicle_type/view',
        name: 'unmanned_vehicle_type_view',
        component: () => import('../views/unmanned_vehicle_type/view.vue'),
        meta: {
            index: 0,
            title: '无人车类型详情'
        }
    },
    // 商品仓库路由
    {
        path: '/product_inventory/table',
        name: 'product_inventory_table',
        component: () => import('../views/product_inventory/table.vue'),
        meta: {
            index: 0,
            title: '商品仓库列表'
        }
    },
    {
        path: '/product_inventory/view',
        name: 'product_inventory_view',
        component: () => import('../views/product_inventory/view.vue'),
        meta: {
            index: 0,
            title: '商品仓库详情'
        }
    },
    // 入库信息路由
    {
        path: '/storage_information/table',
        name: 'storage_information_table',
        component: () => import('../views/storage_information/table.vue'),
        meta: {
            index: 0,
            title: '入库信息列表'
        }
    },
    {
        path: '/storage_information/view',
        name: 'storage_information_view',
        component: () => import('../views/storage_information/view.vue'),
        meta: {
            index: 0,
            title: '入库信息详情'
        }
    },
    // 出库信息路由
    {
        path: '/outbound_information/table',
        name: 'outbound_information_table',
        component: () => import('../views/outbound_information/table.vue'),
        meta: {
            index: 0,
            title: '出库信息列表'
        }
    },
    {
        path: '/outbound_information/view',
        name: 'outbound_information_view',
        component: () => import('../views/outbound_information/view.vue'),
        meta: {
            index: 0,
            title: '出库信息详情'
        }
    },
    // 报废信息路由
    {
        path: '/scrap_information/table',
        name: 'scrap_information_table',
        component: () => import('../views/scrap_information/table.vue'),
        meta: {
            index: 0,
            title: '报废信息列表'
        }
    },
    {
        path: '/scrap_information/view',
        name: 'scrap_information_view',
        component: () => import('../views/scrap_information/view.vue'),
        meta: {
            index: 0,
            title: '报废信息详情'
        }
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
    },
    // 库存预警路由
    {
        path: '/stock_warning/table',
        name: 'stock_warning_table',
        component: () => import('../views/stock_warning/table.vue'),
        meta: {
            index: 0,
            title: '库存预警列表'
        }
    },
    {
        path: '/stock_warning/view',
        name: 'stock_warning_view',
        component: () => import('../views/stock_warning/view.vue'),
        meta: {
            index: 0,
            title: '库存预警详情'
        }
    }
]

const router = new VueRouter({
    mode: 'hash',
    base: process.env.BASE_URL,
    routes
})

router.beforeEach((to, from, next) => {
    let token = to.query.token;
    if (token) {
        $.db.set("token", token, 120);
    }
    next();
})

router.afterEach((to, from, next) => {
    let title = "校园无人车智能库存与调拨子系统-admin";
    document.title = title;
})

export default router
