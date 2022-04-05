package it.prova.assicurazione;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlMixed;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="assicurati")
public class Assicurati {
	List<Assicurato> assicurati = new ArrayList<>();

	
	@XmlElement(name="assicurato")
	public List<Assicurato> getAssicurati() {
		return assicurati;
	}
	public void setAssicurati(List<Assicurato> assicurati) {
		this.assicurati = assicurati;
	}
	
	
	
	
	
}
