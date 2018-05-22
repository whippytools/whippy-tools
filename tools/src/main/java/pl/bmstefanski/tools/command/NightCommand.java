package pl.bmstefanski.tools.command;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.bmstefanski.commands.CommandArguments;
import pl.bmstefanski.commands.CommandExecutor;
import pl.bmstefanski.commands.Messageable;
import pl.bmstefanski.commands.annotation.Command;
import pl.bmstefanski.commands.annotation.GameOnly;
import pl.bmstefanski.commands.annotation.Permission;
import pl.bmstefanski.tools.Tools;
import pl.bmstefanski.tools.storage.configuration.Messages;

import javax.inject.Inject;

public class NightCommand implements Messageable, CommandExecutor {

  @Inject private Messages messages;

  @Command(name = "night", usage = "[world]", max = 1)
  @Permission("tools.command.night")
  @GameOnly(false)
  @Override
  public void execute(CommandSender commandSender, CommandArguments commandArguments) {

    if (commandArguments.getSize() == 0) {

      if (!(commandSender instanceof Player)) {
        sendMessage(commandSender, messages.getOnlyPlayer());
        return;
      }

      Player player = (Player) commandSender;
      player.getWorld().setTime(12566);

      sendMessage(player, StringUtils.replace(messages.getNight(), "%world%", player.getWorld().getName()));

      return;
    }

    if (Bukkit.getWorld(commandArguments.getParam(0)) == null) {
      sendMessage(commandSender, StringUtils.replace(messages.getWorldNotFound(), "%world%", commandArguments.getParam(0)));
      return;
    }

    World world = Bukkit.getWorld(commandArguments.getParam(0));
    world.setTime(12566);

    sendMessage(commandSender, StringUtils.replace(messages.getNight(), "%world%", world.getName()));
  }

}
