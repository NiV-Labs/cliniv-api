package br.com.ft.gdp.models.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 
 * Classe VisitDTO.java
 *
 * @author <a href="carolexc@gmail.com">Caroline Aguiar</a>
 *
 * @since 8 de set de 2019
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class NewVisitDTO implements Serializable {

    private static final long serialVersionUID = 2370290606342755763L;

    private Long patientId;

    @NotBlank(message = "Informar o MOTIVO da visita é obrigatório")
    private String reasonForEntry;

}
