package buccaneer.system.dao;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAccount;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import buccaneer.system.bean.Role;
import buccaneer.system.bean.User;

public class MyRealm extends AuthorizingRealm {

	private static Logger log = LoggerFactory.getLogger(MyRealm.class);
	
	@Autowired
	RoleDao roleDao;

	@Autowired
	UserDao userDao;

	@Autowired
	PermissionDao permissionDao;
	

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		Session session = SecurityUtils.getSubject().getSession();
		User user = (User)session.getAttribute("user");
		Set<String> set = new HashSet<String>();
		Set<Role> sets = user.getList();
		for (Iterator<Role> iterator = sets.iterator(); iterator.hasNext();) {
			Role role = (Role) iterator.next();
			set.add(role.getName());
		}
		info.setRoles(set);
		return info;
	}
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		List<User> lists = null;
		try{
			lists = userDao.findBy(new String[]{"name","password"},new Object[]{token.getUsername(),new String(token.getPassword(), 0, token.getPassword().length)});
		}catch(Exception e){
			e.printStackTrace();
		}
		if(lists==null||lists.size()==0){
			throw new UnknownAccountException();
		}else if(lists!=null&&lists.size()==1){
			Session session = SecurityUtils.getSubject().getSession();
			session.setAttribute("user", lists.get(0));
			System.out.println(session.toString());
			System.out.println(session.getAttribute("JSESSIONID"));
			
			log.info("成功");
			return new SimpleAccount(lists.get(0).getName(),lists.get(0).getPassword(),getName());
		}
		log.info("失败");
		return null;
	}
}
