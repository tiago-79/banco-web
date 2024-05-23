package br.ada.caixa.config;

import br.ada.caixa.dto.request.ClientePFRequestDto;
import br.ada.caixa.dto.request.ClientePJRequestDto;
import br.ada.caixa.dto.response.ClientePFResponseDto;
import br.ada.caixa.dto.response.ClientePJResponseDto;
import br.ada.caixa.entity.ClientePF;
import br.ada.caixa.entity.ClientePJ;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class AppConfiguration {
    @Bean
    public ModelMapper getModelMapper(){
        Converter<String, LocalDate> dateConverter = new AbstractConverter<>() {
            @Override
            protected LocalDate convert(String s) {
                return null;
            }
        };

        ModelMapper modelMapper = new ModelMapper();

        modelMapper.typeMap(ClientePFRequestDto.class, ClientePF.class)
                .addMapping(ClientePFRequestDto::getCpf, ClientePF::setDocumento);

        modelMapper.typeMap(ClientePF.class, ClientePFResponseDto.class)
                .addMapping(ClientePF::getDocumento, ClientePFResponseDto::setCpf);

        modelMapper.typeMap(ClientePJRequestDto.class, ClientePJ.class)
                .addMapping(ClientePJRequestDto::getCnpj, ClientePJ::setDocumento)
                .addMapping(ClientePJRequestDto::getNomeFantasia, ClientePJ::setNome);

        modelMapper.typeMap(ClientePJ.class, ClientePJResponseDto.class)
                .addMapping(ClientePJ::getDocumento, ClientePJResponseDto::setCnpj)
                .addMapping(ClientePJ::getNome, ClientePJResponseDto::setNome);

        modelMapper.addConverter(dateConverter);

        return modelMapper;
    }

}
