package com.kanq.admin.controller;

import com.kanq.admin.dao.AdminUserRepository;
import com.kanq.admin.entity.Result;
import com.kanq.admin.entity.po.AdminUser;
import com.kanq.admin.util.Md5Util;
import com.kanq.common.enums.ResultEnum;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminUserRepository adminUserRepository;

    @RequestMapping("/toLogin")
    public void tologin(){
        throw new RuntimeException(ResultEnum.TO_LOGIN.getMessage());
//        throw new Result.errorMsg(ResultEnum.TO_LOGIN.getMessage());
    }



    @RequestMapping("/login")
    public Result login(String account,String password){

        Optional<AdminUser> adminUserByAccount = adminUserRepository.findAdminUserByAccount(account);
        if(!adminUserByAccount.isPresent()){
            throw new RuntimeException(ResultEnum.ACCOUNT_IS_WRONG_AND_RELOGIN.getMessage());
        }
        AdminUser adminUser = adminUserByAccount.get();

        //账号和密码加密成密码
        String md5pwd = Md5Util.MD5pwd(adminUser.getAccount(), password);

        System.out.println("getPassword------->"+adminUser.getPassword());
        System.out.println("md5pwd------------>"+md5pwd);

        if(!md5pwd.equals(adminUser.getPassword())){
            throw new RuntimeException(ResultEnum.PASSWORD_WRONG.getMessage());
        }
        //====================================================================================
        UsernamePasswordToken token = new UsernamePasswordToken(adminUser.getAccount(), password);
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        return Result.ok(200,ResultEnum.SUCCESS.getMessage(),token);
    }




}
