package com.gmail.doubledare1202.oneNight;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.command.CommandSender;

import com.gmail.doubledare1202.Messenger;
import com.gmail.doubledare1202.Role;
import com.gmail.doubledare1202.WeAreWolf;
import com.gmail.doubledare1202.WereWolfExecutor;

public class ONTurnNoon {
	private static Map<String,Role> playerRoleMap = WereWolfExecutor.getPlayerRoleMap();

	//誰が誰に何票投票したかを保管するＭＡＰ
	private static Map<String,Integer> playerVoteNum = new HashMap<String,Integer>();

	//何回投票されたかを数える
	private static int countVotePlayer;

	//何票集まったかだけを記憶するLisi<int>
	private static int[] voteNumArray;

	//どの役職が何人いるか
	private static int[] NumOfRolePlayer = new int[4];

	//投票完了した役職ごとの人数
	private static int[] NumOfVotedPlayer = new int[4];

	private static List<String> finishVotePlayer = new ArrayList<String>();

	private static String msg,path;

	public ONTurnNoon(){
		//countVotePlayer = 0;
	}
	public static void startNoonTurn() {
		countVotePlayer = 0;
		WereWolfExecutor.setTurn(false);
		finishVotePlayer.clear();
		//ONTurnNoon.playerRoleMap = WereWolfExecutor.getPlayerRoleMap();

		//playerVoteNumをまず作る　←これ絶対初期化のときに書くべきだろ
		for(int i = 0; i < WereWolfExecutor.getPlayerList().size();i++){
			playerVoteNum.put(WereWolfExecutor.getPlayerList().get(i), 0);
		}

		for(String key : playerRoleMap.keySet()){
			Role data = playerRoleMap.get(key);
			//msg = key + "の役職は" + data + "です";
			//plugin.message(sender,null,msg,null,null,null,null);
			if(data == Role.WEREWOLF){
				ONWereWolf.noonTurnWolf(key);
				NumOfRolePlayer[0]++;
			}else if(data == Role.PHANTOM){
				ONPhantom.nooonTurnPhantom(key);
				NumOfRolePlayer[1]++;
			}else if(data == Role.SEER){
				ONSeer.noonTurnSeer(key);
				NumOfRolePlayer[2]++;
			}else if(data == Role.VILLAGER){
				ONVillager.noonTurnVillager(key);
				NumOfRolePlayer[3]++;
			}
		}

	}
	/*//廃止したクラス　一人一票投票は違うところでやるよ
	public static void voteCommandForEachRole(CommandSender sender,String player){
		for(String key : playerRoleMap.keySet()){
			Role data = playerRoleMap.get(key);
			//msg = key + "の役職は" + data + "です";
			//plugin.message(sender,null,msg,null,null,null,null);
			if(data == Role.WEREWOLF){
				ONWereWolf.vote(sender,player);
			}else if(data == Role.PHANTOM){
				ONPhantom.vote(sender,player);
			}else if(data == Role.SEER){
				ONSeer.vote(sender,player);
			}else if(data == Role.VILLAGER){
				ONVillager.vote(sender,player);
			}
		}
	}
	 */

	public static void voteCommand(CommandSender sender , String player){
		if(canVotePlayer(sender.getName())){
			for(String key : playerVoteNum.keySet()){
				Integer data = playerVoteNum.get(key);
				//String msg = "投票カウント繰り返し判定";
				//Messenger.message(sender, null, msg, null, null, null, null);
				if(key.contentEquals(player)){
					int i = data.intValue();
					i++;
					playerVoteNum.put(key, i);
					break;//一回でも一致したらいいかたぬけちゃう
				}
			}

			countVotePlayer++;

			//msg = "%logo&fあなたは&e" + player + "&fに投票しました";
			path = "wolf_onno_youvote";
			msg = WeAreWolf.japanese.getString(path);
			Messenger.message(sender,null,msg,player,null,null,null);
			if(countVotePlayer == WereWolfExecutor.getPlayerRoleMap().size()){
				//勝利判定に移ります
				ONJudgeWinner.judge();
				for(String key2: playerRoleMap.keySet()){
					//msg = "judgeに移動する";
					//Messenger.message(null,Bukkit.getPlayer(key2),msg,null,null,null,null);
				}
			}

		}else{
			//msg = "%logo&eあなたはすでに投票を終えています";
			path = "wolf_onno_alreadyvote";
			msg = WeAreWolf.japanese.getString(path);
			Messenger.message(sender,null,msg,null,null,null,null);
		}
	}

	public static Map<String, Integer> getPlayerVoteNum(){
		return playerVoteNum;
	}

	//プレイヤーが一人一回までしか投票できなくする
	public static boolean canVotePlayer(String player){
		if(finishVotePlayer.contains(player)){
			return false;
		}else{
			finishVotePlayer.add(player);
			return true;
		}
	}
}
