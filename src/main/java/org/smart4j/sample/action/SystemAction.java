package org.smart4j.sample.action;

import org.smart4j.framework.mvc.DataContext;
import org.smart4j.framework.mvc.annotation.Action;
import org.smart4j.framework.mvc.annotation.Request;
import org.smart4j.framework.mvc.pojo.Params;
import org.smart4j.framework.mvc.pojo.Result;
import org.smart4j.framework.mvc.pojo.View;
import org.smart4j.framework.util.StringUtil;
import org.smart4j.framework.util.WebUtil;
import org.smart4j.sample.Constant;
import org.smart4j.security.SmartSecurityHelper;
import org.smart4j.security.exception.LoginException;

@Action
public class SystemAction {

    @Request.Get("/captcha")
    public void captcha() {
        String captcha = WebUtil.createCaptcha(DataContext.getResponse());
        DataContext.Session.put(Constant.CAPTCHA, captcha);
    }

    @Request.Get("/login")
    public View login() {
        return new View("login.jsp");
    }

    @Request.Post("/login")
    public Result login(Params params) {
        String username = params.getString("username");
        String password = params.getString("password");
        String captcha = params.getString("captcha");

        String sessionCaptcha = DataContext.Session.get(Constant.CAPTCHA);
        if (StringUtil.isNotEmpty(sessionCaptcha) && !sessionCaptcha.equals(captcha)) {
            return new Result(false).data("captcha_error");
        }

        try {
            SmartSecurityHelper.login(username, password, false);
        } catch (LoginException e) {
            return new Result(false);
        }

        return new Result(true);
    }

    @Request.Get("/logout")
    public Result logout() {
        SmartSecurityHelper.logout();
        return new Result(true);
    }
}
