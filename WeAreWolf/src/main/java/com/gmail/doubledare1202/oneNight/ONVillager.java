package com.gmail.doubledare1202.oneNight;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.gmail.doubledare1202.Messenger;
import com.gmail.doubledare1202.WereWolfExecutor;

public class ONVillager {
private static String msg;
	private ONVillager(){

	}
	//夜のターン　役職村人へのメッセージ発信
	public static void nightTurnVillager(String player){
		msg = "&3あなたは村人になりました。";
		Player p = Bukkit.getPlayer(player);
		Messenger.message(null, p, msg, null, null, null, null);
		msg = "&3勝利条件は人狼を倒すことです";
		Messenger.message(null, p, msg, null, null, null, null);
	}
	public static void noonTurnVillager(String key) {
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
