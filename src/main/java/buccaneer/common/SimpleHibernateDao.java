package buccaneer.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metadata.ClassMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import buccaneer.utils.Reflections;

@Transactional
public class SimpleHibernateDao<T, PK extends Serializable> {
	Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	protected SessionFactory sessionFactory;

	protected Class<T> entityClass;

	/**
	 * ʹ�ü̳е�daoʹ�õ�
	 */
	@SuppressWarnings("unchecked")
	public SimpleHibernateDao() {
		this.entityClass = Reflections.getSuperClassGenricType(getClass(), 0);
	}

	/**
	 * ��service����õ�ʱ��ֱ�ӵ���ʹ��
	 * 
	 * @param sessionFactory
	 * @param entityClass
	 */
	public SimpleHibernateDao(final SessionFactory sessionFactory,
			final Class<T> entityClass) {
		this.sessionFactory = sessionFactory;
		this.entityClass = entityClass;
	}

	/**
	 * ��ȡ��ǰsession
	 * 
	 * @return
	 */
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public T find(PK id) {
		getSession().load(entityClass, id);
		return (T) getSession().get(entityClass, id);
	}
	
	public List<T> findBy(final String propertyName, final Object value) {
		Criterion criterion = Restrictions.eq(propertyName, value);
		return find(criterion);
	}
	public List<T> findBy(final String [] propertyNames, final Object [] values) {
		List<Criterion> lists = new ArrayList<Criterion>();
		for(int i = 0 ;i < propertyNames.length ; i++){
			Criterion criterion = Restrictions.eq(propertyNames[i], values[i]);
			lists.add(criterion);
		}
		Criterion [] a = new Criterion []{};
		return find(lists.toArray(a));
	}

	
	/**
	 * ��id�б��ȡ�����б�.
	 * @param idList
	 * @return ���󼯺�
	 */
	public List<T> find(final Collection<PK> idList) {
		return find(Restrictions.in(getIdName(), idList));
	}
	
	@SuppressWarnings("unchecked")
	public List<T> find(final Criterion... criterions) {
		return createCriteria(criterions).list();
	}
	
	/**
	 * ����Criterion��������Criteria.
	 * ��find()�����ɽ��и������Ĳ���.
	 * @param criterions �����ɱ��Criterion.
	 * @return Criteria
	 */
	public Criteria createCriteria(final Criterion... criterions) {
		Criteria criteria = getSession().createCriteria(entityClass);
		for (Criterion c : criterions) {
			criteria.add(c);
		}
		return criteria;
	}
	
	public Criteria createCriteria(Boolean isCache,final Criterion... criterions) {
		Criteria criteria = getSession().createCriteria(entityClass);
		for (Criterion c : criterions) {
			criteria.add(c);
		}
		criteria.setCacheable(isCache);
		return criteria;
	}
	
	/**
	 * ȡ�ö����������.
	 * 
	 * @return �����������
	 */
	public String getIdName() {
		ClassMetadata meta = sessionFactory.getClassMetadata(entityClass);
		return meta.getIdentifierPropertyName();
	}

}
