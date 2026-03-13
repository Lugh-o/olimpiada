package br.com.ucsal.olimpiadas.menu;

import br.com.ucsal.olimpiadas.input.Input;
import br.com.ucsal.olimpiadas.model.*;
import br.com.ucsal.olimpiadas.store.Store;
import java.util.List;
import static br.com.ucsal.olimpiadas.common.CommmonUtils.calcularNota;
import static br.com.ucsal.olimpiadas.common.CommmonUtils.escolherProva;

public class OpcaoAplicarProva extends OpcaoMenu {
    public OpcaoAplicarProva() {
        super("Aplicar prova (selecionar participante + prova)");
    }

    @Override
    void acao(Input in) {
        if (Store.getParticipantes().isEmpty()) {
            System.out.println("cadastre participantes primeiro");
            return;
        }
        if (Store.getProvas().isEmpty()) {
            System.out.println("cadastre provas primeiro");
            return;
        }

        Long participanteId = escolherParticipante(in);
        if (participanteId == null) return;

        Long provaId = escolherProva(in);
        if (provaId == null) return;

        List<Questao> questoesDaProva = Store.getQuestoes().stream().filter(q -> q.getProvaId() == provaId).toList();

        if (questoesDaProva.isEmpty()) {
            System.out.println("esta prova não possui questões cadastradas");
            return;
        }

        Tentativa tentativa = new Tentativa();
        tentativa.setId(Store.getProximaTentativaId());
        tentativa.setParticipanteId(participanteId);
        tentativa.setProvaId(provaId);

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

            Resposta r = new Resposta();
            r.setQuestaoId(q.getId());
            r.setAlternativaMarcada(marcada);
            r.setCorreta(q.isRespostaCorreta(marcada));

            tentativa.getRespostas().add(r);
        }

        Store.adicionarTentativa(tentativa);

        int nota = calcularNota(tentativa);
        System.out.println("\n--- Fim da Prova ---");
        System.out.println("Nota (acertos): " + nota + " / " + tentativa.getRespostas().size());
    }

    private Long escolherParticipante(Input in) {
        System.out.println("\nParticipantes:");
        for (Participante p : Store.getParticipantes()) {
            System.out.printf("  %d) %s%n", p.getId(), p.getNome());
        }
        System.out.print("Escolha o id do participante: ");

        try {
            long id = Long.parseLong(in.nextLine());
            boolean existe = Store.getParticipantes().stream().anyMatch(p -> p.getId() == id);
            if (!existe) {
                System.out.println("id inválido");
                return null;
            }
            return id;
        } catch (Exception e) {
            System.out.println("entrada inválida");
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
