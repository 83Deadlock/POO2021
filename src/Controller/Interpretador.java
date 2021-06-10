package Controller;

import Files.GuardarCarregarEstado;
import Model.GestFootManager;
import View.Apresentacao;

import java.io.IOException;
import java.util.*;

public class Interpretador {
    private final Input in;
    GestFootManager gfm;
    Apresentacao apresentacao;
    Scanner scanner;

    public Interpretador(Apresentacao ap, GestFootManager gf){
        in = new Input();
        apresentacao = ap;
        gfm = gf;
        scanner = new Scanner(System.in);
    }


    /**
     * Controlador geral do programa
     *
     * @throws ClassNotFoundException  Erro
     * @throws IOException             Erro
     */
    public void interpretador() throws ClassNotFoundException, IOException{
        apresentacao.welcome();
        scanner.nextLine();

        int smenu = this.startMenu();
        if (smenu == -1) return;
        if (smenu == 1) createNewGame();

        mainMenu();
        int choice = -1;

        while (choice != 4) {
            System.out.println("Escolha: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                // Gestao de equipa

            } else if (choice == 2) {
                // Realizar jogo
            } else if (choice == 3) {
                // Save Game
                GuardarCarregarEstado.guardaDados("tmp0", gfm);
            } else if (choice == 4) {
                System.out.println("Adeus");
            } else {
                System.out.println("Invalido");
            }

        }
    }



    private void createNewGame() {
        Map<String, Integer> nomesEquipas = this.gfm.getNomesEquipas();
        Map<Integer, Map.Entry<String, Integer>> menuEscolhas = new HashMap<>();
        int i = 1;
        for (Map.Entry<String, Integer> entry : nomesEquipas.entrySet()) {
            menuEscolhas.put(i++, entry);
        }
        boolean flag = true;
        int choice = -1;
        while (flag) {
            apresentacao.imprimeEquipas(menuEscolhas);
            System.out.println("Escolha uma equipa (int)");
            choice =scanner.nextInt();
            scanner.nextLine();
            if (choice >= 1 && choice < i)
                flag = false;
        }
        String eq = menuEscolhas.get(choice).getKey();
        gfm.setMinhaEquipa(eq);
    }

    // private List<String> juntaOverall(Map<String, Integer> nomesEquipas) {
    //     List<String> eqs = new ArrayList<>();
    //     for (Map.Entry<String,Integer> entry : nomesEquipas.entrySet()) {
    //         eq
    //     }
    // }

    private int startMenu() {
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                System.out.println("Inicializando o jogo!");
                return 1;
            case 2:
                System.out.println("Introduza o nome do save:");
                String file_name = scanner.nextLine();
                try {
                    gfm = GuardarCarregarEstado.carregaDados(file_name);
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                return 0;
            case 3:
                return -1;
            default:
                System.out.println("Escolha inválida, tente outra vez");
                return this.startMenu();
        }
    }

    private void mainMenu() {
        System.out.println("1. Gestao de Equipa");
        System.out.println("2. Realizar jogo");
        System.out.println("3. Guardar jogo");
        System.out.println("4. Sair");
    }
}
