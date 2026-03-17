package br.com.ucsal.olimpiadas.menu;

import br.com.ucsal.olimpiadas.common.CommonUtils;
import br.com.ucsal.olimpiadas.input.Input;
import br.com.ucsal.olimpiadas.model.Tentativa;
import br.com.ucsal.olimpiadas.repository.Store;

public class OpcaoListarTentativas extends OpcaoMenu {
    private final Store repository;

    public OpcaoListarTentativas(Store repository) {
        super("Listar tentativas (resumo)");
        this.repository = repository;
    }

    @Override
    void acao(Input in) {
        System.out.println("\n--- Tentativas ---");
        for (Tentativa t : repository.getTentativas()) {
            System.out.printf("#%d | participante=%d | prova=%d | nota=%d/%d%n",
                    t.getId(), t.getParticipanteId(),
                    t.getProvaId(),
                    CommonUtils.calcularNota(t),
                    t.getRespostas().size()
            );
        }
    }
}
