package br.pucmg.sigam.monitoramento.api.controllers;

import br.pucmg.sigam.monitoramento.api.dtos.BarragemRequestDTO;
import br.pucmg.sigam.monitoramento.api.dtos.BarragemResponseDTO;
import br.pucmg.sigam.monitoramento.api.dtos.HabitanteEmailResponseDTO;
import br.pucmg.sigam.monitoramento.application.domain.barragem.services.BarragemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/barragens")
public class BarragemController {
    @Autowired
    private BarragemService service;

    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public ResponseEntity<List<BarragemResponseDTO>> getAllBarragens(@ModelAttribute BarragemRequestDTO requestDTO) {
        return ResponseEntity.ok().body(service.getAllBarragens(requestDTO));
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{id}")
    public ResponseEntity<BarragemResponseDTO> getBarragensById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.getBarragensById(id));
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public ResponseEntity<BarragemResponseDTO> saveNewBarragem(@Valid @RequestBody BarragemRequestDTO requestDTO) {
        return new ResponseEntity(service.saveNewBarragem(requestDTO), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/{id}")
    public ResponseEntity<BarragemResponseDTO> editBarragemById(@PathVariable Long id,
                                                                @Valid @RequestBody BarragemRequestDTO requestDTO)
            throws Exception {
        return ResponseEntity.ok().body(service.editBarragem(id, requestDTO));
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteBarragemById(@PathVariable Long id) {
        service.deleteBarragemById(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/habitantes")
    public ResponseEntity<List<HabitanteEmailResponseDTO>> getAllHabitantesByBarragemId(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.getAllHabitantesByBarragemId(id));
    }
}
