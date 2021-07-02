package us.peaky.counterpack.handlers;

import me.theseems.tomshelby.ThomasBot;
import me.theseems.tomshelby.update.UpdateHandler;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.Update;
import us.peaky.counterpack.api.CounterPackApi;
import us.peaky.counterpack.api.NamedChatCounter;

public class HashtagCounterHandler implements UpdateHandler {
  @Override
  public boolean handleUpdate(ThomasBot thomasBot, Update update) {
    if (!update.hasMessage() || !update.getMessage().hasText()) return true;
    if (update.getMessage().getEntities() == null) return true;

    for (MessageEntity entity : update.getMessage().getEntities()) {
      if (!entity.getType().equals("hashtag")) break;

      NamedChatCounter hashtagCounter =
          CounterPackApi.getChatCounterManager()
              .getOrCreateCounter(update.getMessage().getChatId(), entity.getText().substring(1));

      hashtagCounter.increment();
      thomasBot.sendBack(
          update,
          new SendMessage()
              .setText("#" + hashtagCounter.getName() + " x" + hashtagCounter.getValue()));

      return true;
    }

    return true;
  }

  @Override
  public int getPriority() {
    return 0;
  }
}
