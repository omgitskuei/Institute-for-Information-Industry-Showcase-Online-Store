//package springsecurity.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.ViewResolver;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.view.InternalResourceViewResolver;
//
//// 第二個是可以用Annotation
//@Configuration
//@EnableWebMvc
//@ComponentScan(basePackages="springsecurity")
//public class DemoAppConfig {
//	
//	// define a bean for ViewResolver
//	// 讓jsp可以直接return檔名
//	@Bean
//	public ViewResolver viewResolver() {
//		
//		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//		
//		viewResolver.setPrefix("/WEB-INF/view/");
//		viewResolver.setSuffix(".jsp");
//		
//		return viewResolver;
//	}
//}
