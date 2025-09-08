package proj.model;

import java.util.ArrayList;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Tutor {

	@Id
	private long id;
	private long usuario_id;
	private ArrayList<Animal> listaFavoritos = new ArrayList<Animal>();
	
	
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
	
	public ArrayList<Animal> getListaFavoritos() {
		return listaFavoritos;
	}
	
	public void setListaFavoritos(ArrayList<Animal> listaFavoritos) {
		this.listaFavoritos = listaFavoritos;
	}
	
}
