package br.com.alura.adopet.api.dto.login;

public record LoginResponseDto(
        String token,
        String tipo
) {
}
