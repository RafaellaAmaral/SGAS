package proj.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Voluntario {
	@Id
	private long id;
	private long usuario_id;
	
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public long getUsuario_id() {
		return usuario_id;
	}
	
	public void setUsuario_id(long usuario_id) {
		this.usuario_id = usuario_id;
	}
	

}
