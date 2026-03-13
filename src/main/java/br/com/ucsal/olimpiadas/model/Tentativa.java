package br.com.ucsal.olimpiadas.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tentativa {
    private long id;
    private long participanteId;
    private long provaId;
    private final List<Resposta> respostas = new ArrayList<>();

    public Tentativa(TentativaBuilder tentativaBuilder) {
        this.id = tentativaBuilder.id;
        this.participanteId = tentativaBuilder.participanteId;
        this.provaId = tentativaBuilder.provaId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getParticipanteId() {
        return participanteId;
    }

    public void setParticipanteId(long participanteId) {
        this.participanteId = participanteId;
    }

    public long getProvaId() {
        return provaId;
    }

    public void setProvaId(long provaId) {
        this.provaId = provaId;
    }

    public List<Resposta> getRespostas() {
        return Collections.unmodifiableList(this.respostas);
    }

    public void adicionarResposta(Resposta r) {
        this.respostas.add(r);
    }

    public static class TentativaBuilder {
        private long id;
        private long participanteId;
        private long provaId;

        public TentativaBuilder id(long id) {
            this.id = id;
            return this;
        }

        public TentativaBuilder participanteId(long participanteId) {
            this.participanteId = participanteId;
            return this;
        }

        public TentativaBuilder provaId(long provaId) {
            this.provaId = provaId;
            return this;
        }

        public Tentativa build() {
            return new Tentativa(this);
        }
    }

}
