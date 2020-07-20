package br.com.anderson.agenda1.princinpal;

import br.com.anderson.agenda1.pessoas.Profissional;
import java.util.Scanner;

public class Telas_Profissional {
    public static Profissional cadastrar(){
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
    public static int consultar(Profissional[] profissional){
        System.out.println("\n----- TELA DE CONSULTA PROFISSIONAL -----");
        Profissional p = Telas_Profissional.cod_Profissional(profissional);
        int r = 0;
        
        if(p != null){
            p.consultarR();
            r = p.getCod();
        }
        return r;
    }
    
    //Usuário toma decisão
    public static Profissional opcoes_Profissional(Profissional p){
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
                Telas_Profissional.opcoes_Profissional_Alterar(p);
            }else if(r.equals("d")){
                p = Telas_Profissional.opcoes_Profissional_Deletar(p);
            }else if(r.equals("ag")){
                Telas_Profissional.opcoes_Profissional_Agenda(p);
            }
        }else{
            System.out.println("!!!O código não é válido!!!");
        }
        return p;
    }
    
    public static void opcoes_Profissional_Alterar(Profissional p){
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
    
    public static Profissional opcoes_Profissional_Deletar(Profissional p){
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
    
    public static void opcoes_Profissional_Agenda(Profissional p){
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
                    Telas_Profissional.opcoes_Profissional_Agenda_Agendamentos(p);
                    break;
                case 2:
                    
                    break;
                case 3:
                    break;
            }
        }while(r != 3);
    }
    
    public static void opcoes_Profissional_Agenda_Agendamentos(Profissional p){
        p.getAgenda().consultar_Registro_Agenda();
    }
    
    public static Profissional cod_Profissional(Profissional[] p){
        Profissional r;
        Scanner leitor = new Scanner(System.in);
        int cod = 0; 
        
        System.out.print("Informe o código Profissional: ");
        cod = leitor.nextInt();
        
        return r = p[cod];
    }
}
