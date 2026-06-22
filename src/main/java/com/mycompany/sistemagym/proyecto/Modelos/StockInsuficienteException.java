package com.mycompany.sistemagym.proyecto.Modelos;

public class StockInsuficienteException extends Exception {

    private final int stockDisponible;
    private final int cantidadSolicitada;

    public StockInsuficienteException(String mensaje, int stockDisponible, int cantidadSolicitada) {
        super(mensaje);
        this.stockDisponible = stockDisponible;
        this.cantidadSolicitada = cantidadSolicitada;
    }

    public int getStockDisponible() {
        return stockDisponible;
    }

    public int getCantidadSolicitada() {
        return cantidadSolicitada;
    }
}
