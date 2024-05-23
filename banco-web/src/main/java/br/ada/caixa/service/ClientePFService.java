package br.ada.caixa.service;

import br.ada.caixa.dto.filter.ClientePFFilterDto;
import br.ada.caixa.dto.request.ClientePFRequestDto;
import br.ada.caixa.dto.response.ClientePFResponseDto;
import br.ada.caixa.entity.ClientePF;
import br.ada.caixa.entity.ContaCorrente;
import br.ada.caixa.entity.enums.StatusClienteEnum;
import br.ada.caixa.exceptions.ValidacaoException;
import br.ada.caixa.repository.ClientePFRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientePFService {

    final private ClientePFRepository clientePFRepository;
    final private ModelMapper modelMapper;

    // Injeção de dependência do ClientePFRepository no ClientePFService
    public ClientePFService(ClientePFRepository clientePFRepository, ModelMapper modelMapper) {
        this.clientePFRepository = clientePFRepository;
        this.modelMapper = modelMapper;
        this.modelMapper.typeMap(ClientePFRequestDto.class, ClientePF.class)
                .addMapping(ClientePFRequestDto::getCpf, ClientePF::setDocumento);
    }

    public ClientePFResponseDto criar (ClientePFRequestDto clientePFRequestDto){

        ClientePF clientePF = modelMapper.map(clientePFRequestDto, ClientePF.class);
        clientePF.setStatus(StatusClienteEnum.ATIVO);
        clientePF.setDataCadastro(LocalDate.now());

        ContaCorrente contaCorrente = new ContaCorrente();
        contaCorrente.setCliente(clientePF);
        contaCorrente.setSaldo(BigDecimal.ZERO);

        clientePF.setContas(new ArrayList<>());
        clientePF.getContas().add(contaCorrente);

        clientePF = clientePFRepository.save(clientePF);

        ClientePFResponseDto clientePFResponseDto = modelMapper.map(clientePF, ClientePFResponseDto.class);


        return clientePFResponseDto;
    }

    public void excluir (String cpf){
        clientePFRepository.deleteById(cpf);
    }

    public  ClientePFResponseDto atualizar(String cpf, ClientePFRequestDto clientePFRequestDto){
        return clientePFRepository.findById(cpf)
                .map(clientePF -> {
                    modelMapper.map(clientePFRequestDto, clientePF);
                    /*
                    // forma de fazer sem o mapper
                    clientePF.setCpf(clientePFRequestDto.getCpf());
                    clientePF.setNome(clientePFRequestDto.getNome());
                    clientePF.setStatus(clientePFRequestDto.getStatus());
                    clientePF.setDataCadastro(clientePFRequestDto.getDataCadastro());
                    clientePF.setContas(clientePFRequestDto.getContas());
                    */
                    return clientePFRepository.save(clientePF);

                })
                .map(clientePF -> modelMapper.map(clientePF, ClientePFResponseDto.class))
                .orElseThrow(() -> new ValidacaoException("Cliente PF não existe."));
    }

    public ClientePFResponseDto buscarPorCpf (String cpf){
        return clientePFRepository.findById(cpf)
                .map(clientePF -> modelMapper.map(clientePF,ClientePFResponseDto.class))
                .orElseThrow(() -> new ValidacaoException("Cliente PF não existe."));
    }

    public List<ClientePFResponseDto> listarTodos(ClientePFFilterDto filter){
        return clientePFRepository.findAllByCpfContainsIgnoreCase(filter.getCpf())
                .stream().map(clientePF -> modelMapper.map(clientePF, ClientePFResponseDto.class))
                .collect((Collectors.toList()));
    }
}
