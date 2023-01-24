package org.example;

import java.nio.channels.SelectionKey;

public interface RukovateljDogadjajem {
    void handleEvent(SelectionKey handle) throws Exception;
}
