package org.example;

import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;

public class PrihvatiVezuRukovateljDogadjajem implements RukovateljDogadjajem {
    public void handleEvent(SelectionKey handle) throws Exception {
        System.out.println("PrihvatiVezuRukovateljDogadjajem->handleEvent");

        var kanal = (ServerSocketChannel) handle.channel();
        var veza = kanal.accept();

        if (veza != null) {
            Reaktor.getInstance().registrirajRukovatelja(veza, new KonkretniRukovateljDogadjajem());
        }
    }
}

