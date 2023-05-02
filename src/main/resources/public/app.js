window.eruptSiteConfig = {
    //erupt接口地址，在前后端分离时指定
    domain: "",
    //附件地址，一般情况下不需要指定，如果自定义对象存储空间，则需在此指定附件资源访问地址
    fileDomain: "",
    //标题
    title: "心理咨询预约管理系统",
    //描述
    desc: "苏州大学心理咨询预约管理系统",
    //是否展示版权信息
    copyright: false,
    //logo路径
    logoPath: "/assets/logo.png",
    //logo文字
    logoText: "suda",
    //注册页地址(仅是一个链接，需要自定义实际样式)
    registerPage: "",
    //自定义导航栏按钮，配置后将会出现在页面右上角
    r_tools: [{
        text: "csteaching",
        icon: "fa-eercast",
        mobileHidden: true,
        click: function (event) {
            window.location.replace("http://csteaching.suda.edu.cn");
        }
    }],
    //登录成功事件
    login: function (user) {

    },
    //注销事件
    logout: function (user) {
        window.location.replace("http://csteaching.suda.edu.cn");
    }
};

//路由回调函数
window.eruptRouterEvent = {
    //key表示要监听的路由切换地址，为url hash地址最后一段
    //例如：http://www.erupt.xyz:9999/#/build/table/demo中demo为回调key
    demo: {
        //路由载入事件
        load: function (e) {

        },
        //路由退出事件
        unload: function (e) {

        }
    },
    //$ 为全路径通配符，在任何路由切换时都会执行load与unload事件
    $: {
        load: function (e) {

        },
        unload: function (e) {
        }
    }
};

//erupt生命周期函数
window.eruptEvent = {
    //页面加载完成后回调
    startup: function () {

    }
}