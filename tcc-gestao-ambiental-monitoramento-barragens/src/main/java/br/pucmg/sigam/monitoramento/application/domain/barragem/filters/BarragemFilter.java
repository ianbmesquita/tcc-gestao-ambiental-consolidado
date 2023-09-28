package br.pucmg.sigam.monitoramento.application.domain.barragem.filters;

import br.pucmg.sigam.monitoramento.application.domain.barragem.models.ClassificacaoRisco;
import br.pucmg.sigam.monitoramento.application.domain.barragem.models.StatusBarragem;
import br.pucmg.sigam.monitoramento.application.domain.barragem.models.TipoBarragem;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BarragemFilter {
    private String nome;
    private TipoBarragem tipo;
    private String municipio;
    private String estado;
    private ClassificacaoRisco risco;
    private StatusBarragem status;
}
