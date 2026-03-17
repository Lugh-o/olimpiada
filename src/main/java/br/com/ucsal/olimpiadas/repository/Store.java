package br.com.ucsal.olimpiadas.repository;

import br.com.ucsal.olimpiadas.model.Participante;
import br.com.ucsal.olimpiadas.model.Prova;
import br.com.ucsal.olimpiadas.model.Questao;
import br.com.ucsal.olimpiadas.model.Tentativa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Store {
    private long proximoParticipanteId = 1;
    private long proximaProvaId = 1;
    private long proximaQuestaoId = 1;
    private long proximaTentativaId = 1;

    private final List<Participante> participantes = new ArrayList<>();
    private final List<Prova> provas = new ArrayList<>();
    private final List<Questao> questoes = new ArrayList<>();
    private final List<Tentativa> tentativas = new ArrayList<>();

    public void seed() {
        Prova prova = new Prova.ProvaBuilder()
                .id(getProximaProvaId())
                .titulo("Olimpíada 2026 • Nível 1 • Prova A")
                .build();
        this.adicionarProva(prova);

        Questao q1 = new Questao.QuestaoBuilder()
                .id(getProximaQuestaoId())
                .provaId(prova.getId())
                .enunciado("""
                        Questão 1 — Mate em 1.
                        É a vez das brancas.
                        Encontre o lance que dá mate imediatamente.
                        """)
                .fenInicial("6k1/5ppp/8/8/8/7Q/6PP/6K1 w - - 0 1")
                .alternativas(new String[]{"A) Qh7#", "B) Qf5#", "C) Qc8#", "D) Qh8#", "E) Qe6#"})
                .alternativaCorreta('C')
                .build();
        this.adicionarQuestao(q1);
    }

    public long getProximoParticipanteId() {
        return this.proximoParticipanteId++;
    }

    public long getProximaProvaId() {
        return this.proximaProvaId++;
    }

    public long getProximaQuestaoId() {
        return this.proximaQuestaoId++;
    }

    public long getProximaTentativaId() {
        return this.proximaTentativaId++;
    }

    public List<Prova> getProvas() {
        return Collections.unmodifiableList(this.provas);
    }

    public List<Participante> getParticipantes() {
        return Collections.unmodifiableList(this.participantes);
    }

    public List<Questao> getQuestoes() {
        return Collections.unmodifiableList(this.questoes);
    }

    public List<Tentativa> getTentativas() {
        return Collections.unmodifiableList(this.tentativas);
    }

    public List<Questao> getQuestoesPorProva(long provaId) {
        return this.questoes.stream()
                .filter(q -> q.getProvaId() == provaId)
                .toList();
    }

    public void adicionarParticipante(Participante p) {
        this.participantes.add(p);
    }

    public void adicionarProva(Prova p) {
        this.provas.add(p);
    }

    public void adicionarQuestao(Questao q) {
        this.questoes.add(q);
    }

    public void adicionarTentativa(Tentativa t) {
        this.tentativas.add(t);
    }
}
