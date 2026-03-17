package br.com.ucsal.olimpiadas.model;

public class Prova {
    private long id;
    private String titulo;

    public Prova(ProvaBuilder provaBuilder) {
        this.id = provaBuilder.id;
        this.titulo = provaBuilder.titulo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public static class ProvaBuilder {
        private long id;
        private String titulo;

        public ProvaBuilder id(long id) {
            this.id = id;
            return this;
        }

        public ProvaBuilder titulo(String titulo) {
            if (titulo == null || titulo.isBlank()) {
                throw new IllegalArgumentException("título inválido");
            }

            this.titulo = titulo;
            return this;
        }

        public Prova build() {
            return new Prova(this);
        }
    }
}
