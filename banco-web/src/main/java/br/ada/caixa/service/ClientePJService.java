package br.ada.caixa.service;

import br.ada.caixa.dto.filter.ClientePJFilterDto;
import br.ada.caixa.dto.request.ClientePJRequestDto;
import br.ada.caixa.dto.response.ClientePFResponseDto;
import br.ada.caixa.dto.response.ClientePJResponseDto;
import br.ada.caixa.entity.ClientePJ;
import br.ada.caixa.entity.enums.StatusClienteEnum;
import br.ada.caixa.exceptions.ValidacaoException;
import br.ada.caixa.repository.ClientePJRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientePJService {

    final private ClientePJRepository clientePJRepository;
    final private ModelMapper modelMapper;

    // Injeção de dependência do ClientePJRepository no ClientePJService
    public ClientePJService(ClientePJRepository clientePJRepository, ModelMapper modelMapper) {
        this.clientePJRepository = clientePJRepository;
        this.modelMapper = modelMapper;
        this.modelMapper.typeMap(ClientePJRequestDto.class, ClientePJ.class)
                .addMapping(ClientePJRequestDto::getCnpj, ClientePJ::setDocumento)
                .addMapping(ClientePJRequestDto::getNomeFantasia, ClientePJ::setNome);
    }

    public ClientePJResponseDto criar (ClientePJRequestDto clientePJRequestDto){

        ClientePJ clientePJ = modelMapper.map(clientePJRequestDto, ClientePJ.class);
        clientePJ.setStatus(StatusClienteEnum.ATIVO);
        clientePJ.setDataCadastro(LocalDate.now());

        clientePJ = clientePJRepository.save(clientePJ);

        ClientePJResponseDto clientePJResponseDto = modelMapper.map(clientePJ, ClientePJResponseDto.class);


        return clientePJResponseDto;
    }

    public void excluir (String cnpj){
        clientePJRepository.deleteById(cnpj);
    }

    public  ClientePJResponseDto atualizar(String cnpj, ClientePJRequestDto clientePJRequestDto){
        return clientePJRepository.findById(cnpj)
                .map(clientePJ -> {
                    modelMapper.map(clientePJRequestDto, clientePJ);
                    /*
                    // forma de fazer sem o mapper
                    clientePJ.setCpf(clientePJRequestDto.getCpf());
                    clientePJ.setNome(clientePJRequestDto.getNome());
                    clientePJ.setStatus(clientePJRequestDto.getStatus());
                    clientePJ.setDataCadastro(clientePJRequestDto.getDataCadastro());
                    clientePJ.setContas(clientePJRequestDto.getContas());
                    */
                    return clientePJRepository.save(clientePJ);

                })
                .map(clientePJ -> modelMapper.map(clientePJ, ClientePJResponseDto.class))
                .orElseThrow(() -> new ValidacaoException("Cliente PJ não existe."));
    }

    public ClientePJResponseDto buscarPorCnpj (String cnpj){
        return clientePJRepository.findById(cnpj)
                .map(clientePJ -> modelMapper.map(clientePJ,ClientePJResponseDto.class))
                .orElseThrow(() -> new ValidacaoException("Cliente PJ não encontrado."));
    }

    public List<ClientePJResponseDto> listarTodos(ClientePJFilterDto filter){
        return clientePJRepository.pesquisar(
                        filter.getCnpj() != null ? filter.getCnpj() : null,
                        filter.getNomeFantasia() != null ? filter.getNomeFantasia() : null
                )
                .stream()
                .map(clientePJ -> modelMapper.map(clientePJ, ClientePJResponseDto.class))
                .collect(Collectors.toList());

//        return clientePJRepository.findAllByCnpjContainsIgnoreCase(filter.getCnpj())
//                .stream().map(clientePJ -> modelMapper.map(clientePJ, ClientePJResponseDto.class))
//                .collect((Collectors.toList()));
    }

    public List<ClientePJResponseDto> listarTodosPaginado(ClientePJFilterDto filter, int page, int size){
        return clientePJRepository.pesquisar(
                        filter.getCnpj() != null ? filter.getCnpj() : null,
                        filter.getNomeFantasia() != null ? filter.getNomeFantasia() : null
                )
                .stream()
                .map(clientePJ -> modelMapper.map(clientePJ, ClientePJResponseDto.class))
                .collect(Collectors.toList());
    }
}
