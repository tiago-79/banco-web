package br.ada.caixa.controller;

import br.ada.caixa.dto.filter.ClientePFFilterDto;
import br.ada.caixa.dto.request.ClientePFRequestDto;
import br.ada.caixa.dto.response.ClientePFResponseDto;
import br.ada.caixa.dto.response.ClientePFResponsePageDto;
import br.ada.caixa.entity.ClientePF;
import br.ada.caixa.service.ClientePFService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/clientesPf")
public class ClientePFController {

    final private ClientePFService clientePFService;

    public ClientePFController(ClientePFService clientePFService){
        this.clientePFService = clientePFService;
    }

    @PostMapping("/criar")
    public ResponseEntity<ClientePFResponseDto> criar (@RequestBody @Valid ClientePFRequestDto clientePFRequestDto){
        ClientePFResponseDto clientePFResponseDto = clientePFService.criar(clientePFRequestDto);
        System.out.println("criar" + clientePFRequestDto.toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(clientePFResponseDto);
    }
    @PutMapping("/{cpf}")
    public ResponseEntity<ClientePFResponseDto> atualizar (@PathVariable String cpf,
                                                           @RequestBody @Valid ClientePFRequestDto clientePF){
        // atualizar cliente
        ClientePFResponseDto clientePFResponseDto = clientePFService.atualizar(cpf, clientePF);
        return ResponseEntity.ok( clientePFResponseDto );
    }

    @DeleteMapping("/{cpf}")
    public void excluir (@PathVariable(name = "cpf") @Valid String cpf){
        // deletar cliente
        clientePFService.excluir(cpf);
    }
    @GetMapping("/{cpf}")
    public ResponseEntity<ClientePFResponseDto> getPorCpf(@PathVariable @Valid String cpf){
        return ResponseEntity.ok( clientePFService.buscarPorCpf(cpf) );
    }

    @GetMapping
    public ResponseEntity<List<ClientePFResponseDto>> listarTodos (ClientePFFilterDto filter){
        List<ClientePFResponseDto> listaCliesntesPF = clientePFService.listarTodos(filter);
        return ResponseEntity.ok(listaCliesntesPF);
    }

    @GetMapping("/paginado")
    public ResponseEntity<ClientePFResponsePageDto> listarTodosPaginado (ClientePFFilterDto filter,
                                                                         @RequestParam(defaultValue = "0") int page,
                                                                         @RequestParam(defaultValue = "5") int size){
        ClientePFResponsePageDto listaCliesntesPF = clientePFService.listarTodosPaginado(filter, page, size);
        return ResponseEntity.ok(listaCliesntesPF);
    }
}
