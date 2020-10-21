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
    public static int consultar(List<Profissional> lp) throws br.com.anderson.agenda1.erro.CodInvalido, br.com.anderson.agenda1.erro.SemRegistro{
        System.out.println("\n----- TELA DE CONSULTA PROFISSIONAL -----");
        int indice_cod = Telas_Profissional.cod_Profissional(lp);
        return indice_cod;
    }
    
    public static int cod_Profissional(List<Profissional> lp) throws br.com.anderson.agenda1.erro.SemRegistro, br.com.anderson.agenda1.erro.CodInvalido{
        Scanner leitor = new Scanner(System.in);
        int cod = 0; 
        int indice_cod = 0;

        if(lp.isEmpty()) throw new br.com.anderson.agenda1.erro.SemRegistro();
        else{
            
            System.out.println("(Cod) Profissional");
            
            for(int contador = 0; contador < lp.size(); contador++){
                String espaco_zero = "";
                if(lp.get(contador).getCod() < 10) espaco_zero = "00";
                else if(lp.get(contador).getCod() < 100) espaco_zero = "0";
                
                System.out.println("(" + (espaco_zero + lp.get(contador).getCod()) + ") " + lp.get(contador).getNome());
            }
            
            System.out.print("\nInforme o código Profissional: ");
            cod = leitor.nextInt();
            boolean existe = false;
            
            for(Profissional p: lp){
                if(p.getCod() == cod){
                    existe = true;
                    indice_cod = lp.indexOf(p);
                    break;
                }
            }
            
            if(cod <= 0 || !existe) throw new br.com.anderson.agenda1.erro.CodInvalido();
        }
        return indice_cod;
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

        System.out.println("\n--------- TELA AGENDA ---------");
        p.getAgenda().consultar_Registro_Agenda();
        
        do{
            System.out.print("\nPara sair aperte a tecla 0 e depois enter: ");
            r = leitor.nextInt();
        }while(r != 0);
    }

}
