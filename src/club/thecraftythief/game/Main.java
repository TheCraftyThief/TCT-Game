package club.thecraftythief.game;

import club.thecraftythief.game.models.*;
import club.thecraftythief.engine.model.ModelMgr;

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

        ModelMgr.getInstance().registerModel(new ComputerTowerModel());
        ModelMgr.getInstance().registerModel(new GoldBarModel());
        ModelMgr.getInstance().registerModel(new PhoneModel());
        ModelMgr.getInstance().registerModel(new RubiksCubeModel());
        ModelMgr.getInstance().registerModel(new CashModel());
        ModelMgr.getInstance().registerModel(new LaptopModel());
        ModelMgr.getInstance().registerModel(new TVModel());
        ModelMgr.getInstance().registerModel(new KeyModel());

        log.info("TCT-Game enabled");
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
}
