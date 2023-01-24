package org.example;

import java.io.InputStreamReader;
import java.net.Socket;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;


public class Klijent {
    public static void main(String[] args) throws Exception {
        posaljiPoruku("localhost", 8043, 4000, "Pozdrav!");
    }

    public static void posaljiPoruku(String adresa, int port, int cekanje, String komanda) {
        try (Socket veza = new Socket(adresa, port);
             InputStreamReader isr = new InputStreamReader(veza.getInputStream(), Charset.forName("UTF-8"));
             OutputStreamWriter osw = new OutputStreamWriter(veza.getOutputStream(), Charset.forName("UTF-8"));) {
            veza.setSoTimeout(cekanje);

            osw.write(komanda);
            osw.flush();
            veza.shutdownOutput();
            StringBuilder tekst = new StringBuilder();

            while (true) {
                int i = isr.read();
                if (i == -1) {
                    break;
                }
                tekst.append((char) i);
            }

            veza.shutdownInput();
            veza.close();
            System.out.println(tekst);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
