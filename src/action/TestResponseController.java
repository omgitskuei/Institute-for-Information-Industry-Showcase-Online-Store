package action;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class TestResponseController {

	public static void main(String[] args) {
		//Testing ResponseController.java. For action.TestResponseController.java, use "RUN AS JAVA APPLICATION"
		
		//Testing /responseMsgBody.controller
		RestTemplate template = new RestTemplate();
		ResponseEntity<String> entity = template.getForEntity("http://localhost:8080/SpringMvcWebProject/responseMsgBody.controller", String.class);
		
		String body = entity.getBody();
		MediaType m = entity.getHeaders().getContentType();
		HttpStatus statusCode = entity.getStatusCode();
		
		System.out.println("body: "+body);
		System.out.println("mediaType"+ m);
		System.out.println("statusCode:"+ statusCode);

		//Testing //responseMsgCharsetBody.controller
		RestTemplate template2 = new RestTemplate();
		ResponseEntity<String> entity2 = template2.getForEntity("http://localhost:8080/SpringMvcWebProject/responseMsgCharsetBody.controller", String.class);
		
		String body2 = entity2.getBody();
		MediaType m2 = entity2.getHeaders().getContentType();
		HttpStatus statusCode2 = entity2.getStatusCode();
		
		System.out.println("body: "+body2);
		System.out.println("mediaType"+ m2);
		System.out.println("statusCode:"+ statusCode2);
		
		//Testing //responseMsgCharsetBody2.controller
		RestTemplate template3 = new RestTemplate();
		ResponseEntity<String> entity3 = template3.getForEntity("http://localhost:8080/SpringMvcWebProject/responseMsgCharsetBody2.controller", String.class);
		
		String body3 = entity3.getBody();
		MediaType m3 = entity3.getHeaders().getContentType();
		HttpStatus statusCode3 = entity3.getStatusCode();
		
		System.out.println("body: "+body3);
		System.out.println("mediaType: "+ m3);
		System.out.println("statusCode:"+ statusCode3);
		
		
		//Testing /responseByteArrayImage.controller
		RestTemplate template4 = new RestTemplate();
		ResponseEntity<String> entity4 = template4.getForEntity("http://localhost:8080/SpringMvcWebProject/responseByteArrayImage.controller", String.class);
		
		String body4 = entity4.getBody();
		MediaType m4 = entity4.getHeaders().getContentType();
		HttpStatus statusCode4 = entity4.getStatusCode();
		
		//System.out.println("body: "+body4);
		System.out.println("mediaType: "+ m4);   //This references produces="image/jpeg;charset=UTF-8", not response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		System.out.println("statusCode: "+ statusCode4);
		
	}

}
