/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entities;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Comparator;

/**
 *
 * @author itzfeltrin
 */
public class chegadaComparator implements Comparator<Voo> {
    
    @Override
    public int compare(Voo voo1, Voo voo2) {        
        if(voo1.getChegadaPrev() == null || voo2.getChegadaPrev() == null)
            return 0;
        ZonedDateTime zdt1 = voo1.getChegadaPrev().atZone(ZoneId.of("America/Sao_Paulo"));
        ZonedDateTime zdt2 = voo2.getChegadaPrev().atZone(ZoneId.of("America/Sao_Paulo"));
        long millis1 = zdt1.toInstant().toEpochMilli();
        long millis2 = zdt2.toInstant().toEpochMilli();
        int resp = (int) (millis1 - millis2);
        return resp;
    }
}
