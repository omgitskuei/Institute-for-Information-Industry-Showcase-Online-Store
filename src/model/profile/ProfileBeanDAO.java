package model.profile;


import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProfileBeanDAO implements ProfileBeanDAOInterface {

	// Local fields
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<ProfileBean> getProfiles() {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<ProfileBean> cq = cb.createQuery(ProfileBean.class);
		Root<ProfileBean> root = cq.from(ProfileBean.class);
		cq.select(root);
		Query query = session.createQuery(cq);
		return query.getResultList();
	}

	@Override
	public void saveProfile(ProfileBean theProfile) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.update(theProfile);
	}

	@Override
	public ProfileBean getProfile(int userID) {
		Session currentSession = sessionFactory.getCurrentSession();
		ProfileBean theProfile = currentSession.get(ProfileBean.class, userID);
		return theProfile;
	}
	
	public ProfileBean getProfile(ProfileBean thisP) {
		Session currentSession = sessionFactory.getCurrentSession();
		int userID = thisP.getUserID();
		ProfileBean theProfile = currentSession.get(ProfileBean.class, userID);
		return theProfile;
	}

	@Override
	public void deleteProfile(int userID) {
		Session session = sessionFactory.getCurrentSession();
		ProfileBean book = session.byId(ProfileBean.class).load(userID);
		session.delete(book);

	}

}
