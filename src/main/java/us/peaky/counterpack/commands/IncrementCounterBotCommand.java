package us.peaky.counterpack.commands;

import me.theseems.tomshelby.ThomasBot;
import me.theseems.tomshelby.command.AdminPermissibleBotCommand;
import me.theseems.tomshelby.command.SimpleBotCommand;
import me.theseems.tomshelby.command.SimpleCommandMeta;
import org.telegram.telegrambots.meta.api.objects.Update;
import us.peaky.counterpack.util.CounterCommandUtils;

public class IncrementCounterBotCommand extends SimpleBotCommand
    implements AdminPermissibleBotCommand {

  public IncrementCounterBotCommand() {
    super(SimpleCommandMeta.onLabel("inc").description("Увеличить значение счётчика"));
  }

  @Override
  public void handle(ThomasBot thomasBot, String[] strings, Update update) {
    CounterCommandUtils.consumeCounterDefault(
        thomasBot,
        strings,
        update,
        counter -> {
          counter.increment();
          thomasBot.replyBackText(update, "#" + counter.getName() + " x" + counter.getValue());
        });
  }
}
