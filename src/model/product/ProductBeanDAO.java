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

	@SuppressWarnings({ "rawtypes", "unchecked" })
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

	// This is aka INSERT, SAVE
	@Override
	public void insertProduct(ProductBean theProduct) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(theProduct);
	}

	@Override
	public ProductBean getProduct(int productID) {
		Session currentSession = sessionFactory.getCurrentSession();
		ProductBean theProduct = currentSession.get(ProductBean.class, productID);
		return theProduct;
	}
	
	public ProductBean getProduct(ProductBean beanWithID) {
		Session currentSession = sessionFactory.getCurrentSession();
		int productID = beanWithID.getProductID();
		ProductBean theProduct = currentSession.get(ProductBean.class, productID);
		return theProduct;
	}
	
	@SuppressWarnings("rawtypes")
	public ProductBean getProduct(String productName) {
		Session session = sessionFactory.getCurrentSession();
		String hqlString = "from ProductBean where ProductName=:thisProductName";
		Query q = session.createQuery(hqlString);
		q.setParameter("thisProductName", productName);
		ProductBean existingProduct = (ProductBean) q.uniqueResult();
		if (existingProduct == null) {
			System.out.println("No results found");
		}
		return existingProduct;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<ProductBean> selectAllByCategory(ProductBean beanWithCategory) {
		System.out.println("Begin: ProductBeanDAO.selectAllByCategory()");
		Session session = sessionFactory.getCurrentSession();
		// Get all rows from ProductTable
		Query query = session.createQuery("From ProductBean where ProductCategory=:thisProductCategory"); // This 'From' references ProductBean.java
		query.setParameter("thisProductCategory", beanWithCategory.getProductCategory());
		// Store query results into List results
		List<ProductBean> results = (List<ProductBean>) query.list();
		System.out.println("# of results: " + results.size());
		// Return List results
		System.out.println("Finish: ProductBeanDAO.selectAllByCategory()");
		return results;
	}

	@Override
	public void deleteProduct(int productID) {
		Session session = sessionFactory.getCurrentSession();
		ProductBean book = session.byId(ProductBean.class).load(productID);
		session.delete(book);

	}

}
