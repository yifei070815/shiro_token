package com.kanq.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.kanq.user.config.UserLoginToken;
import com.kanq.user.dao.EmployeeRepository;
import com.kanq.user.entiey.Result;
import com.kanq.user.entiey.pojo.Employee;
import com.kanq.user.enums.ResultEnum;
import com.kanq.user.tonkenService.TokenService;
import com.kanq.user.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
@CrossOrigin(origins = "*", maxAge = 3600) // 解决跨域问题
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private TokenService tokenService;

    @UserLoginToken
    @RequestMapping("/findAll")
    public Result findAll(@RequestParam(value="pageNumber",required=false,defaultValue="1") int pageNumber,
                          @RequestParam(value="pageSize",required=false,defaultValue="3") int pageSize,
                          HttpSession session){
//        Employee employee = (Employee) session.getAttribute("employee");
//        System.out.println(employee.toString());

        //分页 在方法参数体内加上pageable
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        Pageable pageable = PageRequest.of(pageNumber-1, pageSize, sort);

        Page<Employee> employeeList = employeeRepository.findAll(pageable);

        if(employeeList.isEmpty()){
            return Result.errorMsg(ResultEnum.NO_DATA.getMessage());
        }
        return Result.ok(employeeList);
    }

    @RequestMapping("/login")
    public Result login (String phoneNum, String password, String userType, HttpSession session, HttpServletResponse response){

        Optional<Employee> optionalEmployee = employeeRepository.findByPhoneNum(phoneNum);

        if(!optionalEmployee.isPresent()){
            throw new RuntimeException("电话错误");
        }

        Employee employee = optionalEmployee.get();

        String md5pwd = DigestUtils.md5DigestAsHex(password.getBytes());
        if(!employee.getPassword().equals(md5pwd)){
            throw  new RuntimeException("密码错误");
        }
        if(!employee.getUserType().equals(userType)){
            throw new RuntimeException("用户类型错误");
        }

        //======创建 token========================================================================
        //session.setAttribute("employee",employee);

        String token = tokenService.createToken(employee);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("token", token);

        Cookie cookie = new Cookie("token", token);
        cookie.setPath("/");
        response.addCookie(cookie);

        //====================================================================================
        return Result.ok(200,"登录成功",jsonObject);
    }


    /***
     * 这个请求需要验证token才能访问
     */
    @UserLoginToken
    @RequestMapping(value ="/getLoginEmployeeData"  ,method = RequestMethod.GET)
    public Result getMessage(HttpServletRequest httpServletRequest ) {

        //得到token
        String token = httpServletRequest.getHeader("token");


        //得到token中用户的信息 json格式
        String employee_json = JWT.decode(token).getClaim("employee").asString();
        System.out.println("employee_json------->"+employee_json);

        //json格式 转实体类
        Employee employee = JSONObject.parseObject(employee_json, Employee.class);
        System.out.println("employee===========>"+employee.toString());

        String phoneNum = TokenUtil.getTokenPhoneNum();

        System.out.println("phoneNum-------->" + phoneNum);


        //=========================================================

        Optional<Employee> byPhoneNum = employeeRepository.findByPhoneNum(phoneNum);

        if(!byPhoneNum.isPresent()){
            throw new RuntimeException("token 错误");
        }

        Employee employee2 = byPhoneNum.get();

        return new Result(200,null,employee2);

    }



    /**
     * 退出
     */
    @RequestMapping(value = "/logoutSession", method = {RequestMethod.GET, RequestMethod.POST})
    public Result logout(HttpSession session) {
        session.setAttribute("employee",null);

//        Subject currentUser = SecurityUtils.getSubject();
//        currentUser.logout();
        return Result.ok();
    }




}
