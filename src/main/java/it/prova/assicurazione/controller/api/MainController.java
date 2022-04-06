package it.prova.assicurazione.controller.api;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

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
			
				// copio il contenuto del file assicurati.xml in scarto.xml
				try {
				      // salvo il contenuto del file assicurati.xml in una stringa
				      Scanner myReader = new Scanner(file);
				      String data = "";
				      while (myReader.hasNextLine()) {
				        data += myReader.nextLine() + "\n";
				      }
				      myReader.close();
				      
				      // lo scarico in scarto.xml
				      File scartoFile = new File("../assicurazione/scarti/scarto.xml");
				      BufferedWriter scarto = new BufferedWriter(new FileWriter(scartoFile));
				      scarto.write(""); // svuoto il file per assicurarmi che sia vuoto
				      scarto.write(data);
				      scarto.close();
				      
				      /*
				      // provo a leggere il contenuto
				      File provaFile = new File("../assicurazione/scarti/scarto.xml");
				      Scanner prova = new Scanner(provaFile);
				      while (prova.hasNextLine()) {
					        data += prova.nextLine() + "\n";
					  }
				      System.out.println(data);
				      prova.close();
				      */
				      
				      scartoFile.createNewFile();
				      
				    } catch (Exception e) {
				      System.out.println("An error occurred.");
				      e.printStackTrace();
				    }
				
				throw new NumeroSinistriNotValidException("Il numero dei sinistri dev'essere compreso tra 0 e 10");
			}
		}

		for (Assicurato assicurato : assicurati.getAssicurati()) {
			assicuratoService.checkIfExistAndUpdateOrInsert(assicurato);
		}
		
		
	}

}
