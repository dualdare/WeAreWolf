package com.gmail.doubledare1202.oneNight;

import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.doubledare1202.Messenger;
import com.gmail.doubledare1202.Role;
import com.gmail.doubledare1202.WeAreWolf;
import com.gmail.doubledare1202.WereWolfExecutor;
import com.gmail.doubledare1202.WolfUtils;

public class ONSeer {
	private static String msg,path;
	private static boolean canDivine = true;//falseなら占いできる＝まだ占っていない
	private static boolean canVote = true;//falseなら投票できる

	//占い師の人数を数える
	//public static int countSeer = 0;
	private ONSeer(){
		//初期化処理はここに書くべきだよね
	}
	//夜のターン　役職占い師のへのメッセージ発信
	public static void nightTurnSeer(String player){
		canDivine = false;
		Player p = Bukkit.getPlayer(player);
		/*
		msg = "%= %logo - Rule - &aOneNight %e夜のターン %=";
		Messenger.message(null, p, msg, null, null, null, null);
		msg = "あなたは占い師になりました。";
		Messenger.message(null, p, msg, null, null, null, null);
		msg = "役職 占い師 人間陣営";
		Messenger.message(null, p, msg, null, null, null, null);
		msg = "勝利条件 人間と協力し、人狼の正体を暴くこと。";
		Messenger.message(null, p, msg, null, null, null, null);
		msg = "特殊能力-占い 他のプレイヤー1人の役職を占いによって知ることができます。";
		Messenger.message(null, p, msg, null, null, null, null);
		msg = "&e/wr target <player> &fで占いたいプレイヤーを指定して占いましょう。";
		Messenger.message(null, p, msg, null, null, null, null);
		//msg = "*このコマンドに意味はありませんが、全員がtargetコマンドを実行することで"
		//		+ "昼のターンに移行します。";

		 */
		path = "wolf_onni_first";
		msg = WeAreWolf.japanese.getString(path);
		Messenger.message(null, p, msg, null, null, null, null);

		int line = 5;
		for(int i = 0 ; i < line ; i++){
			path = "wolf_onnise_" + i;
			msg = WeAreWolf.japanese.getString(path);
			Messenger.message(null, p, msg, null, null, null, null);

		}

	}

	public static void divinePlayer(CommandSender sender,String player){
		if(!canDivine){
			Map<String,Role> roleMap = WereWolfExecutor.getPlayerRoleMap();
			for(String key : roleMap.keySet()){
				Role data = roleMap.get(key);
				if(key.contentEquals(player)){
					//msg = "%logo&b[占い結果]&f" + player + "&bの役職は&f" + data + "&bです";
					path = "wolf_onni_ability";
					msg = WeAreWolf.japanese.getString(path);
					Messenger.message(sender, null, msg, null, null, null, null);
					path = "wolf_onnise_divineresult";
					msg = WeAreWolf.japanese.getString(path);
					Messenger.message(sender, null, msg, player, WolfUtils.roleToJapanese(data), null, null);
					canDivine = true;
					WereWolfExecutor.countEndTargetPlayer++;
					if(WereWolfExecutor.countEndTargetPlayer == WereWolfExecutor.getPlayerRoleMap().size()){
						//昼の投票ターンに移動する
						ONTurnNoon.startNoonTurn();
					}
				}else{
					//一致しなかった場合のデバックメッセ
					//msg = key + "と" + player+ "は見つかりませんでした。";
					//Messenger.message(sender, null, msg, null, null, null, null);
				}
			}
		}else{//もう占いを実行していたら
			//msg = "あなたはすでに占いました！";
			path = "wolf_onnise_alreadyability";
			msg = WeAreWolf.japanese.getString(path);
			Messenger.message(sender, null, msg, null, null, null, null);
		}
	}

	public static void noonTurnSeer(String key) {
		Player p = Bukkit.getPlayer(key);
		canVote = false;
		/*
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
		*/
		int line = 5;
		for(int i = 0 ; i < line ; i++){
			path = "wolf_onno_" + i;
			msg = WeAreWolf.japanese.getString(path);
			Messenger.message(null, p, msg, null, null, null, null);

		}

	}

	public static void vote(CommandSender sender,String player){
		if(!canVote){
			canVote = true;
			ONTurnNoon.voteCommand(sender, player);
		}else{
			msg = "占いクラスあなたはもう投票しました。";
			Messenger.message(sender, null, msg, null, null, null, null);
		}
	}
}
