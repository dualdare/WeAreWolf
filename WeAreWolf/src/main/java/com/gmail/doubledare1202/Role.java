package com.gmail.doubledare1202;

public enum Role {
	BODYGUARD(

	Team.VILLAGER, Species.HUMAN), FREEMASON(

	Team.VILLAGER, Species.HUMAN), MEDIUM(

	Team.VILLAGER, Species.HUMAN), POSSESSED(

	Team.WEREWOLF, Species.HUMAN), SEER(

	Team.VILLAGER, Species.HUMAN), VILLAGER(

	Team.VILLAGER, Species.HUMAN), WEREWOLF(

	Team.WEREWOLF, Species.WEREWOLF), PHANTOM(Team.VILLAGER, Species.HUMAN);

	private Team teamType;
	private Species species;

	private Role(Team teamType, Species species) {
		this.teamType = teamType;
		this.species = species;
	}

	public Team getTeam() {
		return this.teamType;
	}

	public Species getSpecies() {
		return this.species;
	}
}
