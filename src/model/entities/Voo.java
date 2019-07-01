/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author itzfeltrin
 */
public class Voo implements java.lang.Comparable<Voo> {

    private String icaoEmpresa;
    private String nrVoo;
    private String codDI;
    private char codTipoLinha;
    private String icaoOrigem;
    private String icaoDestino;
    private LocalDateTime partidaPrev;
    private LocalDateTime partidaReal;
    private LocalDateTime chegadaPrev;
    private LocalDateTime chegadaReal;
    private SituacaoVoo situacao;
    private String codJustificativa;

    public Voo(String[] array) {
        this.icaoEmpresa = array[0];
        this.nrVoo = array[1];
        this.codDI = array[2];
        this.codTipoLinha = array[3].charAt(0);
        this.icaoOrigem = array[4];
        this.icaoDestino = array[5];
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        if (array[6].charAt(0) == '-') {
            this.partidaPrev = null;
            //System.out.print("6 = null - ");
        } else {
            this.partidaPrev = LocalDateTime.parse(array[6].replaceAll("-", " "), df);
            //System.out.print("6 = not null - ");
        }
        if (array[7].charAt(0) == '-') {
            this.partidaReal = null;
            //System.out.print("7 = null - ");
        } else {
            this.partidaReal = LocalDateTime.parse(array[7].replaceAll("-", " "), df);
            //System.out.print("7 = not null - ");
        }
        if (array[8].charAt(0) == '-') {
            this.chegadaPrev = null;
            //System.out.print("8 = null - ");
        } else {
            this.chegadaPrev = LocalDateTime.parse(array[8].replaceAll("-", " "), df);
            //System.out.print("8 = not null - ");
        }
        if (array[9].charAt(0) == '-') {
            this.chegadaReal = null;
            //System.out.print("9 = null - ");
        } else {
            this.chegadaReal = LocalDateTime.parse(array[8].replaceAll("-", " "), df);
            //System.out.print("9 = not null - ");
        }
        this.situacao = SituacaoVoo.valueOf(array[10].toUpperCase());
        if (array.length <= 11) {
            this.codJustificativa = null;
        } else {
            this.codJustificativa = array[11];
        }
        //System.out.println("partida prev = " + this.partidaPrev + " - partida real - " + this.partidaReal + " - chegada prev - " + this.chegadaPrev + " - chegada real - " + this.chegadaReal);
    }

    @Override
    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        if (this.partidaPrev != null && this.partidaReal != null) {
            return centerString(16, dtf.format(this.partidaPrev)) + " - " + centerString(6, this.icaoOrigem) + " - " + centerString(7, this.icaoDestino) + " - " + centerString(5, this.icaoEmpresa)
                    + " - " + centerString(5, this.nrVoo) + " - " + centerString(6, this.codDI) + " - " + centerString(16, dtf.format(this.partidaReal))
                    + " - " + this.situacao;
        } else {
            return centerString(16, "-") + " - " + centerString(6, this.icaoOrigem) + " - " + centerString(7, this.icaoDestino) + " - " + centerString(5, this.icaoEmpresa)
                    + " - " + centerString(5, this.nrVoo) + " - " + centerString(6, this.codDI) + " - " + centerString(16, "-")
                    + " - " + this.situacao;
        }
    }

    public static String centerString(int width, String s) {
        return String.format("%-" + width + "s", String.format("%" + (s.length() + (width - s.length()) / 2) + "s", s));
    }

    public String[] consultarChegadas(String data, String sigla) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("HH:mm");
        if (this.situacao == SituacaoVoo.REALIZADO) {
            if (this.icaoDestino.toUpperCase().equals(sigla.toUpperCase()) && (this.chegadaPrev.format(dtf).equals(data) || (this.chegadaReal.format(dtf).equals(data)))) {
                String[] result = {this.chegadaPrev.format(dtf2), this.icaoOrigem, this.icaoEmpresa, this.nrVoo, this.codDI, this.chegadaReal.format(dtf2), situacao.toString()};
                return result;
            }
        } else if (this.situacao == SituacaoVoo.CANCELADO) {
            if (this.icaoDestino.toUpperCase().equals(sigla.toUpperCase())) {
                if (this.chegadaPrev != null && this.chegadaPrev.format(dtf).equals(data)) {
                    String[] result = {this.chegadaPrev.format(dtf2), this.icaoOrigem, this.icaoEmpresa, this.nrVoo, this.codDI, "-", situacao.toString()};
                    return result;
                }
            }
        }
        return null;
    }

    public String[] consultarSaidas(String data, String sigla) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("HH:mm");
        if (this.situacao == SituacaoVoo.REALIZADO) {
            if (this.icaoOrigem.toUpperCase().equals(sigla.toUpperCase()) && (this.partidaPrev.format(dtf).equals(data) || this.partidaReal.format(dtf).equals(data))) {
                String[] result = {this.partidaPrev.format(dtf2), this.icaoDestino, this.icaoEmpresa, this.nrVoo, this.codDI, this.partidaReal.format(dtf2), situacao.toString()};
                return result;
            }
        } else if (this.situacao == SituacaoVoo.CANCELADO) {
            if (this.icaoDestino.toUpperCase().equals(sigla.toUpperCase())) {
                if (this.partidaPrev != null && this.partidaPrev.format(dtf).equals(data)) {
                    String[] result = {this.chegadaPrev.format(dtf2), this.icaoOrigem, this.icaoEmpresa, this.nrVoo, this.codDI, "-", situacao.toString()};
                    return result;
                }
            }
        }
        return null;
    }

    
    @Override
    public int compareTo(Voo other) {
        if(getChegadaPrev() == null || other.getChegadaPrev() == null)
            return 0;        
        int i = getChegadaPrev().compareTo(other.getChegadaPrev());
        if (i != 0) 
            return i;

        if(getPartidaPrev() == null || other.getPartidaPrev() == null)
            return 0;
        i = getPartidaPrev().compareTo(other.getPartidaPrev());
        if (i != 0) 
            return i;
        
        return 0;
    }

    public String geticaoEmpresa() {
        return icaoEmpresa;
    }

    public String getNrVoo() {
        return nrVoo;
    }

    public String getCodDI() {
        return codDI;
    }

    public char getCodTipoLinha() {
        return codTipoLinha;
    }

    public String geticaoOrigem() {
        return icaoOrigem;
    }

    public String geticaoDestino() {
        return icaoDestino;
    }

    public LocalDateTime getPartidaPrev() {
        return partidaPrev;
    }

    public LocalDateTime getPartidaReal() {
        return partidaReal;
    }

    public LocalDateTime getChegadaPrev() {
        return chegadaPrev;
    }

    public LocalDateTime getChegadaReal() {
        return chegadaReal;
    }

    public SituacaoVoo getSituacao() {
        return situacao;
    }

    public String getCodJustificativa() {
        return codJustificativa;
    }
}
