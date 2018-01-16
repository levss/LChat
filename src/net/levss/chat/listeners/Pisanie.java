package net.levss.chat.listeners;

import org.bukkit.Server;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;

import net.levss.chat.Main;

public class Pisanie implements Listener
{
	Main plugin;

	public Pisanie(Main plugin)
	{
		
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		
	}
}
