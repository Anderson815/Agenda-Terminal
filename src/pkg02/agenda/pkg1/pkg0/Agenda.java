package pkg02.agenda.pkg1.pkg0;
public class Agenda {
    //Atributos
    private Pessoa[] pessoa = new Pessoa[100];
    private int[] sessao = new int[100];
    private int[] dia = new int[100];
    private int[] mes = new int[100];
    private int[] ano = new int[100];
    private boolean[] valido = new boolean[100];
    
    private int cod;
    
    //Métodos de Interface
    public void inserir_Registro_Agenda(Pessoa p, int s, int d, int m, int a){
        
        this.pessoa[this.getCod()] = p;
        this.sessao[this.getCod()] = s;
        this.dia[this.getCod()] = d;
        this.mes[this.getCod()] = m;
        this.ano[this.getCod()] = a;
        this.valido[this.getCod()] = true;
        
        this.setCod(this.getCod() + 1);
    }
    
    // Efeito gráfico
    public void consultar_Registro_Agenda(){
        System.out.println("------------------------------");
        for(int contador = 0; contador < 100; contador++){           
            if(this.valido[contador]){
            System.out.println("Pessoa: " + this.pessoa[contador].getNome());
            System.out.println("Data: " + this.dia[contador] + "/" + this.mes[contador] + "/" + this.ano[contador]);
            System.out.println("Sessao: " + this.sessao[contador]);
            System.out.println("------------------------------");
            }
        }
    }
    
    
    
    // Efeito funcional
    public boolean consultar_Registro_Agenda(Pessoa p, int s, int d, int m, int a ){
        boolean r = true;
        
        for(int verificador_indice = 0; verificador_indice <= 99; verificador_indice++){
            if(s == p.agenda.sessao[verificador_indice] &&
            d == p.agenda.dia[verificador_indice] &&
            m == p.agenda.mes[verificador_indice] &&
            a == p.agenda.ano[verificador_indice] &&
            p.agenda.valido[verificador_indice]){
                r = false;
            }
        }
        return r;
    }
    
    
    
    public void remover_Registro_Agenda(int cod){
        this.valido[cod] = false;
    }
    
    //Métodos especiais
    public Agenda(){
        this.setCod(0);
    }
    
    public Pessoa[] getP() {
        return pessoa;
    }

    public void setP(Pessoa[] p) {
        this.pessoa = p;
    }

    public int[] getSessao() {
        return sessao;
    }

    public void setSessao(int[] sessao) {
        this.sessao = sessao;
    }

    public int[] getDia() {
        return dia;
    }

    public void setDia(int[] dia) {
        this.dia = dia;
    }

    public int[] getMes() {
        return mes;
    }

    public void setMes(int[] mes) {
        this.mes = mes;
    }

    public int[] getAno() {
        return ano;
    }

    public void setAno(int[] ano) {
        this.ano = ano;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }
    
}
