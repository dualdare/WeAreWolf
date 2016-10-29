package com.gmail.doubledare1202;

import java.io.IOException;

import org.bukkit.permissions.Permission;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.mcstats.Metrics;


public class WeAreWolf extends JavaPlugin {

	private WolfCommands wolfExecutor;
	private Actions actions;
	//private CommandExecutor debugWolfExecutor;
	//private DebugActions debugActions;

	public Permission joinPermisson = new Permission("wearewolf.join");
	public Permission GMPermisson = new Permission("wearewolf.GM");
	public Permission debugPermisson = new Permission("weareWolf.debug");

	public PluginManager pm;

	public void onEnable() {
		actions = new Actions(this);
		getLogger().info(
				"\u001b[00;31m" + "onEnableメソッドが呼び出されたよ！！" + "\u001b[00m");
		// コンフィグをセーブする
		saveDefaultConfig();

		wolfExecutor = new WolfCommands(this, actions);
		getCommand("wr").setExecutor(wolfExecutor);

		//pm.addPermission(GMPermisson);
		//pm.addPermission(debugPermisson);
		//pm.addPermission(joinPermisson);

		// GOOOOOOO Metrics
		try {
			Metrics metrics = new Metrics(this);
			metrics.start();
		} catch (IOException e) {
			// Failed to submit the stats :-(
		}

	}

	@Override
	public void onDisable() {
		// TODO ここに、プラグインが無効化された時の処理を実装してください。
		getLogger().info("onDisableメソッドが呼び出されたよ！！");
	}

	/*
	public void message(CommandSender sender, Player player, String message,
			String value, String world, String target, Double cost) {
		if (message != null) {
			message = message.replaceAll("%world", world)
					.replaceAll("%color", value).replaceAll("%prefix", value)
					.replaceAll("%suffix", value).replaceAll("%s", value)
					.replaceAll("%playerName", world)
					.replaceAll("%player", target)
					.replaceAll("%groupName", value).replaceAll("%part", value)
					.replaceAll("%value", target)
					.replaceAll("%version", "3.8.1")
					.replaceAll("%pl", "[We're Wolf]");
			message = ChatColor.translateAlternateColorCodes('&', message);
			if (cost != null) {
				message = message.replaceAll("%costs", Double.toString(cost));
			}
			if (player != null) {
				player.sendMessage(message);
			} else if (sender != null) {
				sender.sendMessage(message);
			}
		}
		// If message is null
		else {
			if (player != null) {
				player.sendMessage(ChatColor.DARK_RED
						+ "Somehow this message is not defined. Please check your localization.yml");

			} else if (sender != null) {
				sender.sendMessage(ChatColor.DARK_RED
						+ "Somehow this message is not defined. Please check your localization.yml");

			}
		}
	}
	 */

}
