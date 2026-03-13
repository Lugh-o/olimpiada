package br.com.ucsal.olimpiadas.menu;

import br.com.ucsal.olimpiadas.input.Input;
import br.com.ucsal.olimpiadas.model.Questao;
import br.com.ucsal.olimpiadas.repository.Store;

import static br.com.ucsal.olimpiadas.common.CommmonUtils.escolherProva;

public class OpcaoCadastrarQuestao extends OpcaoMenu {
    private final Store repository;

    public OpcaoCadastrarQuestao(Store repository) {
        super("Cadastrar questão (A–E) em uma prova");
        this.repository = repository;
    }

    @Override
    void acao(Input in) {
        if (repository.getProvas().isEmpty()) {
            System.out.println("não há provas cadastradas");
            return;
        }

        Long provaId = escolherProva(in, repository);
        if (provaId == null) return;

        System.out.println("Enunciado:");
        String enunciado = in.nextLine();

        String[] alternativas = new String[5];
        for (int i = 0; i < 5; i++) {
            char letra = (char) ('A' + i);
            System.out.print("Alternativa " + letra + ": ");
            alternativas[i] = letra + ") " + in.nextLine();
        }

        System.out.print("Alternativa correta (A–E): ");
        char correta;
        try {
            correta = Questao.normalizar(in.nextLine().trim().charAt(0));
        } catch (Exception e) {
            System.out.println("alternativa inválida");
            return;
        }

        // TODO Builder
        Questao q = new Questao();
        q.setId(repository.getProximaQuestaoId());
        q.setProvaId(provaId);
        q.setEnunciado(enunciado);
        q.setAlternativas(alternativas);
        q.setAlternativaCorreta(correta);

        repository.adicionarQuestao(q);

        System.out.println("Questão cadastrada: " + q.getId() + " (na prova " + provaId + ")");
    }
}
