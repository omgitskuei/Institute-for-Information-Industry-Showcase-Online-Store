package model.mailingList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MailBeanService {
	// Variables: Local Fields
	private MailBeanDAO mDAO;
	
	// Constructors
	@Autowired
	public MailBeanService(MailBeanDAO mDAO) {
		this.mDAO = mDAO;
	}
	
	// Methods
	public boolean insertMail(String email) {
		MailBean bean = new MailBean();
		bean.setEmail(email);
		return mDAO.insertMail(bean);
	}
	
	public boolean insertMail(MailBean bean) {
		return mDAO.insertMail(bean);
	}
	
	public MailBean selectMail(String email) {
		return mDAO.selectMail(email);
	}
	
	public MailBean selectMail(MailBean bean) {
		return mDAO.selectMail(bean.getEmail());
	}
	
	public List<MailBean> selectAllMail() {
		return mDAO.selectAllMail();
	}
}
