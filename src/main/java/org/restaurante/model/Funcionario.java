package org.restaurante.model;

public class Funcionario {

    private int matriculaFuncionario;
    private String nomeFuncionario;

    public int getMatriculaFuncionario() {return matriculaFuncionario;}

    public String getNomeFuncionario() {return nomeFuncionario;}
    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }

    public Funcionario(int matriculaFuncionario, String nomeFuncionario) {
        this.matriculaFuncionario = matriculaFuncionario;
        this.nomeFuncionario = nomeFuncionario;
    }
}