package com.gmail.doubledare1202;

import java.util.ArrayList;
import java.util.List;



public class WolfPlayer{
	private String wolfPlayer = "aaa";
	private Role role;
	private Team team;
	//List<データ型> リストの名前 = new ArrayList<データ型>();
	private List<String> wolfJoinPlayer = new ArrayList<String>();
	private List<String> wolfGameMasterPlayer = new ArrayList<String>();
	public WolfPlayer(String wolfPlayer){
		this.wolfPlayer = wolfPlayer;
		//リストへの追加
		//リストの名前.add( データ );
		wolfJoinPlayer.add(wolfPlayer);
	}

	public WolfPlayer(){

	}

	public Role getRole(){
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}

	public String getWolfPlayer() {
		return wolfPlayer;
	}

	public boolean setWolfPlayer(String wolfPlayer) {
		//プレイヤーの名前をリストにぶち込むメソッド
		//重複しないようにしてます^^
		if(!wolfJoinPlayer.contains(wolfPlayer)){
			wolfJoinPlayer.add(wolfPlayer);
			return true;
		}else{
			return false;
		}
	}

	public boolean removeWolfPlayer(String wolfPlayer){
		//プレイヤーの名前をリストから省きます。
		if(wolfJoinPlayer.contains(wolfPlayer)){
			wolfJoinPlayer.remove(wolfPlayer);
			return true;
		}else{
			return false;
		}
	}

	public List<String> getJoinPlayerList(){
		//参加しているプレイヤーのリストを返します。
		return wolfJoinPlayer;
	}

	//ゲームマスターの処理
	//
	public boolean setWolfGameMasterPlayer(String wolfPlayer) {
		//プレイヤーの名前をリストにぶち込むメソッド
		//重複しないようにしてます^^
		if(!wolfGameMasterPlayer.contains(wolfPlayer)){
			wolfGameMasterPlayer.add(wolfPlayer);
			return true;
		}else{
			return false;
		}
	}

	public boolean removeWolfGameMasterPlayer(String wolfPlayer){
		//ゲームマスターの名前をリストから省きます。
		if(wolfGameMasterPlayer.contains(wolfPlayer)){
			wolfGameMasterPlayer.remove(wolfPlayer);
			return true;
		}else{
			return false;
		}
	}
	public List<String> getWolfGameMasterPlayer() {
		//参加しているゲームマスターのリストを返します。
		return wolfGameMasterPlayer;
	}

	public void setWolfGameMasterPlayer(List<String> wolfGameMasterPlayer) {
		this.wolfGameMasterPlayer = wolfGameMasterPlayer;
	}
	
	
}
