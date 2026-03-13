package br.com.ucsal.olimpiadas.menu;

import br.com.ucsal.olimpiadas.input.Input;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private final Input in;
    private final ArrayList<OpcaoMenu> listaOpcoes;

    public Menu(Input in, List<OpcaoMenu> listaOpcoes) {
        this.in = in;
        this.listaOpcoes = new ArrayList<>(listaOpcoes);
    }

    public void printOpcoes() {
        System.out.println("\n=== OLIMPÍADA DE QUESTÕES (V2) ===");
        for (int i = 0; i < listaOpcoes.size(); i++) {
            System.out.println((i + 1) + ") " + listaOpcoes.get(i).getTexto());
        }
        System.out.print("> ");
    }

    public void executar() {
        printOpcoes();

        String entrada = in.nextLine();

        int entradaInteira;
        try {
            entradaInteira = Integer.parseInt(entrada) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Opção inválida.");
            return;
        }

        if (entradaInteira < 0 || entradaInteira >= listaOpcoes.size()) {
            System.out.println("Opção inválida.");
            return;
        }

        listaOpcoes.get(entradaInteira).acao(in);
    }
}
