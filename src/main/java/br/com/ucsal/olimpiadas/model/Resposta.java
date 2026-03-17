package br.com.ucsal.olimpiadas.model;

public class Resposta {
    private long questaoId;
    private char alternativaMarcada;
    private boolean correta;

    private Resposta(RespostaBuilder respostaBuilder) {
        this.questaoId = respostaBuilder.questaoId;
        this.alternativaMarcada = respostaBuilder.alternativaMarcada;
        this.correta = respostaBuilder.correta;
    }

    public long getQuestaoId() {
        return questaoId;
    }

    public void setQuestaoId(long questaoId) {
        this.questaoId = questaoId;
    }

    public char getAlternativaMarcada() {
        return alternativaMarcada;
    }

    public void setAlternativaMarcada(char alternativaMarcada) {
        this.alternativaMarcada = alternativaMarcada;
    }

    public boolean isCorreta() {
        return correta;
    }

    public void setCorreta(boolean correta) {
        this.correta = correta;
    }

    public static boolean verificar(Questao q, char marcada) {
        return Character.toUpperCase(marcada) == Character.toUpperCase(q.getAlternativaCorreta());
    }

    public static class RespostaBuilder {
        private long questaoId;
        private char alternativaMarcada;
        private boolean correta;

        public RespostaBuilder questaoId(long questaoId) {
            this.questaoId = questaoId;
            return this;
        }

        public RespostaBuilder alternativaMarcada(char alternativaMarcada) {
            this.alternativaMarcada = alternativaMarcada;
            return this;
        }

        public RespostaBuilder correta(boolean correta) {
            this.correta = correta;
            return this;
        }

        public Resposta build() {
            return new Resposta(this);
        }
    }
}