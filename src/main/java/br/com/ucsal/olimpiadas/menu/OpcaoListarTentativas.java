package br.com.ucsal.olimpiadas.menu;

import br.com.ucsal.olimpiadas.input.Input;
import br.com.ucsal.olimpiadas.model.Tentativa;
import br.com.ucsal.olimpiadas.store.Store;

import static br.com.ucsal.olimpiadas.common.CommmonUtils.calcularNota;

public class OpcaoListarTentativas extends OpcaoMenu {
    public OpcaoListarTentativas() {
        super("Listar tentativas (resumo)");
    }

    @Override
    void acao(Input in) {
        System.out.println("\n--- Tentativas ---");
        for (Tentativa t : Store.getTentativas()) {
            System.out.printf("#%d | participante=%d | prova=%d | nota=%d/%d%n", t.getId(), t.getParticipanteId(), t.getProvaId(), calcularNota(t), t.getRespostas().size());
        }
    }
}
