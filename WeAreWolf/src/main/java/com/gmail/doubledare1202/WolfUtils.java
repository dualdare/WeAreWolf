package com.gmail.doubledare1202;

import org.bukkit.entity.Player;

public class WolfUtils {
	//便利なもの、どっかにstaticで書いておきたいものを置いておく
	private WeAreWolf plugin;

	public WolfUtils(WeAreWolf weAreWolf) {
		plugin = weAreWolf;
	}

	//IPアドレス一致判定
	public static boolean equalsIpAddress(Player p1 ,Player p2){
		if(p1.getAddress().getAddress().getHostAddress().equals(
				p2.getAddress().getAddress().getHostAddress())){
			return true;
		}else{
			return false;
		}
	}

	//playerがオンラインかどうか
	public static boolean playerOnline(Player player){
		if(player.isOnline()){
			return true;
		}else{
			return false;
		}
	}

	//役職の名前を日本語にします
	public static String roleToJapanese(Role role){
		if(role == Role.WEREWOLF){
			return "&c人狼&f";
		}else if(role == Role.PHANTOM){
			return "&d怪盗&f";
		}else if(role == Role.SEER){
			return "&b占い師&f";
		}else if(role == Role.VILLAGER){
			return "&a村人&f";
		}else{
			return null;
		}
	}

	/*
	public boolean getLang(){
		if(plugin.getConfig().getString("language") == "japanese"){
			return true;
		}
		return false;
	}


	public static String separateLang(String path){
		if(getLang()){
			String msg = WeAreWolf.japanese.getString(path);
			return msg;
		}else{
			return null;
		}
	}
	*/

}
