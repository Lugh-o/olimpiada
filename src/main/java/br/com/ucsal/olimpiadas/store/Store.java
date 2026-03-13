package br.com.ucsal.olimpiadas.store;

import br.com.ucsal.olimpiadas.model.Participante;
import br.com.ucsal.olimpiadas.model.Prova;
import br.com.ucsal.olimpiadas.model.Questao;
import br.com.ucsal.olimpiadas.model.Tentativa;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Store {
    private static long proximoParticipanteId = 1;
    private static long proximaProvaId = 1;
    private static long proximaQuestaoId = 1;
    private static long proximaTentativaId = 1;

    private static final List<Participante> participantes = new ArrayList<>();
    private static final List<Prova> provas = new ArrayList<>();
    private static final List<Questao> questoes = new ArrayList<>();
    private static final List<Tentativa> tentativas = new ArrayList<>();

    public static void seed() {
        Prova prova = new Prova();
        prova.setId(getProximaProvaId());
        prova.setTitulo("Olimpíada 2026 • Nível 1 • Prova A");
        Store.adicionarProva(prova);

        Questao q1 = new Questao();
        q1.setId(getProximaQuestaoId());
        q1.setProvaId(prova.getId());

        q1.setEnunciado("""
                Questão 1 — Mate em 1.
                É a vez das brancas.
                Encontre o lance que dá mate imediatamente.
                """);

        q1.setFenInicial("6k1/5ppp/8/8/8/7Q/6PP/6K1 w - - 0 1");

        q1.setAlternativas(new String[]{"A) Qh7#", "B) Qf5#", "C) Qc8#", "D) Qh8#", "E) Qe6#"});

        q1.setAlternativaCorreta('C');

        adicionarQuestao(q1);
    }

    public static long getProximoParticipanteId() {
        return proximoParticipanteId++;
    }

    public static long getProximaProvaId() {
        return proximaProvaId++;
    }

    public static long getProximaQuestaoId() {
        return proximaQuestaoId++;
    }

    public static long getProximaTentativaId() {
        return proximaTentativaId++;
    }

    public static List<Prova> getProvas() {
        return Collections.unmodifiableList(provas);
    }

    public static List<Participante> getParticipantes() {
        return Collections.unmodifiableList(participantes);
    }

    public static List<Questao> getQuestoes() {
        return Collections.unmodifiableList(questoes);
    }

    public static List<Tentativa> getTentativas() {
        return Collections.unmodifiableList(tentativas);
    }

    public static void adicionarParticipante(Participante p) {
        participantes.add(p);
    }

    public static void adicionarProva(Prova p) {
        provas.add(p);
    }

    public static void adicionarQuestao(Questao q) {
        questoes.add(q);
    }

    public static void adicionarTentativa(Tentativa t) {
        tentativas.add(t);
    }
}
