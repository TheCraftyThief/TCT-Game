package club.thecraftythief.game;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import java.util.logging.Logger;

public class Main extends JavaPlugin implements Listener {
    @Override
    public void onLoad() {
        super.onLoad();
        Logger log = getLogger();
        log.info("Starting TCT-Game load...");
        log.info("Finished loading sequence");
    }

    @Override
    public void onEnable() {
        super.onEnable();
        Logger log = getLogger();
        log.info("Enabling TCT-Game");
        Bukkit.getPluginManager().registerEvents(this, this);
        log.info("TCT-Game enabled");
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    ArmorStand stand;
    @EventHandler
    public void onMove(PlayerMoveEvent e) {

        getLogger().info("Move!");

        if(stand == null) {
            stand = (ArmorStand) e.getPlayer().getWorld().spawnEntity(e.getTo(), EntityType.ARMOR_STAND);
            stand.setGravity(false);
            getLogger().info("Armor stand made!");
        }

        Location newLoc = e.getTo().clone();
        Vector newLocVec = newLoc.getDirection().multiply(2);
        stand.teleport(newLoc.add(newLocVec));
        getLogger().info("Set stand spot!");
        getLogger().info("Placed at: "+newLoc);
    }
}
