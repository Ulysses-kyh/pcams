package com.suda.pcams.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import xyz.erupt.jpa.dao.EruptDao;
import xyz.erupt.upms.fun.LoginProxy;
import xyz.erupt.upms.model.EruptUser;
import xyz.erupt.upms.service.EruptUserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Proxy包即Service包，通过jpa写sql业务逻辑
 * 自定义的登陆逻辑
 *
 * @author Ulysses
 * @since 2023-04-23
 */
public class PcamsLoginProxy implements LoginProxy {

    @Resource
    private EruptDao eruptDao;

    @Autowired
    private EruptUserService eruptUserService;

    // 额外请求参数从request对象中获取
    @Resource
    private HttpServletRequest request;

//    @Override
//    public EruptUser login(String account, String pwd){
//
//        LoginModel loginModel = ((EruptUserService) EruptSpringUtil.getBean(EruptUserService.class)).login("admin", "123456");
//        if (loginModel.isPass()) {
//            return loginModel.getEruptUser();
//        } else {
//            throw new RuntimeException(loginModel.getReason());
//        }
//    }

//    @Override
//    public void loginSuccess(EruptUser eruptUser, String token) {
//        // TODO
//    }

    @Override
    public void logout(String token) {
        // TODO
    }

    @Override
    public void beforeChangePwd(EruptUser eruptUser, String newPwd) {
        // TODO
    }

}
