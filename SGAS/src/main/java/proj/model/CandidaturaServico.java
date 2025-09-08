package proj.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class CandidaturaServico {
	
	@Id
	private long id;
	private Voluntario voluntario;
	private Servico servico;
	private LocalDateTime dataCandidatura;
	private String status;
	private LocalDateTime dataServico;
	private byte presenca;
	private Usuario funcionarioResponsavel;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public Voluntario getVoluntario() {
		return voluntario;
	}
	
	public void setVoluntario(Voluntario voluntario) {
		this.voluntario = voluntario;
	}
	
	public Servico getServico() {
		return servico;
	}
	
	public void setServico(Servico servico) {
		this.servico = servico;
	}
	
	public LocalDateTime getDataCandidatura() {
		return dataCandidatura;
	}
	
	public void setDataCandidatura(LocalDateTime dataCandidatura) {
		this.dataCandidatura = dataCandidatura;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public LocalDateTime getDataServico() {
		return dataServico;
	}
	
	public void setDataServico(LocalDateTime dataServico) {
		this.dataServico = dataServico;
	}
	
	public byte getPresenca() {
		return presenca;
	}
	
	public void setPresenca(byte presenca) {
		this.presenca = presenca;
	}
	
	public Usuario getFuncionarioResponsavel() {
		return funcionarioResponsavel;
	}
	
	public void setFuncionarioResponsavel(Usuario funcionarioResponsavel) {
		this.funcionarioResponsavel = funcionarioResponsavel;
	}
	
}
