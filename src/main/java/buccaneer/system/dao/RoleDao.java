package buccaneer.system.dao;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import buccaneer.common.HibernateDao;
import buccaneer.system.bean.Role;

@Repository
@Transactional
public class RoleDao extends HibernateDao<Role, Integer> {
	public void test(){
		Criteria createCriteria = getSession().createCriteria("");
		createCriteria.addOrder(Order.)
		
	}
}
