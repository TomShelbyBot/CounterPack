package us.peaky.counterpack;

import me.theseems.tomshelby.command.BotCommand;
import me.theseems.tomshelby.pack.JavaBotPackage;
import me.theseems.tomshelby.update.UpdateHandler;
import us.peaky.counterpack.api.CounterPackApi;
import us.peaky.counterpack.commands.*;
import us.peaky.counterpack.handlers.HashtagCounterHandler;
import us.peaky.counterpack.impl.MetaChatCounterManager;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class CounterBotPackage extends JavaBotPackage {
  private List<BotCommand> commandList = new LinkedList<>();
  private List<UpdateHandler> handlerList = new LinkedList<>();

  @Override
  public void onEnable() {
    CounterPackApi.setChatCounterManager(new MetaChatCounterManager(getBot()));
    handlerList = Collections.singletonList(new HashtagCounterHandler());
    commandList =
        Arrays.asList(
            new DecrementCounterBotCommand(),
            new DeleteCounterBotCommand(),
            new IncrementCounterBotCommand(),
            new SetCounterValueBotCommand(),
            new TopCounterBotCommand());

    for (BotCommand botCommand : commandList) bot.getCommandContainer().attach(botCommand);

    for (UpdateHandler updateHandler : handlerList)
      bot.getUpdateHandlerManager().addUpdateHandler(updateHandler);
  }

  @Override
  public void onDisable() {
    for (BotCommand botCommand : commandList)
      bot.getCommandContainer().detach(botCommand.getMeta().getLabel());

    for (UpdateHandler updateHandler : handlerList)
      bot.getUpdateHandlerManager().removeUpdateHandler(updateHandler);
  }
}
