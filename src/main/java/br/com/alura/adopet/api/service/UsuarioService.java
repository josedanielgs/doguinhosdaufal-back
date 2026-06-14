package br.com.alura.adopet.api.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.alura.adopet.api.dto.usuario.CreateUsuarioRequestDto;
import br.com.alura.adopet.api.dto.usuario.UpdateUsuarioRequestDto;
import br.com.alura.adopet.api.dto.usuario.UsuarioMapper;
import br.com.alura.adopet.api.dto.usuario.UsuarioResponseDto;
import br.com.alura.adopet.api.model.Usuario;
import br.com.alura.adopet.api.repository.UsuarioRepository;

import java.util.List;
import java.util.UUID;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public UsuarioResponseDto criar(CreateUsuarioRequestDto request) {
        if (usuarioRepository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("Já existe usuário com esse email.");
        }

        Usuario usuario = UsuarioMapper.toEntity(request);

        // Exemplo simples. Em projeto real, aqui entraria passwordEncoder.encode(...)
        usuario.setSenha("{noop}" + usuario.getSenha());

        Usuario salvo = usuarioRepository.save(usuario);
        return UsuarioMapper.toResponse(salvo);
    }

    @Transactional(readOnly = true)
    public UsuarioResponseDto buscarPorId(UUID id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado."));

        return UsuarioMapper.toResponse(usuario);
    }

    @Transactional(readOnly = true)
    public List<UsuarioResponseDto> listar() {
        return usuarioRepository.findAll()
                .stream()
                .map(UsuarioMapper::toResponse)
                .toList();
    }

    @Transactional
    public UsuarioResponseDto atualizar(UUID id, UpdateUsuarioRequestDto request) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado."));

        UsuarioMapper.updateEntity(usuario, request);

        if (request.senha() != null) {
            // Em projeto real, criptografar aqui
            usuario.setSenha("{noop}" + request.senha());
        }

        Usuario atualizado = usuarioRepository.save(usuario);
        return UsuarioMapper.toResponse(atualizado);
    }

    @Transactional
    public void deletar(UUID id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado."));

        usuarioRepository.delete(usuario);
    }
}
