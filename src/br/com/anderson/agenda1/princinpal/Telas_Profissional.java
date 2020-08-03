package br.com.anderson.agenda1.princinpal;

import br.com.anderson.agenda1.pessoas.Profissional;
import java.util.Scanner;
import java.util.List;

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
    public static int consultar(List<Profissional> profissional) throws br.com.anderson.agenda1.erro.CodInvalido{
        System.out.println("\n----- TELA DE CONSULTA PROFISSIONAL -----");
        Profissional p = Telas_Profissional.cod_Profissional(profissional);
        return profissional.indexOf(p);
    }
    
    //Usuário toma decisão
    public static void opcoes_Profissional(List<Profissional> profissional, Profissional p){
        String r = "";
        Scanner leitor = new Scanner(System.in);

        do{
            p.consultarR();
            System.out.println("\nOpções: ");
            System.out.println("    A - ALTERAR");
            System.out.println("    D - DELETAR");
            System.out.println("    AG - AGENDA");
            System.out.println("    S - Sair");
            System.out.print("Escolha uma das opções (letras): ");
            r = leitor.next();
        }while(!r.equals("s") && !r.equals("d") && !r.equals("a") && !r.equals("ag"));

        switch(r){
            case "a":
                Telas_Profissional.opcoes_Profissional_Alterar(p);
                break;
            case "d":
                Telas_Profissional.opcoes_Profissional_Deletar(profissional, p);
                break;
            case "ag":
                Telas_Profissional.opcoes_Profissional_Agenda(p);
                break;
        }
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
            }else repetir = false;
        }while(repetir);
        System.out.print("\nInforme o valor desse dado: ");
        dado = leitor.next(); 
        
        p.alterarR(dado, ndado);
        System.out.println("CAMPO ALTERADO COM SUCESSO");
    }
    
    public static void opcoes_Profissional_Deletar(List<Profissional> profissional, Profissional p){
        Scanner leitor = new Scanner(System.in);
        String r = "";
        
        do{
            System.out.print("Tem certeza que deseja DELETAR esse registro? (S/N): ");
            r = leitor.next();
            
            if(!r.equals("s") && !r.equals("n")) System.out.println("OPÇÃO INVÁLIDA");
            
        }while(!r.equals("s") && !r.equals("n"));
        
        if(r.equals("s")) profissional.remove(p); 
        
        System.out.println("REGISTRO DELETADO COM SUCESSO");
    }    
    
    public static void opcoes_Profissional_Agenda(Profissional p){
        Scanner leitor = new Scanner(System.in);
        int r = 0;
        
        do{
            do{
                System.out.println("\n----- TELA OPÇÕES AGENDA -----");
                System.out.println("1 - Agendamentos");
                System.out.println("2 - Sair");
                System.out.print("Escolha uma das opções: ");
                r = leitor.nextInt();

                if(r < 1 || r > 2) System.out.println("OPÇÃO INVÁLIDA");
                
            }while(r < 1 || r > 2);

            switch(r){
                case 1:
                    Telas_Profissional.opcoes_Profissional_Agenda_Agendamentos(p);
                    break;
                case 2:
                    break;
            }
        }while(r != 2);
    }
    
    public static void opcoes_Profissional_Agenda_Agendamentos(Profissional p){
        p.getAgenda().consultar_Registro_Agenda();
    }
    
    public static Profissional cod_Profissional(List<Profissional> p) throws br.com.anderson.agenda1.erro.CodInvalido{
        Profissional r;
        Scanner leitor = new Scanner(System.in);
        int cod = 0; 
        
        System.out.print("Informe o código Profissional: ");
        cod = leitor.nextInt();
        cod--;
        
        if(cod < 0 || cod >= p.size()) throw new br.com.anderson.agenda1.erro.CodInvalido();
        
        return r = p.get(cod);
    }
}
