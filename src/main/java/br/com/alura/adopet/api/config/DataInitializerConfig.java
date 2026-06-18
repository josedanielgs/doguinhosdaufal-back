package br.com.alura.adopet.api.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.alura.adopet.api.model.PerfilUsuario;
import br.com.alura.adopet.api.model.Usuario;
import br.com.alura.adopet.api.repository.UsuarioRepository;

@Configuration
public class DataInitializerConfig {

    @Bean
    public CommandLineRunner initAdminUser(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder
    ) {
        return args -> {
            String emailAdmin = "admin@teste.com";

            if (usuarioRepository.findByEmail(emailAdmin).isEmpty()) {
                Usuario usuario = new Usuario();
                usuario.setNome("Administrador");
                usuario.setEmail(emailAdmin);
                usuario.setSenha(passwordEncoder.encode("123456"));
                usuario.setPerfil(PerfilUsuario.ADMIN);
                usuario.setAtivo(true);

                usuarioRepository.save(usuario);
                System.out.println("Usuário admin criado com sucesso.");
            }
        };
    }
}
