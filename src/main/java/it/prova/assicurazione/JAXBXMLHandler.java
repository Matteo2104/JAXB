package it.prova.assicurazione;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class JAXBXMLHandler {
	// Import: Unmarshalling
	public static Assicurati unmarshal(File importFile) throws JAXBException {

		JAXBContext context = JAXBContext.newInstance(Assicurati.class);
		Unmarshaller um = context.createUnmarshaller();
		Assicurati assicurati = (Assicurati) um.unmarshal(importFile);

		return assicurati;
	}
}
