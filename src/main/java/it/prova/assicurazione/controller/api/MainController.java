package it.prova.assicurazione.controller.api;

import java.io.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.prova.assicurazione.Assicurati;
import it.prova.assicurazione.Assicurato;
import it.prova.assicurazione.JAXBXMLHandler;
import it.prova.assicurazione.service.AssicuratoService;

@RestController
@RequestMapping("api/main")
public class MainController {
	@Autowired 
	private AssicuratoService assicuratoService;
	
	@Value("${path_file}")
	private String path_file;
	
	@GetMapping("vai")
	public void vai() {
		
		try {
			//System.out.println("il path è " + path_file);
			File file = new File(path_file);
			Assicurati assicurati = JAXBXMLHandler.unmarshal(file);
			//System.out.println(assicurati.getAssicurati());
			
			for (Assicurato assicurato : assicurati.getAssicurati()) {
				assicuratoService.checkIfExistAndUpdateOrInsert(assicurato);
			}
			
			
			
		
		
		
		
		
		
		} catch (Exception e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		}
		
		
		
		
		
	}

}
