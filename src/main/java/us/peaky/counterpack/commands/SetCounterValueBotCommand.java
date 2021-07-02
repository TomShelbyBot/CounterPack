package us.peaky.counterpack.commands;

import me.theseems.tomshelby.ThomasBot;
import me.theseems.tomshelby.command.SimpleBotCommand;
import me.theseems.tomshelby.command.SimpleCommandMeta;
import org.telegram.telegrambots.meta.api.objects.Update;
import us.peaky.counterpack.util.CounterCommandUtils;
import us.peaky.counterpack.util.CreatorPermissibleBotCommand;

public class SetCounterValueBotCommand extends SimpleBotCommand
    implements CreatorPermissibleBotCommand {

  public SetCounterValueBotCommand() {
    super(SimpleCommandMeta.onLabel("setcnt").description("Установить значение счётчика"));
  }

  @Override
  public void handle(ThomasBot thomasBot, String[] strings, Update update) {
    if (strings.length < 2) {
      thomasBot.replyBackText(update, "Укажите название счётчика и его значение");
      return;
    }

    long value;
    try {
      value = Long.parseLong(strings[1]);
    } catch (NumberFormatException e) {
      thomasBot.replyBackText(update, "Укажите, пожалуйста, валидное значение для счётчика");
      return;
    }

    CounterCommandUtils.consumeCounterDefault(
        thomasBot,
        strings,
        update,
        counter -> {
          counter.setValue(value);
          thomasBot.replyBackText(update, "#" + counter.getName() + " x" + counter.getValue());
        });
  }
}
