package club.thecraftythief.game;

import club.thecraftythief.engine.model.ModelMgr;
import club.thecraftythief.game.models.*;
import org.bukkit.entity.ArmorStand;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

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
