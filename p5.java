package com.company;

import java.util.Scanner;
import java.util.Vector;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.Path;
import java.nio.file.Files;
import java.io.IOException;


class WektoryRoznejDlugosciException extends Exception{

    private final String wektorpierwszy;
    private final String wektordrugi;

    public WektoryRoznejDlugosciException(String message, String wektor1, String wektor2){
        super(message);
        wektorpierwszy = wektor1;
        wektordrugi = wektor2;
    }

    public int dlugoscwektorapierwszego(){
        return wektorpierwszy.length();
    }

    public int dlugoscwektoradrugiego(){
        return wektordrugi.length();
    }
}

public class p5{

    static Vector TworzenieWektorow(String Ciag_napis){
        Vector<Integer> W = new Vector<Integer>();
        for(int i=0;i<Ciag_napis.length();i++)
            W.add(Ciag_napis.charAt(i)-'0');
        return W;
    }

    static Vector Dzialania(){
        Vector<Integer> WektorWynikowy = new Vector<Integer>();
        try {
            String w1 = PobieranieWektorow();
            String w2 = PobieranieWektorow();
            if (w1.length() != w2.length())
                throw new WektoryRoznejDlugosciException("Wektory maja rozna dlugoosc", w1, w2);
            Vector<Integer> W1 = TworzenieWektorow(w1);
            Vector<Integer> W2 = TworzenieWektorow(w2);
            WektorWynikowy = DodawanieWektorow(W1, W2);

        } catch (WektoryRoznejDlugosciException e) {
            System.err.println(e.getMessage());
            System.err.println("Dlugosc pierwszego wektora to " + e.dlugoscwektorapierwszego());
            System.err.println("Dlugosc wektora drugiego to " + e.dlugoscwektoradrugiego());
            WektorWynikowy = Dzialania();
        }
        return WektorWynikowy;
    }

    static String PobieranieWektorow(){
        Scanner input = new Scanner(System.in);
        String Wektor = input.nextLine();
        try{
            Integer.parseInt(Wektor);
        } catch (NumberFormatException e){
            System.out.println("Podany wektor nie jest liczba, wprowadz wektor ponownie");
            Wektor=PobieranieWektorow();
        }
        if((Wektor.charAt(0)=='+')||(Wektor.charAt(0)=='-'))
            Wektor=Wektor.substring(1);
        System.out.println(Wektor);
        return Wektor;
    }

    static Vector DodawanieWektorow(Vector <Integer> W1, Vector <Integer> W2){
        Vector<Integer> W = new Vector<Integer>();
        for(int i=0;i<W1.size();i++)
            W.add(W1.elementAt(i) + W2.elementAt(i));
        return W;
    }

    public static void main(String[] args){
        Vector<Integer> Wynik=Dzialania();
        for (int i = 0; i < Wynik.size(); i++)
            System.out.println("Wynik on " + i + ": " + Wynik.get(i));
        try {
            Path file = Paths.get("Vector.txt");
            Files.deleteIfExists(file);
            Files.createFile(file);
            for (int i = 0; i < Wynik.size(); i++ ) {
                String x = String.valueOf(Wynik.elementAt(i));
                Files.write(file, x.getBytes(), StandardOpenOption.APPEND);
                Files.write(file, " ".getBytes(), StandardOpenOption.APPEND);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}