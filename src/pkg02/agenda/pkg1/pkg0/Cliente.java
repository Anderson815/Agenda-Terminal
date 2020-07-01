package pkg02.agenda.pkg1.pkg0;
public class Cliente extends Pessoa{    
    // Classe
    
    private static int codCliente = 1;
    
    public static int getCodCliente() {
        return codCliente;
    }

    public static void setCodCliente(int codCliente) {
        Cliente.codCliente = codCliente;
    }
    
    
    // Objeto
    
    public Cliente(String nome, String sexo, String email, String celular, String rg, String cpf){
        super(nome, sexo, email, celular, rg, cpf);
        this.setCod(Cliente.getCodCliente());
        Cliente.setCodCliente(Cliente.getCodCliente() + 1);
        
    }
    
    public void agendar(Pessoa p1, Pessoa p2, int s, int d, int m, int a){
        // p1 é o próprio objeto (Cliente)
        // p2 é o objeto pessoa com quem p1 quer agendar (Profissional)
        boolean cadastrar = this.agenda.consultar_Registro_Agenda(p2, s, d, m, a);
        if(cadastrar){
            this.agenda.inserir_Registro_Agenda(p2, s, d, m, a);
            p2.agenda.inserir_Registro_Agenda(p1, s, d, m, a);
            System.out.println("!!! Agendamento concluido !!!");
        }else{
            System.out.println("ERRO - JÁ EXISTE UM AGENDAMENTO NESSA DATA E SESSÃO!!!");
        }
    }
    
    
}
