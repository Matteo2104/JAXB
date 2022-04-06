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
import it.prova.assicurazione.exception.NumeroSinistriNotValidException;
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
		
		File file = null;
		Assicurati assicurati = null;
		
		try {
			//System.out.println("il path è " + path_file);
			file = new File(path_file);
			assicurati = JAXBXMLHandler.unmarshal(file);
			//System.out.println(assicurati.getAssicurati());

		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
		
		// eseguo il controllo sul numero dei sinistri
		for (Assicurato assicurato : assicurati.getAssicurati()) {
			if (assicurato.getNumeroSinistri() < 0 || assicurato.getNumeroSinistri() > 10) {
				throw new NumeroSinistriNotValidException("Il numero dei sinistri dev'essere compreso tra 0 e 10");
			}
		}

		for (Assicurato assicurato : assicurati.getAssicurati()) {
			assicuratoService.checkIfExistAndUpdateOrInsert(assicurato);
		}
		
		
	}

}
