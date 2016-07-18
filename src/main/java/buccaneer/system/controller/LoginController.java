package buccaneer.system.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping(value="")
@Component
public class LoginController {
	Logger logger = LoggerFactory.getLogger(LoginController.class);

	@RequestMapping(method = RequestMethod.GET)
	public String login() {
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated() || subject.isRemembered()) {
			return "system/index";
		} else {
			return "system/login";
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "login")
	public String login1() {
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated() || subject.isRemembered()) {
			return "system/index";
		} else {
			return "system/login";
		}
	}
	
	@RequestMapping(value = "login",method = RequestMethod.POST)
	public String loginFail(@RequestParam(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM) String userName) {
		logger.info("登录失败");
		return "system/login";
	}


	@RequestMapping(method = RequestMethod.GET, value = "success")
	public String loginSuccess() {
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated() || subject.isRemembered()) {
			return "system/index";
		} else {
			return "system/login";
		}
	}
	@RequestMapping(method = RequestMethod.GET, value = "logout")
	public String loginOut() {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return "system/login";
	}
}
