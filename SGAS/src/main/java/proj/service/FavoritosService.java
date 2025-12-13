package proj.service;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proj.model.Favoritos;
import proj.model.FavoritosKey;
import proj.repository.FavoritosRepository;

@Service
public class FavoritosService {
	
	@Autowired
	FavoritosRepository favoritosRepository;
	
	public List<Favoritos> listarTodos() {
		
		List<Favoritos> listaFavoritos = favoritosRepository.findAll();
		return listaFavoritos;
	}
	
	public List<Favoritos> listarFavoritosPorUsuario(long usuarioId) {

	    List<Favoritos> listaFavoritos = favoritosRepository.findByUsuarioId(usuarioId);

	    return listaFavoritos;
	}
	
	public void inserir(Favoritos c) {
		favoritosRepository.save(c);
	}
	
	public void alterar(Favoritos c) {
		favoritosRepository.save(c);
	}
	
	public void excluir(FavoritosKey id) {
		Favoritos c = favoritosRepository.findById(id).get();
		
		favoritosRepository.delete(c);
	}
	
	public Favoritos buscarPeloId(FavoritosKey id) {
		return favoritosRepository.getById(id);
	}


}
