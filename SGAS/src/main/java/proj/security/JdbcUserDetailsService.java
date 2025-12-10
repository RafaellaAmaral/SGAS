package proj.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import proj.model.Usuario;
import proj.repository.UsuarioRepository;

@Service
public class JdbcUserDetailsService implements UserDetailsService {
	
	@Autowired
    private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) 
	throws UsernameNotFoundException 
	{
		
        try  {
        	
        	Usuario usuario = usuarioRepository.findByEmail(username); //cemail

        	ArrayList<GrantedAuthority> roles = new ArrayList<GrantedAuthority>() ;
        	
        	if (usuario.isAdministrador()) {
        		roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        	}
        	
        	return new User(username, usuario.getSenha(), roles);
			
		} catch (Exception e) {
			throw new UsernameNotFoundException("Usuário não encontrado com cpf informado");
		}
		
	}
	

}