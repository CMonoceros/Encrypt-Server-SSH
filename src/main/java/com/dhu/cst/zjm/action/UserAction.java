package com.dhu.cst.zjm.action;

/**
 * Created by zjm on 2017/3/8.
 */

import com.dhu.cst.zjm.entity.UserEntity;
import com.dhu.cst.zjm.entity.base.SessionBaseEntity;
import com.dhu.cst.zjm.entity.base.UserBaseEntity;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.dhu.cst.zjm.base.BaseAction;
import com.dhu.cst.zjm.util.JsonUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class UserAction extends BaseAction<UserEntity> {

    public String login() throws Exception {
        model = (UserEntity) JsonUtil.toObject(ServletActionContext.getRequest(),
                UserEntity.class);
        UserBaseEntity user = userService.findUserByIDAndPwd(model.getId(),
                model.getPassword());
        if (user != null) {
            System.out.println("atteribute  "+ServletActionContext.getRequest().getSession().getAttribute("user_inf"));
            if (ServletActionContext.getRequest().getSession().getAttribute("user_inf") == null) {
                saveSession(user);
            }
            String[] s = {"password", "class"};
            String res = JsonUtil.jsonFilter(user, s).toString();
            JsonUtil.sendJson(ServletActionContext.getResponse(), res);
        } else {
            JsonUtil.toJson(ServletActionContext.getResponse(), null);
        }
        return "login";
    }

    private void saveSession(UserBaseEntity user) {
        HttpServletRequest req = ServletActionContext.getRequest();
        HttpServletResponse resp = ServletActionContext.getResponse();
        HttpSession hs = req.getSession();
        int ago = 60 * 60 * 24;
        long now = new Date().getTime() / 1000;
        int time = Integer.parseInt(now + "") + ago;
        SessionBaseEntity sessionBaseEntity = new SessionBaseEntity();
        sessionBaseEntity.setId(hs.getId());
        sessionBaseEntity.setUserId(user.getId());
        sessionBaseEntity.setTime(time);
        Cookie cookie = new Cookie("userId", user.getId() + "");
        cookie.setPath("/");
        cookie.setMaxAge(ago);
        resp.addCookie(cookie);
        cookie = new Cookie("sessionId", hs.getId());
        cookie.setPath("/");
        cookie.setMaxAge(ago);
        resp.addCookie(cookie);
        sessionService.saveSession(user.getId(), hs.getId(), time);
    }

    public String register() throws Exception {
        model = (UserEntity) JsonUtil.toObject(ServletActionContext.getRequest(),
                UserEntity.class);
        UserBaseEntity user = userService.saveUserByNameAndPwd(model.getName(), model.getPassword());
        if (user != null) {
            String[] s = {"password", "class"};
            String res = JsonUtil.jsonFilter(user, s).toString();
            JsonUtil.sendJson(ServletActionContext.getResponse(), res);
        } else {
            JsonUtil.toJson(ServletActionContext.getResponse(), null);
        }
        return null;
    }
}

