package org.example;

import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.net.InetSocketAddress;

public class Main {

    public static void main(String[] args) throws Exception {

        ServerSocketChannel server = ServerSocketChannel.open();
        server.socket().bind(new InetSocketAddress(8043));
        server.configureBlocking(false);

        var demux = Selector.open();
        server.register(demux, SelectionKey.OP_ACCEPT);

        Reaktor reaktor = Reaktor.getInstance();
        reaktor.init(demux, new PrihvatiVezuRukovateljDogadjajem());

        reaktor.handle_events();
    }
}
