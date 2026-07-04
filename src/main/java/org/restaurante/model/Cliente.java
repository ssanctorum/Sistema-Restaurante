package org.restaurante.model;

public class Cliente {

    private int idCliente;
    private String nomeCliente;

    public String getNomeCliente() {return nomeCliente;}
    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public int getIdCliente() {return idCliente;}
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public Cliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }
}
