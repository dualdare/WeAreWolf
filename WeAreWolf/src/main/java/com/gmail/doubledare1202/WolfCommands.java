package com.gmail.doubledare1202;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class WolfCommands implements CommandExecutor {
	private WeAreWolf plugin;
	private Actions actions;

	public WolfCommands(WeAreWolf instance,Actions actionsInstance){
		plugin = instance;
		actions = actionsInstance;
	}
	private String msg,m;



	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		if(args.length > 0 && args[0].equalsIgnoreCase("test")){
			if(args[1].equalsIgnoreCase("1")){
				//sucsess
				//introduction
				actions.help(sender,1);
			}else{
				plugin.message(sender,null,"elseにきたで",null,null,null,null);
			}
			return true;

		}else if(args.length > 0 && args[0].equalsIgnoreCase("help")){
			//sucess
			//help for sender
			int num = 2;
			for(int i = 0; i <= num;i++){
				m = "help_wolf_" + i;
				msg = plugin.getConfig().getString(m);
				plugin.message(sender,null,msg,null,null,null,null);
			}

			return true;

		}else if(args.length > 0 && args[0].equalsIgnoreCase("rule")){
			if(args.length > 1 && args[1].equalsIgnoreCase("OneNight")){
				actions.rule(sender, 1);

			}
			return true;
		}else if(args.length > 0 && (args[0].equalsIgnoreCase("gameCreate") || args[0].equalsIgnoreCase("gC"))){
			actions.gameCreate(sender);
			return true;

		}else if(args.length > 0 && (args[0].equalsIgnoreCase("join") || args[0].equalsIgnoreCase("j"))){
			//できた
			actions.join(sender);
			return true;

		}else if(args.length > 0 && args[0].equalsIgnoreCase("leave")){
			//できた
			actions.leave(sender);
			return true;

		}else if(args.length > 0 && args[0].equalsIgnoreCase("kick")){
			//できた
			actions.kick(sender,args[1]);
			return true;

		}else if(args.length > 0 && (args[0].equalsIgnoreCase("gameStart") || args[0].equalsIgnoreCase("gs"))){
			if(args.length < 2){
				msg = "ゲームルールを入力してください";
				plugin.message(sender,null,msg,null,null,null,null);
				return true;
			}else if(args.length < 4){
				msg = "参加者が足りません";
				plugin.message(sender,null,msg,null,null,null,null);
				return true;
			}else {
				actions.gameStart(sender,args);
				return true;
			}

		}else if(args.length > 0 && args[0].equalsIgnoreCase("vote")){
			actions.vote(sender,args[1]);
			return true;

		}else if(args.length > 0 && args[0].equalsIgnoreCase("target")){
			if(args.length < 2){
				msg = "能力の使用先のプレイヤーを入力してください";
				plugin.message(sender,null,msg,null,null,null,null);
			}else{
				actions.target(sender,args[1]);
			}
			return true;

		}else if(args.length > 0 && args[0].equalsIgnoreCase("over")){
			actions.over(sender);
			return true;
			//defeat

		}else if(args.length > 0 && args[0].equalsIgnoreCase("whisper")){
			//できた
			if(args.length == 1){
				msg = "ささやきをおくるプレイヤーを入力してください";
				plugin.message(sender,null,msg,null,null,null,null);
			}if(args.length > 1){

				actions.whisper(sender,args[1],args[2]);
				return true;
			}else{
				msg = "ささやきをするメッセージを入力してください";
				plugin.message(sender,null,msg,null,null,null,null);
				return true;
			}

			//
			//以下デバッグコマンドの予定
			//

		}else if(args.length > 0 && (args[0].equalsIgnoreCase("joinPlayerList") || args[0].equalsIgnoreCase("jPL"))){

			plugin.message(sender, null, "プレイヤーリストです", null,null,null,null);
			actions.joinPlayerList(sender);
			return true;

		}else if(args.length > 0 && (args[0].equalsIgnoreCase("joinGameMasterList") || args[0].equalsIgnoreCase("jGML"))){

			plugin.message(sender, null, "ゲームマスターリストです", null,null,null,null);
			actions.joinGameMasterList(sender);
			return true;

		}else if(args.length > 0 && (args[0].equalsIgnoreCase("startedPlayerList") || args[0].equalsIgnoreCase("sPL"))){

			plugin.message(sender, null, "ゲームを開始しているプレイヤーのリストです", null,null,null,null);
			actions.startedPlayerList(sender);
			return true;

		}else if(args.length > 0 && (args[0].equalsIgnoreCase("rolePlayerList") || args[0].equalsIgnoreCase("rPL"))){

			plugin.message(sender, null, "プレイヤーと役職のリストです", null,null,null,null);
			actions.rolePlayerList(sender);
			return true;


		}else if(args.length > 0 && (args[0].equalsIgnoreCase("votePlayerList") || args[0].equalsIgnoreCase("vPL"))){

			plugin.message(sender, null, "プレイヤ-と投票数のリストです", null,null,null,null);
			actions.votePlayerList(sender);
			return true;

		}else{

			//defeat
			//String msg = plugin.getConfig().getString("message");
			plugin.message(sender,null,"失敗",null,null,null,null);
			return false;
		}
	}
}

