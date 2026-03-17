package br.com.ucsal.olimpiadas.menu;

import br.com.ucsal.olimpiadas.input.Input;
import br.com.ucsal.olimpiadas.model.Prova;
import br.com.ucsal.olimpiadas.model.Questao;
import br.com.ucsal.olimpiadas.repository.Store;

import static br.com.ucsal.olimpiadas.common.CommonUtils.escolherProva;

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

        Prova prova = escolherProva(in, repository);
        if (prova == null) return;

        long provaId = prova.getId();

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
            correta = in.nextLine().trim().toUpperCase().charAt(0);
        } catch (Exception e) {
            System.out.println("entrada inválida");
            return;
        }

        System.out.println("FEN inicial:");
        String fen = in.nextLine();
        if (fen == null || fen.isBlank()) {
            System.out.println("FEN obrigatório");
            return;
        }

        try {
            Questao q = new Questao.QuestaoBuilder()
                    .id(repository.getProximaQuestaoId())
                    .provaId(provaId)
                    .enunciado(enunciado)
                    .alternativas(alternativas)
                    .alternativaCorreta(correta)
                    .fenInicial(fen)
                    .build();
            repository.adicionarQuestao(q);

            System.out.println(
                    "Questão cadastrada: " + q.getId() + " (na prova " + provaId + ")");
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar questão: " + e.getMessage());
        }
    }
}
