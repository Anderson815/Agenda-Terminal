package br.com.anderson.agenda1.princinpal;

import java.util.Scanner;
import br.com.anderson.agenda1.pessoas.Cliente;
import br.com.anderson.agenda1.pessoas.Profissional;
import java.util.ArrayList;
import java.util.List;
import br.com.anderson.agenda1.erro.CodInvalido;
import br.com.anderson.agenda1.erro.SemRegistro;

public class Agenda10 {
    
    public static void main(String[] args) {
        
        List<Cliente> c = new ArrayList<>();
        //List<Profissional> p = new ArrayList<>();
        
        List<Profissional> p = new ArrayList<>();
        //Profissional[] p = new Profissional[10];
        
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
                                    c.add(Telas_Cliente.cadastrar());
                                    //Cliente cliente = c.get(0);
                                    
                                    break;
                                case 2:
                                    int codCliente = Telas_Cliente.consultar(c);
                                    Telas_Cliente.opcoes_Cliente(c, c.get(codCliente), p);
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
                                    p.add(Telas_Profissional.cadastrar());
                                    //p[Profissional.getCodProfissional()] =  Telas_Profissional.cadastrar();
                                    break;
                                case 2:
                                    int cod = Telas_Profissional.consultar(p);
                                    Telas_Profissional.opcoes_Profissional(p, p.get(cod));
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
            }catch(CodInvalido e){
                System.out.println(e.getMessage());
            }catch(SemRegistro erro){
                System.out.println(erro.getMessage());
            }
        }while(repetir);
        
    }   
}
