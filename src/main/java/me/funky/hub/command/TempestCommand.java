package me.funky.hub.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import lombok.SneakyThrows;
import me.funky.hub.Tempest;
import me.funky.hub.utils.CC;
import org.bukkit.entity.Player;

@CommandAlias("tempest")
@Description("Tempest Hub Core main command")
public class TempestCommand extends BaseCommand {

    @Default
    @HelpCommand
    public void onDefault(Player player) {
        if(!player.hasPermission("tempest.admin")) {
            player.sendMessage(CC.CHAT_BAR);
            player.sendMessage(CC.translate("&f&lThis server is using &e&lTempest Hub Core&7&l."));
            player.sendMessage(CC.CHAT_BAR);
        } else {
            player.sendMessage(CC.CHAT_BAR);
            player.sendMessage(CC.translate("&e&lTempest &7&l» &f&lHelp"));
            player.sendMessage(CC.CHAT_BAR);
            player.sendMessage(CC.translate("&r &7&o* &f/tempest reload &7- &8(&7&oReload Tempest Files&8)"));
            player.sendMessage(CC.translate("&r &7&o* &f/tempest setspawn &7- &8(&7&oSets the spawn location&8)"));
            player.sendMessage(CC.translate("&r &7&o* &f/tempest build &7- &8(&7&oToggle buildmode&8)"));
            player.sendMessage(CC.CHAT_BAR);
        }
    }
    @Subcommand("reload")
    @CommandPermission("tempest.admin")
    @Description("Reload Tempest Files")
    @SneakyThrows
    public void onReload(Player player) {
        Tempest.getInstance().reloadConfig();
        Tempest.getInstance().getMessagesYML().reloadConfig();
        player.sendMessage(CC.translate(Tempest.getInstance().getMessagesYML().getConfig().getString("RELOAD")));
    }
    @Subcommand("build|buildmode")
    @CommandPermission("tempest.admin")
    @Description("Toggle buildmode on/off")
    public void toggleBuildmode(Player player) {
        if(Tempest.getInstance().getBuildManager().getBuilders().contains(player)) {
            Tempest.getInstance().getBuildManager().disableBuildmode(player);
        } else if(!Tempest.getInstance().getBuildManager().getBuilders().contains(player)) {
            Tempest.getInstance().getBuildManager().enableBuildmode(player);
        }
    }
    @Subcommand("setspawn|setlobby")
    @CommandPermission("tempest.admin")
    @Description("Set the lobby location")
    public void setSpawn(Player player) {
        Tempest.getInstance().getConfig().set("Spawn.X", player.getLocation().getX());
        Tempest.getInstance().getConfig().set("Spawn.Y", player.getLocation().getY());
        Tempest.getInstance().getConfig().set("Spawn.Z", player.getLocation().getZ());
        Tempest.getInstance().getConfig().set("Spawn.Yaw", player.getLocation().getYaw());
        Tempest.getInstance().getConfig().set("Spawn.Pitch", player.getLocation().getPitch());
        Tempest.getInstance().saveConfig();
        player.sendMessage(CC.translate(Tempest.getInstance().getMessagesYML().getConfig().getString("SETSPAWN")));
    }
}
