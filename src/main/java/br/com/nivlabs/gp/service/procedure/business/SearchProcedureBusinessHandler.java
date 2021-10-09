package br.com.nivlabs.gp.service.procedure.business;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import br.com.nivlabs.gp.controller.filters.ProcedureFilters;
import br.com.nivlabs.gp.exception.HttpException;
import br.com.nivlabs.gp.models.domain.tiss.Procedure;
import br.com.nivlabs.gp.models.dto.ProcedureDTO;
import br.com.nivlabs.gp.models.dto.ProcedureInfoDTO;
import br.com.nivlabs.gp.repository.ProcedureRepository;
import br.com.nivlabs.gp.service.BaseBusinessHandler;

/**
 * 
 * Componente específico para consulta de procedimentos
 *
 * @author viniciosarodrigues
 * @since 08-10-2021
 *
 */
@Component
public class SearchProcedureBusinessHandler implements BaseBusinessHandler {

    @Autowired
    protected Logger logger;

    @Autowired
    protected ProcedureRepository procedureRepository;

    /**
     * Realiza uma busca paginada de procedimentos
     * 
     * @param filters Filtros de busca de procedimentos
     * @param pageRequest Configurações de paginação
     * @return Página de procedimentos
     */
    @Transactional
    public Page<ProcedureDTO> getPage(ProcedureFilters filters, Pageable pageRequest) {
        return procedureRepository.resumedList(filters, pageRequest);
    }

    /**
     * Realiza uma busca de procedimento por identificador único do mesmo
     * 
     * @param id Identificador único do procedimento
     * @return Procedimento encontrado
     */
    @Transactional
    public ProcedureInfoDTO byId(Long id) {
        if (id == null) {
            throw new HttpException(HttpStatus.UNPROCESSABLE_ENTITY, "O identificador do procedimento não foi enviado para a pesquisa");
        }
        logger.info("Iniciando busca de procedimento por ID :: {}", id);
        var procedureEntity = procedureRepository.findById(id).orElseThrow(() -> new HttpException(HttpStatus.NOT_FOUND,
                String.format("Procedimento com código %s não encontrado!", id)));

        return convertEntityToDTO(procedureEntity);
    }

    /**
     * Converte uma entidade relacional em DTO
     * 
     * @param procedureEntity Entidade relacional que representa um procedimento
     * @return DTO de procedimento
     */
    @Transactional
    private ProcedureInfoDTO convertEntityToDTO(Procedure procedureEntity) {
        logger.info("Convertendo entidade encontrada de Procedimento em DTO...");

        ProcedureInfoDTO procedureInfo = new ProcedureInfoDTO();

        procedureInfo.setId(procedureEntity.getId());
        procedureInfo.setDescription(procedureEntity.getDescription());
        procedureInfo.setBaseValue(procedureEntity.getBaseValue());
        procedureInfo.setFrequency(procedureEntity.getFrequency());
        procedureInfo.setSpecialAuthorization(procedureEntity.isSpecialAuthorization());
        procedureInfo.setPreviousAudit(procedureEntity.isPreviousAudit());
        procedureInfo.setSpecialty(procedureEntity.isSpecialty());
        procedureInfo.setMaxAge(procedureEntity.getMaxAge());
        procedureInfo.setMinAge(procedureEntity.getMinAge());

        logger.info("Procedimento convertido :: {}", procedureInfo);

        return procedureInfo;
    }

}
