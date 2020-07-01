package pkg02.agenda.pkg1.pkg0;

import java.util.Scanner;

public class Agenda10 {
    
    public static void main(String[] args) {
        
        Cliente[] c = new Cliente[100];
        Profissional[] p = new Profissional[10];
        
        Scanner leitor = new Scanner(System.in);

        int op = 0, op2 = 0;
        boolean repetir = true;
                
        // MENU PRINCIPAL
        
        do{
            System.out.println("");
            System.out.println("----- MENU PRINCIPAL -----");
            System.out.println("1 - Cliente ");
            System.out.println("2 - Profissional");
            System.out.println("3 - sair");
            System.out.print("Escolha uma das opções: ");
            try{
                op = leitor.nextInt();

                switch(op){
                    case 1:
                        // SUB MENU - CLIENTE
                        do{
                            System.out.println("");
                            System.out.println("----- TELA DO CLIENTE -----");
                            System.out.println("1 - Cadastrar");
                            System.out.println("2 - Consultar");
                            System.out.println("3 - Voltar ao Menu");
                            System.out.print("Escolha uma das opções: ");

                            op2 = leitor.nextInt();


                            switch(op2){
                                case 1:
                                    c[Cliente.getCodCliente()] = Agenda10.telaCliente1();
                                    break;
                                case 2:
                                    int codCliente = Agenda10.telaCliente2_1(c);
                                    c[codCliente] = Agenda10.telaCliente2_2(c[codCliente], p);
                                    break;
                                case 3:
                                    break;
                                default:
                                    System.out.println("OPÇÃO INVÁLIDA");
                                    break;
                            }

                        }while(op2 != 3);
                        break;
                    case 2:
                        //SUB MENU - PROFISSIONAL
                        do{
                            System.out.println("");
                            System.out.println("----- TELA DO PROFISSIONAL -----");
                            System.out.println("1 - Cadastrar");
                            System.out.println("2 - Consultar");
                            System.out.println("3 - Voltar ao Menu");
                            System.out.print("Escolha uma das opções: ");

                            op2 = leitor.nextInt();

                            switch(op2){
                                case 1:
                                    p[Profissional.getCodProfissional()] =  Agenda10.telaProfissional1();
                                    break;
                                case 2:
                                    int cod = Agenda10.telaProfissional2_1(p);
                                    p[cod] = Agenda10.telaProfissional2_2(p[cod]);
                                    break;
                                case 3:
                                    break;
                                default:
                                    System.out.println("OPÇÃO INVÁLIDA");
                                    break;
                            }

                        }while(op2 != 3);
                        break;
                    case 3:
                        //SAIR DO PROGRAMA
                        repetir = false;
                        break;
                    default:
                        //FILTRO DE ERRO
                        System.out.println("!!! OPÇÃO INVÁLIDA !!!");
                        repetir = true;
                        break;
                }
            }catch(java.util.InputMismatchException erro){
                System.out.println("!!! ERRO !!! ");
                leitor.nextLine();
            }   
        }while(repetir);
        
    }
    
    // Métodos das Telas Principais do Cliente
    
    public static Cliente telaCliente1(){
        Scanner leitor = new Scanner(System.in);
        String[] dados = new String[6];
        Cliente c;
        
        System.out.println("");
        System.out.println("----- TELA CADASTRAR CLIENTE -----");
        System.out.print("Nome: ");
        dados[0] = leitor.next();
        System.out.print("Sexo: ");
        dados[1] = leitor.next();
        System.out.print("E-Mail: ");
        dados[2] = leitor.next();
        System.out.print("Celular: ");
        dados[3] = leitor.next();
        System.out.print("RG: ");
        dados[4] = leitor.next();
        System.out.print("CPF: ");
        dados[5] = leitor.next();
        
        c = new Cliente(dados[0], dados[1], dados[2], dados[3], dados[4], dados[5]);
        System.out.println("REGISTRO CADASTRADO COM SUCESSO");
        return c;
    }
    
    //Usuário recebe as informações do registro
    public static int telaCliente2_1(Cliente[] clientes){
        System.out.println("\n----- TELA DE CONSULTA CLIENTE -----");
        Cliente c = Agenda10.codCliente(clientes);
        int r = 0;
        if(c != null){
            c.consultarR();
            r = c.getCod();
        }
        return r;
    }
    
    //Usuário toma decisão
    public static Cliente telaCliente2_2(Cliente c, Profissional[] ps){
        String r = "";
        Scanner leitor = new Scanner(System.in);
        
        if(c != null){
            do{
                System.out.println("\nOpções: ");
                System.out.println("    A - ALTERAR");
                System.out.println("    D - DELETAR");
                System.out.println("    AG - AGENDA");
                System.out.println("    S - Sair");
                System.out.print("Escolha uma das opções (letras): ");
                r = leitor.next();
            }while(!r.equals("s") && !r.equals("d") && !r.equals("a") && !r.equals("ag"));

            // se for alterar
            if(r.equals("a")){
                Agenda10.casoAltereCliente(c);
            }else if(r.equals("d")){
                c = Agenda10.casoDeleteCliente(c);
            }else if(r.equals("ag")){
                Agenda10.casoAgendaCliente(c, ps);
            }
        }else{
            System.out.println("!!! ERRO - O CÓDIGO NÃO É VÁLIDO !!!");
        }
        return c;
    }
    
    public static void casoAltereCliente(Cliente c){
        int ndado;
        String dado;
        boolean repetir = false;
        Scanner leitor = new Scanner(System.in);
        
        do{
            System.out.print("Qual dado você quer alterar(numeros): ");
            ndado = leitor.nextInt();
            if((ndado < 1 || ndado > 6) || ndado == 2){
                System.out.println("\n!!! Coloque um campo válido !!!");
                repetir = true;
            }else{
                repetir = false;
            }
        }while(repetir);
        System.out.print("\nInforme o valor desse dado: ");
        dado = leitor.next(); 

        c.alterarR(dado, ndado);
        System.out.println("CAMPO ALTERADO COM SUCESSO");
    }
    
    public static Cliente casoDeleteCliente(Cliente c){
        Scanner leitor = new Scanner(System.in);
        String r = "";
        
        do{
            System.out.print("Tem certeza que deseja DELETAR esse registro? (S/N): ");
            r = leitor.next();
            
            if(!r.equals("s") && !r.equals("n")){
                System.out.println("OPÇÃO INVÁLIDA");
            }
        }while(!r.equals("s") && !r.equals("n"));
        if(r.equals("s")){
           c = null;
        }
        
        System.out.println("REGISTRO DELETADO COM SUCESSO");
        return c;
    }

    public static void casoAgendaCliente(Cliente c, Profissional[] ps){
        Scanner leitor = new Scanner(System.in);
        int r = 0;
        
        do{
            do{
                System.out.println("\n----- TELA OPÇÕES AGENDA -----");
                System.out.println("1 - Agendar");
                System.out.println("2 - Agendamentos");
                System.out.println("3 - Sair");
                System.out.print("Escolha uma das opções: ");
                r = leitor.nextInt();

                if(r < 1 || r > 3){
                    System.out.println("OPÇÃO INVÁLIDA");
                }
            }while(r < 1 || r > 3);

            switch(r){
                case 1:
                    Agenda10.casoAgenda_Agendar1(c, ps);
                    break;
                case 2:
                    Agenda10.casoAgenda_Agendamentos2(c);
                    break;
                case 3:
                    break;
            }
        }while(r != 3);
    }
    
    public static void casoAgenda_Agendar1(Cliente c, Profissional[] ps){
        Scanner leitor = new Scanner(System.in);
        int r[] = new int[4];
        Profissional p = null;
        
        System.out.println("");
        System.out.println("----- TELA PARA AGENDAR -----");
        p = Agenda10.codProfissional(ps);
              
        System.out.print("Sessão: ");
        r[0] = leitor.nextInt();
        System.out.print("Dia: ");
        r[1] = leitor.nextInt();
        System.out.print("Mês: ");
        r[2] = leitor.nextInt();
        System.out.print("Ano: ");
        r[3] = leitor.nextInt();
        
        c.agendar(c, p, r[0], r[1], r[2], r[3]);
        
    }
    
    public static void casoAgenda_Agendamentos2(Cliente c){
        c.agenda.consultar_Registro_Agenda();
    }
    
    public static Cliente codCliente(Cliente[] c){
        Cliente r;
        Scanner leitor = new Scanner(System.in);
        int cod = 0; 
        
        System.out.print("Informe o código Cliente: ");
        cod = leitor.nextInt();
        
        return r = c[cod];
    }    
    
    // Métodos das Telas Principais do Profissional
    public static Profissional telaProfissional1(){
        Scanner leitor = new Scanner(System.in);
        String[] dados = new String[7];
        Profissional p;
        
        System.out.println("");
        System.out.println("----- TELA CADASTRAR PROFISSIONAL -----");
        System.out.print("Nome: ");
        dados[0] = leitor.next();
        System.out.print("Sexo: ");
        dados[1] = leitor.next();
        System.out.print("E-Mail: ");
        dados[2] = leitor.next();
        System.out.print("Celular: ");
        dados[3] = leitor.next();
        System.out.print("RG: ");
        dados[4] = leitor.next();
        System.out.print("CPF: ");
        dados[5] = leitor.next();
        System.out.print("Função: ");
        dados[6] = leitor.next();
        
        p = new Profissional(dados[0], dados[1], dados[2], dados[3], dados[4], dados[5], dados[6]);
        System.out.println("REGISTRO CADASTRADO COM SUCESSO");
        return p;
    }
    
    //Usuário recebe as informações do registro
    public static int telaProfissional2_1(Profissional[] profissional){
        System.out.println("\n----- TELA DE CONSULTA PROFISSIONAL -----");
        Profissional p = Agenda10.codProfissional(profissional);
        int r = 0;
        
        if(p != null){
            p.consultarR();
            r = p.getCod();
        }
        return r;
    }
    
    //Usuário toma decisão
    public static Profissional telaProfissional2_2(Profissional p){
        String r = "";
        Scanner leitor = new Scanner(System.in);
        
        if(p != null){
            do{
                System.out.println("\nOpções: ");
                System.out.println("    A - ALTERAR");
                System.out.println("    D - DELETAR");
                System.out.println("    AG - AGENDA");
                System.out.println("    S - Sair");
                System.out.print("Escolha uma das opções (letras): ");
                r = leitor.next();
            }while(!r.equals("s") && !r.equals("d") && !r.equals("a") && !r.equals("ag"));

            // se for alterar
            if(r.equals("a")){
                Agenda10.casoAltereProfissional(p);
            }else if(r.equals("d")){
                p = Agenda10.casoDeleteProfissional(p);
            }else if(r.equals("ag")){
                Agenda10.casoAgendaProfissional(p);
            }
        }else{
            System.out.println("!!!O código não é válido!!!");
        }
        return p;
    }
    
    public static void casoAltereProfissional(Profissional p){
        int ndado;
        String dado;
        boolean repetir = false;
        Scanner leitor = new Scanner(System.in);
        
        do{
            System.out.print("Qual dado você quer alterar(numeros): ");
            ndado = leitor.nextInt();
            if((ndado < 1 || ndado > 7) || ndado == 2){
                System.out.println("\n!!! Coloque um campo válido !!!");
                repetir = true;
            }else{
                repetir = false;
                }
        }while(repetir);
        System.out.print("\nInforme o valor desse dado: ");
        dado = leitor.next(); 
        
        p.alterarR(dado, ndado);
        System.out.println("CAMPO ALTERADO COM SUCESSO");
    }
    
    public static Profissional casoDeleteProfissional(Profissional p){
        Scanner leitor = new Scanner(System.in);
        String r = "";
        
        do{
            System.out.print("Tem certeza que deseja DELETAR esse registro? (S/N): ");
            r = leitor.next();
            
            if(!r.equals("s") && !r.equals("n")){
                System.out.println("OPÇÃO INVÁLIDA");
            }
        }while(!r.equals("s") && !r.equals("n"));
        if(r.equals("s")){
           p = null;
        }
        
        System.out.println("REGISTRO DELETADO COM SUCESSO");
        return p;
    }    
    
    public static void casoAgendaProfissional(Profissional p){
        Scanner leitor = new Scanner(System.in);
        int r = 0;
        
        do{
            do{
                System.out.println("\n----- TELA OPÇÕES AGENDA -----");
                System.out.println("1 - Agendamentos");
                System.out.println("2 - Consultar Agenda através de uma pessoa");
                System.out.println("3 - Sair");
                System.out.print("Escolha uma das opções: ");
                r = leitor.nextInt();

                if(r < 1 || r > 3){
                    System.out.println("OPÇÃO INVÁLIDA");
                }
            }while(r < 1 || r > 3);

            switch(r){
                case 1:
                    Agenda10.casoAgenda_Agendamentos1(p);
                    break;
                case 2:
                    
                    break;
                case 3:
                    break;
            }
        }while(r != 3);
    }
    
    public static void casoAgenda_Agendamentos1(Profissional p){
        p.agenda.consultar_Registro_Agenda();
    }
    
    public static Profissional codProfissional(Profissional[] p){
        Profissional r;
        Scanner leitor = new Scanner(System.in);
        int cod = 0; 
        
        System.out.print("Informe o código Profissional: ");
        cod = leitor.nextInt();
        
        return r = p[cod];
    }
          
}
