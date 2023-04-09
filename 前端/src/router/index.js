import {createRouter,createWebHistory} from 'vue-router'

export default createRouter({
    history:createWebHistory(),
    //路由表
    routes:[
        {
          path:'/',
          redirect:'LoginWeb'
        },
        {
            path:'/MainPage',
            name:'main',
            component:()=>import('../components/MainPage'),
            meta:{
                title:'主页'
            },
            children:[
                {
                    path:'/NeedWeb',
                    name:'NeedWeb',
                    component:()=>import('../components/NeedWeb'),
                    meta:{
                        title:'需求界面'
                    }
                },
                {
                    path:'/DonateWeb',
                    name:'DonateWeb',
                    component:()=>import('../components/DonateWeb'),
                    meta:{
                        title:'捐赠界面'
                    }
                },
                {
                    path:'/LogisticsEdit',
                    name:'LogisticsEdit',
                    component:()=>import('../components/LogisticsEdit'),
                    meta:{
                        title:'物流编辑页面'
                    }
                },
                {
                    path:'/DocumentWeb',
                    name:'DocumentWeb',
                    component:()=>import('../components/DocumentWeb'),
                    meta:{
                        title:'个人资料'
                    },
                },
                {
                    path:'/LogisticsWeb',
                    name:'LogisticsWeb',
                    component:()=>import('../components/LogisticsWeb'),
                    meta:{
                        title:'物流信息'
                    },
                },
                {
                    path:'/ExamineWeb',
                    name:'ExamineWeb',
                    component:()=>import('../components/ExamineWeb'),
                    meta:{
                        title:'审核页面'
                    },
                    beforeEnter(to, from, next) {
                        if (sessionStorage.getItem('userRoleId') === "0") {
                            next()
                        } else {
                            alert("权限不足 请切换到管理员账户后再重试")
                        }
                    }

                },
                {
                    path:'/FrontPage',
                    name:'FrontPage',
                    component:()=>import('../components/FrontPage'),
                    meta:{
                        title:'主页'
                    }
                },
                {
                    path:'/DetailsWeb',
                    name:'DetailsWeb',
                    component:()=>import('../components/DetailsWeb'),
                    meta:{
                        title:'具体信息界面'
                    }
                },
            ]
        },

        {
            path:'/LoginWeb',
            name:'LoginWeb',
            component:()=>import('../components/LoginWeb'),
            meta:{
                title:'登录界面'
            }
        },
        {
            path:'/Register',
            name:'RegisterWeb',
            component:()=>import('../components/Register'),
            meta:{
                title:'注册界面'
            }
        },


    ]
})