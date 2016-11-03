package com.gmail.doubledare1202.oneNight;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.doubledare1202.Messenger;
import com.gmail.doubledare1202.WereWolfExecutor;

public class ONVillager {
	private static String msg;

	private static List<String> villagerMember = new ArrayList<String>();
	private ONVillager(){

	}
	//夜のターン　役職村人へのメッセージ発信
	public static void nightTurnVillager(String player){
		Player p = Bukkit.getPlayer(player);
		villagerMember.clear();
		msg = "%= %logo - Rule - &aOneNight %e夜のターン %=";
		Messenger.message(null, p, msg, null, null, null, null);
		msg = "あなたは村人になりました。";
		Messenger.message(null, p, msg, null, null, null, null);
		msg = "役職 村人 人間陣営";
		Messenger.message(null, p, msg, null, null, null, null);
		msg = "勝利条件 人間と協力し、人狼の正体を暴くこと。";
		Messenger.message(null, p, msg, null, null, null, null);
		msg = "特殊能力-なし";
		Messenger.message(null, p, msg, null, null, null, null);
		msg = "&e/wr target <player> &fで怪しいと思うプレイヤーに殺意を向けましょう";
		Messenger.message(null, p, msg, null, null, null, null);
		msg = "*このコマンドに意味はありませんが、全員がtargetコマンドを実行することで"
				+ "昼のターンに移行します。";
		Messenger.message(null, p, msg, null, null, null, null);
	}

	public static void noonTurnVillager(String key) {
		Player p = Bukkit.getPlayer(key);
		villagerMember.add(key);
		msg = "%= %logo - Rule - &aOneNight %e昼のターン %=";
		Messenger.message(null, p, msg, null, null, null, null);
		msg = "長い夜が明けました。全員目をあけてください。";
		Messenger.message(null, p, msg, null, null, null, null);
		msg = "昼のターンです。それでは議論を始めます。";
		Messenger.message(null, p, msg, null, null, null, null);
		msg = "&e/wr vote <player> &fで処刑したいプレイヤーに投票しましょう";
		Messenger.message(null, p, msg, null, null, null, null);
		msg = "投票数が一番多いプレイヤーが処刑されます。（同票の場合は同票の人全員が処刑されます";
		Messenger.message(null, p, msg, null, null, null, null);

	}

	public static void targetPlayer() {
		WereWolfExecutor.countEndTargetPlayer++;
		if(WereWolfExecutor.countEndTargetPlayer == WereWolfExecutor.getPlayerRoleMap().size()){
			//昼の投票ターンに移動する
			ONTurnNoon.startNoonTurn();
		}
	}

	public static void vote(CommandSender sender, String player) {
		if(villagerMember.contains(sender.getName())){
			villagerMember.remove(sender.getName());
			ONTurnNoon.voteCommand(sender, player);
		}else{
			msg = "村人クラスあなたはもう投票しています";
			Messenger.message(sender, null, msg, null, null, null, null);
		}
	}


}
