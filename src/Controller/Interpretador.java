package Controller;

import Files.GuardarCarregarEstado;
import Model.*;
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
                case 1: menuEditarConstituicao();
                        break;
                case 2: menuTransferencias();
                        break;
                case 3: verConstituiçãoAtual();
                        break;
                case 4: verJogosRealizados();
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
        int choice = -1;
        boolean flag = false;
        while(!flag){
            System.out.println("1. Ver constituição atual");
            System.out.println("2. Alterar Constituição");
            System.out.println("3. Sair");
            System.out.print("Escolha: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch(choice){
                case 1: verConstituiçãoAtual();
                        break;
                case 2: alterarConstituicao();
                        break;
                case 3: flag = true;
                        break;
            }
        }
    }

    private void alterarConstituicao() {
        int tatica = -1;
        boolean flag = false;
        while(!flag){
            System.out.println("Escolha a sua tática");
            System.out.println("1. 4-4-2");
            System.out.println("2. 4-3-3");
            tatica = scanner.nextInt();
            scanner.nextLine();
            if(tatica == 1 || tatica == 2){
                flag = true;
            } else {
                System.out.println("Opção Inválida");
            }
        }
        this.gfm.getEquipas().get(this.gfm.getMinhaEquipa()).setConstituicao(tatica,gfm.getEquipasCloned().get(gfm.getMinhaEquipa()).getJogadores());
        int decisao = -1;
        while(decisao != 0 && decisao != 1){
            System.out.println("A sua equipa foi alterada automaticamente! Deseja ver a sua constituição atual? (Sim (1)/Não (0))");
            decisao = scanner.nextInt();
            scanner.nextLine();
            if(decisao != 0 && decisao != 1) System.out.println("Opção Inválida");
        }
        if(decisao == 1){
            verConstituiçãoAtual();
        }
        decisao = -1;
        while(decisao != 0 && decisao != 1){
            System.out.println("Pretende alterar os jogadores? (Sim (1)/Não (0))");
            decisao = scanner.nextInt();
            scanner.nextLine();
            if(decisao != 0 && decisao != 1) System.out.println("Opção Inválida");
        }
        if(decisao == 1){
            System.out.println("-------------------Guarda-Redes-------------------");
            alteraGuardaRedes();
            System.out.println("---------------------Laterais---------------------");
            alteraLaterais();
            System.out.println("---------------------Defesas----------------------");
            alteraDefesas();
            System.out.println("----------------------Medios----------------------");
            alteraMedios();
            System.out.println("---------------------Extremos---------------------");
            alteraExtremos();
            System.out.println("---------------------Avançados--------------------");
            alteraAvancados();
        }
    }

    public void alteraGuardaRedes() {
        boolean flag = false;
        int choice = -1;
        while(!flag){
            GuardaRedes gr = gfm.getEquipasCloned().get(gfm.getMinhaEquipa()).getGrTitular();
            System.out.println("Titular\n\t" + gr.getNumeroCamisola() + " - " + gr.toStringBasic());

            Map<Integer,Jogador> suplentesMap = gfm.getEquipasCloned().get(gfm.getMinhaEquipa()).getGuardaRedes();
            suplentesMap.remove(gr.getNumeroCamisola());
            System.out.println("Suplentes -> \n" + suplentes(suplentesMap));

            System.out.println("Que jogador pretende trocar? (0 para sair)");
            choice = scanner.nextInt();
            scanner.nextLine();
            if(choice == 0){
                flag = true;
            } else if(gr.getNumeroCamisola() == choice){
                flag = true;
                int suplente = -1;
                boolean valid = false;
                while(!valid){
                    System.out.println("Qual o suplente a entrar?");
                    suplente = scanner.nextInt();
                    scanner.nextLine();
                    if(suplentesMap.containsKey(suplente)){
                        valid = true;
                        gfm.getEquipas().get(gfm.getMinhaEquipa()).trocaJogadoresConstituicao(gr.getNumeroCamisola(),suplente,0);
                    } else {
                        System.out.println("Opção Inválida");
                    }
                }
            } else if (suplentesMap.containsKey(choice)){
                flag = true;
                gfm.getEquipas().get(gfm.getMinhaEquipa()).trocaJogadoresConstituicao(gr.getNumeroCamisola(),choice,0);
            } else {
                System.out.println("opção Inválida");
            }
        }
    }

    public void alteraDefesas(){
        boolean flag = false;
        int choice = -1;
        while(!flag){
            Defesa titulares[] = gfm.getEquipasCloned().get(gfm.getMinhaEquipa()).getDefesasTitulares();
            Map<Integer,Jogador> numerosTitulares = new HashMap<>();
            for(int i = 0; i < titulares.length; i++){
                numerosTitulares.put(titulares[i].getNumeroCamisola(),titulares[i]);
            }
            System.out.println("Titulares\n" + suplentes(numerosTitulares));
            Map<Integer,Jogador> suplentesMap = gfm.getEquipasCloned().get(gfm.getMinhaEquipa()).getDefesas();
            for(Integer i: numerosTitulares.keySet()){
                suplentesMap.remove(i);
            }
            System.out.println("Suplentes -> \n" + suplentes(suplentesMap));

            System.out.println("Que jogador pretende trocar? (0 para sair)");
            choice = scanner.nextInt();
            scanner.nextLine();
            if(choice == 0) {
                flag = true;
            } else if (numerosTitulares.containsKey(choice)){
                int suplente = -1;
                boolean valid = false;
                while(!valid){
                    System.out.println("Qual o suplente a entrar?");
                    suplente = scanner.nextInt();
                    scanner.nextLine();
                    if(suplentesMap.containsKey(suplente)){
                        valid = true;
                        gfm.getEquipas().get(gfm.getMinhaEquipa()).trocaJogadoresConstituicao(choice,suplente,0);
                    } else {
                        System.out.println("Opção Inválida");
                    }
                }
            } else if (suplentesMap.containsKey(choice)){
                int titular = -1;
                boolean valid = false;
                while(!valid){
                    System.out.println("Qual o suplente a entrar?");
                    titular = scanner.nextInt();
                    scanner.nextLine();
                    if(numerosTitulares.containsKey(titular)){
                        valid = true;
                        gfm.getEquipas().get(gfm.getMinhaEquipa()).trocaJogadoresConstituicao(titular,choice,0);
                    } else {
                        System.out.println("Opção Inválida");
                    }
                }
            } else {
                System.out.println("opção Inválida");
            }
        }

    }

    public void alteraLaterais(){
        boolean flag = false;
        int choice = -1;
        while(!flag){
            Lateral titulares[] = gfm.getEquipasCloned().get(gfm.getMinhaEquipa()).getLateraisTitulares();
            Map<Integer,Jogador> numerosTitulares = new HashMap<>();
            for(int i = 0; i < titulares.length; i++){
                numerosTitulares.put(titulares[i].getNumeroCamisola(),titulares[i]);
            }
            System.out.println("Titulares\n" + suplentes(numerosTitulares));
            Map<Integer,Jogador> suplentesMap = gfm.getEquipasCloned().get(gfm.getMinhaEquipa()).getLaterais();
            for(Integer i: numerosTitulares.keySet()){
                suplentesMap.remove(i);
            }
            System.out.println("Suplentes -> \n" + suplentes(suplentesMap));

            System.out.println("Que jogador pretende trocar? (0 para sair)");
            choice = scanner.nextInt();
            scanner.nextLine();
            if(choice == 0) {
                flag = true;
            } else if (numerosTitulares.containsKey(choice)){
                int suplente = -1;
                boolean valid = false;
                while(!valid){
                    System.out.println("Qual o suplente a entrar?");
                    suplente = scanner.nextInt();
                    scanner.nextLine();
                    if(suplentesMap.containsKey(suplente)){
                        valid = true;
                        gfm.getEquipas().get(gfm.getMinhaEquipa()).trocaJogadoresConstituicao(choice,suplente,1);
                    } else {
                        System.out.println("Opção Inválida");
                    }
                }
            } else if (suplentesMap.containsKey(choice)){
                int titular = -1;
                boolean valid = false;
                while(!valid){
                    System.out.println("Qual o titular a sair?");
                    titular = scanner.nextInt();
                    scanner.nextLine();
                    if(numerosTitulares.containsKey(titular)){
                        valid = true;
                        gfm.getEquipas().get(gfm.getMinhaEquipa()).trocaJogadoresConstituicao(titular,choice,1);
                    } else {
                        System.out.println("Opção Inválida");
                    }
                }
            } else {
                System.out.println("opção Inválida");
            }
        }

    }

    public void alteraExtremos(){
        boolean flag = false;
        int choice = -1;
        while(!flag){
            Lateral titulares[] = gfm.getEquipasCloned().get(gfm.getMinhaEquipa()).getExtremosTitulares();
            Map<Integer,Jogador> numerosTitulares = new HashMap<>();
            for(int i = 0; i < titulares.length; i++){
                numerosTitulares.put(titulares[i].getNumeroCamisola(),titulares[i]);
            }
            System.out.println("Titulares\n" + suplentes(numerosTitulares));
            Map<Integer,Jogador> suplentesMap = gfm.getEquipasCloned().get(gfm.getMinhaEquipa()).getLaterais();
            for(Integer i: numerosTitulares.keySet()){
                suplentesMap.remove(i);
            }
            System.out.println("Suplentes -> \n" + suplentes(suplentesMap));

            System.out.println("Que jogador pretende trocar? (0 para sair)");
            choice = scanner.nextInt();
            scanner.nextLine();
            if(choice == 0) {
                flag = true;
            } else if (numerosTitulares.containsKey(choice)){
                int suplente = -1;
                boolean valid = false;
                while(!valid){
                    System.out.println("Qual o suplente a entrar?");
                    suplente = scanner.nextInt();
                    scanner.nextLine();
                    if(suplentesMap.containsKey(suplente)){
                        valid = true;
                        gfm.getEquipas().get(gfm.getMinhaEquipa()).trocaJogadoresConstituicao(choice,suplente,0);
                    } else {
                        System.out.println("Opção Inválida");
                    }
                }
            } else if (suplentesMap.containsKey(choice)){
                int titular = -1;
                boolean valid = false;
                while(!valid){
                    System.out.println("Qual o titular a sair?");
                    titular = scanner.nextInt();
                    scanner.nextLine();
                    if(numerosTitulares.containsKey(titular)){
                        valid = true;
                        gfm.getEquipas().get(gfm.getMinhaEquipa()).trocaJogadoresConstituicao(titular,choice,0);
                    } else {
                        System.out.println("Opção Inválida");
                    }
                }
            } else {
                System.out.println("opção Inválida");
            }
        }

    }

    public void alteraMedios(){
        boolean flag = false;
        int choice = -1;
        while(!flag){
            Medio titulares[] = gfm.getEquipasCloned().get(gfm.getMinhaEquipa()).getMediosTitulares();
            Map<Integer,Jogador> numerosTitulares = new HashMap<>();
            for(int i = 0; i < titulares.length; i++){
                numerosTitulares.put(titulares[i].getNumeroCamisola(),titulares[i]);
            }
            System.out.println("Titulares\n" + suplentes(numerosTitulares));
            Map<Integer,Jogador> suplentesMap = gfm.getEquipasCloned().get(gfm.getMinhaEquipa()).getMedios();
            for(Integer i: numerosTitulares.keySet()){
                suplentesMap.remove(i);
            }
            System.out.println("Suplentes -> \n" + suplentes(suplentesMap));

            System.out.println("Que jogador pretende trocar? (0 para sair)");
            choice = scanner.nextInt();
            scanner.nextLine();
            if(choice == 0) {
                flag = true;
            } else if (numerosTitulares.containsKey(choice)){
                int suplente = -1;
                boolean valid = false;
                while(!valid){
                    System.out.println("Qual o suplente a entrar?");
                    suplente = scanner.nextInt();
                    scanner.nextLine();
                    if(suplentesMap.containsKey(suplente)){
                        valid = true;
                        gfm.getEquipas().get(gfm.getMinhaEquipa()).trocaJogadoresConstituicao(choice,suplente,0);
                    } else {
                        System.out.println("Opção Inválida");
                    }
                }
            } else if (suplentesMap.containsKey(choice)){
                int titular = -1;
                boolean valid = false;
                while(!valid){
                    System.out.println("Qual o suplente a entrar?");
                    titular = scanner.nextInt();
                    scanner.nextLine();
                    if(numerosTitulares.containsKey(titular)){
                        valid = true;
                        gfm.getEquipas().get(gfm.getMinhaEquipa()).trocaJogadoresConstituicao(titular,choice,0);
                    } else {
                        System.out.println("Opção Inválida");
                    }
                }
            } else {
                System.out.println("opção Inválida");
            }
        }

    }

    public void alteraAvancados(){
        boolean flag = false;
        int choice = -1;
        while(!flag){
            Avancado titulares[] = gfm.getEquipasCloned().get(gfm.getMinhaEquipa()).getAvancadosTitulares();
            Map<Integer,Jogador> numerosTitulares = new HashMap<>();
            for(int i = 0; i < titulares.length; i++){
                numerosTitulares.put(titulares[i].getNumeroCamisola(),titulares[i]);
            }
            System.out.println("Titulares\n" + suplentes(numerosTitulares));
            Map<Integer,Jogador> suplentesMap = gfm.getEquipasCloned().get(gfm.getMinhaEquipa()).getAvancado();
            for(Integer i: numerosTitulares.keySet()){
                suplentesMap.remove(i);
            }
            System.out.println("Suplentes -> \n" + suplentes(suplentesMap));

            System.out.println("Que jogador pretende trocar? (0 para sair)");
            choice = scanner.nextInt();
            scanner.nextLine();
            if(choice == 0) {
                flag = true;
            } else if (numerosTitulares.containsKey(choice)){
                int suplente = -1;
                boolean valid = false;
                while(!valid){
                    System.out.println("Qual o suplente a entrar?");
                    suplente = scanner.nextInt();
                    scanner.nextLine();
                    if(suplentesMap.containsKey(suplente)){
                        valid = true;
                        gfm.getEquipas().get(gfm.getMinhaEquipa()).trocaJogadoresConstituicao(choice,suplente,0);
                    } else {
                        System.out.println("Opção Inválida");
                    }
                }
            } else if (suplentesMap.containsKey(choice)){
                int titular = -1;
                boolean valid = false;
                while(!valid){
                    System.out.println("Qual o suplente a entrar?");
                    titular = scanner.nextInt();
                    scanner.nextLine();
                    if(numerosTitulares.containsKey(titular)){
                        valid = true;
                        gfm.getEquipas().get(gfm.getMinhaEquipa()).trocaJogadoresConstituicao(titular,choice,0);
                    } else {
                        System.out.println("Opção Inválida");
                    }
                }
            } else {
                System.out.println("opção Inválida");
            }
        }

    }

    private String suplentes(Map<Integer, Jogador> jogadores) {
        StringBuilder sb = new StringBuilder();
        for(Integer i : jogadores.keySet()){
            sb.append("\t"+jogadores.get(i).getNumeroCamisola() + " - " + jogadores.get(i).toStringBasic()+"\n");
        }
        return sb.toString();
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
