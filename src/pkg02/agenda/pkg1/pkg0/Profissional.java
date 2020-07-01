package pkg02.agenda.pkg1.pkg0;
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
    
    public Profissional(String nome, String sexo, String email, String celular, String rg, String cpf, String funcao){
        super(nome, sexo, email, celular, rg, cpf);
        this.setFuncao(funcao);
        this.setCod(Profissional.getCodProfissional());
        Profissional.setCodProfissional(Profissional.getCodProfissional() + 1);
    }
    
    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }
    
    
}
