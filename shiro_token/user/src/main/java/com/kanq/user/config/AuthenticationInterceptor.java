package com.kanq.user.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.kanq.user.dao.EmployeeRepository;
import com.kanq.user.entiey.pojo.Employee;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Optional;

@Configuration
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Resource
    EmployeeRepository employeeRepository;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        String token = httpServletRequest.getHeader("token");// 从 http 请求头中取出 token

        // 如果不是映射到方法直接通过
        if(!(object instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod=(HandlerMethod)object;
        Method method=handlerMethod.getMethod();
        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }

        //检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(UserLoginToken.class)) {
            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
            if (userLoginToken.required()) {
                // 执行认证
                if (token == null) {
                    throw new RuntimeException("无token，请重新登录");
                }
                // 获取 token 中的 user phoneNumber
                String phoneNumber;
                try {
                    phoneNumber = JWT.decode(token).getAudience().get(0);
                } catch (JWTDecodeException j) {
                    throw new RuntimeException("401 用户不存在，请重新登录");
                }

                //withExpiresAt(end)失效时间
                //获取失效时间
                long endTime = JWT.decode(token).getExpiresAt().getTime();

                long nowTime = System.currentTimeMillis();

                System.out.println("endTime------->"+endTime  + " nowTime----->"+nowTime   );

                if(nowTime>endTime){
                    throw new RuntimeException("token 已过期，请重新登录");
                }

                Optional<Employee> byPhoneNum = employeeRepository.findByPhoneNum(phoneNumber);
                if (!byPhoneNum.isPresent()) {
                    throw new RuntimeException("用户不存在，请重新登录");
                }
                Employee employee = byPhoneNum.get();
                // 验证 token 使用秘钥//JWT.create().sign(Algorithm.HMAC256("KANQ-TOKEN-SECRET"));
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("KANQ-TOKEN-SECRET")).build();
                try {
                    jwtVerifier.verify(token);
                } catch (JWTVerificationException e) {
                    throw new RuntimeException("token已过期");
                }

                    return true;
                }
        }



        //得到token中用户的信息 json格式
//        String employee_json = JWT.decode(token).getClaim("employee").asString();
//
//        JWTVerifier verifier = JWT.require(Algorithm.HMAC256("password")).build();

        return true;
    }


    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {

    }







}
