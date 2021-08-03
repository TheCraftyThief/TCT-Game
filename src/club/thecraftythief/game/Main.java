package club.thecraftythief.game;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Logger;

public class Main extends JavaPlugin implements Listener {
    HashMap<UUID, ArmorStand> armorStands = new HashMap<>();

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

    public ArmorStand getArmorStand(UUID uuid) {
        return armorStands.get(uuid);
    }

    public boolean armorStandExist(UUID uuid) {
        return armorStands.get(uuid) != null;
    }

    public boolean armorStandDead(UUID uuid) {
        if(armorStandExist(uuid)) {
            return getArmorStand(uuid).isDead();
        }
        return true;
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        UUID uuid = e.getPlayer().getUniqueId();
        if(armorStandExist(uuid) && !armorStandDead(uuid)) {
            getLogger().info("Move!");

            Location newLoc = e.getTo().clone();
            Vector newLocVec = newLoc.getDirection().multiply(2);
            getArmorStand(uuid).teleport(newLoc.add(newLocVec));
            getLogger().info("Set stand spot!");
            getLogger().info("Placed at: " + newLoc);
        } else {
            ArmorStand stand = (ArmorStand) e.getPlayer().getWorld().spawnEntity(e.getTo(), EntityType.ARMOR_STAND);
            stand.setGravity(false);
            armorStands.remove(uuid);
            armorStands.put(uuid, stand);
            getLogger().info("Armor stand made!");
        }
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        UUID uuid = e.getPlayer().getUniqueId();
        if(armorStandExist(uuid)) {
            getArmorStand(uuid).remove();
            armorStands.remove(uuid);
        }
    }
}
