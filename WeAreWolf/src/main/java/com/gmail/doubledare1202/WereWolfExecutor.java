package com.gmail.doubledare1202;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gmail.doubledare1202.oneNight.ONTurnNight;
import com.gmail.doubledare1202.oneNight.OneNight;

public class WereWolfExecutor {
	private static List<String> playerList = new ArrayList<String>();
	private static int day;
	private static boolean turn;//trueが夜 falseが昼
	private static boolean onGame;
	private static Role role;
	private static Map<String,Role> playerRoleMap = new HashMap<String,Role>();

	//Seerと怪盗になった人の人数を数える
	//夜のターン能力使用の終了判定に使う←お亡くなりになりました
	//private static int countSEandPT = 0;

	// /wr targetを打ったプレイヤーをカウントする
	// 夜のターンtargetを打つことを必須とする
	public static int countEndTargetPlayer = 0;


	public WereWolfExecutor(){

	}
	public WereWolfExecutor(List<String> playerList){
		this.setPlayerList(playerList);
		initWereWolf();
	}
	private void initWereWolf() {
		setDay(1);
		setTurn(true);
		setOnGame(true);
		countEndTargetPlayer = 0;
		//String プレイヤーの名前,Role各役職、をHashMapにして保存
		Role[] a = OneNight.getShuffledOneNightRole(playerList.size());
		for(int i = 0; i < playerList.size() ; i++){
			playerRoleMap.put(playerList.get(i),a[i]);
		}

		ONTurnNight.startNightTurn();
	}

	public static List<String> getPlayerList() {
		return playerList;
	}
	public void setPlayerList(List<String> playerList) {
		WereWolfExecutor.playerList = playerList;
	}

	public boolean setPlayer(String addPlayer) {
		//人狼中プレイヤーの名前をリストにぶち込むメソッド
		//重複しないようにしてます^^
		if(!playerList.contains(addPlayer)){
			playerList.add(addPlayer);
			return true;
		}else{
			return false;
		}
	}

	public boolean removePlayer(String removePlayer){
		//人狼中のプレイヤーの名前をリストから省きます。
		if(playerList.contains(removePlayer)){
			playerList.remove(removePlayer);
			return true;
		}else{
			return false;
		}
	}
	public static int getDay() {
		return day;
	}
	public static void setDay(int day) {
		WereWolfExecutor.day = day;
	}
	public static boolean isTurn() {
		return turn;
	}
	public static void setTurn(boolean turn) {
		WereWolfExecutor.turn = turn;
	}
	public static boolean isOnGame() {
		return onGame;
	}
	public static void setOnGame(boolean onGame) {
		WereWolfExecutor.onGame = onGame;
	}
	public static Role getRole() {
		return role;
	}
	public static void setRole(Role role) {
		WereWolfExecutor.role = role;
	}
	public static Map<String,Role> getPlayerRoleMap() {
		return playerRoleMap;
	}
	public static void setPlayerRoleMap(Map<String,Role> playerRoleMap) {
		WereWolfExecutor.playerRoleMap = playerRoleMap;
	}
}
