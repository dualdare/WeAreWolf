package com.gmail.doubledare1202;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.mcstats.Metrics;


public class WeAreWolf extends JavaPlugin {

	private WolfCommands wolfExecutor;
	private WolfTabCompleter wolfTabCompleter;
	private Actions actions;
	//private CommandExecutor debugWolfExecutor;
	//private DebugActions debugActions;

	public Permission joinPermisson = new Permission("wearewolf.join");
	public Permission GMPermisson = new Permission("wearewolf.GM");
	public Permission debugPermisson = new Permission("weareWolf.debug");
	public File japaneseFile;
	public FileConfiguration japanese;

	public PluginManager pm;

	public void onEnable() {
		actions = new Actions(this);
		//getLogger().info("\u001b[00;31m" + "onEnableメソッドが呼び出されたよ！！" + "\u001b[00m");
		getLogger().info("Good Morning! Now, WeAreWolf enable!");
		// コンフィグをセーブする

		loadConfig();
		saveConfig();

		japaneseFile = new File(getDataFolder(), "japanese.yml");
		japanese = YamlConfiguration.loadConfiguration(japaneseFile);
		japanese();

		wolfExecutor = new WolfCommands(this, actions);
		getCommand("wr").setExecutor(wolfExecutor);
		wolfTabCompleter = new WolfTabCompleter(this);
		getCommand("wr").setTabCompleter(wolfTabCompleter);

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
		getLogger().info("See you! I hope you are't killed by werewolf");
	}

	//コンフィグ製作
	private void loadConfig(){
		getConfig().addDefault("test", "true");
		getConfig().options().copyDefaults(true);
	}

	//日本語言語ファイル製作
	private void japanese() {
		japanese.addDefault("test", "true");
		japanese.options().copyDefaults(true);
		try {
		    japanese.save(japaneseFile);
		} catch (IOException e) {
			getLogger().warning("Fialed");
		}
	}
}
