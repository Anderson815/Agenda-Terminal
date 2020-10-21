package br.com.anderson.agenda1.princinpal;

import br.com.anderson.agenda1.pessoas.Cliente;
import br.com.anderson.agenda1.pessoas.Profissional;
import java.util.Scanner;
import java.util.List;

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
    
    
    public static int consultar(List<Cliente> lc) throws br.com.anderson.agenda1.erro.CodInvalido, br.com.anderson.agenda1.erro.SemRegistro{
        Scanner leitor = new Scanner(System.in);
        int cod = 0;
        int indice_cod = 0;
        
        System.out.println("\n----- TELA DE CONSULTA CLIENTE -----");  
        if(lc.isEmpty()) throw new br.com.anderson.agenda1.erro.SemRegistro();
        else{
            System.out.println("(Cod) Nome");
            for(int indice = 0; indice < lc.size(); indice++){
                String espaco_zero =  "";
                
                if(lc.get(indice).getCod() - 10 < 0) espaco_zero = "00";
                else if(lc.get(indice).getCod() - 100 < 0) espaco_zero = "0";
                else espaco_zero = ""; 
                
                System.out.println("(" + espaco_zero + lc.get(indice).getCod() + ") " + lc.get(indice).getNome());
            }
            
            System.out.print("\nInforme o código do Cliente: ");
            cod = leitor.nextInt();
                    
            boolean existe = false;
            
            for(int indice = 0; indice < lc.size(); indice++){
                if(lc.get(indice).getCod() == cod){
                    existe = true;
                    indice_cod = indice;
                    break;
                }
            }
            if(cod <= 0 || !existe) throw new br.com.anderson.agenda1.erro.CodInvalido();   
        }
        
        return indice_cod;
    }
    
    //Usuário toma decisão
    public static void opcoes_Cliente(List<Cliente> lc, Cliente c, List<Profissional> ps) throws br.com.anderson.agenda1.erro.CodInvalido, br.com.anderson.agenda1.erro.SemRegistro{
        String r = "";
        Scanner leitor = new Scanner(System.in);

        do{
            c.consultarR();
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
                Telas_Cliente.opcoes_Cliente_Alterar(c);
                break;
            case "d":
                Telas_Cliente.opcoes_Cliente_Deletar(lc, c);
                break;
            case "ag":
                Telas_Cliente.opcoes_Cliente_Agenda(c, ps);
                break;
        }
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
            }else repetir = false;
        }while(repetir);
        System.out.print("\nInforme o valor desse dado: ");
        dado = leitor.next(); 

        c.alterarR(dado, ndado);
        System.out.println("CAMPO ALTERADO COM SUCESSO");
    }
    
    public static void opcoes_Cliente_Deletar(List<Cliente> lc, Cliente c){
        Scanner leitor = new Scanner(System.in);
        String r = "";
        
        do{
            System.out.print("Tem certeza que deseja DELETAR esse registro? (S/N): ");
            r = leitor.next();
            
            if(!r.equals("s") && !r.equals("n")) System.out.println("OPÇÃO INVÁLIDA");
            
        }while(!r.equals("s") && !r.equals("n"));
        
        if(r.equals("s"))lc.remove(c);
        
        System.out.println("REGISTRO DELETADO COM SUCESSO");
    }

    public static void opcoes_Cliente_Agenda(Cliente c, List<Profissional> ps) throws br.com.anderson.agenda1.erro.CodInvalido, br.com.anderson.agenda1.erro.SemRegistro{
        Scanner leitor = new Scanner(System.in);
        int r = 3;
               
        do{
            System.out.println("\n--------- TELA AGENDA ---------");
            c.getAgenda().consultar_Registro_Agenda();
            
            do{
            System.out.println("OPÇÕES:");
            System.out.println("1 - Agendar");
            System.out.println("2 - Cancelar");
            System.out.println("3 - Sair da agenda");
            System.out.print("Escolha uma das opções: ");
            
            r = leitor.nextInt();
            
            if(r < 1 || r > 3){
                System.out.println("OPÇÃO INVÁLIDA");
            }
            }while(r <1 || r > 3);
            
            switch(r){
                case 1:
                    Telas_Cliente.opcoes_Cliente_Agenda_Agendar(c, ps);
                    break;
                case 2:
                    Telas_Cliente.opcoes_Cliente_Agenda_Cancelar(c, ps);
                    break;
                case 3:
                    break;
            }
            
        }while(r != 3);

    }
    
    public static void opcoes_Cliente_Agenda_Agendar(Cliente c, List<Profissional> ps) throws br.com.anderson.agenda1.erro.CodInvalido, br.com.anderson.agenda1.erro.SemRegistro{
        Scanner leitor = new Scanner(System.in);
        int r[] = new int[4];
        Profissional p = null;
        
        System.out.println("");
        System.out.println("----- TELA PARA AGENDAR -----");
        try{
            p = ps.get(Telas_Profissional.cod_Profissional(ps));

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
    
    public static void opcoes_Cliente_Agenda_Cancelar(Cliente c, List<Profissional> ps){
        Scanner leitor = new Scanner(System.in);
        int r = 0;
        String conf = "";
        
        if(c.getAgenda().quantidade_registros() > 0){
            do{
                System.out.println("----- TELA PARA CANCELAR -----");
                System.out.print("Digite o número do registro que você quer cancelar: ");
                r = leitor.nextInt();
                
                if(r < 1 || r > c.getAgenda().quantidade_registros()){
                    System.out.println("ERRO - REGISTRO INEXISTENTE\n");
                }
            }while(r < 1 || r > c.getAgenda().quantidade_registros()); 
            do{
                System.out.println("REGISTRO:\n");
                r--;
                c.getAgenda().consultar_Registro_Agenda(r);//alterado
                System.out.print("\nDeseja mesmo cancelar esse agendamento? (S/N): ");
                conf = leitor.next();
                if(!conf.equals("s") && !conf.equals("n")) System.out.println("OPÇÃO INVÁLIDA");
                else{
                    if(conf.equals("s")){
                        Profissional p = (Profissional) c.getAgenda().getPessoa(r);
                        int rp = p.getAgenda().getCod(c.getAgenda().getRegistro(r));
                        
                        c.getAgenda().remover_Registro_Agenda(r);
                        p.getAgenda().remover_Registro_Agenda(rp);
                        
                        // CONTINUAR AQUI
                    }
                }
            }while(!conf.equals("s") && !conf.equals("n"));
        }else{
            System.out.println("NÃO TEM NADA AGENDADO PARA ESSE CLIENTE");
        }
    }
}
