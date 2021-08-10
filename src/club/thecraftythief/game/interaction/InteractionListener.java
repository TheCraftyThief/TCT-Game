package club.thecraftythief.game.interaction;

import club.thecraftythief.engine.data.DataStore;
import club.thecraftythief.engine.entity.Model;
import club.thecraftythief.engine.model.ModelMgr;
import club.thecraftythief.engine.model.events.ModelInteractEvent;
import club.thecraftythief.engine.player.PlayerWrapper;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

import java.util.List;
import java.util.UUID;

public class InteractionListener implements Listener {

    @EventHandler
    public void onScroll(PlayerItemHeldEvent event) {
        PlayerWrapper player = new PlayerWrapper(event.getPlayer());
        Model carrying = player.getCarrying();
        if (carrying == null) {
            return;
        }
        event.setCancelled(true);
        player.getInventory().setHeldItemSlot(4);

        int newSlot = event.getNewSlot();
        int oldSlot = event.getPreviousSlot();
        int difference = newSlot - oldSlot;

        float yaw = carrying.getLocation().getYaw();
        float pitch = carrying.getLocation().getPitch();
        double diffSq = Math.pow(difference, 2);
        yaw += (difference < 0 ? -diffSq : diffSq);
        carrying.setRotation(yaw, pitch);
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        PlayerWrapper player = new PlayerWrapper(event.getPlayer());
        Model carrying = player.getCarrying();
        if (carrying != null) {
            //Dont change rotation
            float yaw = carrying.getLocation().getYaw();
            float pitch = carrying.getLocation().getPitch();
            Location newLoc = event.getTo().clone();
            Vector newLocVec = newLoc.getDirection().multiply(2);
            carrying.teleport(newLoc.add(newLocVec));
            carrying.setRotation(yaw, pitch);
        }
    }

    @EventHandler
    public void onModelClick(ModelInteractEvent event) {
        PlayerWrapper player = event.getPlayer();
        Model stand = event.getEntity();
        if (stand.getCarrier() != null) {
            if(stand.getCarrier().getUniqueId().equals(player.getUniqueId())) {
                stand.setCarrier(null);
            }
        } else {
            if(player.getCarrying() != null) {
                return;
            }
            stand.setCarrier(player);
            player.getInventory().setHeldItemSlot(4);
        }
    }
}
