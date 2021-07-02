package us.peaky.counterpack.impl;

import me.theseems.tomshelby.storage.TomMeta;
import us.peaky.counterpack.api.ChatCounter;

public class MetaChatCounter implements ChatCounter {
    private static final String COUNTER_META_KEY = "counterValue";

    private final Long chatId;
    private final TomMeta counterMeta;

    public MetaChatCounter(Long chatId, TomMeta counterMeta) {
        this.chatId = chatId;
        this.counterMeta = counterMeta;
    }

    @Override
    public synchronized Long getValue() {
        return counterMeta.getLong(COUNTER_META_KEY).orElse(0L);
    }

    @Override
    public synchronized Long getChatId() {
        return chatId;
    }

    @Override
    public synchronized void setValue(long value) {
        counterMeta.set(COUNTER_META_KEY, value);
    }
}
