package club.thecraftythief.game.interaction;

import club.thecraftythief.engine.data.DataStore;
import club.thecraftythief.engine.model.events.ModelInteractEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

import java.util.UUID;

public class InteractionListener implements Listener {

    //TODO: Check if a player is already carrying the item! This is kinda critical :)

    private final static String CARRY_KEY = "carrying_stand";

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        String standID = DataStore.read(player, CARRY_KEY);
        if (standID != null) {
            UUID standUUID = UUID.fromString(standID);
            Entity ent = Bukkit.getEntity(standUUID);
            if (ent instanceof ArmorStand) {
                Location newLoc = event.getTo().clone();
                Vector newLocVec = newLoc.getDirection().multiply(2);
                ent.teleport(newLoc.add(newLocVec));
            }
        }
    }

    @EventHandler
    public void onModelClick(ModelInteractEvent event) {
        Player player = event.getPlayer();
        ArmorStand stand = event.getEntity();
        String standID = DataStore.read(player, CARRY_KEY);
        if (standID == null) {
            DataStore.store(player, CARRY_KEY, stand.getUniqueId().toString());
        } else {
            DataStore.clear(player, CARRY_KEY);
        }
    }
}
