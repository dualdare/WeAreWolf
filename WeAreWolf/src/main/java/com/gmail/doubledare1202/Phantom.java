package com.gmail.doubledare1202;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Phantom {
	private static String msg;
	private static boolean canChange = true;//falseで実行可能＝falseのとき他人の心を奪える
	private static Map<String,Role> playerRoleMap = new HashMap<String,Role>();
	//private static Map<String,Role> roleList = new HashMap<String,Role>();

	//こいつらは最後の勝利判定クラスで使用する
	//占い師とかぶるしうまくかけないから入れ替え処理はあとに回すことにした、
	private static Role stackChangeRole;
	private static String[] PTChangePlayer= new String[2];

	public static boolean thisGameJoinPhantom = false;//このゲームに怪盗が参加してるとtrue

	//怪盗の人数を数える
	//public static int countPhantom;


	private Phantom(){

	}
	//夜のターン　役職怪盗のメッセージ発信
	public static void nightTurnPhantom(String player){
		msg = "&3あなたは怪盗になりました。";
		Player p = Bukkit.getPlayer(player);
		Messenger.message(null, p, msg, null, null, null, null);
		msg = "&3勝利条件は人狼が死なないことです";
		Messenger.message(null, p, msg, null, null, null, null);
		msg = "&3他の人の心を盗んでください /wr target <player>";
		Messenger.message(null, p, msg, null, null, null, null);
		canChange = false;
		stackChangeRole = null;
		PTChangePlayer[0] = null;
		PTChangePlayer[1] = null;
		//countPhantom = 0;

		thisGameJoinPhantom = true;
	}

	public static void changePlayer(CommandSender sender, String player) {
		Map<String,Role> roleList = WereWolfExecutor.getPlayerRoleMap();
		//Role a;
		if(!canChange){
			//Map<String,Role> roleMap = WereWolfExecutor.getPlayerRoleMap();
			for(String key : roleList.keySet()){
				Role data = roleList.get(key);
				if(key.contentEquals(player)){
					//msg = "&1[占い結果]" + player + "の役職は" + data + "です";
					//Messenger.message(sender, null, msg, null, null, null, null);
					stackChangeRole = data;//入れ替える相手のRoledata
					PTChangePlayer[0] = sender.getName();//新しい役職になるひと
					PTChangePlayer[1] = player;//怪盗になるひと
					msg =  player + "と心を入れ替えました";
					Messenger.message(sender, null, msg, null, null, null, null);
					msg = "あなたは" + stackChangeRole  + "となりました。";
					Messenger.message(sender, null, msg, null, null, null, null);
					canChange = true;
					//心入れ替え処理をここに書く
					//roleList.remove(player);
					//roleList.remove(sender.getName());←これはいらない
					//putするときすでにkeyが存在数ならそれのdataだけを書き換えるから

					//roleList.put(player, Role.PHANTOM);
					//roleList.put(sender.getName(), a);
					WereWolfExecutor.countEndTargetPlayer++;
					if(WereWolfExecutor.countEndTargetPlayer == WereWolfExecutor.getPlayerRoleMap().size()){
						//昼の投票ターンに移動する
						ONTurnNoon.startNoonTurn();
					}
				}else{
					msg = "怪盗繰り返したけどなかった";
					Messenger.message(sender, null, msg, null, null, null, null);
				}
			}

		}else{
			msg = "あなたはすでに怪盗の能力を使いました！";
			Messenger.message(sender, null, msg, null, null, null, null);
		}
		//setPlayerRoleMap(roleList);

	}
	public static Map<String,Role> getPlayerRoleMap() {
		return playerRoleMap;
	}
	public static void setPlayerRoleMap(Map<String,Role> playerRoleMap) {
		Phantom.playerRoleMap = playerRoleMap;
	}

	public static boolean getCanChange(){
		return canChange;
	}
	public static void nooonTurnPhantom(String key) {
		Player p = Bukkit.getPlayer(key);
		msg = "長い夜が明けました…";
		Messenger.message(null, p, msg, null, null, null, null);
		msg = "昼のターンです/wr vote <player>で投票をしてください";
		Messenger.message(null, p, msg, null, null, null, null);
	}

	public static Role getStackChangeRole(){
		return stackChangeRole;
	}

	public static String[] getPTChangePlayer(){
		return PTChangePlayer;
	}


}
