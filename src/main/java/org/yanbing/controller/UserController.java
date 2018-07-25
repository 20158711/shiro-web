package org.yanbing.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yanbing.vo.User;

@Controller
public class UserController {

    @RequestMapping(value = "/loginUrl",method = RequestMethod.POST,
            produces = "application/json;charset=utf-8")
    @ResponseBody
    public String login(User user){
        Subject subject=SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken(user.getUsername(),user.getPassword());
        try {
            token.setRememberMe(user.isRememberMe());
            subject.login(token);
        }catch (AuthenticationException e){
            return e.getMessage();
        }
        if (subject.hasRole("admin"))
            return "有admin权限";
        return "登录成功";
    }

//    @RequiresRoles("admin")
    @RequestMapping("/testRole")
    @ResponseBody
    public String testRole(){
        return "testRole success";
    }

    @RequestMapping("/testRoleAdminAndManager")
    @ResponseBody
    public String testRoleAndManager(){
        return "testRoleAdminAndManager success";
    }

    @RequestMapping("/testRoleAdminOrManager")
    @ResponseBody
    public String testRoleAdminOrManager(){
        return "testRoleAdminOrManager success";
    }

//    @RequiresPermissions("user:select")
    @RequestMapping("/testPermission")
    @ResponseBody
    public String testPermission(){
        return "testPermission success";
    }

    @RequestMapping("/testPermissionUpdate")
    @ResponseBody
    public String testPermissionUpdate(){
        return "testPermissionUpdate success";
    }


}
