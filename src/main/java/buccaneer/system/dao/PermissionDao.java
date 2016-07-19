package buccaneer.system.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import buccaneer.common.HibernateDao;
import buccaneer.system.bean.Permission;
import buccaneer.system.bean.Role;

@Repository
public class PermissionDao extends HibernateDao<PermissionDao, Integer> {
	@Autowired
	RoleDao roleDao;
	
	public Set<Permission> findPermissionsByUserID(Integer userId) {
		Session session = getSession();
		String queryString = new String();
		queryString = queryString + "select kk.id as id, kk.name as name from(";
		queryString = queryString + "select per.id as id, per.name as name, rps.role_id as role_id from permisson per inner join role_permissions rps on per.id=rps.permissions_id";
		queryString = queryString + ") as kk";
		queryString = queryString + "inner join user_roles urs on (kk.role_id = urs.roles_id and user_id = "+userId+" )";
		SQLQuery sqlQuery = session.createSQLQuery(queryString);
		@SuppressWarnings("unchecked")
		List<Permission> list = sqlQuery.addEntity(Permission.class).list();
		Set<Permission> set = new HashSet<Permission>(list);
		return set;
	}

	public Set<Permission> findPermissionsByRoleID(Integer roleId) {
		Role find = roleDao.find(roleId);
		return find.getList();
	}
}
