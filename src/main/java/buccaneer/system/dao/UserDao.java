package buccaneer.system.dao;

import org.springframework.stereotype.Repository;

import buccaneer.common.HibernateDao;
import buccaneer.system.bean.User;

@Repository
public class UserDao extends HibernateDao<User, Integer> {
	
}
