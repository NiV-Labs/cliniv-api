/**
 * 
 */
package br.com.nivlabs.gp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.nivlabs.gp.exception.ObjectNotFoundException;
import br.com.nivlabs.gp.models.domain.Anamnesis;
import br.com.nivlabs.gp.models.dto.AnamnesisDTO;
import br.com.nivlabs.gp.repository.AnamneseRepository;

/**
 * Camada de serviço de anamenese do paciente
 * 
 * @author viniciosarodrigues
 *
 */
@Service
public class AnamnesisService implements GenericService<Anamnesis, Long> {

	@Autowired
	private AnamneseRepository dao;

	public Page<AnamnesisDTO> searchDTOPage(Pageable pageSettings) {
		Page<Anamnesis> page = dao.findAll(pageSettings);
		List<AnamnesisDTO> newPage = new ArrayList<>();
		page.getContent().forEach(domain -> {
			newPage.add(domain.getAnamneseDTOFromDomain());
		});

		return new PageImpl<>(newPage, pageSettings, page.getTotalElements());
	}

	@Override
	public Anamnesis findById(Long id) {
		try {
			return dao.findById(id).orElseThrow(
					() -> new ObjectNotFoundException(String.format("Anamnesis Item ID: [%s] não encontrado!", id)));

		} catch (ObjectNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Anamnesis update(Long id, Anamnesis entity) {
		Anamnesis anamnese = findById(id);
		BeanUtils.copyProperties(entity, anamnese, "id");
		return anamnese;
	}

	@Override
	public void delete(Anamnesis entity) {
		deleteById(entity.getId());
	}

	@Override
	public void deleteById(Long id) {
		Anamnesis anamnese = findById(id);
		dao.delete(anamnese);
	}

	@Override
	public Anamnesis persist(Anamnesis entity) {
		entity.setId(null);
		return dao.save(entity);
	}

}
