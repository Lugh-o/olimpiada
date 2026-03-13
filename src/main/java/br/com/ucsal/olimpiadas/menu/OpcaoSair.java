package br.com.ucsal.olimpiadas.menu;

import br.com.ucsal.olimpiadas.input.Input;

public class OpcaoSair extends OpcaoMenu {
    public OpcaoSair() {
        super("Sair");
    }

    @Override
    void acao(Input in) {
        System.out.println("Encerrando...");
        System.exit(0);
    }
}
