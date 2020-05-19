package com.kanq.user.config;//package com.kanq.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.util.StringUtils;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Configuration
////非法登录拦截
//public class LoginInterceptor implements HandlerInterceptor {
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
//
//        // PreFlight请求，忽略本拦截器
////        if (CorsUtils.isPreFlightRequest(request)) {
////            return true;
////        }
////        if(request.getRequestURI().equalsIgnoreCase("/employee/sendShortMsg")){
////            return true;
////        }
////        if(request.getRequestURI().equalsIgnoreCase("/employee/register")){
////            return true;
////        }
////        if(request.getRequestURI().equalsIgnoreCase("/employee/login")){
////            return true;
////        }
//
//        //=======================================================
//
//        //token判断是否登录
//        String token = request.getHeader("token");
//        System.out.println("token--------->"+token);
//        // 有token表示用户已登录（生产环境应该校验token合法性）
//        if (!StringUtils.isEmpty(token)) {
//            return true;
//        }
//
//        //=======================================================
////        //session.setAttribute("employee",employee) session判断是否登录
////        HttpSession session = request.getSession();
////        if(session.getAttribute("employee")!=null){
////            return true;
////        }
////        else {
//////            // 根据系统需要，返回特定的消息格式
//////            Result result = Result.ok();
////            write(request, response,"拦截器拦截" );
////        }
//////
//////        //跳转到登录页
//////        String url = "/employee/login";
//////        response.sendRedirect(url);
//        return false;
//
//    }
//
////    /**
////     * 通过response返回错误信息给前端
////     *
////     * @param request 请求
////     * @param response 响应
////     * @param content 响应内容
////     */
//    private void write(HttpServletRequest request, HttpServletResponse response, String content)
//            throws IOException {
//
//        String origin = request.getHeader("Origin");
//        response.setHeader("Access-Control-Allow-Origin", origin);
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("application/json; charset=utf-8");
//        response.getWriter().write(content);
//    }
//
//    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
//
//    }
//
//    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
//
//    }
//}
