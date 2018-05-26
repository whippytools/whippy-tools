/*
 MIT License

 Copyright (c) 2018 Whippy ToolsImpl

 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all
 copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 SOFTWARE.
 */

package pl.bmstefanski.tools.command;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import pl.bmstefanski.commands.CommandArguments;
import pl.bmstefanski.commands.CommandExecutor;
import pl.bmstefanski.commands.annotation.Command;
import pl.bmstefanski.commands.annotation.GameOnly;
import pl.bmstefanski.commands.annotation.Permission;
import pl.bmstefanski.tools.impl.type.MessageType;
import pl.bmstefanski.tools.impl.util.message.MessageBundle;
import pl.bmstefanski.tools.manager.UserManager;

import javax.inject.Inject;
import java.util.Arrays;

public class ListCommand implements CommandExecutor {

  @Inject private UserManager userManager;

  @Command(name = "list", usage = "[full/basic]", min = 1, max = 1)
  @Permission("tools.command.list")
  @GameOnly(false)
  @Override
  public void execute(CommandSender commandSender, CommandArguments commandArguments) {

    if (commandArguments.getParam(0).equalsIgnoreCase("basic")) {
      int playersOnlineSize = this.userManager.getOnlinePlayers().size();
      int maxPlayers = Bukkit.getMaxPlayers();

      MessageBundle.create(MessageType.LIST_BASIC)
        .withField("online", playersOnlineSize + "")
        .withField("max", maxPlayers + "")
        .sendTo(commandSender);
    } else if (commandArguments.getParam(0).equalsIgnoreCase("full")) {
      MessageBundle.create(MessageType.LIST_FULL)
        .withField("online", Arrays.toString(this.userManager.getOnlinePlayers().toArray()))
        .sendTo(commandSender);
    }
  }

}
