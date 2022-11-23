package ufpel.trabfinalpoo.generalClasses;

public class Atividade {
    protected int codigo;
    protected String tipoAtv;
    protected String unidade;
    protected int qtdeMin;
    protected int qtdeMax;

    public Atividade() {}

    public Atividade(int codigo, String tipoAtv, String unidade, int qtde, int qtdeMax) {
        this.codigo = codigo;
        this.tipoAtv = tipoAtv;
        this.unidade = unidade;
        this.qtdeMin = qtde;
        this.qtdeMax = qtdeMax;
    }

    @Override
    public String toString() {
        return codigo + " - " + tipoAtv;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getTipoAtv() {
        return tipoAtv;
    }

    public String getUnidade() {
        return unidade;
    }

    public int getQtdeMin() {
        return qtdeMin;
    }

    public int getQtdeMax() {
        return qtdeMax;
    }
}
