package com.gmail.doubledare1202;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Actions {
	public WeAreWolf plugin;
	private String msg,m;

	private List<String> startedPlayer = new ArrayList<String>();


	WolfPlayer wolfPlayer = new WolfPlayer();
	WereWolfExecutor WereWolf;

	public Actions(WeAreWolf instance){
		plugin = instance;
	}



	//Command /wolf help Executor
	public void help(CommandSender sender,int pagenumber){
		int line = 2;

		for(int i = 0; i < line;i++){
			m = "help_wolf_" + pagenumber +"_"+ i;
			msg = plugin.getConfig().getString(m);
			plugin.message(sender,null,msg,null,null,null,null);
		}
	}

	//Command /wolf rule Executor
	public void rule(CommandSender sender,int pagenumber){
		int line = 2;

		for(int i = 0; i < line;i++){
			m = "help_wolf_" + pagenumber +"_"+ i;
			msg = plugin.getConfig().getString(m);
			plugin.message(sender,null,msg,null,null,null,null);
		}
	}

	//Command /wolf gameCreate Executor
	public void gameCreate(CommandSender sender){
		if(sender.hasPermission("wearewolf.GM")){
			if(wolfPlayer.setWolfGameMasterPlayer(sender.getName())){
				msg = "&6" + sender.getName() + "は人狼ゲームのゲームマスターになりました。";
				plugin.message(sender,null,msg,null,null,null,null);
			}else{
				msg = "&6" + sender.getName() + "はすでにゲームマスターになっています！";
				plugin.message(sender,null,msg,null,null,null,null);
			}
		}else{
			msg = "you don't have permission";
			Messenger.message(sender,null,msg,null,null,null,null);
		}
	}

	//Command /wolf join Executor
	public void join(CommandSender sender){
		if(wolfPlayer.setWolfPlayer(sender.getName())){
			msg = "&6" + sender.getName() + "は人狼に参加しました。";
			plugin.message(sender,null,msg,null,null,null,null);
		}else{
			msg = "&6" + sender.getName() + "はすでに参加しています！";
			plugin.message(sender,null,msg,null,null,null,null);
		}
	}

	//Command /wolf leave Executor
	public void leave(CommandSender sender){
		if(wolfPlayer.removeWolfPlayer(sender.getName())){
			msg= "&6" + sender.getName() + "は人狼ゲームから退出しました。";
			plugin.message(sender,null,msg,null,null,null,null);

		}else{
			msg = "&6" + sender.getName() + "はゲームに参加していません！";
			plugin.message(sender,null,msg,null,null,null,null);
		}
	}

	//Command /wolf kick Executor
	public void kick(CommandSender sender, String player){
		if(wolfPlayer.removeWolfPlayer(player)){
			//String型からplayer型への強制返還
			Player toPlayer = Bukkit.getPlayer(player);
			msg = "&6あなたは" + player + "を人狼ゲームからキックしました。";
			plugin.message(sender,null,msg,null,null,null,null);
			m = "&6あなたは" + sender.getName() + "により人狼ゲームからキックされました。";
			plugin.message(null,toPlayer,m,null,null,null,null);
		}else{
			msg = "&6" + sender.getName() + "はゲームに参加していません！";
			plugin.message(sender,null,msg,null,null,null,null);
		}

	}

	//Command /wolf gameStart Executor
	public void gameStart(CommandSender sender,String[] args){

		int fail = 0;
		if(wolfPlayer.getWolfGameMasterPlayer().contains(sender.getName())){
			for(int i = 2; i < args.length;i++){
				if(!wolfPlayer.getJoinPlayerList().contains(args[i])){
					//wolfPlayer.removeWolfPlayer(args[i]);
					//startedPlayer.add(args[i]);
					fail++;

				}
			}
			if(fail == 0){//問題がないなら
				startedPlayer.clear();
				for(int i = 2;i<args.length;i++){

					if(wolfPlayer.getJoinPlayerList().contains(args[i])){
						wolfPlayer.removeWolfPlayer(args[i]);
						wolfPlayer.removeWolfGameMasterPlayer(args[i]);
						startedPlayer.add(args[i]);
					}
				}
				wolfPlayer.removeWolfPlayer(sender.getName());
				wolfPlayer.removeWolfGameMasterPlayer(sender.getName());
				startedPlayer.add(sender.getName());
				msg = "&6" + sender.getName() + "は人狼を開始しました。！";
				plugin.message(sender,null,msg,null,null,null,null);
				WereWolf = new WereWolfExecutor(startedPlayer);
				//msg = "&6" + sender.getName() + "は人狼を開始しました。！";
				//plugin.message(sender,null,msg,null,null,null,null);

			}else if(args.length >9){//人数多すぎる
				msg = "&6参加人数が多すぎます！GMをあわえて8人までです。";
				plugin.message(sender,null,msg,null,null,null,null);

			}else if(fail > 0 || args.length <10){//joinしていないプレイヤーを指定した場合
				msg = "&6" + "指定したプレイヤー"+ "はゲームに参加していません！";
				plugin.message(sender,null,msg,null,null,null,null);

			}else{

				//msg = "&6" + sender.getName() + "が人狼を開始しました。！";
				//plugin.message(sender,null,msg,null,null,null,null);
			}
		}else{//そもそもsenderがGMじゃない場合
			msg = "&6このコマンドはゲームマスターしか実行できません！";
			plugin.message(sender,null,msg,null,null,null,null);
		}
	}

	//Command /wolf vote Executor
	public void vote(CommandSender sender,String player){
		//WereWolfExecutor.setPlayer(sender.getName());
		if(WereWolfExecutor.isTurn() == false && WereWolfExecutor.isOnGame()){
			ONTurnNoon.voteCommand(sender,player);
		}else{
			msg = "このコマンドは人狼ゲームを進行しているプレイヤーのみが実行可能です";
			plugin.message(sender,null,msg,null,null,null,null);
		}

	}

	//Command /wolf target Executor
	public void target(CommandSender sender,String player){
		if(WereWolfExecutor.isTurn() && WereWolfExecutor.isOnGame()){
			ONTurnNight.targetCommand(sender,player);
		}else{
			msg = "このコマンドは人狼ゲームを進行しているプレイヤーのみが実行可能です";
			plugin.message(sender,null,msg,null,null,null,null);
		}

	}

	//Command /wolf over Executor
	public void over(CommandSender sender){


	}

	//Command /wolf whisper Executor
	public void whisper(CommandSender sender,String player,String message){
		msg = message;
		Player toPlayer = Bukkit.getPlayer(player);
		plugin.message(sender,toPlayer,msg,null,null,null,null);
	}

	public void joinPlayerList(CommandSender sender){
		//WolfPlayer wolfPlayer = new WolfPlayer(sender.getName());

		List<String> playerList;
		playerList = wolfPlayer.getJoinPlayerList();
		for(int i = 0;i<playerList.size();i++){
			msg = playerList.get(i);
			plugin.message(sender,null,msg,null,null,null,null);
		}
	}

	public void joinGameMasterList(CommandSender sender){
		//WolfPlayer wolfPlayer = new WolfPlayer(sender.getName());

		List<String> playerList;
		playerList = wolfPlayer.getWolfGameMasterPlayer();
		for(int i = 0;i<playerList.size();i++){
			msg = playerList.get(i);
			plugin.message(sender,null,msg,null,null,null,null);
		}
	}


	public void startedPlayerList(CommandSender sender){
		//WolfPlayer wolfPlayer = new WolfPlayer(sender.getName());

		List<String> playerList;
		playerList = startedPlayer;
		for(int i = 0;i<playerList.size();i++){
			msg = playerList.get(i);
			plugin.message(sender,null,msg,null,null,null,null);
		}
	}

	public void rolePlayerList(CommandSender sender){
		//WolfPlayer wolfPlayer = new WolfPlayer(sender.getName());
		if(Phantom.getCanChange()){//true＝まだ怪盗能力を使っていないなら
			msg = "怪盗能力使用前";
			plugin.message(sender,null,msg,null,null,null,null);
			Map<String, Role> roleMap = WereWolfExecutor.getPlayerRoleMap();
			for(String key : roleMap.keySet()){
				Role data = roleMap.get(key);
				msg = key + "の役職は" + data + "です";
				plugin.message(sender,null,msg,null,null,null,null);
			}
		}else{//false ＝怪盗能力使用後なら
			msg = "怪盗能力使用後";
			plugin.message(sender,null,msg,null,null,null,null);
			//class Phantom では変えないことにしたので上と同じ処理
			//Map<String, Role> roleMap = Phantom.getPlayerRoleMap();
			Map<String, Role> roleMap = WereWolfExecutor.getPlayerRoleMap();
			for(String key : roleMap.keySet()){
				Role data = roleMap.get(key);
				msg = key + "の役職は" + data + "です";
				plugin.message(sender,null,msg,null,null,null,null);

			}
		}
	}

	public void votePlayerList(CommandSender sender){
		//WolfPlayer wolfPlayer = new WolfPlayer(sender.getName());

		//msg = "投票結果を出力します";
		//plugin.message(sender,null,msg,null,null,null,null);
		Map<String, Integer> roleMap = ONTurnNoon.getPlayerVoteNum();
		for(String key : roleMap.keySet()){
			Integer data = roleMap.get(key);
			msg = key + "への投票数は" + data + "です";
			plugin.message(sender,null,msg,null,null,null,null);
		}
	}

}