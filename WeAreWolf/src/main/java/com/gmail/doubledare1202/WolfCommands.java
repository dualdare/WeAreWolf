package com.gmail.doubledare1202;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class WolfCommands implements CommandExecutor {
	private WeAreWolf plugin;
	private Actions actions;
	private String msg,m,path;


	public WolfCommands(WeAreWolf instance, Actions actionsInstance) {
		plugin = instance;
		actions = actionsInstance;
	}

	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {
		if (args.length > 0 && args[0].equalsIgnoreCase("help")) {
			if (sender.hasPermission("wearewolf.join")) {
				// sucess
				// help for sender
				actions.help(sender);
			}
			return true;

		} else if (args.length > 0 && args[0].equalsIgnoreCase("rule")) {
			if (sender.hasPermission("wearewolf.join")) {
				if (args.length > 1 && args[1].equalsIgnoreCase("OneNight")) {
					actions.rule(sender, "OneNight");
				}else if(args.length > 1 && args[1].equalsIgnoreCase("Normal")){
					actions.rule(sender, "Normal");
				}else{
					path = "wolf_common_nogamerule";
					msg = WeAreWolf.japanese.getString(path);
					Messenger.message(sender, null, msg, null, null, null, null);
					path = "wolf_common_gamerule";
					msg = WeAreWolf.japanese.getString(path);
					Messenger.message(sender, null, msg, null, null, null, null);
				}
			}
			return true;
		} else if (args.length > 0 && args[0].equalsIgnoreCase("role")) {
			if (sender.hasPermission("wearewolf.join")) {
				if (args.length > 1 && args[1].equalsIgnoreCase("OneNight")) {
					actions.role(sender, "OneNight");
				}else if(args.length > 1 && args[1].equalsIgnoreCase("Normal")){
					actions.rule(sender, "Normal");
				}else{
					path = "wolf_common_nogamerule";
					msg = WeAreWolf.japanese.getString(path);
					Messenger.message(sender, null, msg, null, null, null, null);
					path = "wolf_common_gamerule";
					msg = WeAreWolf.japanese.getString(path);
					Messenger.message(sender, null, msg, null, null, null, null);
				}
			}
			return true;
		} else if (args.length > 0
				&& (args[0].equalsIgnoreCase("gameCreate") || args[0]
						.equalsIgnoreCase("gC") || args[0].equalsIgnoreCase("joinGM")
						|| args[0].equalsIgnoreCase("jg"))) {
			if (sender.hasPermission("wearewolf.GM")) {
				actions.gameCreate(sender);
			}
			return true;

		} else if (args.length > 0
				&& (args[0].equalsIgnoreCase("join") || args[0]
						.equalsIgnoreCase("j"))) {
			if (sender.hasPermission("wearewolf.join")) {
				// できた
				actions.join(sender);
			}
			return true;

		} else if (args.length > 0 && args[0].equalsIgnoreCase("leave")) {
			if (sender.hasPermission("wearewolf.join")) {
				// できた
				actions.leave(sender);
			}
			return true;

		} else if (args.length > 0 && args[0].equalsIgnoreCase("kick")) {
			if (sender.hasPermission("wearewolf.GM")) {
				// できた
				actions.kick(sender, args[1]);
			}
			return true;

		} else if (args.length > 0
				&& (args[0].equalsIgnoreCase("gameStart") || args[0]
						.equalsIgnoreCase("gs"))) {
			if (sender.hasPermission("wearewolf.GM")) {
				if (args.length < 2) {
					path = "wolf_common_nogamerule";
					msg = WeAreWolf.japanese.getString(path);
					Messenger.message(sender, null, msg, null, null, null, null);
					path = "wolf_common_gamerule";
					msg = WeAreWolf.japanese.getString(path);
					Messenger.message(sender, null, msg, null, null, null, null);

				} else if (args.length < 4) {
					//msg = "参加者が足りません";
					path = "wolf_gson_numfew";
					msg = WeAreWolf.japanese.getString(path);
					Messenger.message(sender, null, msg, null, null, null, null);

				} else {
					actions.gameStart(sender, args);

				}
			}
			return true;
		} else if (args.length > 0 && args[0].equalsIgnoreCase("vote")) {
			if (sender.hasPermission("wearewolf.join")) {
				actions.vote(sender, args[1]);
			}
			return true;

		} else if (args.length > 0 && args[0].equalsIgnoreCase("target")) {
			if (sender.hasPermission("wearewolf.join")) {
				if (args.length < 2) {
					//msg = "能力の使用先のプレイヤーを入力してください";
					path = "wolf_target_notarget";
					msg = WeAreWolf.japanese.getString(path);
					Messenger.message(sender, null, msg, null, null, null, null);
				} else {
					actions.target(sender, args[1]);
				}
			}
			return true;
		} else if (args.length > 0 && args[0].equalsIgnoreCase("over")) {
			if (sender.hasPermission("wearewolf.join")) {
				actions.over(sender);
			}
			// defeat
			return true;
		} else if (args.length > 0 && (args[0].equalsIgnoreCase("whisper")
				|| args[0].equalsIgnoreCase("w"))) {
			if (sender.hasPermission("wearewolf.join")) {
				// できた
				if (args.length == 1) {
					//msg = "ささやきをおくるプレイヤーを入力してください";
					path = "wolf_whisper_nolistener";
					msg = WeAreWolf.japanese.getString(path);
					Messenger.message(sender, null, msg, null, null, null, null);
				}
				if (args.length > 1) {

					actions.whisper(sender, args[1], args[2]);
				} else {
					//msg = "ささやきをするメッセージを入力してください";
					path = "wolf_whisper_nomsg";
					msg = WeAreWolf.japanese.getString(path);
					Messenger.message(sender, null, msg, null, null, null, null);
				}
			}
			return true;
			//
			// 以下デバッグコマンドの予定
			//

		} else if (args.length > 0
				&& (args[0].equalsIgnoreCase("joinPlayerList") || args[0]
						.equalsIgnoreCase("jPL"))) {
			if (sender.hasPermission("wearewolf.debug")) {
				actions.joinPlayerList(sender);
			}
			return true;

		} else if (args.length > 0
				&& (args[0].equalsIgnoreCase("joinGameMasterList") || args[0]
						.equalsIgnoreCase("jGML"))) {
			if (sender.hasPermission("wearewolf.debug")) {
				actions.joinGameMasterList(sender);
			}
			return true;

		} else if (args.length > 0
				&& (args[0].equalsIgnoreCase("startedPlayerList") || args[0]
						.equalsIgnoreCase("sPL"))) {
			if (sender.hasPermission("wearewolf.debug")) {
				actions.startedPlayerList(sender);
			}
			return true;

		} else if (args.length > 0
				&& (args[0].equalsIgnoreCase("rolePlayerList") || args[0]
						.equalsIgnoreCase("rPL"))) {
			if (sender.hasPermission("wearewolf.debug")) {
				actions.rolePlayerList(sender);
			}
			return true;

		} else if (args.length > 0
				&& (args[0].equalsIgnoreCase("votePlayerList") || args[0]
						.equalsIgnoreCase("vPL"))) {
			if (sender.hasPermission("wearewolf.debug")) {
				actions.votePlayerList(sender);
			}
			return true;

		} else {

			// defeat
			// String msg = plugin.getConfig().getString("message");
			path = "wolf_common_nocommand";
			msg = WeAreWolf.japanese.getString(path);
			Messenger.message(sender, null, msg, null, null, null, null);
			return true;
		}
	}
}
