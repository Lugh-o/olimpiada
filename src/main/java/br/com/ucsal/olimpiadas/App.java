package br.com.ucsal.olimpiadas;

import br.com.ucsal.olimpiadas.input.ConsoleInput;
import br.com.ucsal.olimpiadas.input.Input;
import br.com.ucsal.olimpiadas.menu.*;
import br.com.ucsal.olimpiadas.repository.Store;
import java.util.List;
import java.util.Scanner;

public class App {
    static void main() {
        Store repository = new Store();
        Input in = new ConsoleInput(new Scanner(System.in));
        List<OpcaoMenu> opcoes = List.of(
                new OpcaoCadastrarParticipante(repository),
                new OpcaoCadastrarProva(repository),
                new OpcaoCadastrarQuestao(repository),
                new OpcaoAplicarProva(repository),
                new OpcaoListarTentativas(repository),
                new OpcaoSair());
        Menu menu = new Menu(in, opcoes);
        repository.seed();

        while (true) {
            menu.executar();
        }
    }
}