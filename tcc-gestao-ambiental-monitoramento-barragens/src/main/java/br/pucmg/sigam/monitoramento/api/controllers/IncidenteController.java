package br.pucmg.sigam.monitoramento.api.controllers;

import br.pucmg.sigam.monitoramento.api.dtos.IncidenteInfoResponse;
import br.pucmg.sigam.monitoramento.api.dtos.IncidenteRequestDTO;
import br.pucmg.sigam.monitoramento.api.dtos.IncidenteResponseDTO;
import br.pucmg.sigam.monitoramento.application.domain.incidente.services.IncidenteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/v1/incidentes")
public class IncidenteController {
    @Autowired
    private IncidenteService service;

    @PreAuthorize("hasRole('OUTSOURCED')")
    @GetMapping("/info")
    public ResponseEntity<IncidenteInfoResponse> getDataFormHabitantes() {
        return ResponseEntity.ok().body(service.getDataFormHabitantes());
    }

    @PreAuthorize("hasRole('OUTSOURCED')")
    @GetMapping
    public ResponseEntity<List<IncidenteResponseDTO>> getAllIncidentes() {
        return ResponseEntity.ok().body(service.getAllIncidentes());
    }

    @PreAuthorize("hasRole('OUTSOURCED')")
    @PostMapping
    public ResponseEntity<IncidenteResponseDTO> saveNewIncidente(@Valid @RequestBody IncidenteRequestDTO requestDTO) {
        return new ResponseEntity(service.saveNewIncidente(requestDTO), HttpStatus.CREATED);
    }
}
