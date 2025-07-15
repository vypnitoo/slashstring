package com.vypnito.slashstring.listeners;

import com.vypnito.slashstring.SlashString;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemSpawnEvent;

public class ItemDropListener implements Listener {

	private final SlashString plugin;

	public ItemDropListener(SlashString plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onItemSpawn(ItemSpawnEvent event) {
		Item item = event.getEntity();
		if (item.getItemStack().getType() == Material.STRING) {
			event.setCancelled(true);
			plugin.getLogger().info(plugin.getPluginConfig().getMessageItemDeleted());
		}
	}
}