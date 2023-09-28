package br.pucmg.sigam.monitoramento.api.controllers;

import br.pucmg.sigam.monitoramento.api.dtos.LeituraSensorRequestDTO;
import br.pucmg.sigam.monitoramento.api.dtos.SensorInfoResponseDTO;
import br.pucmg.sigam.monitoramento.api.dtos.SensorRequestDTO;
import br.pucmg.sigam.monitoramento.api.dtos.SensorResponseDTO;
import br.pucmg.sigam.monitoramento.application.domain.sensor.services.SensorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/sensores")
public class SensorController {
    @Autowired
    private SensorService service;

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/info")
    public ResponseEntity<SensorInfoResponseDTO> getaAllSensorDataScreen() {
        return ResponseEntity.ok().body(service.getaAllSensorDataScreen());
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public ResponseEntity<List<SensorResponseDTO>> getAllSensores() {
        return ResponseEntity.ok().body(service.getAllSensores());
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{id}")
    public ResponseEntity<SensorResponseDTO> getSensorById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.getSensorById(id));
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public ResponseEntity<SensorResponseDTO> saveNewSensor(@Valid @RequestBody SensorRequestDTO requestDTO) {
        return new ResponseEntity(service.saveNewSensor(requestDTO), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/{id}")
    public ResponseEntity<SensorResponseDTO> editSensorById(@PathVariable Long id,
                                                            @Valid @RequestBody SensorRequestDTO requestDTO)
            throws Exception {
        return ResponseEntity.ok().body(service.editSensor(id, requestDTO));
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteSensorById(@PathVariable Long id) {
        service.deleteSensorById(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/leitura")
    public ResponseEntity readSensorData(@PathVariable Long id, @Valid @RequestBody LeituraSensorRequestDTO requestDTO) {
        service.readSensorData(requestDTO, id);

        return ResponseEntity.noContent().build();
    }
}
