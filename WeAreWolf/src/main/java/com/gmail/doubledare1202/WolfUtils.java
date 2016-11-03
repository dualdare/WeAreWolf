package com.gmail.doubledare1202;

import org.bukkit.entity.Player;

public class WolfUtils {
	//便利なもの、どっかにstaticで書いておきたいものを置いておく

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
}
