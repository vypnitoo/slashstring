package com.vypnito.slashstring.commands;

import com.vypnito.slashstring.SlashString;
import com.vypnito.slashstring.config.PluginConfig;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class StringCommand implements CommandExecutor {

	private final SlashString plugin;
	private final PluginConfig config;
	private final Map<UUID, Long> cooldowns = new HashMap<>();

	public StringCommand(SlashString plugin, PluginConfig config) {
		this.plugin = plugin;
		this.config = config;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Only players can use this command.");
			return true;
		}

		Player player = (Player) sender;
		UUID playerUUID = player.getUniqueId();
		long currentTime = System.currentTimeMillis();
		long cooldownTime = config.getCooldownSeconds() * 1000L;

		// Cooldown check
		if (cooldowns.containsKey(playerUUID)) {
			long lastUsed = cooldowns.get(playerUUID);
			long timeLeft = (lastUsed + cooldownTime) - currentTime;

			if (timeLeft > 0) {
				String message = config.getMessageCooldown()
						.replace("%seconds%", String.format("%.1f", timeLeft / 1000.0));
				player.sendMessage(ChatColor.RED + message);
				return true;
			}
		}

		PlayerInventory inventory = player.getInventory();
		boolean filledAnySlot = false;

		// Iterate through the main inventory slots (0-35).
		// Slots 0-8 are hotbar, 9-35 are main inventory.
		// Slots 36-39 are armor, 40 is off-hand. We explicitly exclude these.
		for (int i = 0; i <= 35; i++) { // Loop only for main inventory and hotbar
			ItemStack item = inventory.getItem(i);
			if (item == null || item.getType() == Material.AIR) {
				// Fill with a full stack of 64 string.
				inventory.setItem(i, new ItemStack(Material.STRING, 64));
				filledAnySlot = true;
			}
		}

		if (filledAnySlot) {
			player.sendMessage(ChatColor.GREEN + config.getMessageFilled());
			cooldowns.put(playerUUID, currentTime);
		} else {
			player.sendMessage(ChatColor.YELLOW + config.getMessageNoEmptySlots());
		}

		return true;
	}
}