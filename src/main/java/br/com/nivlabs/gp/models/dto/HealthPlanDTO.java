package br.com.nivlabs.gp.models.dto;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import br.com.nivlabs.gp.enums.Abragency;
import br.com.nivlabs.gp.enums.ContractType;
import br.com.nivlabs.gp.enums.Segmentation;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Plano de saúde")
public class HealthPlanDTO extends DataTransferObjectBase {

    private static final long serialVersionUID = -6773781566276160000L;

    @ApiModelProperty("Identificador único  do plano")
    private Long id;

    @ApiModelProperty("Código da operadora do plano")
    @NotNull(message = "O código da operadora do plano (ANS) é obrigatório")
    private String operatorCode;

    @ApiModelProperty("Nome da operadora do plano")
    private String operatorName;

    @ApiModelProperty("Código do plano")
    @NotNull(message = "O código do plano de saúde é obrigatório")
    private Long planCode;

    @ApiModelProperty("Nome comercial")
    @NotNull(message = "O nome comercial do plano de saúde é obrigatório")
    private String commercialName;

    @ApiModelProperty("Segmentação")
    @NotNull(message = "A segmentação do plano é obrigatória")
    private Segmentation segmentation;

    @ApiModelProperty("Tipo do contrato")
    @NotNull(message = "O tipo de contrato do plano é obrigatório")
    private ContractType contractType;

    @ApiModelProperty("Abrangência")
    @NotNull(message = "A abrangência do plano é obrigatória")
    private Abragency abrangency;

    @ApiModelProperty("Tipo do plano")
    private String type;

    @ApiModelProperty("Código do plano de saúde do paciente")
    private String patientPlanNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPlanCode() {
        return planCode;
    }

    public void setPlanCode(Long planCode) {
        this.planCode = planCode;
    }

    public String getCommercialName() {
        return commercialName;
    }

    public void setCommercialName(String commercialName) {
        this.commercialName = commercialName;
    }

    public Segmentation getSegmentation() {
        return segmentation;
    }

    public void setSegmentation(Segmentation segmentation) {
        this.segmentation = segmentation;
    }

    public ContractType getContractType() {
        return contractType;
    }

    public void setContractType(ContractType contractType) {
        this.contractType = contractType;
    }

    public Abragency getAbrangency() {
        return abrangency;
    }

    public void setAbrangency(Abragency abrangency) {
        this.abrangency = abrangency;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPatientPlanNumber() {
        return patientPlanNumber;
    }

    public void setPatientPlanNumber(String patientPlanNumber) {
        this.patientPlanNumber = patientPlanNumber;
    }

    public String getOperatorCode() {
        return operatorCode;
    }

    public void setOperatorCode(String operatorCode) {
        this.operatorCode = operatorCode;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("HealthPlanDTO [id=");
        builder.append(id);
        builder.append(", operatorCode=");
        builder.append(operatorCode);
        builder.append(", operatorName=");
        builder.append(operatorName);
        builder.append(", planCode=");
        builder.append(planCode);
        builder.append(", commercialName=");
        builder.append(commercialName);
        builder.append(", segmentation=");
        builder.append(segmentation);
        builder.append(", contractType=");
        builder.append(contractType);
        builder.append(", abrangency=");
        builder.append(abrangency);
        builder.append(", type=");
        builder.append(type);
        builder.append(", patientPlanNumber=");
        builder.append(patientPlanNumber);
        builder.append("]");
        return builder.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(abrangency, commercialName, contractType, id, operatorCode, operatorName, patientPlanNumber, planCode,
                            segmentation, type);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        HealthPlanDTO other = (HealthPlanDTO) obj;
        return abrangency == other.abrangency && Objects.equals(commercialName, other.commercialName) && contractType == other.contractType
                && Objects.equals(id, other.id) && Objects.equals(operatorCode, other.operatorCode)
                && Objects.equals(operatorName, other.operatorName) && Objects.equals(patientPlanNumber, other.patientPlanNumber)
                && Objects.equals(planCode, other.planCode) && segmentation == other.segmentation && Objects.equals(type, other.type);
    }

}
