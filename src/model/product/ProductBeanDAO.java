package model.product;

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
public class ProductBeanDAO implements ProductBeanDAOInterface {

	// Local fields
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<ProductBean> selectAll() {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<ProductBean> cq = cb.createQuery(ProductBean.class);
		Root<ProductBean> root = cq.from(ProductBean.class);
		cq.select(root);
		Query query = session.createQuery(cq);
		return query.getResultList();
	}

	@Override
	public void saveProduct(ProductBean theProduct) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(theProduct);
	}

	@Override
	public ProductBean getProduct(int productID) {
		Session currentSession = sessionFactory.getCurrentSession();
		ProductBean theProduct = currentSession.get(ProductBean.class, productID);
		return theProduct;
	}

	@Override
	public void deleteProduct(int productID) {
		Session session = sessionFactory.getCurrentSession();
		ProductBean book = session.byId(ProductBean.class).load(productID);
		session.delete(book);

	}

}
