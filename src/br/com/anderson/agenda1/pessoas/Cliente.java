package br.com.anderson.agenda1.pessoas;
import br.com.anderson.agenda1.base.Pessoa;

public class Cliente extends Pessoa{    
    // Classe
    
    public static int cod_Cliente = 1;
    
    public static void setCodCliente(){
        cod_Cliente++;
    }
    
    public static int getCodCliente(){
        return cod_Cliente;
    }
    
    // Objeto
   
    public Cliente(String nome, String sexo, String email, String celular, String rg, String cpf){
        super(nome, sexo, email, celular, rg, cpf);  
        this.setCod(Cliente.getCodCliente());
        Cliente.setCodCliente();
    }
    
    public void agendar(Pessoa p1, Pessoa p2, int s, int d, int m, int a){
        // p1 é o próprio objeto (Cliente)
        // p2 é o objeto pessoa com quem p1 quer agendar (Profissional)
        boolean cadastrar = this.agenda.disponivel_essa_sessao(p2, s, d, m, a);
        if(cadastrar){
            this.agenda.inserir_Registro_Agenda(p2, s, d, m, a);
            p2.getAgenda().inserir_Registro_Agenda(p1, s, d, m, a);            
            System.out.println("!!! Agendamento concluido !!!");
        }else{
            System.out.println("ERRO - JÁ EXISTE UM AGENDAMENTO NESSA DATA E SESSÃO!!!");
        }
               
    }    
}
