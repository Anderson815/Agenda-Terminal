package br.com.anderson.agenda1.base;
public abstract class Pessoa {
    
    // Atributos
    private String nome, rg, cpf, sexo, celular, email;
    private int cod;
    protected Agenda agenda = new Agenda();

    // Métodos de Interface
    
    //mudar o método consultarR() para toString()
    public void consultarR(){
        System.out.println("\n------------------------------");
        System.out.println("1 - Nome: " + this.getNome());
        System.out.println("2 - Sexo: " + this.getSexo());
        System.out.println("3 - E-Mail: " + this.getEmail());
        System.out.println("4 - Celular: " + this.getCelular());
        System.out.println("5 - RG: " + this.getRg());
        System.out.println("6 - CPF: " + this.getCpf());  
        
    }
    
    public void alterarR(String novodado, int atributo){
        switch(atributo){
            case 1:
                this.setNome(novodado);
                break;
            case 3:
                this.setEmail(novodado);
                break;
            case 4:
                this.setCelular(novodado);
                break;
            case 5:
                this.setRg(novodado);
                break;
            case 6:
                this.setCpf(novodado);
                break;
        }
    }
    
    
    // Métodos especiais
    
    public Pessoa(String nome, String sexo, String email, String celular, String rg, String cpf) {
        this.nome = nome;
        this.sexo = sexo;
        this.email = email;
        this.celular = celular;
        this.rg = rg;
        this.cpf = cpf;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }
    
    @Override
    public String toString(){
        return this.getNome();
    }
    
}
