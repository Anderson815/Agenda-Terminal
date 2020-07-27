package br.com.anderson.agenda1.erro;
public class CodInvalido extends Exception{    
    @Override
    public String getMessage(){
        return "!!! ESSE CÓDIGO NÃO EXISTE !!!";
    }
}
