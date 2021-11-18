package xyz.rusin.globalvillagerhealbonus;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Villager;
import com.destroystokyo.paper.entity.villager.ReputationType;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import java.util.UUID;

public class VillagerInteractionListener implements Listener {
    
    @EventHandler
    public void onVillagerClicked(PlayerInteractEntityEvent event) {
        Entity entity = event.getRightClicked();
        if (entity.getType() != EntityType.VILLAGER) {
            return;
        }
        Villager villager = (Villager)entity;
        if (villager.getProfession() == Villager.Profession.NITWIT || villager.getProfession() == Villager.Profession.NONE) {
            return;
        }
        // we have a villager you can trade with now

        // find out max reputation values
        int majorMaxValue = villager.getReputations().values()
            .stream()
            .map(reputation -> reputation.getReputation(ReputationType.MAJOR_POSITIVE))
            .max(Integer::compare)
            .get();
        
        int minorMaxValue = villager.getReputations().values()
            .stream()
            .map(reputation -> reputation.getReputation(ReputationType.MINOR_POSITIVE))
            .max(Integer::compare)
            .get();
        
        // set max reputation values
        UUID id = event.getPlayer().getUniqueId();
        var reputation = villager.getReputation(id);
        reputation.setReputation(ReputationType.MAJOR_POSITIVE, majorMaxValue);
        reputation.setReputation(ReputationType.MINOR_POSITIVE, minorMaxValue);

        villager.setReputation(id, reputation);
    }
}
