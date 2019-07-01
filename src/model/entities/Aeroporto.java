/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entities;

/**
 *
 * @author Administrador
 */
public class Aeroporto {
    private String sigla;
    private String nome;
    private String cidade;
    private String estado;
    private String pais;
    private String continente;
    
    public Aeroporto(String[] array){
        this.sigla = array[0].trim();
        this.nome = array[1].trim();
        this.cidade = array[2].trim();
        this.estado = array[3].trim();
        this.pais = array[4].trim();
        this.continente = array[5].trim();
    }

    public Aeroporto(String sigla, String nome, String cidade, String estado, String pais, String continente) {
        this.sigla = sigla;
        this.nome = nome;
        this.cidade = cidade;
        this.estado = estado;
        this.pais = pais;
        this.continente = continente;
    }

    public String getSigla() {
        return sigla;
    }

    public String getNome() {
        return nome;
    }
    
    public String getNomeByCod(String cod) {
        if(this.sigla.toUpperCase().equals(cod.toUpperCase())){
            return this.nome;
        }
        else {
            return null;
        }
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }

    public String getPais() {
        return pais;
    }

    public String getContinente() {
        return continente;
    }
    
    @Override
    public String toString(){
        return "Sigla: " + this.sigla + ", Nome: " + this.nome + ", Cidade: " + this.cidade + ", Estado: " + this.estado + ", País: " + this.pais
                + ", Continente: " + this.continente + ".";
        // terminei aqui por enquanto
    }
}
