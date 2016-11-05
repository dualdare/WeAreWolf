package com.gmail.doubledare1202;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.doubledare1202.oneNight.ONTurnNight;
import com.gmail.doubledare1202.oneNight.ONTurnNoon;

public class Actions {
	public WeAreWolf plugin;
	private String msg, m,path;

	private List<String> startedPlayer = new ArrayList<String>();

	WolfPlayer wolfPlayer = new WolfPlayer();
	WereWolfExecutor WereWolf;

	public Actions(WeAreWolf instance) {
		plugin = instance;
	}

	// Command /wolf help Executor
	public void help(CommandSender sender) {
		int line = 11;
		for(int i = 0 ; i < line; i++ ){
			path = "wolf_help_" + i;
			msg = WeAreWolf.japanese.getString(path);

			Messenger.message(sender, null, msg, null, null, null, null);
		}
	}

	// Command /wolf rule Executor
	public void rule(CommandSender sender, String rule) {
		if(rule == "OneNight"){
			int line = 29;
			for(int i = 0 ; i < line; i++ ){
				path = "wolf_rule_onenight_" + i;
				msg = WeAreWolf.japanese.getString(path);
				Messenger.message(sender, null, msg, null, null, null, null);
			}
		}else if(rule == "Normal"){
			//ノーマルモード実装時用
			path = "wolf_common_commingsoon";
			msg = WeAreWolf.japanese.getString(path);
			Messenger.message(sender, null, msg, null, null, null, null);
		}else{
			//この前で判定してるからこないはず
		}
	}

	public void role(CommandSender sender, String role) {
		if(role == "OneNight"){
			int line = 6;
			for(int i = 0 ; i < line; i++ ){
				path = "wolf_role_onenight_" + i;
				msg = WeAreWolf.japanese.getString(path);
				Messenger.message(sender, null, msg, null, null, null, null);
			}
		}else if(role == "Normal"){
			path = "wolf_common_commingsoon";
			msg = WeAreWolf.japanese.getString(path);
			Messenger.message(sender, null, msg, null, null, null, null);
			//ノーマルモード実装時用
		}else{
			//この前で判定してるからこないはず
		}
	}

	// Command /wolf gameCreate Executor
	public void gameCreate(CommandSender sender) {

		if (wolfPlayer.setWolfGameMasterPlayer(sender.getName())) {
			//msg = "%logo&6" + sender.getName() + "&fは人狼ゲームのゲームマスターになりました。";
			path = "wolf_joingm_sucsess";
			msg = WeAreWolf.japanese.getString(path);
			Messenger.message(sender, null, msg, sender.getName(), null, null, null);
		} else {
			path = "wolf_joingm_failed";
			msg = WeAreWolf.japanese.getString(path);
			Messenger.message(sender, null, msg, sender.getName(), null, null, null);
		}

	}

	// Command /wolf join Executor
	public void join(CommandSender sender) {
		if (wolfPlayer.setWolfPlayer(sender)) {
			//msg = "%logo&6" + sender.getName() + "&fは人狼ゲームの待機部屋に入りました。";
			path = "wolf_join_sucsess";
			msg = WeAreWolf.japanese.getString(path);
			Messenger.message(sender, null, msg, sender.getName(), null, null, null);
		} else {
			//msg = "%logo&6" + sender.getName() + "&fはすでに待機部屋に入っています！";
			path = "wolf_join_failed";
			msg = WeAreWolf.japanese.getString(path);
			Messenger.message(sender, null, msg, sender.getName(), null, null, null);
		}
	}

	// Command /wolf leave Executor
	public void leave(CommandSender sender) {
		if (wolfPlayer.removeWolfPlayer(sender.getName())) {
			//msg = "%logo&6" + sender.getName() + "&fは人狼ゲームの待機部屋から退出しました。";
			path = "wolf_leave_sucsess";
			msg = WeAreWolf.japanese.getString(path);
			Messenger.message(sender, null, msg, sender.getName(), null, null, null);

		} else {
			//msg = "%logo&6" + sender.getName() + "&fは待機部屋にいません！";
			path = "wolf_leave_failed";
			msg = WeAreWolf.japanese.getString(path);
			Messenger.message(sender, null, msg, sender.getName(), null, null, null);
		}
	}

	// Command /wolf kick Executor
	public void kick(CommandSender sender, String player) {
		if (wolfPlayer.removeWolfPlayer(player)) {
			// String型からplayer型への強制返還
			Player toPlayer = Bukkit.getPlayer(player);
			//msg = "%logo&6あなたは" + player + "&fを人狼ゲームの待機部屋からキックしました。";
			path = "wolf_kick_sucsess";
			msg = WeAreWolf.japanese.getString(path);
			Messenger.message(sender, null, msg, player, null, null, null);
			//m = "%logo&6あなたは" + sender.getName() + "&fにより待機部屋からキックされました。";
			path = "wolf_kick_kicked";
			msg = WeAreWolf.japanese.getString(path);
			Messenger.message(null, toPlayer, m, sender.getName(), null, null, null);
		} else {
			//msg = "%logo&6" + sender.getName() + "&fは待機部屋にいません！";
			path = "wolf_kick_failed";
			msg = WeAreWolf.japanese.getString(path);
			Messenger.message(sender, null, msg, player, null, null, null);
		}

	}

	// Command /wolf gameStart Executor
	public void gameStart(CommandSender sender, String[] args) {
		if(args[1].equalsIgnoreCase("OneNight")){
			int fail = 0;
			if (wolfPlayer.getWolfGameMasterPlayer().contains(sender.getName())) {
				for (int i = 2; i < args.length; i++) {
					if (!wolfPlayer.getJoinPlayerList().contains(args[i])) {
						fail++;
					}
				}
				if (fail == 0) {// 問題がないなら
					startedPlayer.clear();
					for (int i = 2; i < args.length; i++) {

						if (wolfPlayer.getJoinPlayerList().contains(args[i])) {
							wolfPlayer.removeWolfPlayer(args[i]);
							wolfPlayer.removeWolfGameMasterPlayer(args[i]);
							startedPlayer.add(args[i]);
						}
					}
					wolfPlayer.removeWolfPlayer(sender.getName());
					wolfPlayer.removeWolfGameMasterPlayer(sender.getName());
					startedPlayer.add(sender.getName());
					//msg = "%logo&6" + sender.getName() + "&fがワンナイト人狼を開始しました。！";
					path = "wolf_gson_sucsess";
					msg = WeAreWolf.japanese.getString(path);
					Messenger.message(sender, null, msg, sender.getName(), null, null, null);
					WereWolf = new WereWolfExecutor(startedPlayer);
				} else if (args.length > 9) {// 人数多すぎる
					//msg = "%logo&6参加人数が多すぎます！GMをあわえて8人までです。";
					path = "wolf_gson_numover";
					msg = WeAreWolf.japanese.getString(path);
					Messenger.message(sender, null, msg, null, null, null, null);

				} else if (fail > 0 || args.length < 10) {// joinしていないプレイヤーを指定した場合
					//msg = "%logo&6" + "指定したプレイヤー" + "はゲームに参加していません！";
					path = "wolf_gson_nojoin";
					msg = WeAreWolf.japanese.getString(path);
					Messenger.message(sender, null, msg, null, null, null, null);

				} else {

					// msg = "&6" + sender.getName() + "が人狼を開始しました。！";
					// plugin.message(sender,null,msg,null,null,null,null);
				}
			} else {// そもそもsenderがGMじゃない場合
				//msg = "%logo&6このコマンドはゲームマスターしか実行できません！";
				path = "wolf_common_nogm";
				msg = WeAreWolf.japanese.getString(path);
				Messenger.message(sender, null, msg, null, null, null, null);
			}
		}else if(args[1].equalsIgnoreCase("normal")){
			//普通の人狼のときの参加の処理を書く
			//msg = "%logo&fNormalは未実装です。今後のアップデートにご期待ください！";
			path = "wolf_common_commingsoon";
			msg = WeAreWolf.japanese.getString(path);
			Messenger.message(sender, null, msg, null, null, null, null);
		}else{
			//msg = "%logo&6正しいゲームルールを入力してください。";
			path = "wolf_common_nogamerule";
			msg = WeAreWolf.japanese.getString(path);
			Messenger.message(sender, null, msg, null, null, null, null);
			path = "wolf_common_gamerule";
			msg = WeAreWolf.japanese.getString(path);
			Messenger.message(sender, null, msg, null, null, null, null);

		}
	}

	// Command /wolf vote Executor
	public void vote(CommandSender sender, String player) {
		// WereWolfExecutor.setPlayer(sender.getName());
		if (WereWolfExecutor.isTurn() == false && WereWolfExecutor.isOnGame()) {
			ONTurnNoon.voteCommand(sender, player);
		} else {
			//msg = "%logo&6このコマンドは人狼ゲームを進行しているプレイヤーのみが実行可能です";
			path = "wolf_common_nogs";
			msg = WeAreWolf.japanese.getString(path);
			Messenger.message(sender, null, msg, null, null, null, null);
		}

	}

	// Command /wolf target Executor
	public void target(CommandSender sender, String player) {
		if (WereWolfExecutor.isTurn() && WereWolfExecutor.isOnGame()) {
			ONTurnNight.targetCommand(sender, player);
		} else {
			path = "wolf_common_nogs";
			msg = WeAreWolf.japanese.getString(path);
			Messenger.message(sender, null, msg, null, null, null, null);
		}

	}

	// Command /wolf over Executor
	public void over(CommandSender sender) {
		//未使用
	}

	// Command /wolf whisper Executor
	public void whisper(CommandSender sender, String player, String message) {
		String a = message;
		Player toPlayer = Bukkit.getPlayer(player);
		path = "wolf_whisper_listen";
		msg = WeAreWolf.japanese.getString(path);
		String b = msg + a;
		Messenger.message(sender, toPlayer, b, sender.getName(), null, null, null);

		path = "wolf_whisper_sender";
		msg = WeAreWolf.japanese.getString(path);
		String c = msg + a;
		Messenger.message(sender, toPlayer, c, player, null, null, null);
	}

	//debugコマンドはとりあえず見送りで
	public void joinPlayerList(CommandSender sender) {
		StringBuilder sb = new StringBuilder();
		String a = "%logo&6現在待機室にいる人は、&f";
		sb.append(a);
		List<String> playerList;
		playerList = wolfPlayer.getJoinPlayerList();
		for (int i = 0; i < playerList.size(); i++) {
			sb.append(playerList.get(i));
			sb.append(".");
		}
		sb.append("&6です。");
		Messenger.message(sender, null, sb.toString(), null, null, null, null);
	}

	public void joinGameMasterList(CommandSender sender) {
		StringBuilder sb = new StringBuilder();
		String a = "%logo&6現在ゲームマスターになっている人は、&f";
		sb.append(a);
		List<String> playerList;
		playerList = wolfPlayer.getWolfGameMasterPlayer();
		for (int i = 0; i < playerList.size(); i++) {
			sb.append(playerList.get(i));
			sb.append(".");
		}
		sb.append("&6です。");
		Messenger.message(sender, null, sb.toString(), null, null, null, null);
	}

	public void startedPlayerList(CommandSender sender) {
		// WolfPlayer wolfPlayer = new WolfPlayer(sender.getName());
		StringBuilder sb = new StringBuilder();
		String a = "%logo&6現在人狼ゲームを進行している人は、&f";
		sb.append(a);
		List<String> playerList;
		playerList = startedPlayer;
		for (int i = 0; i < playerList.size(); i++) {
			sb.append(playerList.get(i));
			sb.append(".");
		}
		sb.append("&6です。");
		Messenger.message(sender, null, sb.toString(), null, null, null, null);
	}

	public void rolePlayerList(CommandSender sender) {
		msg = "%logo&6プレイヤーと役職を表示します";
		Messenger.message(sender, null, msg, null, null, null, null);
		// WolfPlayer wolfPlayer = new WolfPlayer(sender.getName());
		msg = "怪盗能力使用前";
		Messenger.message(sender, null, msg, null, null, null, null);
		Map<String, Role> roleMap = WereWolfExecutor.getPlayerRoleMap();
		for (String key : roleMap.keySet()) {
			Role data = roleMap.get(key);
			msg = key + "&6の役職は&f、" + data + "&6です";
			Messenger.message(sender, null, msg, null, null, null, null);
		}
	}

	public void votePlayerList(CommandSender sender) {
		msg = "%logo&6プレイヤーとそのプレイヤーに投票された数を表示します";
		Messenger.message(sender, null, msg, null, null, null, null);
		Map<String, Integer> roleMap = ONTurnNoon.getPlayerVoteNum();
		for (String key : roleMap.keySet()) {
			Integer data = roleMap.get(key);
			msg = key + "&6への投票数は&f、" + data + "です";
			Messenger.message(sender, null, msg, null, null, null, null);
		}
	}

}