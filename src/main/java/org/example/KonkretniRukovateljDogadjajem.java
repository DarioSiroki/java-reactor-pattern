package org.example;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

public class KonkretniRukovateljDogadjajem implements RukovateljDogadjajem {
    private final ByteBuffer ulazniBuffer = ByteBuffer.allocate(1024);

    public KonkretniRukovateljDogadjajem() {
    }

    public void handleEvent(SelectionKey handle) throws Exception {
        System.out.println("KonkretniRukovateljDogadjajem->handleEvent");
        var kanal = (SocketChannel) handle.channel();
        kanal.read(ulazniBuffer);
        ulazniBuffer.flip();
        byte[] buffer = new byte[ulazniBuffer.limit()];
        ulazniBuffer.get(buffer);
        System.out.printf("Klijent poslao poruku: %s\n", new String(buffer));
        kanal.close();
    }
}
