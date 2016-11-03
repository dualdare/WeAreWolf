package com.gmail.doubledare1202.oneNight;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.doubledare1202.Messenger;
import com.gmail.doubledare1202.WereWolfExecutor;

public class ONWereWolf {
	private static String msg;

	private static List<String> wereWolfMember = new ArrayList<String>();

	public ONWereWolf(){

	}

	//OneNightの夜のターン　役職人狼へのメッセージ発信
	public static void nightTurnWolf(String player){
		wereWolfMember.clear();

		Player p = Bukkit.getPlayer(player);
		msg = "%= %logo - Rule - &aOneNight &e夜のターン %=";
		Messenger.message(null, p, msg, null, null, null, null);
		msg = "あなたは人狼になりました。";
		Messenger.message(null, p, msg, null, null, null, null);
		msg = "役職 人狼 人狼陣営";
		Messenger.message(null, p, msg, null, null, null, null);
		msg = "勝利条件 他の人狼と協力し、人間に自分の正体をバレないようにする。";
		Messenger.message(null, p, msg, null, null, null, null);
		msg = "特殊能力-なし";
		Messenger.message(null, p, msg, null, null, null, null);
		msg = "&e/wr target <player> &fで殺したいプレイヤーに殺意を向けましょう。";
		Messenger.message(null, p, msg, null, null, null, null);
		msg = "*このコマンドに意味はありませんが、全員がtargetコマンドを実行することで"
				+ "昼のターンに移行します。";
		Messenger.message(null, p, msg, null, null, null, null);

	}

	public static void noonTurnWolf(String key) {
		Player p = Bukkit.getPlayer(key);
		wereWolfMember.add(key);

		msg = "%= %logo - Rule - &aOneNight &e昼のターン %=";
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
		if(wereWolfMember.contains(sender.getName())){//まだ投票してないなら
			//			List<String> playerRoleList = WereWolfExecutor.getPlayerList();
			//			for(int i = 0 ; i < playerRoleList.size(); i++){
			//				if(player.equals(playerRoleList.get(i)));
			//				playerRoleList.remove(playerRoleList.get(i));
			wereWolfMember.remove(sender.getName());
			ONTurnNoon.voteCommand(sender, player);
		}else{
			msg = "人狼クラスあなたはもう投票しています";
			Messenger.message(sender, null, msg, null, null, null, null);
		}
	}

}