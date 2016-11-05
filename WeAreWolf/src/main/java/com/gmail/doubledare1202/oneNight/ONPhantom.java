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

public class ONPhantom {
	private static String msg,path;
	private static boolean canChange = true;//falseで実行可能＝falseのとき他人の心を奪える

	private static Role stackChangeRole;
	private static String[] PTChangePlayer= new String[2];

	public static boolean thisGameJoinPhantom = false;//このゲームに怪盗が参加してるとtrue

	public static boolean canVote = false;
	private ONPhantom(){

	}
	//夜のターン　役職怪盗のメッセージ発信
	public static void nightTurnPhantom(String player){
		Player p = Bukkit.getPlayer(player);
		canChange = false;
		stackChangeRole = null;
		PTChangePlayer[0] = null;
		PTChangePlayer[1] = null;

		thisGameJoinPhantom = true;
		/*
		msg = "%= %logo - Rule - &aOneNight %e夜のターン %=";
		Messenger.message(null, p, msg, null, null, null, null);
		msg = "あなたは怪盗になりました。";
		Messenger.message(null, p, msg, null, null, null, null);
		msg = "役職 怪盗 人間陣営";
		Messenger.message(null, p, msg, null, null, null, null);
		msg = "勝利条件 人間と協力し、人狼の正体を暴くこと";
		Messenger.message(null, p, msg, null, null, null, null);
		msg = "特殊能力-心変わり 他のプレイヤー1人と自分の役職を入れ替えることができます。";
		Messenger.message(null, p, msg, null, null, null, null);
		msg = "&e/wr target <player> で入れ替えるプレイヤーを決めてください。(入れ替えない場合は自分の名前を指定してください)";
		Messenger.message(null, p, msg, null, null, null, null);
		*/
		path = "wolf_onni_first";
		msg = WeAreWolf.japanese.getString(path);
		Messenger.message(null, p, msg, null, null, null, null);

		int line = 5;
		for(int i = 0 ; i < line ; i++){
			path = "wolf_onniph_" + i;
			msg = WeAreWolf.japanese.getString(path);
			Messenger.message(null, p, msg, null, null, null, null);

		}
	}

	public static void changePlayer(CommandSender sender, String player) {
		Map<String,Role> roleList = WereWolfExecutor.getPlayerRoleMap();
		if(!canChange){
			for(String key : roleList.keySet()){
				Role data = roleList.get(key);
				if(key.contentEquals(player)){
					stackChangeRole = data;//入れ替える相手のRoledata
					PTChangePlayer[0] = sender.getName();//新しい役職になるひと
					PTChangePlayer[1] = player;//怪盗になるひと

					path = "wolf_onni_ability";
					msg = WeAreWolf.japanese.getString(path);
					Messenger.message(sender, null, msg, null, null, null, null);
					//msg =  player + "と心を入れ替えました";
					path = "wolf_onniph_changeplayer";
					msg = WeAreWolf.japanese.getString(path);
					Messenger.message(sender, null, msg, player, null, null, null);
					//msg = "あなたは" + stackChangeRole  + "となりました。";
					path = "wolf_onniph_yourrole";
					msg = WeAreWolf.japanese.getString(path);//ここせこい
					Messenger.message(sender, null, msg, WolfUtils.roleToJapanese(stackChangeRole), null, null, null);
					canChange = true;

					WereWolfExecutor.countEndTargetPlayer++;
					if(WereWolfExecutor.countEndTargetPlayer == WereWolfExecutor.getPlayerRoleMap().size()){
						//昼の投票ターンに移動する
						ONTurnNoon.startNoonTurn();
					}
				}else{
					//msg = "怪盗繰り返したけどなかった";
					//Messenger.message(sender, null, msg, null, null, null, null);
				}
			}

		}else{
			//msg = "あなたはすでに怪盗の能力を使いました！";
			path = "wolf_onniph_alreadyability";
			msg = WeAreWolf.japanese.getString(path);
			Messenger.message(sender, null, msg, null, null, null, null);
		}
		//setPlayerRoleMap(roleList);

	}


	public static boolean getCanChange(){
		return canChange;
	}
	public static void nooonTurnPhantom(String key) {
		Player p = Bukkit.getPlayer(key);
		canVote = true;
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

	public static Role getStackChangeRole(){
		return stackChangeRole;
	}

	public static String[] getPTChangePlayer(){
		return PTChangePlayer;
	}
	public static void vote(CommandSender sender, String player) {
		if(!canVote){
			ONTurnNoon.voteCommand(sender, player);
			canVote = false;
		}else{
			msg = "怪盗クラスあなたは投票しました。";
			Messenger.message(sender, null, msg, null, null, null, null);
		}
	}


}
