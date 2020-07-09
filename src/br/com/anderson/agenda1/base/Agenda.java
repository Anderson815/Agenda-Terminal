package br.com.anderson.agenda1.base;

public class Agenda {
    //Atributos
    
    // fazer uma tabela, ou seja uma matriz(vetor[][])
    private Object[][] registros = new Object[0][5];
    private int cod;
    
    
    //Métodos
    /**
     * Será inserido um novo registro de agendamento na agenda da pessoa
     * @param p é a pessoa que será agendada
     * @param s é a sessão que será agendado
     * @param d é o dia que será agendado
     * @param m é o mês que será agendado
     * @param a é o ano que será agendado
     */
    public void inserir_Registro_Agenda(Pessoa p, int s, int d, int m, int a){
        Object[][] matriz = this.registros;
        this.registros = new Object[this.registros.length + 1][5];
        int cod = 0;
        
        while(matriz.length != 0 && cod < matriz.length){
            for(int id = 0; id < 5; id++){
                this.registros[cod][id] = matriz[cod][id];
            }
            cod++;
        }
        
        this.registros[this.registros.length - 1][0] = p;
        this.registros[this.registros.length - 1][1] = s;
        this.registros[this.registros.length - 1][2] = d;
        this.registros[this.registros.length - 1][3] = m;
        this.registros[this.registros.length - 1][4] = a;

    }
    
    /**
     * Exibe todos os agendamentos da pessoa
     */
    public void consultar_Registro_Agenda(){
        int cod = 0;
        while(this.registros.length != 0 && cod < this.registros.length){
            System.out.println("----- " + (cod + 1) + "º REGISTRO -----");
            System.out.println("Pessoa: " + this.registros[cod][0].toString());
            System.out.println("Data: " + this.registros[cod][2].toString() + "/" + this.registros[cod][3].toString() + "/" + this.registros[cod][4].toString());
            System.out.println("Sessão: " + this.registros[cod][1].toString());
            cod++;
        }
    }
    
    /**
     * Irá analisar se a data, a sessão e a pessoa(Profissional) está disponível para agendamento
     * @param p é a pessoa que será agendada
     * @param s é a sessão que será agendado
     * @param d é o dia que será agendado
     * @param m é o mês que será agendado
     * @param a é o ano que será agendado
     * @return retorna true se stiver disponípel para agendamento, caso contrário retorna false
     */
    public boolean disponivel_essa_sessao(Pessoa p, int s, int d, int m, int a ){
        boolean r = true;
        
        for(int agendamento = 0; agendamento < p.agenda.registros.length; agendamento++){
            if(Integer.parseInt(p.agenda.registros[agendamento][1].toString()) == s &&
            Integer.parseInt(p.agenda.registros[agendamento][2].toString()) == d &&
            Integer.parseInt(p.agenda.registros[agendamento][3].toString()) == m &&
            Integer.parseInt(p.agenda.registros[agendamento][4].toString()) == a){
                r = false;
            }
            
        }
        
        return r;
    }
    
/**
 *  serve para remover agendamentos
 * @param cod_del codigo do registro que será deletado 
 */    
    public void remover_Registro_Agenda(int cod_del){
        Object[][] matriz = this.registros;
        this.registros = new Object[this.registros.length - 1][5];
        
        for(int cod = 0; cod < this.registros.length; cod ++){
            for(int coluna = 0; coluna < 5; coluna++){
                if(cod < cod_del)this.registros[cod][coluna] = matriz[cod][coluna];
                else this.registros[cod][coluna] = matriz[cod + 1][coluna];
            }
        }        
    }
           
}
