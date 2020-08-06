package br.com.anderson.agenda1.erro;
public class SemRegistro extends Exception{
    @Override
    public String getMessage(){
        return "!!! NÃO EXITE REGISTROS !!!";
    }
}
