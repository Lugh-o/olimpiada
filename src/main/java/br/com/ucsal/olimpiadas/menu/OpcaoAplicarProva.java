package br.com.ucsal.olimpiadas.menu;

import br.com.ucsal.olimpiadas.common.FenUtils;
import br.com.ucsal.olimpiadas.common.ParticipanteUtils;
import br.com.ucsal.olimpiadas.input.Input;
import br.com.ucsal.olimpiadas.model.*;
import br.com.ucsal.olimpiadas.repository.Store;

import java.util.List;

import static br.com.ucsal.olimpiadas.common.CommonUtils.calcularNota;
import static br.com.ucsal.olimpiadas.common.CommonUtils.escolherProva;

public class OpcaoAplicarProva extends OpcaoMenu {
    private final Store repository;

    public OpcaoAplicarProva(Store repository) {
        super("Aplicar prova (selecionar participante + prova)");
        this.repository = repository;
    }

    @Override
    void acao(Input in) {
        if (repository.getParticipantes().isEmpty()) {
            System.out.println("Cadastre participantes primeiro.");
            return;
        }
        if (repository.getProvas().isEmpty()) {
            System.out.println("Cadastre provas primeiro.");
            return;
        }

        Long participanteId = ParticipanteUtils.escolherParticipante(in, repository);
        if (participanteId == null) return;

        Prova prova = escolherProva(in, repository);
        if (prova == null) return;

        long provaId = prova.getId();

        List<Questao> questoesDaProva = repository.getQuestoesPorProva(provaId);

        if (questoesDaProva.isEmpty()) {
            System.out.println("Esta prova não possui questões cadastradas.");
            return;
        }

        Tentativa tentativa = new Tentativa.TentativaBuilder()
                .id(repository.getProximaTentativaId())
                .participanteId(participanteId)
                .provaId(provaId)
                .build();

        System.out.println("\n--- Início da Prova ---");

        for (Questao q : questoesDaProva) {
            System.out.println("\nQuestão #" + q.getId());
            System.out.println(q.getEnunciado());

            System.out.println("Posição inicial:");
            FenUtils.imprimirTabuleiroFen(q.getFenInicial());

            for (String alt : q.getAlternativas()) {
                System.out.println(alt);
            }

            System.out.print("Sua resposta (A–E): ");

            char marcada;
            try {
                marcada = in.nextLine().trim().toUpperCase().charAt(0);
            } catch (Exception e) {
                marcada = 'X';
            }

            boolean correta = Resposta.verificar(q, marcada);

            Resposta r = new Resposta.RespostaBuilder()
                    .questaoId(q.getId())
                    .alternativaMarcada(marcada)
                    .correta(correta)
                    .build();

            tentativa.adicionarResposta(r);
        }

        repository.adicionarTentativa(tentativa);

        int nota = calcularNota(tentativa);
        System.out.println("\n--- Fim da Prova ---");
        System.out.println("Nota (acertos): " + nota + " / " + tentativa.getRespostas().size());
    }


}
