package com.test.nio.demo;

import java.io.IOException;
import java.nio.channels.SelectionKey;

/**
 * @author zhouj
 * @since 2020-04-23
 */
public interface ServerHandlerBs {

    void handleAccept(SelectionKey selectionKey) throws IOException;

    String handleRead(SelectionKey selectionKey) throws IOException;
}
