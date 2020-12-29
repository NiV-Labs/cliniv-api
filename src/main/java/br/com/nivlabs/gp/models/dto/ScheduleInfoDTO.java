package br.com.nivlabs.gp.models.dto;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import br.com.nivlabs.gp.enums.ScheduleStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Informações do agendamento")
public class ScheduleInfoDTO extends DataTransferObjectBase {

    private static final long serialVersionUID = -7082539967186369611L;

    @ApiModelProperty("Identificador único do agendamento")
    private Long id;

    @ApiModelProperty("Paciente do agendamento")
    private PatientInfoDTO patient;

    @ApiModelProperty("Profissional do agendamento")
    private ResponsibleInfoDTO professional;

    @ApiModelProperty("Data/Hora do agendamento")
    @DateTimeFormat(iso = ISO.DATE)
    private LocalDateTime schedulingDateAndTime;

    @ApiModelProperty("Anotações sobre o agendamento")
    private String annotation;

    @ApiModelProperty("Situação atual do agendamento")
    private ScheduleStatus status;

    public ScheduleInfoDTO() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PatientInfoDTO getPatient() {
        return patient;
    }

    public void setPatient(PatientInfoDTO patient) {
        this.patient = patient;
    }

    public ResponsibleInfoDTO getProfessional() {
        return professional;
    }

    public void setProfessional(ResponsibleInfoDTO professional) {
        this.professional = professional;
    }

    public LocalDateTime getSchedulingDateAndTime() {
        return schedulingDateAndTime;
    }

    public void setSchedulingDateAndTime(LocalDateTime schedulingDateAndTime) {
        this.schedulingDateAndTime = schedulingDateAndTime;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public ScheduleStatus getStatus() {
        return status;
    }

    public void setStatus(ScheduleStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ScheduleInfoDTO [id=" + id + ", patient=" + patient + ", professional=" + professional + ", schedulingDateAndTime="
                + schedulingDateAndTime + ", annotation=" + annotation + ", status=" + status + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((annotation == null) ? 0 : annotation.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((patient == null) ? 0 : patient.hashCode());
        result = prime * result + ((professional == null) ? 0 : professional.hashCode());
        result = prime * result + ((schedulingDateAndTime == null) ? 0 : schedulingDateAndTime.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ScheduleInfoDTO other = (ScheduleInfoDTO) obj;
        if (annotation == null) {
            if (other.annotation != null)
                return false;
        } else if (!annotation.equals(other.annotation))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (patient == null) {
            if (other.patient != null)
                return false;
        } else if (!patient.equals(other.patient))
            return false;
        if (professional == null) {
            if (other.professional != null)
                return false;
        } else if (!professional.equals(other.professional))
            return false;
        if (schedulingDateAndTime == null) {
            if (other.schedulingDateAndTime != null)
                return false;
        } else if (!schedulingDateAndTime.equals(other.schedulingDateAndTime))
            return false;
        if (status != other.status)
            return false;
        return true;
    }

}
