package br.com.ucsal.olimpiadas.menu;

import br.com.ucsal.olimpiadas.input.Input;
import br.com.ucsal.olimpiadas.model.Prova;
import br.com.ucsal.olimpiadas.repository.Store;

public class OpcaoCadastrarProva extends OpcaoMenu {
    private final Store repository;

    public OpcaoCadastrarProva(Store repository) {
        super("Cadastrar prova");
        this.repository = repository;
    }

    @Override
    void acao(Input in) {
        System.out.print("Título da prova: ");
        String titulo = in.nextLine();
        try {
            Prova prova = new Prova.ProvaBuilder()
                    .id(repository.getProximaProvaId())
                    .titulo(titulo)
                    .build();
            repository.adicionarProva(prova);
            System.out.println("Prova criada: " + prova.getId());
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar prova: " + e.getMessage());
        }
    }
}
