package br.com.ucsal.olimpiadas.model;

import java.util.Arrays;

public class Questao {
    private long id;
    private long provaId;
    private String enunciado;
    private String[] alternativas;
    private char alternativaCorreta;
    private String fenInicial;

    public Questao(QuestaoBuilder questaoBuilder) {
        this.id = questaoBuilder.id;
        this.provaId = questaoBuilder.provaId;
        this.enunciado = questaoBuilder.enunciado;
        this.alternativas = questaoBuilder.alternativas;
        this.alternativaCorreta = questaoBuilder.alternativaCorreta;
        this.fenInicial = questaoBuilder.fenInicial;
    }

    public String getFenInicial() {
        return fenInicial;
    }

    public void setFenInicial(String fenInicial) {
        this.fenInicial = fenInicial;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProvaId() {
        return provaId;
    }

    public void setProvaId(long provaId) {
        this.provaId = provaId;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public String[] getAlternativas() {
        return alternativas;
    }

    public void setAlternativas(String[] alternativas) {
        if (alternativas == null || alternativas.length != 5) {
            throw new IllegalArgumentException("A questão deve possuir exatamente 5 alternativas.");
        }
        this.alternativas = Arrays.copyOf(alternativas, 5);
    }

    public char getAlternativaCorreta() {
        return alternativaCorreta;
    }

    public void setAlternativaCorreta(char alternativaCorreta) {
        this.alternativaCorreta = normalizar(alternativaCorreta);
    }

    public boolean isRespostaCorreta(char marcada) {
        return normalizar(marcada) == alternativaCorreta;
    }

    public static char normalizar(char c) {
        char up = Character.toUpperCase(c);
        if (up < 'A' || up > 'E') {
            throw new IllegalArgumentException("Alternativa deve estar entre A e E.");
        }
        return up;
    }

    public static class QuestaoBuilder {
        private long id;
        private long provaId;
        private String enunciado;
        private String[] alternativas = new String[5];
        private char alternativaCorreta;
        private String fenInicial = "";

        public QuestaoBuilder id(long id) {
            this.id = id;
            return this;
        }

        public QuestaoBuilder provaId(long provaId) {
            this.provaId = provaId;
            return this;
        }

        public QuestaoBuilder enunciado(String enunciado) {
            this.enunciado = enunciado;
            return this;
        }

        public QuestaoBuilder alternativas(String[] alternativas) {
            if (alternativas == null || alternativas.length != 5) {
                throw new IllegalArgumentException("A questão deve possuir exatamente 5 alternativas.");
            }
            this.alternativas = Arrays.copyOf(alternativas, 5);
            return this;
        }

        public QuestaoBuilder alternativaCorreta(char alternativaCorreta) {
            char up = Character.toUpperCase(alternativaCorreta);
            if (up < 'A' || up > 'E') {
                throw new IllegalArgumentException("Alternativa deve estar entre A e E");
            }
            this.alternativaCorreta = up;
            return this;
        }

        public QuestaoBuilder fenInicial(String fenInicial) {
            if (fenInicial == null || fenInicial.isBlank()) {
                throw new IllegalArgumentException("FEN obrigatório");
            }

            String[] parts = fenInicial.split(" ");
            if (parts.length == 0) {
                throw new IllegalArgumentException("FEN inválido");
            }

            String[] ranks = parts[0].split("/");
            if (ranks.length != 8) {
                throw new IllegalArgumentException("FEN inválido (precisa ter 8 ranks)");
            }

            this.fenInicial = fenInicial;
            return this;
        }

        public Questao build() {
            return new Questao(this);
        }
    }
}
