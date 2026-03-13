package br.com.ucsal.olimpiadas;

import br.com.ucsal.olimpiadas.input.ConsoleInput;
import br.com.ucsal.olimpiadas.input.Input;
import br.com.ucsal.olimpiadas.menu.*;
import br.com.ucsal.olimpiadas.model.*;
import br.com.ucsal.olimpiadas.store.Store;
import java.util.List;
import java.util.Scanner;

public class App {
    static void main() {
        Input in = new ConsoleInput(new Scanner(System.in));
        List<OpcaoMenu> opcoes = List.of(
                new OpcaoCadastrarParticipante(),
                new OpcaoCadastrarProva(),
                new OpcaoCadastrarQuestao(),
                new OpcaoAplicarProva(),
                new OpcaoListarTentativas(),
                new OpcaoSair());
        Menu menu = new Menu(in, opcoes);
        Store.seed();

        while (true) {
            menu.executar();
        }
    }
}