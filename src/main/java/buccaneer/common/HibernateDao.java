package buccaneer.common;

import java.io.Serializable;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
@Transactional
public class HibernateDao<T, PK extends Serializable> extends
		SimpleHibernateDao<T, PK> {
	public HibernateDao() {
		super();
	}
	public HibernateDao(final SessionFactory sessionFactory,
			final Class<T> entityClass) {
		super(sessionFactory, entityClass);
	}
}
