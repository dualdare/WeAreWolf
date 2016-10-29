package com.gmail.doubledare1202;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class WereWolf {
	private static String msg;
	public WereWolf(){

	}

	//OneNightの夜のターン　役職人狼へのメッセージ発信
	public static void nightTurnWolf(String player){
		msg = "&3あなたは人狼になりました。";
		Player p = Bukkit.getPlayer(player);
		Messenger.message(null, p, msg, null, null, null, null);
		msg = "&3勝利条件は人狼が死なないことです";
		Messenger.message(null, p, msg, null, null, null, null);
	}

	public static void noonTurnWolf(String key) {
		Player p = Bukkit.getPlayer(key);
		msg = "長い夜が明けました…";
		Messenger.message(null, p, msg, null, null, null, null);
		msg = "昼のターンです/wr vote <player>で投票をしてください";
		Messenger.message(null, p, msg, null, null, null, null);
	}

	public static void targetPlayer() {
		WereWolfExecutor.countEndTargetPlayer++;
		if(WereWolfExecutor.countEndTargetPlayer == WereWolfExecutor.getPlayerRoleMap().size()){
			//昼の投票ターンに移動する
			ONTurnNoon.startNoonTurn();
		}
	}
}