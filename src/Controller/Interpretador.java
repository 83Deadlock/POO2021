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

    /** Construtor
     *
     * @param ap - View
     * @param gf - Model
     */
    public Interpretador(Apresentacao ap, GestFootManager gf){
        in = new Input();
        apresentacao = ap;
        gfm = gf;
    }

    /**
     * Controlador geral do programa
     *
     * @throws ClassNotFoundException  Erro
     * @throws IOException             Erro
     */
    public void interpretador() throws ClassNotFoundException, IOException{
        apresentacao.welcome();
        in.lerLinha();

        int smenu = this.startMenu();
        if (smenu == -1) return;
        if (smenu == 1) createNewGame();


        int choice = -1;

        while (choice != 4) {
            mainMenu();
            apresentacao.printPrompt("Escolha: ");
            choice = in.lerInt();

            if (choice == 1) { //Gestão de Equipa
                menuGestEquipa();
            } else if (choice == 2) { //Realizar jogo
                menuJogo();
            } else if (choice == 3) { // Save Game
                apresentacao.printPrompt("Introduza o nome do save: ");
                String file_name = in.lerLinha();
                int i = GuardarCarregarEstado.guardaDados(file_name, gfm);
                if (i == 0) {
                    apresentacao.printMessage("Jogo guardado");
                } else {
                    apresentacao.printMessage("Erro ao gravar o jogo!");
                }
            } else if (choice == 4) {
                apresentacao.printMessage("Até uma próxima!");
            } else {
                apresentacao.printMessage("Opção inválida.");
            }

        }
    }

    /**
     * Menu da opção de realizar um jogo
     */
    private void menuJogo() {
        Map<Integer,String> menu = new HashMap<>();
        menu.put(1,"Simulação rápida");
        menu.put(2,"Simulação de jogo completo");
        menu.put(3,"Sair");
        boolean flag = false;
        int choice = -1;
        while(!flag){
            apresentacao.printChoiceMenu(menu);
            apresentacao.printPrompt("Opção: ");
            choice = in.lerInt();
            switch (choice){
                case 1:
                    Map<String, Integer> nomesEquipas = this.gfm.getNomesEquipas();
                    Map<Integer, Map.Entry<String, Integer>> menuEscolhas = new HashMap<>();
                    int i = 1;
                    for (Map.Entry<String, Integer> entry : nomesEquipas.entrySet()) {
                        menuEscolhas.put(i++, entry);
                    }
                    boolean flag1 = true;
                    int choice1 = -1;
                    while (flag1) {
                        apresentacao.imprimeEquipas(menuEscolhas);
                        apresentacao.printPrompt("Escolha uma equipa: ");
                        choice1 = in.lerInt();
                        if (choice1 >= 1 && choice1 < i)
                            flag1 = false;
                    }
                    Equipa casa = this.gfm.getEquipasCloned().get(menuEscolhas.get(choice1).getKey());
                    menuEscolhas.remove(choice1);
                    nomesEquipas.remove(casa.getNome());

                    flag1 = true;
                    choice1 = -1;
                    while (flag1) {
                        apresentacao.imprimeEquipas(menuEscolhas);
                        apresentacao.printPrompt("Escolha uma equipa: ");
                        choice1 = in.lerInt();
                        if (choice1 >= 1 && choice1 < i)
                            flag1 = false;
                    }
                    Equipa fora = this.gfm.getEquipasCloned().get(menuEscolhas.get(choice1).getKey());
                    Jogo j =  new Jogo(casa,fora);

                    j.simulacaoRapida();
                    apresentacao.printResultado(j.getEquipaCasa().getNome(),j.getScoreCasa(),j.getEquipaFora().getNome(),j.getScoreFora(),j.getData());
                    gfm.adicionaJogo(j);

                    flag = true;
                    break;
                case 2:
                    nomesEquipas = this.gfm.getNomesEquipas();
                    menuEscolhas = new HashMap<>();
                    i = 1;
                    for (Map.Entry<String, Integer> entry : nomesEquipas.entrySet()) {
                        menuEscolhas.put(i++, entry);
                    }
                    flag1 = true;
                    choice1 = -1;
                    while (flag1) {
                        apresentacao.imprimeEquipas(menuEscolhas);
                        apresentacao.printPrompt("Escolha uma equipa: ");
                        choice1 = in.lerInt();
                        if (choice1 >= 1 && choice1 < i)
                            flag1 = false;
                    }
                    casa = this.gfm.getEquipasCloned().get(menuEscolhas.get(choice1).getKey());
                    menuEscolhas.remove(choice1);
                    nomesEquipas.remove(casa.getNome());

                    flag1 = true;
                    choice1 = -1;
                    while (flag1) {
                        apresentacao.imprimeEquipas(menuEscolhas);
                        apresentacao.printPrompt("Escolha uma equipa: ");
                        choice1 = in.lerInt();
                        if (choice1 >= 1 && choice1 < i)
                            flag1 = false;
                    }
                    fora = this.gfm.getEquipasCloned().get(menuEscolhas.get(choice1).getKey());
                    j =  new Jogo(casa,fora);

                    SimulacaoJogo sj = j.simulacaoCompleta();

                    j.setScoreCasa(sj.getGolosCasa());
                    j.setScoreFora(sj.getGolosFora());
                    try {
                        sj.simula();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    flag = true;
                    break;
                case 3:
                    flag = true;
                    break;
                default:
                    apresentacao.printMessage("Opção Inválida");
                    break;

            }
        }
    }

    /**
     * Controlador para o início de um novo jogo.
     * O jogador tem de escolher a equipa que vai controlar.
     */
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
            apresentacao.printPrompt("Escolha a sua equipa equipa: ");
            choice = in.lerInt();
            if (choice >= 1 && choice < i)
                flag = false;
        }
        String eq = menuEscolhas.get(choice).getKey();
        gfm.setMinhaEquipa(eq);
    }

    /** Menu que apresenta as opções de Iniciar um Jogo Novo, Carregar os Dados de um Jogo anterior ou sair
     *
     * @return 1 - Jogo Novo | 0 - Carregar Jogo | -1 - Sair
     */
    private int startMenu() {
        Map<Integer,String> menu = new HashMap<>();
        menu.put(1,"Novo jogo");
        menu.put(2,"Carregar jogo");
        menu.put(3,"Sair");
        apresentacao.printChoiceMenu(menu);
        apresentacao.printPrompt("Opção: ");
        int choice = in.lerInt();
        switch (choice) {
            case 1:
                apresentacao.printMessage("Inicializando o jogo!");
                return 1;
            case 2:
                apresentacao.printMessage("Introduza o nome do save:");
                String file_name = in.lerLinha();
                try {
                    gfm = GuardarCarregarEstado.carregaDados(file_name);
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                return 0;
            case 3:
                return -1;
            default:
                apresentacao.printMessage("Escolha inválida, tente outra vez");
                return this.startMenu();
        }
    }

    /**
     * Menu Principal do Jogo
     */
    private void mainMenu() {
        Map<Integer,String> menu = new HashMap<>();
        menu.put(1,"Gestao de Equipa");
        menu.put(2,"Realizar jogo");
        menu.put(3,"Guardar jogo");
        menu.put(4,"Sair");
        apresentacao.printChoiceMenu(menu);
    }

    /**
     * Menu para a Gestão de uma Equipa
     */
    private void menuGestEquipa(){
        Map<Integer,String> menu = new HashMap<>();
        menu.put(1,"Editar constituição da equipa");
        menu.put(2,"Transferências");
        menu.put(3,"Ver constituição atual");
        menu.put(4,"Ver jogos realizados");
        menu.put(5,"Consultar Plantel");
        menu.put(6,"Voltar");
        int choice = -1;
        boolean flag = false;
        while(!flag){
            apresentacao.printChoiceMenu(menu);
            apresentacao.printPrompt("Opção: ");
            choice = in.lerInt();
            switch(choice){
                case 1: menuEditarConstituicao();
                        break;
                case 2: menuTransferencias();
                        break;
                case 3: verConstituiçãoAtual();
                        break;
                case 4: verJogosRealizados();
                        break;
                case 5: verPlantel();
                        break;
                case 6: flag = true;
                        break;
                default: apresentacao.printMessage("Opção Inválida");
                         break;
            }
        }

    }

    private void verPlantel() {
        Map<Integer,Jogador> plantel = gfm.getEquipasCloned().get(gfm.getMinhaEquipa()).getJogadores();
        Map<Integer,String> plantelPrintable = plantel.entrySet().stream().collect(Collectors.toMap(par -> par.getKey(),par -> par.getValue().toStringBasic()));
        int choice = -1;
        while(choice != 0){
            apresentacao.printChoiceMenu(plantelPrintable);
            apresentacao.printPrompt("Escolher Jogador (0 para voltar): ");
            choice = in.lerInt();
            if(choice != 0){
                apresentacao.clearScreen();
                apresentacao.printDetalheJogador(plantel.get(choice).detalheJogador());
                apresentacao.printPrompt("Voltar (Enter)");
                in.lerLinha();
                apresentacao.clearScreen();
            }
        }
    }

    /**
     * Método que dá print a todos os jogos da equipa
     */
    private void verJogosRealizados() {
        apresentacao.printMessage(gfm.getJogosWith(gfm.getMinhaEquipa()));
    }

    private void verConstituiçãoAtual() {
        apresentacao.printMessage(gfm.printConstituicao(gfm.getMinhaEquipa()));
    }

    private void menuTransferencias() {
        Map<Integer,String> menu = new HashMap<>();
        menu.put(1,"Comprar jogador");
        menu.put(2,"Vender jogador");
        apresentacao.printPrompt("Your choice: ");
        int choice = -1;
        choice = in.lerInt();
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
        apresentacao.printPrompt("Escolha o jogador para vender (0 para voltar ao menu): ");

        int choice1 = -1;
        while (choice1 < 0 || choice1 > jogs.size()) {
            apresentacao.printPrompt("A sua escolha: ");
            choice1 = in.lerInt();
        }
        if (choice1 == 0)
            return;

        apresentacao.printPrompt("Escolha a equipa a quem vender (0 para voltar ao menu): ");
        Set<String> equipas = gfm.getEquipasCloned().keySet();
        equipas.remove(gfm.getMinhaEquipa());
        Map<Integer, String> opcoes2 = buildMenu(equipas);
        apresentacao.printChoiceMenu(opcoes2);
        int choice2 = -1;
        while (choice2 < 0 || choice2 > equipas.size()) {
            apresentacao.printPrompt("A sua escolha: ");
            choice2 = in.lerInt();
        }
        if (choice2 == 0)
            return;

        Jogador jog = gfm.getEquipas().get(gfm.getMinhaEquipa()).getJogadorByName(opcoes.get(choice1));
        gfm.getEquipas().get(gfm.getMinhaEquipa()).removeJogador(opcoes.get(choice1));
        gfm.getEquipas().get(opcoes2.get(choice2)).adicionaJogador(jog);
    }

    private void menuComprarJogador() {
        apresentacao.printPrompt("Escolha a equipa a quem comprar (0 para voltar ao menu): ");
        Set<String> equipas = gfm.getEquipasCloned().keySet();
        equipas.remove(gfm.getMinhaEquipa());
        Map<Integer, String> opcoesEquipas = buildMenu(equipas);
        apresentacao.printChoiceMenu(opcoesEquipas);
        int choiceEquipa = -1;
        while (choiceEquipa < 0 || choiceEquipa > equipas.size()) {
            apresentacao.printPrompt("A sua escolha: ");
            choiceEquipa = in.lerInt();
        }
        if (choiceEquipa == 0)
            return;


        Equipa equipa = gfm.getEquipas().get(opcoesEquipas.get(choiceEquipa));

        Collection<String> jogs = equipa.getJogadores().values().stream().map(Jogador::getNome).collect(Collectors.toList());
        Map<Integer, String> opcoes = buildMenu(jogs);
        apresentacao.printChoiceMenu(opcoes);
        apresentacao.printPrompt("Escolha o jogador para comprar (0 para voltar ao menu): ");
        int choice1 = -1;
        while (choice1 < 0 || choice1 > jogs.size()) {
            apresentacao.printPrompt("A sua escolha: ");
            choice1 = in.lerInt();
        }
        if (choice1 == 0)
            return;

        Jogador jog = gfm.getEquipas().get(opcoesEquipas.get(choiceEquipa)).getJogadorByName(opcoes.get(choice1));
        equipa.removeJogador(opcoes.get(choice1));
        gfm.getEquipas().get(gfm.getMinhaEquipa()).adicionaJogador(jog);
    }

    private void menuEditarConstituicao() {
        Map<Integer,String> menu = new HashMap<>();
        menu.put(1,"Ver constituição atual");
        menu.put(2,"Alterar constituição");
        menu.put(3,"Sair");
        int choice = -1;
        boolean flag = false;
        while(!flag){
            apresentacao.printChoiceMenu(menu);
            apresentacao.printPrompt("Escolha: ");
            choice = in.lerInt();
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
        Map<Integer,String> taticas = new HashMap<>();
        taticas.put(1,"4-4-2");
        taticas.put(2,"4-3-3");
        int tatica = -1;
        boolean flag = false;
        while(!flag){
            apresentacao.printMessage("Escolha a sua tática");
            apresentacao.printChoiceMenu(taticas);
            apresentacao.printPrompt("Escolha: ");
            tatica = in.lerInt();
            if(tatica == 1 || tatica == 2){
                flag = true;
            } else {
                apresentacao.printMessage("Opção Inválida");
            }
        }
        this.gfm.getEquipas().get(this.gfm.getMinhaEquipa()).setConstituicao(tatica,gfm.getEquipasCloned().get(gfm.getMinhaEquipa()).getJogadores());
        int decisao = -1;
        while(decisao != 0 && decisao != 1){
            apresentacao.printPrompt("A sua equipa foi alterada automaticamente! Deseja ver a sua constituição atual? (Sim (1)/Não (0))");
            decisao = in.lerInt();
            if(decisao != 0 && decisao != 1) apresentacao.printMessage("Opção Inválida");
        }
        if(decisao == 1){
            verConstituiçãoAtual();
        }
        decisao = -1;
        while(decisao != 0 && decisao != 1){
            apresentacao.printPrompt("Pretende alterar os jogadores? (Sim (1)/Não (0))");
            decisao = in.lerInt();
            if(decisao != 0 && decisao != 1) apresentacao.printMessage("Opção Inválida");
        }
        if(decisao == 1){
            apresentacao.printMessage("-------------------Guarda-Redes-------------------");
            alteraGuardaRedes();
            apresentacao.printMessage("---------------------Laterais---------------------");
            alteraLaterais();
            apresentacao.printMessage("---------------------Defesas----------------------");
            alteraDefesas();
            apresentacao.printMessage("----------------------Medios----------------------");
            alteraMedios();
            apresentacao.printMessage("---------------------Extremos---------------------");
            alteraExtremos();
            apresentacao.printMessage("---------------------Avançados--------------------");
            alteraAvancados();
        }
    }

    private void alteraGuardaRedes() {
        boolean flag = false;
        int choice = -1;
        while(!flag){
            GuardaRedes gr = gfm.getEquipasCloned().get(gfm.getMinhaEquipa()).getGrTitular();
            apresentacao.printMessage("Titular\n\t" + gr.getNumeroCamisola() + " - " + gr.toStringBasic());

            Map<Integer,Jogador> suplentesMap = gfm.getEquipasCloned().get(gfm.getMinhaEquipa()).getGuardaRedes();
            suplentesMap.remove(gr.getNumeroCamisola());
            apresentacao.printMessage("Suplentes -> \n" + suplentes(suplentesMap));

            apresentacao.printPrompt("Que jogador pretende trocar? (0 para sair)");
            choice = in.lerInt();
            if(choice == 0){
                flag = true;
            } else if(gr.getNumeroCamisola() == choice){
                flag = true;
                int suplente = -1;
                boolean valid = false;
                while(!valid){
                    apresentacao.printPrompt("Qual o suplente a entrar?");
                    suplente = in.lerInt();
                    if(suplentesMap.containsKey(suplente)){
                        valid = true;
                        gfm.getEquipas().get(gfm.getMinhaEquipa()).trocaJogadoresConstituicao(gr.getNumeroCamisola(),suplente,0);
                    } else {
                        apresentacao.printMessage("Opção Inválida");
                    }
                }
            } else if (suplentesMap.containsKey(choice)){
                flag = true;
                gfm.getEquipas().get(gfm.getMinhaEquipa()).trocaJogadoresConstituicao(gr.getNumeroCamisola(),choice,0);
            } else {
                apresentacao.printMessage("opção Inválida");
            }
        }
    }

    private void alteraDefesas(){
        boolean flag = false;
        int choice = -1;
        while(!flag){
            Defesa titulares[] = gfm.getEquipasCloned().get(gfm.getMinhaEquipa()).getDefesasTitulares();
            Map<Integer,Jogador> numerosTitulares = new HashMap<>();
            for(int i = 0; i < titulares.length; i++){
                numerosTitulares.put(titulares[i].getNumeroCamisola(),titulares[i]);
            }
            apresentacao.printMessage("Titulares\n" + suplentes(numerosTitulares));
            Map<Integer,Jogador> suplentesMap = gfm.getEquipasCloned().get(gfm.getMinhaEquipa()).getDefesas();
            for(Integer i: numerosTitulares.keySet()){
                suplentesMap.remove(i);
            }
            apresentacao.printMessage("Suplentes -> \n" + suplentes(suplentesMap));

            apresentacao.printPrompt("Que jogador pretende trocar? (0 para sair)");
            choice = in.lerInt();
            if(choice == 0) {
                flag = true;
            } else if (numerosTitulares.containsKey(choice)){
                int suplente = -1;
                boolean valid = false;
                while(!valid){
                    apresentacao.printPrompt("Qual o suplente a entrar?");
                    suplente = in.lerInt();
                    if(suplentesMap.containsKey(suplente)){
                        valid = true;
                        gfm.getEquipas().get(gfm.getMinhaEquipa()).trocaJogadoresConstituicao(choice,suplente,0);
                    } else {
                        apresentacao.printMessage("Opção Inválida");
                    }
                }
            } else if (suplentesMap.containsKey(choice)){
                int titular = -1;
                boolean valid = false;
                while(!valid){
                    apresentacao.printPrompt("Qual o titular a sair?");
                    titular = in.lerInt();
                    if(numerosTitulares.containsKey(titular)){
                        valid = true;
                        gfm.getEquipas().get(gfm.getMinhaEquipa()).trocaJogadoresConstituicao(titular,choice,0);
                    } else {
                        apresentacao.printMessage("Opção Inválida");
                    }
                }
            } else {
                apresentacao.printMessage("opção Inválida");
            }
        }

    }

    private void alteraLaterais(){
        boolean flag = false;
        int choice = -1;
        while(!flag){
            Lateral titulares[] = gfm.getEquipasCloned().get(gfm.getMinhaEquipa()).getLateraisTitulares();
            Map<Integer,Jogador> numerosTitulares = new HashMap<>();
            for(int i = 0; i < titulares.length; i++){
                numerosTitulares.put(titulares[i].getNumeroCamisola(),titulares[i]);
            }
            apresentacao.printMessage("Titulares\n" + suplentes(numerosTitulares));
            Map<Integer,Jogador> suplentesMap = gfm.getEquipasCloned().get(gfm.getMinhaEquipa()).getLaterais();
            for(Integer i: numerosTitulares.keySet()){
                suplentesMap.remove(i);
            }
            apresentacao.printMessage("Suplentes -> \n" + suplentes(suplentesMap));

            apresentacao.printPrompt("Que jogador pretende trocar? (0 para sair)");
            choice = in.lerInt();
            if(choice == 0) {
                flag = true;
            } else if (numerosTitulares.containsKey(choice)){
                int suplente = -1;
                boolean valid = false;
                while(!valid){
                    apresentacao.printPrompt("Qual o suplente a entrar?");
                    suplente = in.lerInt();
                    if(suplentesMap.containsKey(suplente)){
                        valid = true;
                        gfm.getEquipas().get(gfm.getMinhaEquipa()).trocaJogadoresConstituicao(choice,suplente,1);
                    } else {
                        apresentacao.printMessage("Opção Inválida");
                    }
                }
            } else if (suplentesMap.containsKey(choice)){
                int titular = -1;
                boolean valid = false;
                while(!valid){
                    apresentacao.printPrompt("Qual o titular a sair?");
                    titular = in.lerInt();
                    if(numerosTitulares.containsKey(titular)){
                        valid = true;
                        gfm.getEquipas().get(gfm.getMinhaEquipa()).trocaJogadoresConstituicao(titular,choice,1);
                    } else {
                        apresentacao.printMessage("Opção Inválida");
                    }
                }
            } else {
                apresentacao.printMessage("opção Inválida");
            }
        }

    }

    private void alteraExtremos(){
        boolean flag = false;
        int choice = -1;
        while(!flag){
            Lateral titulares[] = gfm.getEquipasCloned().get(gfm.getMinhaEquipa()).getExtremosTitulares();
            Map<Integer,Jogador> numerosTitulares = new HashMap<>();
            for(int i = 0; i < titulares.length; i++){
                numerosTitulares.put(titulares[i].getNumeroCamisola(),titulares[i]);
            }
            apresentacao.printMessage("Titulares\n" + suplentes(numerosTitulares));
            Map<Integer,Jogador> suplentesMap = gfm.getEquipasCloned().get(gfm.getMinhaEquipa()).getLaterais();
            for(Integer i: numerosTitulares.keySet()){
                suplentesMap.remove(i);
            }
            apresentacao.printMessage("Suplentes -> \n" + suplentes(suplentesMap));

            apresentacao.printPrompt("Que jogador pretende trocar? (0 para sair)");
            choice = in.lerInt();
            if(choice == 0) {
                flag = true;
            } else if (numerosTitulares.containsKey(choice)){
                int suplente = -1;
                boolean valid = false;
                while(!valid){
                    apresentacao.printPrompt("Qual o suplente a entrar?");
                    suplente = in.lerInt();
                    if(suplentesMap.containsKey(suplente)){
                        valid = true;
                        gfm.getEquipas().get(gfm.getMinhaEquipa()).trocaJogadoresConstituicao(choice,suplente,0);
                    } else {
                        apresentacao.printMessage("Opção Inválida");
                    }
                }
            } else if (suplentesMap.containsKey(choice)){
                int titular = -1;
                boolean valid = false;
                while(!valid){
                    apresentacao.printPrompt("Qual o titular a sair?");
                    titular = in.lerInt();
                    if(numerosTitulares.containsKey(titular)){
                        valid = true;
                        gfm.getEquipas().get(gfm.getMinhaEquipa()).trocaJogadoresConstituicao(titular,choice,0);
                    } else {
                        apresentacao.printMessage("Opção Inválida");
                    }
                }
            } else {
                apresentacao.printMessage("opção Inválida");
            }
        }

    }

    private void alteraMedios(){
        boolean flag = false;
        int choice = -1;
        while(!flag){
            Medio titulares[] = gfm.getEquipasCloned().get(gfm.getMinhaEquipa()).getMediosTitulares();
            Map<Integer,Jogador> numerosTitulares = new HashMap<>();
            for(int i = 0; i < titulares.length; i++){
                numerosTitulares.put(titulares[i].getNumeroCamisola(),titulares[i]);
            }
            apresentacao.printMessage("Titulares\n" + suplentes(numerosTitulares));
            Map<Integer,Jogador> suplentesMap = gfm.getEquipasCloned().get(gfm.getMinhaEquipa()).getMedios();
            for(Integer i: numerosTitulares.keySet()){
                suplentesMap.remove(i);
            }
            apresentacao.printMessage("Suplentes -> \n" + suplentes(suplentesMap));

            apresentacao.printPrompt("Que jogador pretende trocar? (0 para sair)");
            choice = in.lerInt();
            if(choice == 0) {
                flag = true;
            } else if (numerosTitulares.containsKey(choice)){
                int suplente = -1;
                boolean valid = false;
                while(!valid){
                    apresentacao.printPrompt("Qual o suplente a entrar?");
                    suplente = in.lerInt();
                    if(suplentesMap.containsKey(suplente)){
                        valid = true;
                        gfm.getEquipas().get(gfm.getMinhaEquipa()).trocaJogadoresConstituicao(choice,suplente,0);
                    } else {
                        apresentacao.printMessage("Opção Inválida");
                    }
                }
            } else if (suplentesMap.containsKey(choice)){
                int titular = -1;
                boolean valid = false;
                while(!valid){
                    apresentacao.printPrompt("Qual o titular a sair?");
                    titular = in.lerInt();
                    if(numerosTitulares.containsKey(titular)){
                        valid = true;
                        gfm.getEquipas().get(gfm.getMinhaEquipa()).trocaJogadoresConstituicao(titular,choice,0);
                    } else {
                        apresentacao.printMessage("Opção Inválida");
                    }
                }
            } else {
                apresentacao.printMessage("opção Inválida");
            }
        }

    }

    private void alteraAvancados(){
        boolean flag = false;
        int choice = -1;
        while(!flag){
            Avancado titulares[] = gfm.getEquipasCloned().get(gfm.getMinhaEquipa()).getAvancadosTitulares();
            Map<Integer,Jogador> numerosTitulares = new HashMap<>();
            for(int i = 0; i < titulares.length; i++){
                numerosTitulares.put(titulares[i].getNumeroCamisola(),titulares[i]);
            }
            apresentacao.printMessage("Titulares\n" + suplentes(numerosTitulares));
            Map<Integer,Jogador> suplentesMap = gfm.getEquipasCloned().get(gfm.getMinhaEquipa()).getAvancado();
            for(Integer i: numerosTitulares.keySet()){
                suplentesMap.remove(i);
            }
            apresentacao.printMessage("Suplentes -> \n" + suplentes(suplentesMap));

            apresentacao.printPrompt("Que jogador pretende trocar? (0 para sair)");
            choice = in.lerInt();
            if(choice == 0) {
                flag = true;
            } else if (numerosTitulares.containsKey(choice)){
                int suplente = -1;
                boolean valid = false;
                while(!valid){
                    apresentacao.printMessage("Qual o suplente a entrar?");
                    suplente = in.lerInt();
                    if(suplentesMap.containsKey(suplente)){
                        valid = true;
                        gfm.getEquipas().get(gfm.getMinhaEquipa()).trocaJogadoresConstituicao(choice,suplente,0);
                    } else {
                        apresentacao.printMessage("Opção Inválida");
                    }
                }
            } else if (suplentesMap.containsKey(choice)){
                int titular = -1;
                boolean valid = false;
                while(!valid){
                    apresentacao.printPrompt("Qual o titular a sair?");
                    titular = in.lerInt();
                    if(numerosTitulares.containsKey(titular)){
                        valid = true;
                        gfm.getEquipas().get(gfm.getMinhaEquipa()).trocaJogadoresConstituicao(titular,choice,0);
                    } else {
                        apresentacao.printMessage("Opção Inválida");
                    }
                }
            } else {
                apresentacao.printMessage("Opção Inválida");
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
