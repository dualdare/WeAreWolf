package com.gmail.doubledare1202;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

public class ONTurnNoon {
	private static Map<String,Role> playerRoleMap = new HashMap<String,Role>();

	//誰が誰に何票投票したかを保管するＭＡＰ
	private static Map<String,Integer> playerVoteNum = new HashMap<String,Integer>();

	//何回投票されたかを数える
	private static int countVotePlayer;

	//何票集まったかだけを記憶するLisi<int>
	private static int[] voteNumArray;


	public ONTurnNoon(){
		//countVotePlayer = 0;
	}
	public static void startNoonTurn() {
		countVotePlayer = 0;
		WereWolfExecutor.setTurn(false);
		ONTurnNoon.playerRoleMap = WereWolfExecutor.getPlayerRoleMap();

		//playerVoteNumをまず作る　←これ絶対初期化のときに書くべきだろ
		for(int i = 0; i < WereWolfExecutor.getPlayerList().size();i++){
			playerVoteNum.put(WereWolfExecutor.getPlayerList().get(i), 0);
		}

		for(String key : playerRoleMap.keySet()){
			Role data = playerRoleMap.get(key);
			//msg = key + "の役職は" + data + "です";
			//plugin.message(sender,null,msg,null,null,null,null);
			if(data == Role.WEREWOLF){
				WereWolf.noonTurnWolf(key);
			}else if(data == Role.PHANTOM){
				Phantom.nooonTurnPhantom(key);
				//WereWolfExecutor.setCountSEandPT();
			}else if(data == Role.SEER){
				Seer.noonTurnSeer(key);
				//WereWolfExecutor.setCountSEandPT();
			}else if(data == Role.VILLAGER){
				Villager.noonTurnVillager(key);
			}
		}

	}

	public static void voteCommand(CommandSender sender , String player){
		//if(WereWolfExecutor.isTurn() == false && WereWolfExecutor.getDay() == 1 && WereWolfExecutor.isOnGame()){
		/*
		playerVoteNum.put(sender.getName(), player);
		countVotePlayer++;
		for(String key : playerVoteNum.keySet()){
			String data = playerVoteNum.get(key);
			if(data == player){


			}
		}

		voteNumArray = new int[playerRoleMap.size()];
		for(String key : playerRoleMap.keySet()){
			Role data = playerRoleMap.get(key);


		}


			//playerVoteNum をまず作らなきゃだめじゃん
			for(int i = 0; i < WereWolfExecutor.getPlayerList().size();i++){
				playerVoteNum.put(WereWolfExecutor.getPlayerList().get(i), 0);
			}*/

		for(String key : playerVoteNum.keySet()){

			//
			//一人一回投票にするようにする判定を書く
			//
			Integer data = playerVoteNum.get(key);
			String msg = "投票カウント繰り返し判定";
			Messenger.message(sender, null, msg, null, null, null, null);
			if(key.contentEquals(player)){
				int i = data.intValue();
				//msg = "key == player にはいった";
				Messenger.message(sender, null, "intValue = " + i, null, null, null, null);
				i++;

				//msg = "key == player にはいった";
				Messenger.message(sender, null, "i++=" + i, null, null, null, null);

				playerVoteNum.put(key, i);
				//data = (Integer)i;
				//data++;

				msg = "key == player にはいった";
				Messenger.message(sender, null, msg, null, null, null, null);
			}
		}
		countVotePlayer++;

		String msg = "あなたは" + player + "に投票しました";
		Messenger.message(sender,null,msg,null,null,null,null);
		if(countVotePlayer == WereWolfExecutor.getPlayerRoleMap().size()){
			//勝利判定に移ります
			ONJudgeWinner.judge();
			for(String key: playerRoleMap.keySet()){
				msg = "judgeに移動する";
				Messenger.message(null,Bukkit.getPlayer(key),msg,null,null,null,null);
			}
		}
		//}else{
		//	String msg = "このコマンド人狼に参加しているプレイヤーのみが実行できます。";
		//	Messenger.message(sender, null, msg, null, null, null, null);
		//}
	}

	public static Map<String, Integer> getPlayerVoteNum(){
		return playerVoteNum;
	}
}
