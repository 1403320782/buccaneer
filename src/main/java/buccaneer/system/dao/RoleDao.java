package buccaneer.system.dao;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import buccaneer.common.HibernateDao;
import buccaneer.system.bean.User;

@Repository
@Transactional
public class RoleDao extends HibernateDao<User, Integer> {
}
