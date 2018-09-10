package cc.messcat.dao.system;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import cc.messcat.bases.BaseDaoImpl;
import cc.messcat.entity.WebSite;

public class WebSiteDaoImpl extends BaseDaoImpl implements WebSiteDao {

	public WebSiteDaoImpl() {
	}

	public void delete(WebSite webSite) {
		getHibernateTemplate().delete(webSite);
	}

	public void delete(Long id) {
		getHibernateTemplate().delete(get(id));
	}

	public WebSite get(Long id) {
		return (WebSite) getHibernateTemplate().get(WebSite.class, id);

	}

	public void save(WebSite webSite) {
		getHibernateTemplate().save(webSite);
	}

	public void update(WebSite webSite) {
		getHibernateTemplate().merge(webSite);
	}

	public WebSite findWebSite() {
		Session session =  this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query  = session.createQuery("from WebSite");
		WebSite webSite = (WebSite)query.uniqueResult();
		return webSite;
	}
	
	public WebSite getWebSite() {
		Session session =  this.getSession();
		Query query  = session.createQuery("from WebSite");
		WebSite webSite = (WebSite)query.uniqueResult();
		session.close();
		return webSite;
	}
}