package us.peaky.counterpack.commands;

import me.theseems.tomshelby.ThomasBot;
import me.theseems.tomshelby.command.AdminPermissibleBotCommand;
import me.theseems.tomshelby.command.SimpleBotCommand;
import me.theseems.tomshelby.command.SimpleCommandMeta;
import org.telegram.telegrambots.meta.api.objects.Update;
import us.peaky.counterpack.util.CounterCommandUtils;

public class DecrementCounterBotCommand extends SimpleBotCommand
    implements AdminPermissibleBotCommand {

  public DecrementCounterBotCommand() {
    super(SimpleCommandMeta.onLabel("dec").description("Уменьшить значение счётчика"));
  }

  @Override
  public void handle(ThomasBot thomasBot, String[] strings, Update update) {
    CounterCommandUtils.consumeCounterDefault(
        thomasBot,
        strings,
        update,
        counter -> {
          counter.decrement();
          thomasBot.replyBackText(update, "#" + counter.getName() + " x" + counter.getValue());
        });
  }
}
