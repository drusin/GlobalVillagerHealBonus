package xyz.rusin.globalvillagerhealbonus;

import org.bukkit.plugin.java.JavaPlugin;

public class GlobalVillagerHealBonus extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new VillagerInteractionListener(), this);
    }
}
