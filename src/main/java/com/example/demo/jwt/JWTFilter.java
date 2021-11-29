package com.example.demo.jwt;

import com.example.demo.util.SpringContextUtil;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.util.AntPathMatcher;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.omg.PortableInterceptor.LOCATION_FORWARD;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.naming.AuthenticationException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.logging.Logger;

public class JWTFilter extends BasicHttpAuthenticationFilter {

    private static final String TOKEN = "Authorization";

    private AntPathMatcher pathMatcher = new AntPathMatcher();

    private  final  static org.slf4j.Logger log= LoggerFactory.getLogger(JWTFilter.class);

    /**
     * 执行登录认证
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue){

        log.info("-------------------是否允许通过-------------");
        if (isLoginAttempt(request, response))
        {
            try
            {
                execteLogin(request,response);
                return true;
            }
            catch (Exception e)
        {
            response401(request,response);
        }
        }
        return true;
        }

    /**
     * 判断用户是否想要登录
     * @param request
     * @param response
     * @return
     */

    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        String token=req.getHeader("Authorization");
        if(token!=null)
        {
            return true;
        }
        else
            return false;
    }



    protected boolean execteLogin(ServletRequest request, ServletResponse response) throws Exception{
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader("Authorization");
        JWTToken jwtToken = new JWTToken(token);
        //提交给realm 进行登录，如果错误抛出异常
        getSubject(request,response).login(jwtToken);
        //如果没有异常则代表登入成功，返回true
        return true;
    }

    private void response401(ServletRequest request ,ServletResponse response)
    {
        try {
            HttpServletResponse servletResponse = (HttpServletResponse) response;
            servletResponse.sendRedirect("/401");
        }catch (Exception e)
        {
          System.out.println(e.getMessage());
        }

    }

    /**
     * 跨域支持
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }
}
