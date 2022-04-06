package it.prova.assicurazione.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.prova.assicurazione.Assicurato;

public interface AssicuratoRepository extends CrudRepository<Assicurato, Long> {
	@Query("from Assicurato where nome like %?1% and cognome like %?2% and dataDiNascita = ?3 and codiceFiscale like %?4%")
	public Optional<Assicurato> findByAllFieldsExceptSinistri(String nome, String cognome, Date dataDiNascita, String codiceFiscale);
}
