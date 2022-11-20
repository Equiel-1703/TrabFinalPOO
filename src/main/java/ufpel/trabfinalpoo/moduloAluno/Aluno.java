package ufpel.trabfinalpoo.moduloAluno;

class Aluno {
    protected String nome;
    protected String email;
    protected String matricula;
    protected String curso;
    protected String semIngresso;
    protected int prevFormat;

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
        if(email.matches("[\\w]+@inf.ufpel.edu.br"))
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

    public void setCurso(String curso) {
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

    public String getCurso() {
        return curso;
    }

    public String getSemIngresso() {
        return semIngresso;
    }

    public int getPrevFormat() {
        return prevFormat;
    }

    @Override
    public String toString() {
        return nome + '\n' + email + '\n' + matricula + '\n' + curso + '\n' + semIngresso + '\n' + prevFormat;
    }
}
