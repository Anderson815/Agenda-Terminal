package br.com.anderson.agenda1.princinpal;

import br.com.anderson.agenda1.pessoas.Cliente;
import br.com.anderson.agenda1.pessoas.Profissional;
import java.util.Scanner;

/**
 *
 * @author ander
 */
public class Telas_Cliente {
    
    public static Cliente cadastrar(){
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
    
    
    public static int consultar(Cliente[] clientes){
        System.out.println("\n----- TELA DE CONSULTA CLIENTE -----");
        Cliente c = Telas_Cliente.cod_Cliente(clientes);
        int r = 0;
        if(c != null){
            c.consultarR();
            r = c.getCod();
        }
        return r;
    }
    
    //Usuário toma decisão
    public static Cliente opcoes_Cliente(Cliente c, Profissional[] ps){
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
                Telas_Cliente.opcoes_Cliente_Alterar(c);
            }else if(r.equals("d")){
                c = Telas_Cliente.opcoes_Cliente_Deletar(c);
            }else if(r.equals("ag")){
                Telas_Cliente.opcoes_Cliente_Agenda(c, ps);
            }
        }else{
            System.out.println("!!! ERRO - O CÓDIGO NÃO É VÁLIDO !!!");
        }
        return c;
    }
    
    public static void opcoes_Cliente_Alterar(Cliente c){
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
    
    public static Cliente opcoes_Cliente_Deletar(Cliente c){
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

    public static void opcoes_Cliente_Agenda(Cliente c, Profissional[] ps){
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
                    Telas_Cliente.opcoes_Cliente_Agenda_Agendar(c, ps);
                    break;
                case 2:
                    Telas_Cliente.opcoes_Cliente_Agenda_Agendamentos(c);
                    break;
                case 3:
                    break;
            }
        }while(r != 3);
    }
    
    public static void opcoes_Cliente_Agenda_Agendar(Cliente c, Profissional[] ps){
        Scanner leitor = new Scanner(System.in);
        int r[] = new int[4];
        Profissional p = null;
        
        System.out.println("");
        System.out.println("----- TELA PARA AGENDAR -----");
        try{
            p = Telas_Profissional.cod_Profissional(ps);

            System.out.print("Sessão: ");
            r[0] = leitor.nextInt();
            System.out.print("Dia: ");
            r[1] = leitor.nextInt();
            System.out.print("Mês: ");
            r[2] = leitor.nextInt();
            System.out.print("Ano: ");
            r[3] = leitor.nextInt();

            c.agendar(c, p, r[0], r[1], r[2], r[3]);
        }catch(ArrayIndexOutOfBoundsException erro){
            System.out.println("!!! ERRO !!! - Não temos profissional com esse COD");
        }catch(java.util.InputMismatchException erro){
            System.out.println("!!! ERRO !!! - Você não digitou o COD corretamente");
        }
    }
    
    public static void opcoes_Cliente_Agenda_Agendamentos(Cliente c){
        c.getAgenda().consultar_Registro_Agenda();
    }
    
    public static Cliente cod_Cliente(Cliente[] c){
        Cliente r;
        Scanner leitor = new Scanner(System.in);
        int cod = 0; 
        
        System.out.print("Informe o código Cliente: ");
        cod = leitor.nextInt();
        
        return r = c[cod];
    }    
}
