<<<<<<< HEAD
package com.volta.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.volta.project.exception.BusinessException;
import com.volta.project.model.entity.User;
import com.volta.project.model.request.UserRegisterRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户服务
 *
 * @author volta
 */
public interface UserService extends IService<User> {

    /**
     * @author jiawenqi
     * @Description 实现用户注册
//     * @param userAccount 用户账户
//     * @param userPassword 用户密码
//     * @param checkPassword 校验密码
     * @return 新用户 id
     */
    long userRegister(UserRegisterRequest userRegisterRequest);

    /**
     * 用户登录
     *
     * @param userAccount 用户账户
     * @param userPassword 用户密码
     * @param request
     * @return 脱敏后的用户信息
     */
    String userLogin(String userAccount, String userPassword, HttpServletRequest request);

    /**
     * 用户脱敏
     *
     * @param originUser
     * @return
     */
    User getSafetyUser(User originUser);

    /**
     * 用户注销
     *
     * @param request
     * @return
     */
    int userLogout(HttpServletRequest request);

    /**
     * 是否为管理员
     *
     * @param request
     * @return
     */
    //boolean isAdmin(HttpServletRequest request);

    /**
     * 是否为管理员
     *
     * @param user
     * @return
     */
    //boolean isAdmin(User user);

    /**
     * 断言是管理员
     *
     * @param request
     */
    //void assertAdmin(HttpServletRequest request);

    /**
     * 获取登录用户（查缓存）
     *
     * @param request
     * @return
     * @throws BusinessException 未登录则抛异常
     */
    User getLoginUser(HttpServletRequest request);

    interface AppArtifactService {
    }
}
=======
package com.volta.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.volta.project.exception.BusinessException;
import com.volta.project.model.entity.User;
import javax.servlet.http.HttpServletRequest;

/**
 * 用户服务
 *
 * @author volta
 */
public interface UserService extends IService<User> {

    /**
     * @author jiawenqi
     * @Description 实现用户注册
     * @param userAccount 用户账户
     * @param userPassword 用户密码
     * @param checkPassword 校验密码
     * @return 新用户 id
     */
    long userRegister(String userAccount, String userPassword, String checkPassword);

    /**
     * 用户登录
     *
     * @param userAccount 用户账户
     * @param userPassword 用户密码
     * @param request
     * @return 脱敏后的用户信息
     */
    User userLogin(String userAccount, String userPassword, HttpServletRequest request);

    /**
     * 用户脱敏
     *
     * @param originUser
     * @return
     */
    User getSafetyUser(User originUser);

    /**
     * 用户注销
     *
     * @param request
     * @return
     */
    int userLogout(HttpServletRequest request);

    /**
     * 是否为管理员
     *
     * @param request
     * @return
     */
    //boolean isAdmin(HttpServletRequest request);

    /**
     * 是否为管理员
     *
     * @param user
     * @return
     */
    //boolean isAdmin(User user);

    /**
     * 断言是管理员
     *
     * @param request
     */
    //void assertAdmin(HttpServletRequest request);

    /**
     * 获取登录用户（查缓存）
     *
     * @param request
     * @return
     * @throws BusinessException 未登录则抛异常
     */
    User getLoginUser(HttpServletRequest request);

    interface AppArtifactService {
    }
}
>>>>>>> 1e22f938e2eb3fa324401ee522c8f440cc7949de
