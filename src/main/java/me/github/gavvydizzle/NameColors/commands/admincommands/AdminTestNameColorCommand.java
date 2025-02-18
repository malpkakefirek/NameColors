package me.github.gavvydizzle.NameColors.commands.admincommands;

import com.github.mittenmc.serverutils.SubCommand;
import me.github.gavvydizzle.NameColors.Main;
import me.github.gavvydizzle.NameColors.colors.NameColor;
import me.github.gavvydizzle.NameColors.commands.AdminCommandManager;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class AdminTestNameColorCommand extends SubCommand {

    public AdminTestNameColorCommand(AdminCommandManager commandManager) {
        setName("testNameColor");
        setDescription("Test what the name color formatting will do to text");
        setSyntax("/" + commandManager.getCommandDisplayName() + " testNameColor <id> <message>");
        setColoredSyntax(ChatColor.YELLOW + getSyntax());
        setPermission(commandManager.getPermissionPrefix() + getName().toLowerCase());
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        if (args.length < 3) {
            sender.sendMessage(getColoredSyntax());
            return;
        }

        if (args.length > 3) {
            sender.sendMessage(ChatColor.RED + "No spaces are allowed in the message portion");
            return;
        }

        NameColor nameColor = Main.getInstance().getColorManager().getNameColorByID(args[1]);
        if (nameColor == null) {
            sender.sendMessage(ChatColor.RED + "'" + args[1] + "' is not a valid name color!");
            return;
        }

        sender.sendMessage("Output: " + nameColor.getPattern().withPattern(args[2]));
    }

    @Override
    public List<String> getSubcommandArguments(CommandSender sender, String[] args) {
        ArrayList<String> list = new ArrayList<>();

        if (args.length == 2) {
            StringUtil.copyPartialMatches(args[1], Main.getInstance().getColorManager().getNameColorIDs(), list);
        }

        return list;
    }

}