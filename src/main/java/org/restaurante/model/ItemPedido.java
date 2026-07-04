package org.restaurante.model;

public class ItemPedido {

    private int idPedido;
    private int idPrato;
    private int quantidade;

    public int getIdPedido() {return idPedido;}
    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdPrato() {return idPrato;}
    public void setIdPrato(int idPrato) {
        this.idPrato = idPrato;
    }

    public int getQuantidade() {return quantidade;}
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public ItemPedido(int idPedido, int idPrato, int quantidade) {
        this.idPedido = idPedido;
        this.idPrato = idPrato;
        this.quantidade = quantidade;
    }
}