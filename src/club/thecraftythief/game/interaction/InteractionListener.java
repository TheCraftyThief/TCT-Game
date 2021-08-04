package club.thecraftythief.game.interaction;

import club.thecraftythief.engine.data.DataStore;
import club.thecraftythief.engine.model.ModelMgr;
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

import javax.xml.crypto.Data;
import java.util.List;
import java.util.UUID;

public class InteractionListener implements Listener {

    private final static String BEING_CARRIED_KEY = "being_carried";

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        List<ArmorStand> modelStands = ModelMgr.getInstance().getSpawnedModels(player.getWorld());
        ArmorStand carryingStand = null;
        //Using this to iterate to avoid making copies
        for(int i = 0; i < modelStands.size(); i++) {
            ArmorStand current = modelStands.get(i);
            String uuidStr = DataStore.read(current, BEING_CARRIED_KEY);
            if(uuidStr == null) {
                continue;
            }
            UUID carrierUUID = UUID.fromString(uuidStr);
            if(carrierUUID.equals(player.getUniqueId())) {
                Location newLoc = event.getTo().clone();
                Vector newLocVec = newLoc.getDirection().multiply(2);
                current.teleport(newLoc.add(newLocVec));
            }
        }
    }

    @EventHandler
    public void onModelClick(ModelInteractEvent event) {
        Player player = event.getPlayer();
        ArmorStand stand = event.getEntity();
        String carrierID = DataStore.read(stand, BEING_CARRIED_KEY);
        if(carrierID != null) {
            UUID carrierUUID = UUID.fromString(carrierID);
            if(carrierUUID.equals(player.getUniqueId())) {
                DataStore.clear(stand, BEING_CARRIED_KEY);
            }
        }
        else {
            List<ArmorStand> modelStands = ModelMgr.getInstance().getSpawnedModels(player.getWorld());
            for(int i = 0; i < modelStands.size(); i++) {
                ArmorStand current = modelStands.get(i);
                if(!current.getUniqueId().equals(stand.getUniqueId())) {
                    String currentCarrierID = DataStore.read(current, BEING_CARRIED_KEY);
                    if(currentCarrierID != null) {
                        UUID carrierUUID = UUID.fromString(currentCarrierID);
                        if(player.getUniqueId().equals(carrierUUID)) {
                            return;
                        }
                    }
                }
            }
            DataStore.store(stand, BEING_CARRIED_KEY, player.getUniqueId().toString());
        }
    }
}