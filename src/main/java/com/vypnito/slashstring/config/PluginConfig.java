package com.vypnito.slashstring.config;

import com.vypnito.slashstring.SlashString;
import org.bukkit.configuration.file.FileConfiguration;

public class PluginConfig {

	private final SlashString plugin;
	private final FileConfiguration config;

	private static final int DEFAULT_COOLDOWN_SECONDS = 3;
	private static final String DEFAULT_MESSAGE_COOLDOWN = "You are on cooldown! Please wait %seconds% seconds.";
	private static final String DEFAULT_MESSAGE_FILLED = "Your empty inventory slots have been filled with string.";
	private static final String DEFAULT_MESSAGE_NO_EMPTY_SLOTS = "You have no empty inventory slots to fill.";
	private static final String DEFAULT_MESSAGE_ITEM_DELETED = "A string item was deleted on spawn.";


	public PluginConfig(SlashString plugin) {
		this.plugin = plugin;
		this.config = plugin.getConfig();
		loadConfig();
	}

	private void loadConfig() {
		config.addDefault("cooldown_seconds", DEFAULT_COOLDOWN_SECONDS);
		config.addDefault("message_cooldown", DEFAULT_MESSAGE_COOLDOWN);
		config.addDefault("message_filled", DEFAULT_MESSAGE_FILLED);
		config.addDefault("message_no_empty_slots", DEFAULT_MESSAGE_NO_EMPTY_SLOTS);
		config.addDefault("message_item_deleted", DEFAULT_MESSAGE_ITEM_DELETED);

		config.options().copyDefaults(true);
		plugin.saveConfig();
	}

	public int getCooldownSeconds() {
		return config.getInt("cooldown_seconds", DEFAULT_COOLDOWN_SECONDS);
	}

	public String getMessageCooldown() {
		return config.getString("message_cooldown", DEFAULT_MESSAGE_COOLDOWN);
	}

	public String getMessageFilled() {
		return config.getString("message_filled", DEFAULT_MESSAGE_FILLED);
	}

	public String getMessageNoEmptySlots() {
		return config.getString("message_no_empty_slots", DEFAULT_MESSAGE_NO_EMPTY_SLOTS);
	}

	public String getMessageItemDeleted() {
		return config.getString("message_item_deleted", DEFAULT_MESSAGE_ITEM_DELETED);
	}
}