package com.gmail.doubledare1202.oneNight;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bukkit.command.CommandSender;

import com.gmail.doubledare1202.Messenger;
import com.gmail.doubledare1202.Role;
import com.gmail.doubledare1202.WeAreWolf;
import com.gmail.doubledare1202.WereWolfExecutor;

public class ONTurnNight {
	private static Map<String,Role> playerRoleMap = WereWolfExecutor.getPlayerRoleMap();
	//private static int countSEandPT = 0;
	public static List<String> WereWolfList = new ArrayList<String>();

	private ONTurnNight(){

	}

	public static void startNightTurn() {
		//ONTurnNight.playerRoleMap = WereWolfExecutor.getPlayerRoleMap();
		//人狼同市を知るときに使うリスト、最初に実装しないと間に合わない
		for(String key : playerRoleMap.keySet()){
			Role data = playerRoleMap.get(key);
			if(data == Role.WEREWOLF){
				WereWolfList.add(key);
			}
		}
		for(String key : playerRoleMap.keySet()){
			Role data = playerRoleMap.get(key);
			if(data == Role.WEREWOLF){
				ONWereWolf.nightTurnWolf(key);
			}else if(data == Role.PHANTOM){
				ONPhantom.nightTurnPhantom(key);
			}else if(data == Role.SEER){
				ONSeer.nightTurnSeer(key);
			}else if(data == Role.VILLAGER){
				ONVillager.nightTurnVillager(key);
			}
		}

	}

	public static void targetCommand(CommandSender sender , String player){
		//ONTurnNight.playerRoleMap = WereWolfExecutor.getPlayerRoleMap();
		if(WereWolfExecutor.isTurn() && WereWolfExecutor.getDay() == 1 && WereWolfExecutor.isOnGame()){
			for(String key : playerRoleMap.keySet()){
				//Messenger.message(sender, null, key, null, null, null, null);
				Role data = playerRoleMap.get(key);
				//if(key == sender.getName()){
				if(key.contentEquals(sender.getName())){
					if(data == Role.WEREWOLF){
						//				WereWolf.nightTurnWolf(key);
						ONWereWolf.targetPlayer(sender);
						//Messenger.message(sender, null, "target_ww", null, null, null, null);
					}else if(data == Role.PHANTOM){
						ONPhantom.changePlayer(sender,player);
						//Messenger.message(sender, null, "target_phantom", null, null, null, null);
					}else if(data == Role.SEER){
						ONSeer.divinePlayer(sender, player);
						//Messenger.message(sender, null, "target_Seer", null, null, null, null);
					}else if(data == Role.VILLAGER){
						ONVillager.targetPlayer();
						//Messenger.message(sender, null, "target_villager", null, null, null, null);
					}
				}else{

					//String msg = "%logo&6あなたには役職がありません、";
					//Messenger.message(sender, null, msg, null, null, null, null);

				}
				/*
				String msg = "targetコマンドを実行します！";
				Messenger.message(sender, null, msg, null, null, null, null);
				 */
			}
		}else{
			//String msg = "%logo&eこのコマンド人狼に参加しているプレイヤーのみが実行できます。";
			String path = "wolf_common_nogs";
			String msg = WeAreWolf.japanese.getString(path);
			Messenger.message(sender, null, msg, null, null, null, null);
		}
	}

	public static void clear(){
		WereWolfList.clear();
	}
}
