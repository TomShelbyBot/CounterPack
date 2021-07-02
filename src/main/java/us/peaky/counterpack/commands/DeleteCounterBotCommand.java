package us.peaky.counterpack.commands;

import me.theseems.tomshelby.ThomasBot;
import me.theseems.tomshelby.command.SimpleBotCommand;
import me.theseems.tomshelby.command.SimpleCommandMeta;
import org.telegram.telegrambots.meta.api.objects.Update;
import us.peaky.counterpack.api.CounterPackApi;
import us.peaky.counterpack.util.CounterCommandUtils;
import us.peaky.counterpack.util.CreatorPermissibleBotCommand;

public class DeleteCounterBotCommand extends SimpleBotCommand
    implements CreatorPermissibleBotCommand {

  public DeleteCounterBotCommand() {
    super(SimpleCommandMeta.onLabel("delcnt").description("Удалить счётчик"));
  }

  @Override
  public void handle(ThomasBot thomasBot, String[] strings, Update update) {
    CounterCommandUtils.consumeCounterDefault(
        thomasBot,
        strings,
        update,
        counter -> {
          CounterPackApi.getChatCounterManager().deleteCounter(counter);
          thomasBot.replyBackText(
              update, "Счётчик с именем '" + counter.getName() + "' был успешно удалён.");
        });
  }
}
