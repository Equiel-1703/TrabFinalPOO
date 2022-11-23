package ufpel.trabfinalpoo.generalClasses;

public enum Curso {
    CCOMP("Ciência da Computação"), ENGCOMP("Engenharia da Computação");

    private String nome;
    private Curso(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return this.nome;
    }
}
