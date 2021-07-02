package us.peaky.counterpack.impl;

import me.theseems.tomshelby.storage.TomMeta;
import us.peaky.counterpack.api.NamedChatCounter;

public class NamedMetaChatCounter extends MetaChatCounter implements NamedChatCounter {
    private final String name;

    public NamedMetaChatCounter(String name, Long chatId, TomMeta counterMeta) {
        super(chatId, counterMeta);
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
