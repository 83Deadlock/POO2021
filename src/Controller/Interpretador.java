package Controller;

import Files.GuardarCarregarEstado;
import Model.Equipa;
import Model.GestFootManager;
import Model.Jogador;
import View.Apresentacao;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

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


        int choice = -1;

        while (choice != 4) {
            mainMenu();
            System.out.println("Escolha: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                // Gestao de equipa
                menuGestEquipa();
            } else if (choice == 2) {
                // Realizar jogo
            } else if (choice == 3) {
                // Save Game
                System.out.println("Introduza o nome do save:");
                String file_name = scanner.nextLine();
                int i = GuardarCarregarEstado.guardaDados(file_name, gfm);
                if (i == 0) {
                    System.out.println("Jogo guardado");
                } else {
                    System.out.println("Erro ao gravar o jogo :(");
                }
            } else if (choice == 4) {
                System.out.println("Adeus");
            } else {
                System.out.println("Opção inválida.");
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

    private int startMenu() {
        apresentacao.mainMenu();
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

    private void menuGestEquipa(){
        int choice = -1;
        boolean flag = false;
        while(!flag){
            apresentacao.menuGestEquipa();
            choice = scanner.nextInt();
            scanner.nextLine();
            switch(choice){
                case 1: menuEditarConstituicao(); //TODO
                        break;
                case 2: menuTransferencias(); //TODO
                        break;
                case 3: verConstituiçãoAtual(); //DONE
                        break;
                case 4: verJogosRealizados(); //DONE
                        break;
                case 5: flag = true;
                        break;
            }
        }

    }

    private void verJogosRealizados() {
        apresentacao.printMessage(gfm.getJogosWith(gfm.getMinhaEquipa()));
    }

    private void verConstituiçãoAtual() {
        apresentacao.printMessage(gfm.printConstituicao(gfm.getMinhaEquipa()));
    }

    private void menuTransferencias() {
        System.out.println("1. Comprar Jogador");
        System.out.println("2. Vender Jogador");
        System.out.print("Your choice: ");
        int choice = -1;
        choice = scanner.nextInt();
        scanner.nextLine();
        if (choice == 1) {
            menuComprarJogador();
        } else if (choice == 2) {
            menuVenderJogador();
        } else {
        }
    }

    private void menuVenderJogador() {
        Equipa equipa = gfm.getEquipasCloned().get(gfm.getMinhaEquipa());
        Collection<String> jogs = equipa.getJogadores().values().stream().map(Jogador::getNome).collect(Collectors.toList());
        Map<Integer, String> opcoes = buildMenu(jogs);
        apresentacao.printChoiceMenu(opcoes);
        System.out.println("Escolha o jogador para trocar");
        System.out.println("Escolher 0 volta ao menu principal");
        int choice1 = -1;
        while (choice1 < 0 || choice1 > jogs.size()) {
            System.out.print("A sua escolha: ");
            choice1 = scanner.nextInt();
            scanner.nextLine();
        }
        if (choice1 == 0)
            return;

        System.out.println("Escolha a equipa com quem trocar: ");
        System.out.println("Escolher 0 volta ao menu principal");
        Set<String> equipas = gfm.getEquipasCloned().keySet();
        equipas.remove(gfm.getMinhaEquipa());
        Map<Integer, String> opcoes2 = buildMenu(equipas);
        apresentacao.printChoiceMenu(opcoes2);
        int choice2 = -1;
        while (choice2 < 0 || choice2 > equipas.size()) {
            System.out.print("A sua escolha: ");
            choice2 = scanner.nextInt();
            scanner.nextLine();
        }
        if (choice2 == 0)
            return;

        Jogador jog = gfm.getEquipas().get(gfm.getMinhaEquipa()).getJogadorByName(opcoes.get(choice1));
        gfm.getEquipas().get(gfm.getMinhaEquipa()).removeJogador(opcoes.get(choice1));
        gfm.getEquipas().get(opcoes2.get(choice2)).adicionaJogador(jog);
    }

    private void menuComprarJogador() {
        System.out.println("Escolha a equipa com quem trocar: ");
        System.out.println("Escolher 0 volta ao menu principal");
        Set<String> equipas = gfm.getEquipasCloned().keySet();
        equipas.remove(gfm.getMinhaEquipa());
        Map<Integer, String> opcoesEquipas = buildMenu(equipas);
        apresentacao.printChoiceMenu(opcoesEquipas);
        int choiceEquipa = -1;
        while (choiceEquipa < 0 || choiceEquipa > equipas.size()) {
            System.out.print("A sua escolha: ");
            choiceEquipa = scanner.nextInt();
            scanner.nextLine();
        }
        if (choiceEquipa == 0)
            return;


        Equipa equipa = gfm.getEquipas().get(opcoesEquipas.get(choiceEquipa));

        Collection<String> jogs = equipa.getJogadores().values().stream().map(Jogador::getNome).collect(Collectors.toList());
        Map<Integer, String> opcoes = buildMenu(jogs);
        apresentacao.printChoiceMenu(opcoes);
        System.out.println("Escolha o jogador para trocar");
        System.out.println("Escolher 0 volta ao menu principal");
        int choice1 = -1;
        while (choice1 < 0 || choice1 > jogs.size()) {
            System.out.print("A sua escolha: ");
            choice1 = scanner.nextInt();
            scanner.nextLine();
        }
        if (choice1 == 0)
            return;

        Jogador jog = gfm.getEquipas().get(opcoesEquipas.get(choiceEquipa)).getJogadorByName(opcoes.get(choice1));
        equipa.removeJogador(opcoes.get(choice1));
        gfm.getEquipas().get(gfm.getMinhaEquipa()).adicionaJogador(jog);
    }

    private void menuEditarConstituicao() {
        //TODO
    }

    private Map<Integer, String> buildMenu(Collection<String> opcoes) {
        Map<Integer, String> menu = new HashMap<>();
        int i = 1;
        for (String opcao : opcoes) {
            menu.put(i++, opcao);
        }
        return menu;
    }
}
