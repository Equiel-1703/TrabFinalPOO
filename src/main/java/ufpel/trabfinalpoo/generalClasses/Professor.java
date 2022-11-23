package ufpel.trabfinalpoo.generalClasses;

import java.io.Serializable;

public class Professor implements Serializable {
    private String nome;
    private String SIAPE;
    private String dataCad;

    @Override
    public String toString() {
        return nome + " <" + SIAPE + ">";
    }

    public boolean setNome(String nome) {
        if(!nome.isEmpty())
        {
            this.nome = nome;
            return true;
        }

        return false;
    }

    public boolean setSIAPE(String SIAPE) {
        if(SIAPE.matches("[0-9]{7}"))
        {
            this.SIAPE = SIAPE;
            return true;
        }

        return false;
    }

    public void setDataCad(String data) {
        this.dataCad = data;
    }

    // Getters

    public String getNome() {
        return nome;
    }

    public String getSIAPE() {
        return SIAPE;
    }

    public String getDataCad() {
        return dataCad;
    }
}
