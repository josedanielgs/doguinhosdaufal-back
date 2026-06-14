package br.com.alura.adopet.api.dto.usuario;


import br.com.alura.adopet.api.model.Usuario;

public class UsuarioMapper {

    private UsuarioMapper() {
    }

    public static Usuario toEntity(CreateUsuarioRequestDto request) {
        Usuario usuario = new Usuario();
        usuario.setNome(request.nome());
        usuario.setEmail(request.email());
        usuario.setSenha(request.senha()); // depois o service criptografa
        usuario.setPerfil(request.perfil());
        usuario.setAtivo(true);
        return usuario;
    }

    public static void updateEntity(Usuario usuario, UpdateUsuarioRequestDto request) {
        if (request.nome() != null) {
            usuario.setNome(request.nome());
        }
        if (request.email() != null) {
            usuario.setEmail(request.email());
        }
        if (request.senha() != null) {
            usuario.setSenha(request.senha());
        }
        if (request.perfil() != null) {
            usuario.setPerfil(request.perfil());
        }
        if (request.ativo() != null) {
            usuario.setAtivo(request.ativo());
        }
    }

    public static UsuarioResponseDto toResponse(Usuario usuario) {
        return new UsuarioResponseDto(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getPerfil(),
                usuario.getAtivo(),
                usuario.getDataCriacao()
        );
    }
}
