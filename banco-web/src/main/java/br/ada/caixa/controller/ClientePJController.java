package br.ada.caixa.controller;

import br.ada.caixa.dto.filter.ClientePJFilterDto;
import br.ada.caixa.dto.request.ClientePJRequestDto;
import br.ada.caixa.dto.response.ClientePJResponseDto;
import br.ada.caixa.service.ClientePJService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/clientesPj")
public class ClientePJController {

    final private ClientePJService clientePJService;

    public ClientePJController(ClientePJService clientePJService){
        this.clientePJService = clientePJService;
    }

    @PostMapping("/criar")
    public ResponseEntity<ClientePJResponseDto> criar (@RequestBody @Valid ClientePJRequestDto clientePFRequestDto){
        ClientePJResponseDto clientePFResponseDto = clientePJService.criar(clientePFRequestDto);
        System.out.println("criar" + clientePFRequestDto.toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(clientePFResponseDto);
    }

    @PutMapping("/{cnpj}")
    public ResponseEntity<ClientePJResponseDto> atualizar (@PathVariable String cnpj,
                                                           @RequestBody @Valid ClientePJRequestDto clientePJ){
        // atualizar cliente
        ClientePJResponseDto clientePJResponseDto = clientePJService.atualizar(cnpj, clientePJ);
        System.out.println("atualizar " + cnpj);
        return ResponseEntity.ok( clientePJResponseDto );
    }

    @DeleteMapping("/{cnpj}")
    public void excluir (@PathVariable(name = "cnpj") @Valid String cnpj){
        // deletar cliente
        clientePJService.excluir(cnpj);
        System.out.println("deletar" + cnpj);
    }

    @GetMapping("/{cnpj}")
    public ResponseEntity<ClientePJResponseDto> getPorCnpj(@PathVariable @Valid String cnpj){
        System.out.println("getPorCnpj " + cnpj);
        return ResponseEntity.ok( clientePJService.buscarPorCnpj(cnpj) );
    }

    @GetMapping
    public ResponseEntity<List<ClientePJResponseDto>> listarTodos (ClientePJFilterDto filter){
        List<ClientePJResponseDto> listaCliesntesPJ = clientePJService.listarTodos(filter);
        System.out.println("getPorCpf " + listaCliesntesPJ);
        return ResponseEntity.ok(listaCliesntesPJ);
    }
}
