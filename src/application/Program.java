/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import model.entities.Aeroporto;
import model.entities.Voo;
import telas.TelaPrincipal;

/**
 *
 * @author Administrador
 */
public class Program {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        BufferedReader br = null;
        try {
            String dadAbril = "csv/VRA_Abril_2019.csv";        
            String dadAero = "csv/DadosAeroportos.csv";            
            String line = "";
            String cvsSplitBy = ";";
            ArrayList<Aeroporto> listaAero = new ArrayList<>();
            ArrayList<Voo> listaVoo = new ArrayList<>();
            // ler e criar os aeroportos a partid do csv
            Scanner sc = new Scanner(System.in);
            br = new BufferedReader(new FileReader(dadAero));
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] aeroporto = line.split(cvsSplitBy);
                Aeroporto aero = new Aeroporto(aeroporto);
                listaAero.add(aero);
            }

            //ler e criar os voos a partir do csv
            int i = 0;
            br = new BufferedReader(new FileReader(dadAbril));            
            while ((line = br.readLine()) != null) {
                // use comma as separator
                //System.out.print(++i + " ");
                String[] voo = line.split(cvsSplitBy);                
                Voo aux = new Voo(voo);                  
                listaVoo.add(aux);                
            }
            
            Collections.sort(listaVoo);
            
            TelaPrincipal tp = new TelaPrincipal(listaAero, listaVoo);
            tp.setVisible(true);  

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e){
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro! " + e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public static String printLabel(){
        return "Partida Prevista - Origem - Destino -  Cia  -  Voo  - Portão - Partida Estimada - Situação";
    }
}
