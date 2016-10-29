package com.gmail.doubledare1202.oneNight;

import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.doubledare1202.Messenger;
import com.gmail.doubledare1202.Role;
import com.gmail.doubledare1202.WereWolfExecutor;

public class ONSeer {
	private static String msg;
	private static boolean canDivine = true;//falseなら占いできる＝まだ占っていない

	//占い師の人数を数える
	//public static int countSeer = 0;
	private ONSeer(){
		//初期化処理はここに書くべきだよね
	}
	//夜のターン　役職占い師のへのメッセージ発信
	public static void nightTurnSeer(String player){

		msg = "&3あなたは占い師になりました。";
		Player p = Bukkit.getPlayer(player);
		Messenger.message(null, p, msg, null, null, null, null);
		msg = "&3勝利条件は人狼を倒すことです";
		Messenger.message(null, p, msg, null, null, null, null);
		msg = "&3あなたは一人のプレイヤーを占いことができます /wr target <player>";
		Messenger.message(null, p, msg, null, null, null, null);
		canDivine = false;
		//countSeer = 0;
	}

	public static void divinePlayer(CommandSender sender,String player){
		if(!canDivine){
			Map<String,Role> roleMap = WereWolfExecutor.getPlayerRoleMap();
			for(String key : roleMap.keySet()){
				Role data = roleMap.get(key);
				if(key.contentEquals(player)){
					msg = "%logo&b[占い結果]&f" + player + "&bの役職は&f" + data + "&bです";
					Messenger.message(sender, null, msg, null, null, null, null);
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
			msg = "あなたはすでに占いました！";
			Messenger.message(sender, null, msg, null, null, null, null);
		}
	}

	public static void noonTurnSeer(String key) {
		Player p = Bukkit.getPlayer(key);
		msg = "長い夜が明けました…";
		Messenger.message(null, p, msg, null, null, null, null);
		msg = "昼のターンです/wr vote <player>で投票をしてください";
		Messenger.message(null, p, msg, null, null, null, null);
	}
}
