package br.com.ucsal.olimpiadas.menu;

import br.com.ucsal.olimpiadas.input.Input;
import br.com.ucsal.olimpiadas.model.Prova;
import br.com.ucsal.olimpiadas.store.Store;

public class OpcaoCadastrarProva extends OpcaoMenu {
    public OpcaoCadastrarProva() {
        super("Cadastrar prova");
    }

    @Override
    void acao(Input in) {
        System.out.print("Título da prova: ");
        String titulo = in.nextLine();

        if (titulo == null || titulo.isBlank()) {
            System.out.println("título inválido");
            return;
        }

        Prova prova = new Prova();
        prova.setId(Store.getProximaProvaId());
        prova.setTitulo(titulo);

        Store.adicionarProva(prova);
        System.out.println("Prova criada: " + prova.getId());
    }
}
