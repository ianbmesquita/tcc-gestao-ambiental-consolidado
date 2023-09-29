package br.pucmg.sigam.monitoramento.application.domain.habitante.mappers;

import br.pucmg.sigam.monitoramento.api.dtos.*;
import br.pucmg.sigam.monitoramento.application.domain.barragem.mappers.BarragemMapper;
import br.pucmg.sigam.monitoramento.application.domain.barragem.models.Barragem;
import br.pucmg.sigam.monitoramento.application.domain.habitante.models.Endereco;
import br.pucmg.sigam.monitoramento.application.domain.habitante.models.Habitante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class HabitanteMapper {
    @Autowired
    private BarragemMapper barragemMapper;

    public Habitante convertHabitanteRequestDTOToHabitanteEntity(final HabitanteRequestDTO requestDTO,
                                                                 final Barragem barragem) {
        return Habitante.builder()
                .nome(requestDTO.getNome())
                .nascimento(requestDTO.getNascimento())
                .telefone(requestDTO.getTelefone())
                .email(requestDTO.getEmail())
                .barragem(barragem)
                .endereco(convertEnderecoRequestDTOToEnderecoEntity(requestDTO.getEndereco()))
                .build();
    }

    private Endereco convertEnderecoRequestDTOToEnderecoEntity(final EnderecoRequestDTO requestDTO) {
        return Endereco.builder()
                .cep(requestDTO.getCep())
                .logradouro(requestDTO.getLogradouro())
                .numero(requestDTO.getNumero())
                .complemento(requestDTO.getComplemento())
                .bairro(requestDTO.getBairro())
                .municipio(requestDTO.getMunicipio())
                .estado(requestDTO.getEstado())
                .build();
    }

    public HabitanteResponseDTO convertHabitanteEntityToHabitanteResponseDTO(final Habitante habitante) {
        return HabitanteResponseDTO.builder()
                .id(habitante.getId())
                .nome(habitante.getNome())
                .nascimento(habitante.getNascimento())
                .telefone(habitante.getTelefone())
                .email(habitante.getEmail())
                .barragem_proxima(habitante.getBarragem().getNome())
                .endereco(convertEnderecoEntityToEnderecoResponseDTO(habitante.getEndereco()))
                .build();
    }

    private EnderecoResponseDTO convertEnderecoEntityToEnderecoResponseDTO(final Endereco endereco) {
        return EnderecoResponseDTO.builder()
                .id(endereco.getId())
                .cep(endereco.getCep())
                .logradouro(endereco.getLogradouro())
                .numero(endereco.getNumero())
                .complemento(endereco.getComplemento())
                .bairro(endereco.getBairro())
                .municipio(endereco.getMunicipio())
                .estado(endereco.getEstado())
                .build();
    }

    public List<HabitanteResponseDTO> convertListHabitanteEntityToListHabitanteResponseDTO(List<Habitante> habitantes) {
        List habitantesDTO = new ArrayList();

        for (Habitante habitante : habitantes) {
            habitantesDTO.add(convertHabitanteEntityToHabitanteResponseDTO(habitante));
        }

        return habitantesDTO;
    }

    public List<HabitanteEmailResponseDTO> convertListHabitanteEntityToListEmailHabitanteResponseDTO(List<Habitante> habitantes) {
        List habitantesDTO = new ArrayList();

        for (Habitante habitante : habitantes) {
            habitantesDTO.add(HabitanteEmailResponseDTO.builder().email(habitante.getEmail()).build());
        }

        return habitantesDTO;
    }

    public HabitanteInfoResponseDTO convertDataToHabitanteInfoResponseDTO(final List<Barragem> barragens,
                                                                          final List<EstadoResponseDTO> estados) {
        return HabitanteInfoResponseDTO.builder()
                .barragens(barragemMapper.convertListBarragemEntityToListBarragemResponseDTO(barragens))
                .estados(estados)
                .build();
    }
}
