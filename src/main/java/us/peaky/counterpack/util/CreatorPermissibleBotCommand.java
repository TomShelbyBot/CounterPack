package us.peaky.counterpack.util;

import me.theseems.tomshelby.Main;
import me.theseems.tomshelby.command.PermissibleBotCommand;
import org.telegram.telegrambots.meta.api.methods.groupadministration.GetChatAdministrators;
import org.telegram.telegrambots.meta.api.objects.ChatMember;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public interface CreatorPermissibleBotCommand extends PermissibleBotCommand {
  @Override
  default boolean canUse(Long chatId, Integer userId) {
    try {
      for (ChatMember chatMember :
          Main.getBot().execute(new GetChatAdministrators().setChatId(chatId))) {
        if (chatMember.getUser().getId().equals(userId) && chatMember.getStatus().equals("creator"))
          return true;
      }
    } catch (TelegramApiException e) {
      e.printStackTrace();
    }
    return false;
  }
}
