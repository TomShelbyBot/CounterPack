package us.peaky.counterpack.impl;

import me.theseems.tomshelby.ThomasBot;
import me.theseems.tomshelby.storage.TomMeta;
import us.peaky.counterpack.api.ChatCounterManager;
import us.peaky.counterpack.api.NamedChatCounter;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

public class MetaChatCounterManager implements ChatCounterManager {
  private static final String COUNTER_CONTAINER_META_KEY = "counters";

  private final ThomasBot thomasBot;

  public MetaChatCounterManager(ThomasBot bot) {
    this.thomasBot = bot;
  }

  @Override
  public Optional<NamedChatCounter> getCounter(Long chatId, String name) {
    Optional<TomMeta> optionalContainer =
        thomasBot.getChatStorage().getChatMeta(chatId).getContainer(COUNTER_CONTAINER_META_KEY);

    if (optionalContainer.isEmpty()) return Optional.empty();
    TomMeta container = optionalContainer.get();

    Optional<TomMeta> namedCounterContainer = container.getContainer(name);
    if (namedCounterContainer.isEmpty()) return Optional.empty();

    return Optional.of(new NamedMetaChatCounter(name, chatId, namedCounterContainer.get()));
  }

  @Override
  public Stream<NamedChatCounter> getChatCounters(Long chatId) {
    Optional<TomMeta> optionalContainer =
        thomasBot.getChatStorage().getChatMeta(chatId).getContainer(COUNTER_CONTAINER_META_KEY);

    if (optionalContainer.isEmpty()) return Stream.empty();

    return optionalContainer.get().getKeys().stream()
        .map(s -> getCounter(chatId, s).orElse(null))
        .filter(Objects::nonNull);
  }

  @Override
  public NamedChatCounter getOrCreateCounter(Long chatId, String name) {
    Optional<NamedChatCounter> current = getCounter(chatId, name);
    return current.orElseGet(
        () ->
            new NamedMetaChatCounter(
                name,
                chatId,
                thomasBot
                    .getChatStorage()
                    .getChatMeta(chatId)
                    .getOrCreateContainer(COUNTER_CONTAINER_META_KEY)
                    .getOrCreateContainer(name)));
  }

  @Override
  public boolean hasCounter(Long chatId, String name) {
    return getCounter(chatId, name).isPresent();
  }

  @Override
  public void deleteCounter(NamedChatCounter counter) {
    thomasBot
        .getChatStorage()
        .getChatMeta(counter.getChatId())
        .getContainer(COUNTER_CONTAINER_META_KEY)
        .ifPresent(tomMeta -> tomMeta.remove(counter.getName()));
  }
}
