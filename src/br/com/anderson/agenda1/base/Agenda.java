package br.com.anderson.agenda1.base;

public class Agenda {
    //Atributos
    
    // fazer uma tabela, ou seja uma matriz(vetor[][])
    private Object[][] registros = new Object[0][5];
    
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
            int s = Integer.parseInt(this.registros[cod][1].toString());
            int d = Integer.parseInt(this.registros[cod][2].toString());
            int m = Integer.parseInt(this.registros[cod][3].toString());
            
            String ses = (s < 10)? "0" + Integer.toString(s) : Integer.toString(s);
            String dia = (d < 10)? "0" + Integer.toString(d) : Integer.toString(d);
            String mes = (m < 10)? "0" + Integer.toString(m) : Integer.toString(m);
            
            String sub_classe_pessoa = (this.registros[cod][0] instanceof br.com.anderson.agenda1.pessoas.Cliente)? "Cliente: " : "Profissional: ";
            
            System.out.println("" + (cod + 1) + "º REGISTRO");
            System.out.println(sub_classe_pessoa + this.registros[cod][0].toString());
            System.out.print("Sessão: " + ses);
            System.out.println("     Data: " + dia + "/" + mes + "/" + this.registros[cod][4].toString());
            System.out.println("-------------------------------");
            cod++;
        }
    }
    
    /**
     * Esse método escreve na tela somente os detalhes do registro informado
     * @param cod é o indice do registro que deseja consultar
     */
    public void consultar_Registro_Agenda(int cod){
        int s = Integer.parseInt(this.registros[cod][1].toString());
        int d = Integer.parseInt(this.registros[cod][2].toString());
        int m = Integer.parseInt(this.registros[cod][3].toString());
        
        String ses = s < 10? "0" + Integer.toString(s): Integer.toString(s);
        String dia = d < 10? "0" + Integer.toString(d): Integer.toString(d);
        String mes = m < 10? "0" + Integer.toString(m): Integer.toString(m);
        
        System.out.println((cod + 1) + "º REGISTRO");
        System.out.println("Profissional: " + this.registros[cod][0].toString());
        System.out.print("Sessão: " + ses);
        System.out.println("     Data: " + dia + "/" + mes + "/" + this.registros[cod][4].toString());
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
        for(int agendamento = 0; agendamento < p.agenda.registros.length; agendamento++){
            if(Integer.parseInt(p.agenda.registros[agendamento][1].toString()) == s &&
            Integer.parseInt(p.agenda.registros[agendamento][2].toString()) == d &&
            Integer.parseInt(p.agenda.registros[agendamento][3].toString()) == m &&
            Integer.parseInt(p.agenda.registros[agendamento][4].toString()) == a){
                return false;
            }
            
        }
        return true;
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
    
    public int quantidade_registros(){
        return this.registros.length;
    }
    
    public Object getPessoa(int cod){
        return this.registros[cod][0]; //Pessoa do registro
    }
    
    public int getCod(Object[] parametros){
        int r = -1;
        // o indice 0 está comparando dois objetoa diferentes, um é cliente e outro é profissional
        for(int indice = 0; indice < this.registros.length; indice++){
            if(Integer.parseInt(this.registros[indice][1].toString()) == Integer.parseInt(parametros[1].toString()) &&
            Integer.parseInt(this.registros[indice][2].toString()) == Integer.parseInt(parametros[2].toString()) &&
            Integer.parseInt(this.registros[indice][3].toString()) == Integer.parseInt(parametros[3].toString()) &&
            Integer.parseInt(this.registros[indice][4].toString()) == Integer.parseInt(parametros[4].toString())){
                r = indice;
                break;
            }
        }
        return r;
    }
    
    public Object[] getRegistro(int cod){
        return this.registros[cod];
    }
}
