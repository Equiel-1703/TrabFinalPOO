package ufpel.trabfinalpoo.generalClasses;

import java.io.Serializable;

public class Aluno implements Serializable {
    private String nome;
    private String email;
    private String matricula;
    private Curso curso;
    private String semIngresso;
    private int prevFormat;
    private String iconID;
    private String dataCad;

    public Aluno() {
        this.iconID = "default.png";
    }

    // ---------------------------------- Setters ----------------------------------
    public boolean setNome(String nome) {
        if(!nome.isEmpty())
        {
            this.nome = nome;
            return true;
        }

        return false;
    }

    public boolean setEmail(String email) {
        if(email.matches("[\\w\\.]+@inf.ufpel.edu.br"))
        {
            this.email = email;
            return true;
        }

        return false;
    }

    public boolean setMatricula(String matricula) {
        if(matricula.matches("[0-9]{8}"))
        {
            this.matricula = matricula;
            return true;
        }

        return false;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public boolean setSemIngresso(String semIngresso) {
        if(semIngresso.matches("[0-9]{4}/[0-9]"))
        {
            this.semIngresso = semIngresso;
            return true;
        }

        return false;
    }

    public boolean setPrevFormat(int prevFormat) {
        if(prevFormat >= 2022)
        {
            this.prevFormat = prevFormat;
            return true;
        }

        return false;
    }

    public void setIconID(String iconID) {
        this.iconID = iconID;
    }

    public void setDataCad(String dataCad) {
        this.dataCad = dataCad;
    }

    // ---------------------------------- Getters ----------------------------------


    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getMatricula() {
        return matricula;
    }

    public Curso getCurso() {
        return curso;
    }

    public String getSemIngresso() {
        return semIngresso;
    }

    public int getPrevFormat() {
        return prevFormat;
    }

    public String getIconID() {
        return iconID;
    }

    public String getDataCad() {
        return dataCad;
    }

    @Override
    public String toString() {
        return nome + " " + matricula + " <" + email + ">";
    }
}
