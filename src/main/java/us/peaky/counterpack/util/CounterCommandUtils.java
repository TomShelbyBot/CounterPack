package us.peaky.counterpack.util;

import me.theseems.tomshelby.ThomasBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import us.peaky.counterpack.api.CounterPackApi;
import us.peaky.counterpack.api.NamedChatCounter;

import java.util.Optional;
import java.util.function.Consumer;

public class CounterCommandUtils {

  public static void consumeCounterDefault(
      ThomasBot thomasBot, String[] strings, Update update, Consumer<NamedChatCounter> consumer) {
    consumeCounter(
        strings,
        update,
        () -> thomasBot.replyBackText(update, "Укажите название счётчика"),
        (name) ->
            thomasBot.replyBackText(update, "Счётчика с именем '" + name + "'  не существует"),
        consumer);
  }

  public static void consumeCounter(
      String[] strings,
      Update update,
      Runnable onNotProvided,
      Consumer<String> onNotFound,
      Consumer<NamedChatCounter> consumer) {
    if (strings.length == 0) {
      onNotProvided.run();
      return;
    }

    String counterName = strings[0];
    Optional<NamedChatCounter> counter =
        CounterPackApi.getChatCounterManager()
            .getCounter(update.getMessage().getChatId(), counterName);

    if (counter.isEmpty()) {
      onNotFound.accept(counterName);
      return;
    }

    consumer.accept(counter.get());
  }
}
