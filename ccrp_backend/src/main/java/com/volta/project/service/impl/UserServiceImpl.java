package com.volta.project.service.impl;

import static com.volta.project.constant.UserConstant.USER_LOGIN_STATE;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.volta.project.common.ErrorCode;
import com.volta.project.common.JwtUtils;
import com.volta.project.exception.BusinessException;
import com.volta.project.mapper.UserMapper;
import com.volta.project.model.entity.User;
import com.volta.project.model.request.UserRegisterRequest;
import com.volta.project.service.UserService;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
 * 用户服务实现类
 *
 * @author volta
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Resource
    private UserMapper userMapper;

    /**
     * 盐值，混淆密码
     */
    private static final String SALT = "volta";

    /**
     * @author jiawenqi
     * @Description 实现用户注册
//     * @param userAccount 用户账户
//     * @param userPassword 用户密码
     * @return 新用户 id
     */
    @Override
    public long userRegister(UserRegisterRequest userRegisterRequest) {
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
//        String checkPassword = userRegisterRequest.getCheckPassword();
//        System.out.println("你好哈哈哈");
        // 1. 校验
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
        }
        if (userAccount.length() < 4) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户账号过短");
        }
        if (userPassword.length() < 8) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户密码过短");
        }
        // 账户不能包含特殊字符
        String validPattern = "[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Matcher matcher = Pattern.compile(validPattern).matcher(userAccount);
        if (matcher.find()) {
            return -1;
        }
        // 密码和校验密码相同
//        if (!userPassword.equals(checkPassword)) {
//            return -1;
//        }
        // 账户不能重复
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount", userAccount);
        long count = userMapper.selectCount(queryWrapper);
        if (count > 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号重复");
        }
        // 2. 加密
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
//        String encryptPassword = userPassword;
        // 3. 插入数据
        //可以结合数据库和需求对下列新添数据进行修改
        User user = new User();
        user.setUserName(userRegisterRequest.getUserName());
        user.setUserAccount(userAccount);
        user.setUserPassword(encryptPassword);
        user.setAvatarUrl(userRegisterRequest.getAvatarUrl());
        user.setEmail(userRegisterRequest.getEmail());
        user.setPhone(userRegisterRequest.getPhone());
        user.setId_card(userRegisterRequest.getId_card());
        user.setDian_zan(userRegisterRequest.getDian_zan());
        user.setPing_lun(userRegisterRequest.getPing_lun());
        user.setCreateTime(userRegisterRequest.getCreateTime());
        user.setUpdateTime(userRegisterRequest.getUpdateTime());
//        System.out.println("你好"+user);
        boolean saveResult = this.save(user);

        if (!saveResult) {
            return -1;
        }
        return user.getId();
    }

    @Override
    public String userLogin(String userAccount, String userPassword, HttpServletRequest request) {
        // 1. 校验
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            return null;
        }
        if (userAccount.length() < 4) {
            return null;
        }
        if (userPassword.length() < 8) {
            return null;
        }
        // 账户不能包含特殊字符
        String validPattern = "[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Matcher matcher = Pattern.compile(validPattern).matcher(userAccount);
        if (matcher.find()) {
            return null;
        }
        // 2. 加密
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
//        String encryptPassword = userPassword;
        // 查询用户是否存在
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount", userAccount);
        queryWrapper.eq("userPassword", encryptPassword);
        System.out.println("得到的账号为"+userAccount);
        System.out.println("得到的加密为"+encryptPassword);
        User user = userMapper.selectOne(queryWrapper);
        System.out.println("从数据库中得到的用户："+user);
        // 用户不存在
        if (user == null) {
            log.info("user login failed, userAccount cannot match userPassword");
            return null;
        }
        // 3. 用户脱敏
        User safetyUser = getSafetyUser(user);
//        System.out.println("设置一些信息后的用户："+safetyUser);
        // 4. 记录用户的登录态
        request.getSession().setAttribute(USER_LOGIN_STATE, safetyUser);

        //得到jwt并返回
        Map<String, Object> claims = new HashMap<>();
        claims.put("userAccount",user.getUserAccount());
        claims.put("userPassword",user.getUserPassword());
        claims.put("userName",user.getUserName());
        String jwt = JwtUtils.generateJwt(claims); //jwt中包含了当前登录管理员的信息
//        return Result.success(jwt);
        return jwt;
    }

    /**
     * 用户脱敏
     *
     * @param originUser
     * @return
     */
    @Override
    public User getSafetyUser(User originUser) {
        if (originUser == null) {
            return null;
        }
        User safetyUser = new User();
        safetyUser.setId(originUser.getId());
        safetyUser.setUserName(originUser.getUserName());
        safetyUser.setUserAccount(originUser.getUserAccount());
        safetyUser.setAvatarUrl(originUser.getAvatarUrl());
//        safetyUser.setGender(originUser.getGender());
        safetyUser.setPhone(originUser.getPhone());
        safetyUser.setEmail(originUser.getEmail());
/*        safetyUser.setUserRole(originUser.getUserRole());
        safetyUser.setUserStatus(originUser.getUserStatus());*/
        safetyUser.setCreateTime(originUser.getCreateTime());
        return safetyUser;
    }

    /**
     * 用户注销
     *
     * @param request
     */
    @Override
    public int userLogout(HttpServletRequest request) {
        // 移除登录态
        request.getSession().removeAttribute(USER_LOGIN_STATE);
        return 1;
    }

/*    @Override
    public boolean isAdmin(HttpServletRequest request) {
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User user = (User) userObj;
        return user != null && user.getUserRole() == UserConstant.ADMIN_ROLE;
    }

    @Override
    public boolean isAdmin(User user) {
        return user != null && user.getUserRole() == UserConstant.ADMIN_ROLE;
    }

    @Override
    public void assertAdmin(HttpServletRequest request) {
        if (!isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH);
        }
    }*/

    @Override
    public User getLoginUser(HttpServletRequest request) {
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User loginUser = (User) userObj;
        if (loginUser == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN);
        }
        return loginUser;
    }

}




