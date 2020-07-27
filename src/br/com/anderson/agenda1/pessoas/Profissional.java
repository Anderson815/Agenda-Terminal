package br.com.anderson.agenda1.pessoas;
import br.com.anderson.agenda1.base.Pessoa;

public class Profissional extends Pessoa{
    
    // Da Classe
    
    private static int codProfissional = 1;
    
    public static void setCodProfissional(int c){
        Profissional.codProfissional = c;
    }
    
    public static int getCodProfissional(){
        return codProfissional;
    }
    
    // Do Objeto
    
    private String funcao;
    
    @Override
    public void consultarR(){
        super.consultarR();
        System.out.println("7 - Função: " + this.getFuncao());
    }
    
    @Override
    public void alterarR(String novodado, int atributo){
        super.alterarR(novodado, atributo);
        if(atributo == 7){
            this.setFuncao(novodado);
        }
    }
    
    public void agendamento_automatico(Pessoa p1, int s, int d, int m, int a){
        this.agenda.inserir_Registro_Agenda(p1, s, d, m, a);
    }
    
    public Profissional(String nome, String sexo, String email, String celular, String rg, String cpf, String funcao){
        super(nome, sexo, email, celular, rg, cpf);
        this.setFuncao(funcao);
        //this.setCod(Profissional.getCodProfissional());
        Profissional.setCodProfissional(Profissional.getCodProfissional() + 1);
    }
    
    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }
}
