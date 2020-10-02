package net.almostmc.FiatMachine;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldSaveEvent;

class WorldSaveEventListener implements Listener {
    @EventHandler
    public void onWorldSave(WorldSaveEvent event) {
        MoneyManager.saveValues();
    }
}
