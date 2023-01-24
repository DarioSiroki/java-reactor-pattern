package org.example;

import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

public class PrihvatiVezuRukovateljDogadjajem implements RukovateljDogadjajem {


    public void handleEvent(SelectionKey handle) throws Exception {
        System.out.println("PrihvatiVezuRukovateljDogadjajem->handleEvent");

        var kanal = (ServerSocketChannel) handle.channel();
        var socketChannel = kanal.accept();

        if (socketChannel != null) {
            socketChannel.configureBlocking(false);
            Reaktor.getInstance().registrirajHandler(socketChannel, new KonkretniRukovateljDogadjajem());
        }
    }
}

