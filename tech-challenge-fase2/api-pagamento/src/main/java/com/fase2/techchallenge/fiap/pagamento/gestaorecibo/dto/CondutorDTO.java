package com.fase2.techchallenge.fiap.pagamento.gestaorecibo.dto;

public record CondutorDTO(
        String email,
        CondutorDadosPessoaisDTO dadosPessoais,
        CondutorEnderecoDTO endereco,
        Boolean ativacaoAutomatica
) {
}
