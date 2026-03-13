package br.com.ucsal.olimpiadas.menu;

import br.com.ucsal.olimpiadas.input.Input;

public abstract class OpcaoMenu {

    private final String texto;

    public OpcaoMenu(String texto) {
        if (texto == null || texto.isBlank()) {
            throw new IllegalArgumentException("texto inválido");
        }
        this.texto = texto;
    }

    public String getTexto() {
        return texto;
    }

    abstract void acao(Input in);
}