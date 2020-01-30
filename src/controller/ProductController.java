package controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import model.Picture;
import model.PictureService;

public class ProductController extends AbstractController {

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		String name = request.getParameter("name");
		Map<String, String> map = new HashMap<String, String>();
		if (name == null || name.length() == 0) {
			map.put("name", "name is required");
		}
		request.setAttribute("errors", map);
		if (map != null && !(map.isEmpty())) {
			return new ModelAndView("form"); 
		}
		
		
		String price = request.getParameter("price");
		Map<String, String> pricemap = new HashMap<String, String>();
		if (price == null || price.length() == 0) {
			pricemap.put("pricee", "price is required");
		}
		request.setAttribute("errors", pricemap);
		if (pricemap != null && !(pricemap.isEmpty())) {
			return new ModelAndView("form"); 
		}
		
		
		String stock = request.getParameter("stock");
		Map<String, String> stockmap = new HashMap<String, String>();
		if (stock == null || stock.length() == 0) {
			stockmap.put("stock", "stock is required");
		}
		request.setAttribute("errors", stockmap);
		if (stockmap != null && !(stockmap.isEmpty())) {
			return new ModelAndView("form"); 
		}
		
		
		String description = request.getParameter("description");
		Map<String, String> descriptionmap = new HashMap<String, String>();
		if (description == null || description.length() == 0) {
			stockmap.put("description", "description is required");
		}
		request.setAttribute("errors", descriptionmap);
		if (descriptionmap != null && !(descriptionmap.isEmpty())) {
			return new ModelAndView("form"); 
		}
		
		
		String timestamp = request.getParameter("timestamp");
		Map<String, String> timestampmap = new HashMap<String, String>();
		if (timestamp == null || timestamp.length() == 0) {
			timestampmap.put("timestamp", "timestamp is required");
		}
		request.setAttribute("errors", timestampmap);
		if (timestampmap != null && !(timestampmap.isEmpty())) {
			return new ModelAndView("form"); 
		}
		
		
		String category = request.getParameter("category");
		Map<String, String> categorymap = new HashMap<String, String>();
		if (category == null || category.length() == 0) {
			categorymap.put("category", "category is required");
		}
		request.setAttribute("errors", timestampmap);
		if (categorymap != null && !(categorymap.isEmpty())) {
			return new ModelAndView("form"); 
		}
		
		
		HttpSession session = request.getSession();
		session.setAttribute("name", name);
		session.setAttribute("price", price);
		session.setAttribute("stock", stock);
		session.setAttribute("description", description);
		session.setAttribute("timestamp", timestamp);
		session.setAttribute("category", category);
		
		
		return new ModelAndView("success"); 	
		
	}
		
	@Autowired
	private HttpServletRequest request;
	private PictureService pService;
	
	@Autowired
	public ProductController(PictureService pService) {
		this.pService = pService;
	}

		@RequestMapping(path = "/Productcontroller", method = RequestMethod.POST)
		@ResponseBody
		public ResponseEntity<byte[]> uploadFile(@RequestParam(name = "Image") MultipartFile multipartfile)
				throws Exception {
			String fileName = multipartfile.getOriginalFilename();
			System.out.println("fileName=" + fileName);

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.IMAGE_JPEG);

			String savePath = request.getSession().getServletContext().getRealPath("/") + "uploadTempDir\\" + fileName;
			System.out.println("savePath=" + savePath);

			File saveFile = new File(savePath);
			multipartfile.transferTo(saveFile);

			if (fileName != null && fileName.length() != 0) {
				savePicture(fileName, savePath);
			}

			return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(saveFile), headers, HttpStatus.OK);
		}

		
		private void savePicture(String fileName, String savePath) {
			try {
				Picture picture = new Picture();
				picture.setFilename(fileName);

				FileInputStream is1 = new FileInputStream(savePath);
				byte[] data = new byte[is1.available()];
				is1.read(data);
				is1.close();

				picture.setPicture(data);
				pService.insert(picture);			
			} catch (Exception e) {
	            e.printStackTrace();
			}
		}
				
}
	
	

	

