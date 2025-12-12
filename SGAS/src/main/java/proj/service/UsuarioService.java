package proj.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import proj.dto.LoginDTO;
import proj.dto.UsuarioDTO;
import proj.model.Usuario;
import proj.repository.UsuarioRepository;

import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario register(UsuarioDTO dto) {
        if (usuarioRepository.findByEmail(dto.getEmail()) != null) {
            throw new DataIntegrityViolationException("E-mail já cadastrado");
        }

        Usuario u = new Usuario();
        u.setNome(dto.getNome());
        u.setEmail(dto.getEmail().toLowerCase());
        u.setSenha(passwordEncoder.encode(dto.getSenha()));
        u.setAdministrador(Boolean.TRUE.equals(dto.getIsAdministrador()));

        return usuarioRepository.save(u);
    }

    public Usuario login(LoginDTO dto) {
        Usuario u = usuarioRepository.findByEmail(dto.getEmail());
        if (u == null) {
            throw new IllegalArgumentException("Credenciais inválidas");
        }

        if (!passwordEncoder.matches(dto.getSenha(), u.getSenha())) {
            throw new IllegalArgumentException("Credenciais inválidas");
        }

        return u;
    }

}
