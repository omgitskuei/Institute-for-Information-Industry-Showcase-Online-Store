package util;

public class PrintHibernateSpringVersion {
	//Run as Java Application to print to console
	public static void main(String[] args) {
		try {
			String hibernateVersion = org.hibernate.annotations.common.Version.getVersionString();
			System.out.println("Hibernate Version: " + hibernateVersion);
			
			String springVersion = org.springframework.core.SpringVersion.getVersion();
			System.out.println("Spring Version: " + springVersion);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}