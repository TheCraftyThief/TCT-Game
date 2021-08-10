package club.thecraftythief.game;

import club.thecraftythief.engine.Engine;
import club.thecraftythief.engine.model.ModelData;
import club.thecraftythief.engine.model.ModelMgr;
import club.thecraftythief.engine.model.events.ModelInteractEvent;
import club.thecraftythief.game.interaction.InteractionListener;
import club.thecraftythief.game.models.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
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

        Engine.getInstance().getModelManager().registerModel(new ComputerTowerModel());
        Engine.getInstance().getModelManager().registerModel(new GoldBarModel());
        Engine.getInstance().getModelManager().registerModel(new PhoneModel());
        Engine.getInstance().getModelManager().registerModel(new RubiksCubeModel());
        Engine.getInstance().getModelManager().registerModel(new CashModel());
        Engine.getInstance().getModelManager().registerModel(new LaptopModel());
        Engine.getInstance().getModelManager().registerModel(new TVModel());
        Engine.getInstance().getModelManager().registerModel(new KeyModel());
        Engine.getInstance().getModelManager().registerModel(new AmongUsModel());
        Engine.getInstance().getModelManager().registerModel(new CreditCardModel());
        Engine.getInstance().getModelManager().registerModel(new PinkCupModel());

        Bukkit.getPluginManager().registerEvents(this, this);
        Bukkit.getPluginManager().registerEvents(new InteractionListener(), this);

        log.info("TCT-Game enabled");
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
}
