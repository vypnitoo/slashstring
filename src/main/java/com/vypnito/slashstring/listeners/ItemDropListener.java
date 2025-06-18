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
		// Check if the item being spawned (dropped on the ground) is a string.
		if (item.getItemStack().getType() == Material.STRING) {
			// Cancel the event. This prevents the string item from actually appearing in the world.
			event.setCancelled(true);
			// Log a message to the server console indicating that a string item was deleted.
			// We access the plugin's configuration via the getPluginConfig() method (which we added to SlashString.java).
			plugin.getLogger().info(plugin.getPluginConfig().getMessageItemDeleted());
			// No further action is needed to "delete" the item, as cancelling the event prevents its creation entirely.
		}
	}
}