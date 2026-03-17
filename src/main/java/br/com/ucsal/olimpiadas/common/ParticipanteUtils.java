package br.com.ucsal.olimpiadas.common;

import br.com.ucsal.olimpiadas.input.Input;
import br.com.ucsal.olimpiadas.model.Participante;
import br.com.ucsal.olimpiadas.repository.Store;

public class ParticipanteUtils {
    public static Long escolherParticipante(Input in, Store repository) {
        System.out.println("\nParticipantes:");
        for (Participante p : repository.getParticipantes()) {
            System.out.printf("  %d) %s%n", p.getId(), p.getNome());
        }
        System.out.print("Escolha o id do participante: ");

        try {
            long id = Long.parseLong(in.nextLine());
            boolean existe = repository.getParticipantes().stream().anyMatch(p -> p.getId() == id);
            if (!existe) {
                System.out.println("Id inválido.");
                return null;
            }
            return id;
        } catch (Exception e) {
            System.out.println("Entrada inválida.");
            return null;
        }
    }
}
