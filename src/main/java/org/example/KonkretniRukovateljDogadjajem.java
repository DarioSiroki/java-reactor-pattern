package org.example;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class KonkretniRukovateljDogadjajem implements RukovateljDogadjajem {
    private final ByteBuffer inputBuffer = ByteBuffer.allocate(1024);

    public KonkretniRukovateljDogadjajem() {
    }

    public void handleEvent(SelectionKey handle) throws Exception {
        System.out.println("CitajRukovateljDogadjajem->handleEvent");

        var kanal = (SocketChannel) handle.channel();

        kanal.read(inputBuffer);
        inputBuffer.flip();

        byte[] buffer = new byte[inputBuffer.limit()];
        inputBuffer.get(buffer);

        System.out.printf("Klijent poslao poruku: %s\n", new String(buffer));

        var odgovor = ByteBuffer.wrap("Pozdrav i tebi!\n".getBytes());

        kanal.write(odgovor);
        kanal.close();
    }
}
