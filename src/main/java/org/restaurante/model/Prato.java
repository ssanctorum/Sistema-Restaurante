package org.restaurante.model;

public class Prato {

    private int idPrato;
    private String nomePrato;
    private double valorPrato;

    public int getIdPrato() {return idPrato;}
    public void setIdPrato(int idPrato) {this.idPrato = idPrato;}

    public String getNomePrato() {return nomePrato;}
    public void setNomePrato(String nomePrato) {
        this.nomePrato = nomePrato;
    }

    public double getValorPrato() {return valorPrato;}
    public void setValorPrato(double valorPrato) {
        this.valorPrato = valorPrato;
    }


    public Prato(int idPrato, String nomePrato, double valorPrato) {
        this.idPrato = idPrato;
        this.nomePrato = nomePrato;
        this.valorPrato = valorPrato;
    }
}