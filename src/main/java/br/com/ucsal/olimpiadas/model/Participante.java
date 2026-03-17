package br.com.ucsal.olimpiadas.model;

public class Participante {
    private long id;
    private String nome;
    private String email;

    public Participante(ParticipanteBuilder participanteBuilder) {
        this.id = participanteBuilder.id;
        this.nome = participanteBuilder.nome;
        this.email = participanteBuilder.email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static class ParticipanteBuilder {
        private long id;
        private String nome;
        private String email;

        public ParticipanteBuilder id(long id) {
            this.id = id;
            return this;
        }

        public ParticipanteBuilder nome(String nome) {
            if (nome == null || nome.isBlank()) {
                throw new IllegalArgumentException("nome inválido");
            }

            this.nome = nome;
            return this;
        }

        public ParticipanteBuilder email(String email) {
            this.email = email;
            return this;
        }

        public Participante build() {
            return new Participante(this);
        }
    }
}
