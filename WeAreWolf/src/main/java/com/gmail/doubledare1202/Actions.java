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
	private String msg, m;

	private List<String> startedPlayer = new ArrayList<String>();

	WolfPlayer wolfPlayer = new WolfPlayer();
	WereWolfExecutor WereWolf;

	public Actions(WeAreWolf instance) {
		plugin = instance;
	}

	// Command /wolf help Executor
	public void help(CommandSender sender) {
		//とりあえず日本語ですべて書いてからlocalization.ymlにしていくということで
		msg = "%= %logo help %=";
		Messenger.message(sender, null, msg, null, null, null, null);
		msg = "&e/wr help &fこのコマンドです";
		Messenger.message(sender, null, msg, null, null, null, null);
		msg = "&e/wr rule <gameRule> &f人狼ゲームのルールを表示します。<gameRule>OneNight,normal";
		Messenger.message(sender, null, msg, null, null, null, null);
		msg = "&e/wr role <gameRule> &f各ゲームの割り振られる役職を表示します";
		Messenger.message(sender, null, msg, null, null, null, null);
		msg = "&e/wr join &f人狼ゲームの待機部屋に入ります";
		Messenger.message(sender, null, msg, null, null, null, null);
		msg = "&e/wr leave &f待機部屋から退出します。";
		Messenger.message(sender, null, msg, null, null, null, null);
		msg = "&e/wr whisper <player> &fプレイヤーにささやきを送ります。他のプレイヤーに見られることはありません";
		Messenger.message(sender, null, msg, null, null, null, null);
		msg = "&e/wr kick <player> &fプレイヤーを待機部屋から退出させます。";
		Messenger.message(sender, null, msg, null, null, null, null);
		msg = "&e/wr joinGM &f人狼ゲームのゲームマスターになります /wr gameStartでゲームを開始できます";
		Messenger.message(sender, null, msg, null, null, null, null);
		msg = "&e/wr gamestart <gameRule> <参加プレイヤー1> <参加プレイヤー2>...";
		Messenger.message(sender, null, msg, null, null, null, null);
		msg = "&f人狼ゲームを開始します。ゲームルールと待機部屋にいるプレイヤーを任意に選択してゲームを開始できます。";
		Messenger.message(sender, null, msg, null, null, null, null);

	}

	// Command /wolf rule Executor
	public void rule(CommandSender sender, String rule) {
		if(rule == "OneNight"){
			msg = "%= %logo Rule-&aOneNight %=";
			Messenger.message(sender, null, msg, null, null, null, null);
			msg = "人狼ゲームを遊ぼうとすると最低8人ほどの人数が必要で、1ゲーム1時間ほどかかってしまう。"
					+ "ワンナイト人狼はそれを少人数で遊べて、ちゃんと人狼できちゃうお手軽なゲームです。";
			Messenger.message(sender, null, msg, null, null, null, null);
			msg = "&e-勝利条件-";
			Messenger.message(sender, null, msg, null, null, null, null);
			msg = "&b人間陣営 &c人狼&fを見つけ処刑すること。そして&b人間&fを"
					+ "誤って処刑しないこと。";
			Messenger.message(sender, null, msg, null, null, null, null);
			msg = "&c人狼陣営 &b人間&fをだまし処刑されないこと。そして&c人狼&f同士殺し合いをしないこと";
			Messenger.message(sender, null, msg, null, null, null, null);
			msg = "&e-各役職の紹介-";
			Messenger.message(sender, null, msg, null, null, null, null);
			msg  ="&a村人&f(人間陣営) &f特殊な能力はありません。他の人に賛同しながら人狼を見つけましょう";
			Messenger.message(sender, null, msg, null, null, null, null);
			msg = "&b占い師&f(人間陣営) &f他の人の役職をこっそり占うことができます。これは、村にとって大事な情報になります.";
			Messenger.message(sender, null, msg, null, null, null, null);
			msg = "&d怪盗&f(人間陣営) &f他の人の役職と自分の役職を交換できます。"
					+ "&b人間&f陣営と交換した場合は有利に働けますが、"
					+ "&c人狼&fと交換した場合その時から人狼として行動しなければなりません。";
			Messenger.message(sender, null, msg, null, null, null, null);
			msg = "&c人狼&f(人狼陣営) &f人間に正体がばれないように、人間に化けて、村を混乱させましょう。"
					+ "仲間の人狼がいる場合は協力してダマしましょう";
			Messenger.message(sender, null, msg, null, null, null, null);
			msg = "&e-ゲームの流れ-";
			Messenger.message(sender, null, msg, null, null, null, null);
			msg = "&7【夜の時間】&b占い師&fと&d怪盗&fと&c人狼&fの人がこっそり行動する時間です";
			Messenger.message(sender, null, msg, null, null, null, null);
			msg = "&b占い師&f 他のプレイヤー1人の役職を知る。もしくは、選ばれていない他の役職を知る"
					+ "のどちらかの行動をとることができます。";
			Messenger.message(sender, null, msg, null, null, null, null);
			msg = "&c人狼&f 仲間の人狼が誰かを知ることができます。もちろん人狼があなたひとりのときもあります。";
			Messenger.message(sender, null, msg, null, null, null, null);
			msg = "&d怪盗&f 自分の役職と他のプレイヤーの役職を交換することができます。(しなくてもよいです)";
			Messenger.message(sender, null, msg, null, null, null, null);
			msg = "ですが、もし&c人狼&fになってしまった場合は、あなたも人狼として行動しなくてはなりません";
			Messenger.message(sender, null, msg, null, null, null, null);
			msg = "全員の行動が終わったら昼の時間となります";
			Messenger.message(sender, null, msg, null, null, null, null);
			msg = "&e【昼の時間】&f投票の時間となります。人間陣営は人狼をさがすために、"
					+ "人狼陣営は人間にバレないように話し合いをしましょう";
			Messenger.message(sender, null, msg, null, null, null, null);
			msg = "話し合いの時間は、5~10分ほどがおすすめです";
			Messenger.message(sender, null, msg, null, null, null, null);
			msg = "なお、このプラグインでは全員が/wr voteを実行することで昼のターンが終了します";
			Messenger.message(sender, null, msg, null, null, null, null);
			msg = "&a【結果発表】 &f各プレイヤーの投票をもとに勝敗を決めます";
			Messenger.message(sender, null, msg, null, null, null, null);
			msg = "基本的には&e勝利条件&fのとおりですが、もしプレイヤーが全員&b人間陣営の場合は"
					+ "&a&o平和村&fとなり、全員で協力して投票数を同じにしなくてはなりません";
			Messenger.message(sender, null, msg, null, null, null, null);
		}else if(rule == "Normal"){
			//ノーマルモード実装時用
		}else{
			//この前で判定してるからこないはず
		}
	}

	public void role(CommandSender sender, String role) {
		if(role == "OneNight"){
			msg = "%= %logo Role-&aOneNight %=";
			Messenger.message(sender, null, msg, null, null, null, null);
			msg = "人数別に選ばれる候補となる役職を表示します";
			Messenger.message(sender, null, msg, null, null, null, null);
			msg = "&e3人 &f村人1、占い師1、怪盗1、人狼2";
			Messenger.message(sender, null, msg, null, null, null, null);
			msg = "&e4人 &f村人2、占い師1、怪盗1、人狼2";
			Messenger.message(sender, null, msg, null, null, null, null);
			msg = "&e5人 &f村人3、占い師1、怪盗1、人狼2";
			Messenger.message(sender, null, msg, null, null, null, null);
			msg = "&e6人以上 &f村人4、占い師1、怪盗1、人狼2";
			Messenger.message(sender, null, msg, null, null, null, null);
		}else if(role == "Normal"){
			//ノーマルモード実装時用
		}else{
			//この前で判定してるからこないはず
		}
	}

	// Command /wolf gameCreate Executor
	public void gameCreate(CommandSender sender) {

		if (wolfPlayer.setWolfGameMasterPlayer(sender.getName())) {
			msg = "%logo&6" + sender.getName() + "&fは人狼ゲームのゲームマスターになりました。";
			Messenger.message(sender, null, msg, null, null, null, null);
		} else {
			msg = "%logo&6" + sender.getName() + "&fはすでにゲームマスターになっています！";
			Messenger.message(sender, null, msg, null, null, null, null);
		}

	}

	// Command /wolf join Executor
	public void join(CommandSender sender) {
		if (wolfPlayer.setWolfPlayer(sender)) {
			msg = "%logo&6" + sender.getName() + "&fは人狼ゲームの待機部屋に入りました。";
			Messenger.message(sender, null, msg, null, null, null, null);
		} else {
			msg = "%logo&6" + sender.getName() + "&fはすでに待機部屋に入っています！";
			Messenger.message(sender, null, msg, null, null, null, null);
		}
	}

	// Command /wolf leave Executor
	public void leave(CommandSender sender) {
		if (wolfPlayer.removeWolfPlayer(sender.getName())) {
			msg = "%logo&6" + sender.getName() + "&fは人狼ゲームの待機部屋から退出しました。";
			Messenger.message(sender, null, msg, null, null, null, null);

		} else {
			msg = "%logo&6" + sender.getName() + "&fは待機部屋にいません！";
			Messenger.message(sender, null, msg, null, null, null, null);
		}
	}

	// Command /wolf kick Executor
	public void kick(CommandSender sender, String player) {
		if (wolfPlayer.removeWolfPlayer(player)) {
			// String型からplayer型への強制返還
			Player toPlayer = Bukkit.getPlayer(player);
			msg = "%logo&6あなたは" + player + "&fを人狼ゲームの待機部屋からキックしました。";
			Messenger.message(sender, null, msg, null, null, null, null);
			m = "%logo&6あなたは" + sender.getName() + "&fにより待機部屋からキックされました。";
			Messenger.message(null, toPlayer, m, null, null, null, null);
		} else {
			msg = "%logo&6" + sender.getName() + "&fは待機部屋にいません！";
			Messenger.message(sender, null, msg, null, null, null, null);
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
					msg = "%logo&6" + sender.getName() + "&fがワンナイト人狼を開始しました。！";
					Messenger.message(sender, null, msg, null, null, null, null);
					WereWolf = new WereWolfExecutor(startedPlayer);
				} else if (args.length > 9) {// 人数多すぎる
					msg = "%logo&6参加人数が多すぎます！GMをあわえて8人までです。";
					Messenger.message(sender, null, msg, null, null, null, null);

				} else if (fail > 0 || args.length < 10) {// joinしていないプレイヤーを指定した場合
					msg = "%logo&6" + "指定したプレイヤー" + "はゲームに参加していません！";
					Messenger.message(sender, null, msg, null, null, null, null);

				} else {

					// msg = "&6" + sender.getName() + "が人狼を開始しました。！";
					// plugin.message(sender,null,msg,null,null,null,null);
				}
			} else {// そもそもsenderがGMじゃない場合
				msg = "%logo&6このコマンドはゲームマスターしか実行できません！";
				Messenger.message(sender, null, msg, null, null, null, null);
			}
		}else if(args[1].equalsIgnoreCase("normal")){
			//普通の人狼のときの参加の処理を書く
			msg = "%logo&fNormalは未実装です。今後のアップデートにご期待ください！";
			Messenger.message(sender, null, msg, null, null, null, null);
		}else{
			msg = "%logo&6正しいゲームルールを入力してください。";
			Messenger.message(sender, null, msg, null, null, null, null);
			msg = "%logo&f<gameRule> OneNight , Normal";
			Messenger.message(sender, null, msg, null, null, null, null);
		}
	}

	// Command /wolf vote Executor
	public void vote(CommandSender sender, String player) {
		// WereWolfExecutor.setPlayer(sender.getName());
		if (WereWolfExecutor.isTurn() == false && WereWolfExecutor.isOnGame()) {
			ONTurnNoon.voteCommand(sender, player);
		} else {
			msg = "%logo&6このコマンドは人狼ゲームを進行しているプレイヤーのみが実行可能です";
			Messenger.message(sender, null, msg, null, null, null, null);
		}

	}

	// Command /wolf target Executor
	public void target(CommandSender sender, String player) {
		if (WereWolfExecutor.isTurn() && WereWolfExecutor.isOnGame()) {
			ONTurnNight.targetCommand(sender, player);
		} else {
			msg = "%logo&6このコマンドは人狼ゲームを進行しているプレイヤーのみが実行可能です";
			Messenger.message(sender, null, msg, null, null, null, null);
		}

	}

	// Command /wolf over Executor
	public void over(CommandSender sender) {
		//未使用
	}

	// Command /wolf whisper Executor
	public void whisper(CommandSender sender, String player, String message) {
		msg = message;
		Player toPlayer = Bukkit.getPlayer(player);
		Messenger.message(sender, toPlayer, msg, null, null, null, null);
		msg = "%logo&6" + player + "&fにささやきを送りました";
	}

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