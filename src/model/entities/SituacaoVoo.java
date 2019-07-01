/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entities;

/**
 *
 * @author itzfeltrin
 */
public enum SituacaoVoo {
    REALIZADO(0),
    CANCELADO(1);
    
    private int valor;

    private SituacaoVoo(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
    
    
}
