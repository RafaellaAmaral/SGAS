package proj.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Animal {
	@Id
	private long id;
	private String nome;
	private String raca;
	private LocalDateTime dataNascimento;
	private byte porte;
	private String descricao;
	private String imagemURL;
	private byte sexo;
	
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getRaca() {
		return raca;
	}
	
	public void setRaca(String raca) {
		this.raca = raca;
	}
	
	public LocalDateTime getDataNascimento() {
		return dataNascimento;
	}
	
	public void setDataNascimento(LocalDateTime dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public byte getPorte() {
		return porte;
	}
	
	public void setPorte(byte porte) {
		this.porte = porte;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getImagemURL() {
		return imagemURL;
	}
	
	public void setImagemURL(String imagemURL) {
		this.imagemURL = imagemURL;
	}
	
	public byte getSexo() {
		return sexo;
	}
	
	public void setSexo(byte sexo) {
		this.sexo = sexo;
	}
	
	
}
