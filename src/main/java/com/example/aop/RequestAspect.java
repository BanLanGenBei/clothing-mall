package com.example.aop;

import com.example.common.exception.CommonException;
import com.example.common.exception.ExceptionEnum;
import com.example.common.support.LocalStorageUtils;
import com.example.common.support.StorageInfo;
import com.example.model.entity.User;
import com.example.service.UserService;
import com.example.util.IpUtils;
import com.example.util.TokenUtil;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: chenchong
 * @Date: 2021/3/20 19:48
 */
@Component
@Aspect
@Order(1)
@Slf4j
public class RequestAspect {
    public static final String Get = "Get";
    public static final String PUT = "Put";
    public static final String POST = "POST";
    private static final String DELETE = "DELETE";
    private static final String TICKET = "ticket";
    private static final String TOKEN = "authenticator";

    @Resource
    private UserService userService;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Pointcut("execution(* com.example.controller.*Controller.*(..))")
    public void allControllerRequestCut(){
    }

    @Around("allControllerRequestCut()")
    public Object allControllerRequestBeforePoint(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        LocalStorageUtils.remove();
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String remoteIp = IpUtils.getRemoteIp(request);
        StorageInfo storageInfo = LocalStorageUtils.getStorageInfo();
        checkToken(request);
        Object result = null;
        result = proceedingJoinPoint.proceed();
        return result;

    }

    private void checkToken(HttpServletRequest request) {
        String requestURI = request.getMethod() + request.getRequestURI().replace(contextPath, "");
        List<String> list = noCheckUri.stream().filter(requestURI::contains).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(list)) {
            return;
        }
        String tokenReq = request.getHeader(TOKEN);
        Integer userId = null;
        String token = null;
        if(StringUtils.isNotEmpty(tokenReq)){
            token = tokenReq;
            userId = Integer.parseInt(Objects.requireNonNull(TokenUtil.getUserId(tokenReq, "userId")));
            if (userId == null){
                throw new CommonException(ExceptionEnum.INVALID_TOKEN);
            }else{
                User userById = userService.getUserById(userId);
                if(userById != null){
                    StorageInfo storageInfo = new StorageInfo();
                    storageInfo.setUserId(userById.getId());
                    storageInfo.setUserName(userById.getUserName());
                    storageInfo.setToken(token);
                    LocalStorageUtils.setStorageInfo(storageInfo);
                }
            }
        }else{
            throw new CommonException(ExceptionEnum.INVALID_TOKEN);
        }
    }

    /**
     * 不需要权限过滤的路径
     * com.google.common.collect.Lists
     */
    private static final List<String> noCheckUri = Lists.newArrayList(
            "/login/sendVerificationForRegister"

    );

}
