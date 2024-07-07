package me.funky.hub.provider;

import io.github.thatkawaiisam.assemble.AssembleAdapter;
import me.clip.placeholderapi.PlaceholderAPI;
import me.funky.hub.Tempest;
import me.funky.hub.utils.CC;
import me.funky.hub.utils.TaskUtil;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class BoardProvider implements AssembleAdapter {

    public static String title;

    @Override
    public String getTitle(Player player) {
        return CC.translate(getScoreboardTitle());
    }

    @Override
    public List<String> getLines(Player player) {
        List<String> lines = new ArrayList<>();
        for(String DEFAULT : Tempest.getInstance().getScoreboardYML().getConfig().getStringList("SCOREBOARD.LINES.DEFAULT")) {
            lines.add(CC.translate(DEFAULT));
        }
        if(Tempest.getInstance().getServer().getPluginManager().getPlugin("PlaceholderAPI") == null) {
            return CC.translate(lines);
        } else {
            return CC.translate(PlaceholderAPI.setPlaceholders(player, lines));
        }
    }
    public static void create() {
        List<String> titles = Tempest.getInstance().getScoreboardYML().getConfig().getStringList("SCOREBOARD.TITLE");
        final int[] p = {0};
        TaskUtil.runTimer(() -> {
            if (p[0] == titles.size()) p[0] = 0;
            title = titles.get(p[0]++);
        }, 0L, (long) (Tempest.getInstance().getMessagesYML().getConfig().getDouble("SCOREBOARD.UPDATE-DELAY") * 20L));
    }

    public static String getScoreboardTitle() {
        return title;
    }
}
