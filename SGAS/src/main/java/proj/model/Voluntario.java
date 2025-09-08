package proj.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Voluntario {
	@Id
	private long id;
	@OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
	
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public Usuario getUsuario_id() {
		return usuario;
	}
	
	public void setUsuario_id(Usuario u) {
		this.usuario = u;
	}
	

}
