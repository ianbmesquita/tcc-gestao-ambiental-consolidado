package br.pucmg.sigam.monitoramento.api.controllers;

import br.pucmg.sigam.monitoramento.api.dtos.EstadoResponseDTO;
import br.pucmg.sigam.monitoramento.api.dtos.MunicipioResponseDTO;
import br.pucmg.sigam.monitoramento.application.domain.localidade.service.LocalidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/localidade")
public class LocalidadeController {
    @Autowired
    private LocalidadeService service;

    @GetMapping("/cep/{cep}")
    public ResponseEntity getLocalidadeByCEP(@PathVariable String cep) {
        return ResponseEntity.ok().body(service.consultarLocalidadeViaAPI(cep));
    }

    @GetMapping("/estados")
    public ResponseEntity<List<EstadoResponseDTO>> getEstados() {
        return ResponseEntity.ok().body(service.consultarEstadosBrasileiros());
    }

    @GetMapping("/estados/{id}/municipios")
    public ResponseEntity<List<MunicipioResponseDTO>> getMunicipiosByIdEstado(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.consultarMunicipiosPorIdEstado(id));
    }
}
