package br.pucmg.sigam.monitoramento.application.domain.barragem.services;

import br.pucmg.sigam.monitoramento.api.dtos.BarragemRequestDTO;
import br.pucmg.sigam.monitoramento.api.dtos.BarragemResponseDTO;
import br.pucmg.sigam.monitoramento.api.dtos.HabitanteEmailResponseDTO;
import br.pucmg.sigam.monitoramento.application.domain.barragem.mappers.BarragemMapper;
import br.pucmg.sigam.monitoramento.application.domain.barragem.models.ClassificacaoRisco;
import br.pucmg.sigam.monitoramento.application.domain.barragem.models.StatusBarragem;
import br.pucmg.sigam.monitoramento.application.domain.barragem.models.TipoBarragem;
import br.pucmg.sigam.monitoramento.application.domain.habitante.services.HabitanteService;
import br.pucmg.sigam.monitoramento.infra.dataproviders.repositories.BarragemRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.pucmg.sigam.monitoramento.utils.Messages.BARRAGEM_NAO_ENCONTRADA;

@Service
public class BarragemService {
    @Autowired
    private BarragemRepository barragemRepository;

    @Autowired
    private HabitanteService habitanteService;

    @Autowired
    private BarragemMapper mapper;

    public List<BarragemResponseDTO> getAllBarragens(final BarragemRequestDTO requestDTO) {
        var barragens = barragemRepository.findBarragensByParams(mapper.convertBarragemRequestDTOToBarragemFilter(requestDTO));
        return mapper.convertListBarragemEntityToListBarragemResponseDTO(barragens);
    }

    public BarragemResponseDTO getBarragensById(final Long id) {
        var barragemSolicitada = barragemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(BARRAGEM_NAO_ENCONTRADA, id)));

        return mapper.convertBarragemEntityToBarragemResponseDTO(barragemSolicitada);
    }

    public BarragemResponseDTO saveNewBarragem(final BarragemRequestDTO requestDTO) {
        var barragem = barragemRepository.save(mapper.convertBarragemRequestDTOToBarragemEntity(requestDTO));

        return mapper.convertBarragemEntityToBarragemResponseDTO(barragem);
    }

    public BarragemResponseDTO editBarragem(final Long id, final BarragemRequestDTO requestDTO) {
        var barragemSolicitada = barragemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(BARRAGEM_NAO_ENCONTRADA, id)));

        barragemSolicitada.setNome(requestDTO.getNome());
        barragemSolicitada.setTipo(TipoBarragem.valueOf(requestDTO.getTipo()));
        barragemSolicitada.setMunicipio(requestDTO.getMunicipio());
        barragemSolicitada.setEstado(requestDTO.getEstado());
        barragemSolicitada.setRisco(ClassificacaoRisco.valueOf(requestDTO.getRisco()));
        barragemSolicitada.setStatus(StatusBarragem.valueOf(requestDTO.getStatus()));

        var barragemAtualizada = barragemRepository.save(barragemSolicitada);

        return mapper.convertBarragemEntityToBarragemResponseDTO(barragemAtualizada);
    }

    public void deleteBarragemById(final Long id) {
        var barragem = barragemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(BARRAGEM_NAO_ENCONTRADA, id)));

        barragemRepository.delete(barragem);
    }

    public List<HabitanteEmailResponseDTO> getAllHabitantesByBarragemId(final Long id) {
        var barragem = barragemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(BARRAGEM_NAO_ENCONTRADA, id)));

        return habitanteService.getAllHabitantesByBarragemId(barragem.getId());
    }
}
