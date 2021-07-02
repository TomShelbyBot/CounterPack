package us.peaky.counterpack.api;

import java.util.Optional;
import java.util.stream.Stream;

public interface ChatCounterManager {
    NamedChatCounter getOrCreateCounter(Long chatId, String name);
    Optional<NamedChatCounter> getCounter(Long chatId, String name);
    Stream<NamedChatCounter> getChatCounters(Long chatId);

    boolean hasCounter(Long chatId, String name);
    void deleteCounter(NamedChatCounter counter);
}
