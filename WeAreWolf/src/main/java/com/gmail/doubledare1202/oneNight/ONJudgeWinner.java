package com.gmail.doubledare1202.oneNight;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;

import com.gmail.doubledare1202.Messenger;
import com.gmail.doubledare1202.Role;
import com.gmail.doubledare1202.Team;
import com.gmail.doubledare1202.WereWolfExecutor;

public class ONJudgeWinner {
	private static String msg;

	private static Map<String,Role> playerRoleMap = new HashMap<String,Role>();
	private static Map<String,Integer> voteJudge = new HashMap<String,Integer>();

	private ONJudgeWinner(){

	}

	public static void judge(){
		//String Player のマップをもってくる
		ONJudgeWinner.playerRoleMap = WereWolfExecutor.getPlayerRoleMap();
		//String Integer 誰に何票入っているかのMAP
		ONJudgeWinner.voteJudge = ONTurnNoon.getPlayerVoteNum();
		//stackRolePlayer 怪盗能力使用先のプレイヤーの役職
		Role stackRolePlayer = ONPhantom.getStackChangeRole();
		//String[] [0] [1] を間違えないように書く
		String[] changePlayer = ONPhantom.getPTChangePlayer();

		//こっから勝利判定を書く
		//最後にメッセージ発信を書きます

		//怪盗の能力を発動！
		//入れ替え処理をここに書いた

		//ここの怪盗入れ替え処理は怪盗がいなくても発動してる＝nullさんがrplに入り判定が狂う
		//↑治しました

		if(ONPhantom.thisGameJoinPhantom){
			playerRoleMap.put(changePlayer[0], stackRolePlayer);
			playerRoleMap.put(changePlayer[1], Role.PHANTOM);
			for(String key: playerRoleMap.keySet()){
				msg = "全員の投票が終わりました。これより勝利判定に移ります";
				//Messenger.message(null,Bukkit.getPlayer(key),msg,null,null,null,null);
				Messenger.message(null,Bukkit.getPlayer(key),"怪盗入れ替え発動",null,null,null,null);
			}
		}

		//こっから投票数判定
		//①人狼が一番多かったら人間の勝ち これは片方でも両方でもいい　とにかく一人死んだらオワリ
		//②人間が一番多かったら人狼の勝ち　逆にこれは一番じゃないとだめ
		//③二人同じ票数のとき　二人とも処刑　そのどちらかに人狼がいれば人間の勝利
		//④平和村判定　全員が人間かつみんなに一票ずつ入ったときに発動
		//この中に人狼がいたら人狼の勝ち

		//これより書く順番は
		//誰が一番投票が多いか？⇒人狼なら人間勝ち　逆もしかり
		//同票判定⇒全員殺してその中に人狼がいれば人間の価値　逆もしかり
		//平和村判定⇒全員同じ票数かつ中に人狼がいない⇒人間の価値

		//投票数だけを保管する配列　←事故りそう
		int[] voteNum = new int[playerRoleMap.size()];
		int count = 0;
		//投票数を配列にいれてる
		for(String key : voteJudge.keySet()){
			Integer data = voteJudge.get(key);
			voteNum[count] = data;
			count++;
		}

		boolean[] isMaxVoteNum = new boolean[voteNum.length];
		int MAX_VOTENUM = 0;
		//最大値を求めて　さらに最大の票を集めているひとの番号の配列をtrueに
		//してる　番号を対応させてるのでそれで判定する。
		for(int i = 0 ; i < voteNum.length ; i++){
			if(MAX_VOTENUM < voteNum[i]){
				MAX_VOTENUM = voteNum[i];
				for(int j = 0; j < voteNum.length;j++){
					isMaxVoteNum[j] = false;//全部falseにするだけ
				}
				isMaxVoteNum[i] = true;
				//全部消して新しい最大値をtrueにする
			}else if(MAX_VOTENUM == voteNum[i]){
				isMaxVoteNum[i] = true;
				//同じならtrueだけを増やす
			}else if(MAX_VOTENUM < voteNum[i]){
				//小さいものに用はないです
			}
		}

		//投票により殺された人をこのリストにぶち込む
		List<String> killedPlayer = new ArrayList<String>();
		for(int i = 0; i < WereWolfExecutor.getPlayerList().size() ; i++){
			if(isMaxVoteNum[i]){
				killedPlayer.add(WereWolfExecutor.getPlayerList().get(i));
			}
		}

		boolean winWereWolf = false;//trueは勝ち falseは負け
		boolean peaceVillage = false;//平和村ならtrue
		//boolean winHuman = false;
		//平和村判定
		if(killedPlayer.size() == WereWolfExecutor.getPlayerList().size()){
			peaceVillage = true;
			//殺された人の数＝参加していた人数なら平和村！
			for(int i = 0 ;i < killedPlayer.size(); i++){
				String player = killedPlayer.get(i);
				for(String key : playerRoleMap.keySet()){
					Role data = playerRoleMap.get(key);
					if(key.contentEquals(player) && data.getTeam() == Team.WEREWOLF){
						//人狼の勝ち
						winWereWolf = true;//平和村の中に人狼がいたら

					}
				}
			}
		}else{//それ以外の判定＝殺された人の中に人狼が含まれているかどうか
			winWereWolf = true;
			for(int i = 0 ;i < killedPlayer.size(); i++){
				String player = killedPlayer.get(i);
				for(String key : playerRoleMap.keySet()){
					Role data = playerRoleMap.get(key);
					if(key.contentEquals(player) && data.getTeam() == Team.WEREWOLF){
						//人狼の負け
						winWereWolf = false;//殺した人の中に人狼がいたら
					}
				}
			}
		}

		//全員に出力
		for(String key: playerRoleMap.keySet()){
			msg = "全員の投票が終わりました。これより勝利判定に移ります";
			Messenger.message(null,Bukkit.getPlayer(key),msg,null,null,null,null);
			if(peaceVillage){
				msg = "&aこの村は平和村です！！！";
				Messenger.message(null,Bukkit.getPlayer(key),msg,null,null,null,null);
			}
			if(winWereWolf){
				msg = "&l&a人狼陣営の勝ちです！！！おめでとうございます";
			}else{
				msg = "&l&a村人陣営の勝ちです！！！おめでとうございます";
			}

			Messenger.message(null,Bukkit.getPlayer(key),msg,null,null,null,null);
		}
		//msg = "全員の投票が終わりました。これより勝利判定に移ります";
		//Messenger.message(sender,null,msg,null,null,null,null);
	}
}
