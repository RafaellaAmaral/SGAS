package proj.model;

import java.util.ArrayList;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Tutor {

	@Id
	private long id;
    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private ArrayList<Animal> listaFavoritos = new ArrayList<>();
	
	
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
	
	public ArrayList<Animal> getListaFavoritos() {
		return listaFavoritos;
	}
	
	public void setListaFavoritos(ArrayList<Animal> listaFavoritos) {
		this.listaFavoritos = listaFavoritos;
	}
	
}
