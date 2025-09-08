package proj.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class DoacaoFinanceira {

	@Id
	private long id;
	private double valor;
	private String comprovanteURL; 
	private String observacao;
	@OneToOne
    @JoinColumn(name = "usuario_id")
	private Usuario doador;
	
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public double getValor() {
		return valor;
	}
	
	public void setValor(double valor) {
		this.valor = valor;
	}
	
	public String getComprovanteURL() {
		return comprovanteURL;
	}
	
	public void setComprovanteURL(String comprovanteURL) {
		this.comprovanteURL = comprovanteURL;
	}
	
	public String getObservacao() {
		return observacao;
	}
	
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
	public Usuario getDoador() {
		return doador;
	}
	
	public void setDoador(Usuario doador) {
		this.doador = doador;
	}
	
}
