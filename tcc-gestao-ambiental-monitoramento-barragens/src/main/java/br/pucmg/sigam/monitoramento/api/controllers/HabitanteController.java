package br.pucmg.sigam.monitoramento.api.controllers;

import br.pucmg.sigam.monitoramento.api.dtos.HabitanteRequestDTO;
import br.pucmg.sigam.monitoramento.api.dtos.HabitanteResponseDTO;
import br.pucmg.sigam.monitoramento.application.domain.habitante.services.HabitanteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/habitantes")
public class HabitanteController {
    @Autowired
    private HabitanteService service;

    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public ResponseEntity<List<HabitanteResponseDTO>> getAllHabitantes() {
        return ResponseEntity.ok().body(service.getAllHabitantes());
    }

    @PostMapping
    public ResponseEntity<HabitanteResponseDTO> saveNewHabitante(@Valid @RequestBody HabitanteRequestDTO requestDTO) {
        return new ResponseEntity(service.saveNewHabitante(requestDTO), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/{id}")
    public ResponseEntity<HabitanteResponseDTO> editHabitanteById(@PathVariable Long id,
                                                                  @Valid @RequestBody HabitanteRequestDTO requestDTO)
            throws Exception {
        return ResponseEntity.ok().body(service.editHabitante(id, requestDTO));
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteHabitanteById(@PathVariable Long id) {
        service.deleteHabitanteById(id);

        return ResponseEntity.noContent().build();
    }
}
