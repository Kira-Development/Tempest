package me.funky.hub.listener;

import me.funky.hub.Tempest;
import me.funky.hub.utils.CC;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class WorldListener implements Listener {

    @EventHandler
    public void onWeather(WeatherChangeEvent event) {
        if(!Tempest.getInstance().getConfig().getBoolean("WORLD.WEATHER")) {
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if(Tempest.getInstance().getBuildManager().getBuilders().contains(player)) {
            return;
        }
        if(!Tempest.getInstance().getConfig().getBoolean("WORLD.BLOCKS.PLACE")) {
            event.setCancelled(true);
            if(Tempest.getInstance().getMessagesYML().getConfig().getBoolean("BLOCKS.PLACE.ENABLED")) {
                player.sendMessage(CC.translate(Tempest.getInstance().getMessagesYML().getConfig().getString("BLOCKS.PLACE.MESSAGE")));
            }
        }
    }
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if(Tempest.getInstance().getBuildManager().getBuilders().contains(player)) {
            return;
        }
        if(!Tempest.getInstance().getConfig().getBoolean("WORLD.BLOCKS.BREAK")) {
            event.setCancelled(true);
            if(Tempest.getInstance().getMessagesYML().getConfig().getBoolean("BLOCKS.BREAK.ENABLED")) {
                player.sendMessage(CC.translate(Tempest.getInstance().getMessagesYML().getConfig().getString("BLOCKS.BREAK.MESSAGE")));
            }
        }
    }
    @EventHandler
    public void onCreatureSpawn(EntitySpawnEvent event) {
        if(!Tempest.getInstance().getConfig().getBoolean("WORLD.MOBS")) {
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onBlockInFire(BlockBurnEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onExplode(ExplosionPrimeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onExplode(EntityExplodeEvent event) {
        event.setCancelled(true);
    }
    @EventHandler
    public void bucketFill(PlayerBucketFillEvent event) {
        Player player = event.getPlayer();
        if(Tempest.getInstance().getBuildManager().getBuilders().contains(player)) {
            return;
        }
        event.setCancelled(true);
    }

    @EventHandler
    public void bucketEmpty(PlayerBucketEmptyEvent event) {
        Player player = event.getPlayer();
        if(Tempest.getInstance().getBuildManager().getBuilders().contains(player)) {
            return;
        }
        event.setCancelled(true);
    }
    @EventHandler
    public void onFeed(FoodLevelChangeEvent event) {
        event.setCancelled(true);
    }
}
