package br.com.ucsal.olimpiadas.menu;

import br.com.ucsal.olimpiadas.input.Input;
import br.com.ucsal.olimpiadas.model.Participante;
import br.com.ucsal.olimpiadas.store.Store;

public class OpcaoCadastrarParticipante extends OpcaoMenu {
    public OpcaoCadastrarParticipante() {
        super("Cadastrar participante");
    }

    @Override
    void acao(Input in) {
        System.out.print("Nome: ");
        String nome = in.nextLine();

        System.out.print("Email (opcional): ");
        String email = in.nextLine();

        if (nome == null || nome.isBlank()) {
            System.out.println("nome inválido");
            return;
        }

        Participante p = new Participante();
        p.setId(Store.getProximoParticipanteId());
        p.setNome(nome);
        p.setEmail(email);

        Store.adicionarParticipante(p);
        System.out.println("Participante cadastrado: " + p.getId());
    }
}
