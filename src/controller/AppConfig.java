package controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:jdbc.properties")
public class AppConfig {
	@Autowired
	private Environment env;

	@Bean
    public void getDataSource() {
	   String driverName = env.getRequiredProperty("datasource.driverClassName");
	   String url = env.getRequiredProperty("datasource.url");
	   String username = env.getRequiredProperty("datasource.username");
	   String password = env.getRequiredProperty("datasource.password");
	   
	   System.out.println("driverName:" + driverName);
	   System.out.println("url:" + url);
	   System.out.println("username:" + username);
	   System.out.println("password:" + password);
    }
}
