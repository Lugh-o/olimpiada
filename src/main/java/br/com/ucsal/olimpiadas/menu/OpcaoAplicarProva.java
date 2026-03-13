package br.com.ucsal.olimpiadas.menu;

import br.com.ucsal.olimpiadas.input.Input;
import br.com.ucsal.olimpiadas.model.*;
import br.com.ucsal.olimpiadas.repository.Store;

import java.util.List;

import static br.com.ucsal.olimpiadas.common.CommmonUtils.calcularNota;
import static br.com.ucsal.olimpiadas.common.CommmonUtils.escolherProva;

// TODO SRP
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

        Long participanteId = escolherParticipante(in);
        if (participanteId == null) return;

        Long provaId = escolherProva(in, repository);
        if (provaId == null) return;

        List<Questao> questoesDaProva = repository.getQuestoes().stream().filter(q -> q.getProvaId() == provaId).toList();

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
            imprimirTabuleiroFen(q.getFenInicial());

            for (String alt : q.getAlternativas()) {
                System.out.println(alt);
            }

            System.out.print("Sua resposta (A–E): ");
            char marcada;
            try {
                marcada = Questao.normalizar(in.nextLine().trim().charAt(0));
            } catch (Exception e) {
                System.out.println("resposta inválida (marcando como errada)");
                marcada = 'X';
            }

            Resposta r = new Resposta.RespostaBuilder()
                    .questaoId(q.getId())
                    .alternativaMarcada(marcada)
                    .correta(q.isRespostaCorreta(marcada))
                    .build();

            tentativa.adicionarResposta(r);
        }

        repository.adicionarTentativa(tentativa);

        int nota = calcularNota(tentativa);
        System.out.println("\n--- Fim da Prova ---");
        System.out.println("Nota (acertos): " + nota + " / " + tentativa.getRespostas().size());
    }

    private Long escolherParticipante(Input in) {
        System.out.println("\nParticipantes:");
        for (Participante p : repository.getParticipantes()) {
            System.out.printf("  %d) %s%n", p.getId(), p.getNome());
        }
        System.out.print("Escolha o id do participante: ");

        try {
            long id = Long.parseLong(in.nextLine());
            boolean existe = repository.getParticipantes().stream().anyMatch(p -> p.getId() == id);
            if (!existe) {
                System.out.println("Id inválido.");
                return null;
            }
            return id;
        } catch (Exception e) {
            System.out.println("Entrada inválida.");
            return null;
        }
    }

    private void imprimirTabuleiroFen(String fen) {
        String parteTabuleiro = fen.split(" ")[0];
        String[] ranks = parteTabuleiro.split("/");

        System.out.println();
        System.out.println("    a b c d e f g h");
        System.out.println("   -----------------");

        for (int r = 0; r < 8; r++) {

            String rank = ranks[r];
            System.out.print((8 - r) + " | ");

            for (char c : rank.toCharArray()) {

                if (Character.isDigit(c)) {
                    int vazios = c - '0';
                    for (int i = 0; i < vazios; i++) {
                        System.out.print(". ");
                    }
                } else {
                    System.out.print(c + " ");
                }
            }

            System.out.println("| " + (8 - r));
        }

        System.out.println("   -----------------");
        System.out.println("    a b c d e f g h");
        System.out.println();
    }
}
