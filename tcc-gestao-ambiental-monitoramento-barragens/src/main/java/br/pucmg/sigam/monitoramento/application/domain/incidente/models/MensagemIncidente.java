package br.pucmg.sigam.monitoramento.application.domain.incidente.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MensagemIncidente implements Serializable {
    private String dataHora;
    private String grauRisco;
    private String tipoIncidente;
    private String origem;
    private String observacoes;
    private String idBarragem;
    private String nomeBarragem;
}
