package us.peaky.counterpack.commands;

import me.theseems.tomshelby.ThomasBot;
import me.theseems.tomshelby.command.AdminPermissibleBotCommand;
import me.theseems.tomshelby.command.SimpleBotCommand;
import me.theseems.tomshelby.command.SimpleCommandMeta;
import org.telegram.telegrambots.meta.api.objects.Update;
import us.peaky.counterpack.api.CounterPackApi;
import us.peaky.counterpack.api.NamedChatCounter;
import us.peaky.counterpack.util.EmojiUtils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TopCounterBotCommand extends SimpleBotCommand implements AdminPermissibleBotCommand {
  public TopCounterBotCommand() {
    super(SimpleCommandMeta.onLabel("topcnt").description("Выдать топ счётчиков"));
  }

  private String makeEntry(int index, NamedChatCounter counter) {
    return EmojiUtils.getPlaceSymbol(index + 1) // place is 1 indexed whilst index is 0 indexed
        + "\t\t "
        + counter.getName()
        + "\t\t x"
        + counter.getValue();
  }

  @Override
  public void handle(ThomasBot bot, String[] args, Update update) {
    Stream<NamedChatCounter> counterStream =
        CounterPackApi.getChatCounterManager().getChatCounters(update.getMessage().getChatId());

    List<NamedChatCounter> top =
        counterStream
            .sorted((o1, o2) -> -o1.getValue().compareTo(o2.getValue()))
            .limit(15) // obtain top 15
            .collect(Collectors.toList());

    StringBuilder builder = new StringBuilder();
    IntStream.range(0, top.size())
        .mapToObj(index -> makeEntry(index, top.get(index)))
        .forEachOrdered(s -> builder.append(s).append('\n'));

    bot.replyBackText(update, builder.toString());
  }
}
