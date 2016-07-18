package shirotest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

public class Hello {
	public static void main(String[] args) {
		Factory<org.apache.shiro.mgt.SecurityManager> f = new IniSecurityManagerFactory(
				"classpath:TestShiro.ini");
		org.apache.shiro.mgt.SecurityManager s = f.getInstance();
		SecurityUtils.setSecurityManager(s);
		UsernamePasswordToken token = new UsernamePasswordToken("javass", "cc");
		token.setRememberMe(true);
		Subject currentUser = SecurityUtils.getSubject();
		try {
			currentUser.login(token);
			Session session = currentUser.getSession();
			System.out.println(session.getId());
		} catch (UnknownAccountException uae) {
			System.err.println("UnknownAccountException");
		} catch (IncorrectCredentialsException ice) {
			System.err.println("IncorrectCredentialsException");
		} catch (LockedAccountException lae) {
			System.err.println("LockedAccountException");
		} catch (ExcessiveAttemptsException eae) {
			System.err.println("ExcessiveAttemptsException");
		}
		boolean flag = currentUser.isPermitted("p1");
		System.out.println("flag==" + flag);
		IniRealm a = new IniRealm();
	}
}
