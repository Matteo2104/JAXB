package it.prova.assicurazione.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.prova.assicurazione.Assicurato;
import it.prova.assicurazione.repository.AssicuratoRepository;

@Service
public class AssicuratoServiceImpl implements AssicuratoService {
	
	@Autowired
	private AssicuratoRepository repository;
	
	@Override
	@Transactional
	public Assicurato checkIfExistAndUpdateOrInsert(Assicurato assicurato) {
		Assicurato assicuratoReloaded = repository.findByAllFieldsExceptSinistri(
				assicurato.getNome(),
				assicurato.getCognome(),
				assicurato.getDataDiNascita(),
				assicurato.getCodiceFiscale()).orElse(null);
		
		if (assicuratoReloaded != null) {
			return repository.save(assicuratoReloaded);
		} else {
			return repository.save(assicurato);
		}
		
	}

}
