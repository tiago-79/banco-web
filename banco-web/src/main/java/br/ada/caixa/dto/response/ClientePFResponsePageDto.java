package br.ada.caixa.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ClientePFResponsePageDto {
    private long total;
    private long totalPages;
    private int page;
    private int size;

    private List<ClientePFResponseDto> content;
}
