package me.funky.hub;

import co.aikar.commands.PaperCommandManager;
import io.github.thatkawaiisam.assemble.Assemble;
import io.github.thatkawaiisam.assemble.AssembleStyle;
import lombok.Getter;
import lombok.SneakyThrows;
import me.funky.hub.command.TempestCommand;
import me.funky.hub.listener.*;
import me.funky.hub.manager.BuildManager;
import me.funky.hub.manager.LobbyManager;
import me.funky.hub.provider.BoardProvider;
import me.funky.hub.utils.CC;
import me.funky.hub.utils.YamlDoc;
import me.funky.hub.utils.bungee.BungeeUtils;
import me.funky.hub.utils.menu.ButtonListener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.IOException;
import java.util.Arrays;

@Getter
public class Tempest extends JavaPlugin {

    @Getter
    private static Tempest instance;
    private PaperCommandManager paperCommandManager;
    private BuildManager buildManager;
    private LobbyManager lobbyManager;
    private YamlDoc messagesYML;
    private YamlDoc scoreboardYML;
    private YamlDoc tablistYML;
    private YamlDoc selectorYML;
    private YamlDoc subselectorYML;
    private YamlDoc hotbarYML;

    @SneakyThrows
    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        getConfigs();
        getManagers();
        getListeners();
        getCommands();
        getScoreboard();
        getBungee();

        CC.pluginEnabled();

    }

    @Override
    public void onDisable() {
        for(Player player : Bukkit.getOnlinePlayers()) {
            player.kickPlayer(CC.translate(getMessagesYML().getConfig().getString("DISABLE")));
        }
        instance = null;
    }

    public void getManagers() {
        this.paperCommandManager = new PaperCommandManager(this);
        this.buildManager = new BuildManager();
        this.lobbyManager = new LobbyManager();
    }
    public void getCommands() {
        Arrays.asList(
                new TempestCommand()
        ).forEach(command -> paperCommandManager.registerCommand(command));
    }
    public void getConfigs() throws IOException {

        messagesYML = new YamlDoc(getDataFolder(), "messages.yml");
        messagesYML.init();
        scoreboardYML = new YamlDoc(getDataFolder(), "scoreboard.yml");
        scoreboardYML.init();
        tablistYML = new YamlDoc(getDataFolder(), "tablist.yml");
        tablistYML.init();
        selectorYML = new YamlDoc(getDataFolder(), "server-selector.yml");
        selectorYML.init();
        subselectorYML = new YamlDoc(getDataFolder(), "sub-selector.yml");
        subselectorYML.init();
        hotbarYML = new YamlDoc(getDataFolder(), "hotbar.yml");
        hotbarYML.init();

    }
    public void getListeners() {
        Arrays.asList(
                new WorldListener(),
                new JoinListener(),
                new PlayerListener(),
                new ButtonListener(),
                new CommandListener(),
                new ItemListener()
        ).forEach(listener -> getServer().getPluginManager().registerEvents(listener, this));
    }
    public void getScoreboard() {
        if(getConfig().getBoolean("PROVIDERS.SCOREBOARD")) {
            Assemble assemble = new Assemble(this, new BoardProvider());
            assemble.setTicks(2);
            assemble.setAssembleStyle(AssembleStyle.MODERN);
            BoardProvider.create();
            Bukkit.getConsoleSender().sendMessage(CC.translate("&e[Tempest] &aScoreboard is enabled"));
        } else {
            Bukkit.getConsoleSender().sendMessage(CC.translate("&e[Tempest] &cScoreboard is disabled"));
        }
    }
    public void getBungee() {
        new BukkitRunnable() {
            @Override
            public void run() {
                BungeeUtils.refreshGlobalCount();
                BungeeUtils.refreshServerList();
                BungeeUtils.refreshServerCount();
            }
        }.runTaskTimerAsynchronously(this, 10L, 10L);
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", new BungeeUtils());
    }
}
