package shirotest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SessionTest {
	public static void main(String[] args) {
		Logger logger = LoggerFactory.getLogger("sss");
		SecurityManager sm = new DefaultSecurityManager();
		SecurityUtils.setSecurityManager(sm);
		Subject subject = SecurityUtils.getSubject();
		System.out.println(subject.getSession());
		UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
		try {
			// 4、登录，即身份验证
			subject.login(token);
		} catch (AuthenticationException e) {
			logger.error("sss");
		}
	}
}
