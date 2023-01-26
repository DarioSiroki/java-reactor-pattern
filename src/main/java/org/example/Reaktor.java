package org.example;

import java.io.IOException;
import java.nio.channels.*;

public class Reaktor {
    private Selector demux;
    private RukovateljDogadjajem rukovateljSpajanja;
    private static Reaktor i = null;

    public static Reaktor getInstance() {
        if (i == null)
            i = new Reaktor();
        return i;
    }

    void init(Selector demux, RukovateljDogadjajem rukovateljSpajanja) {
        this.demux = demux;
        this.rukovateljSpajanja = rukovateljSpajanja;
    }

    public void registrirajRukovatelja(SocketChannel socketChannel, RukovateljDogadjajem rukovateljDogadjajem) throws IOException {
        socketChannel.configureBlocking(false);
        socketChannel.register(demux, SelectionKey.OP_READ, rukovateljDogadjajem);
    }

    public void obradjujDogadjaje() throws Exception {
        while (true) {
            demux.select();
            var spremniIterator = demux.selectedKeys().iterator();
            while (spremniIterator.hasNext()) {
                SelectionKey handle = spremniIterator.next();
                if (handle.isReadable()) {
                    RukovateljDogadjajem rukovatelj = (RukovateljDogadjajem) handle.attachment();
                    rukovatelj.handleEvent(handle);
                } else if (handle.isAcceptable()) {
                    rukovateljSpajanja.handleEvent(handle);
                }
                spremniIterator.remove();
            }
        }
    }
}
