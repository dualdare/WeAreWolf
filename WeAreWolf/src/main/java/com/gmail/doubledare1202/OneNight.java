package com.gmail.doubledare1202;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class OneNight {
	//Three(Role.WEREWOLF,Role.WEREWOLF,Role.VILLAGER,Role.SEER,Role.PHANTOM),
	//FOUR(Role.WEREWOLF);

	private static final Role[][] oneNightRole =
		{{Role.WEREWOLF,Role.WEREWOLF,Role.VILLAGER,Role.PHANTOM,Role.SEER},
		{Role.WEREWOLF,Role.WEREWOLF,Role.VILLAGER,Role.VILLAGER,Role.PHANTOM,Role.SEER},
		{Role.WEREWOLF,Role.WEREWOLF,Role.VILLAGER,Role.VILLAGER,Role.VILLAGER,
			Role.PHANTOM,Role.PHANTOM},
		{Role.WEREWOLF,Role.WEREWOLF,Role.VILLAGER,Role.VILLAGER,Role.VILLAGER,Role.VILLAGER,
				Role.SEER,Role.PHANTOM},
		{null}};

	public static Role[][] getOnenightrole() {
		return oneNightRole;
	}
	//人数numによってRoleをそのまま返す
	public static Role[] getOneNightrle(int num){
		if(num == 3){
			Role[] roles1 = oneNightRole[0];
			return roles1;
		}else if(num == 4){
			Role[] roles2 = oneNightRole[1];
			return roles2;
		}else if(num == 5){
			Role[] roles3 = oneNightRole[2];
			return roles3;
		}else if(6 <= num || num <= 8){
			Role[] roles4 = oneNightRole[3];
			return roles4;
		}else{
			Role[] rolse5 = oneNightRole[4];
			return rolse5;
		}
	}
	//人数numによってRoleをシャッフルして返す。
	public static Role[] getShuffledOneNightRole(int num){
		Role[] role = oneNightRole[num - 3];
		List<Role> list = Arrays.asList(role);
		Collections.shuffle(list);
		role = (Role[])list.toArray(new Role[0]);
		return role;

	}
}
		/*//Role[] roles;
		switch(num){
		case 3:
			Role[] roles1 = oneNightRole[1];
			break;
		case 4:
			Role[] roles2 = oneNightRole[2];
			break;
		case 5:
			Role[] roles3 = oneNightRole[3];
			break;
		case 6:
			Role[] roles4 = oneNightRole[4];
			break;
		case 7:
			Role[] roles5 = oneNightRole[4];
			break;
		case 8:
			Role[] roles6 = oneNightRole[4];
			break;
		default:

			break;


		}*/
		//return roles;


	//private static final List<Role> num3 =


