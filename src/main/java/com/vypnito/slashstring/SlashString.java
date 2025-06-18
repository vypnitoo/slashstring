package com.vypnito.slashstring;

import com.vypnito.slashstring.commands.StringCommand;
import com.vypnito.slashstring.config.PluginConfig;
import com.vypnito.slashstring.listeners.ItemDropListener;
import org.bukkit.ChatColor; // <-- Ujisti se, že je tento import

import org.bukkit.plugin.java.JavaPlugin;

public final class SlashString extends JavaPlugin {

	private PluginConfig pluginConfig;

	@Override
	public void onEnable() {
		saveDefaultConfig();
		this.pluginConfig = new PluginConfig(this);

		getCommand("string").setExecutor(new StringCommand(this, pluginConfig));

		getServer().getPluginManager().registerEvents(new ItemDropListener(this), this);

		// --- ZPRÁVA, KTERÁ BY NEMĚLA OBSAHOVAT § SYMBOLY PŘÍMO V KÓDU ---
		getLogger().info(ChatColor.AQUA + "========================================");
		getLogger().info(ChatColor.AQUA + "            SLASH STRING");
		getLogger().info(ChatColor.YELLOW + "           made by " + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + "vypnito");
		getLogger().info(ChatColor.AQUA + "========================================");
		getLogger().info(ChatColor.GREEN + "SlashString Plugin Version: " + getDescription().getVersion() + " enabled!");
		// --- KONEC ZPRÁVY ---
	}

	@Override
	public void onDisable() {
		getLogger().info("SlashString has been disabled!");
	}

	public static SlashString getPlugin() {
		return JavaPlugin.getPlugin(SlashString.class);
	}

	public PluginConfig getPluginConfig() {
		return pluginConfig;
	}
}