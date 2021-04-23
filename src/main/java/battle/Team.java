package battle;

import java.util.ArrayList;

public class Team {

	private ArrayList<Pokemon> pokemonTeam;

	public Team(){
		pokemonTeam = new ArrayList<Pokemon>();
	}

	public void addPokemon(Pokemon p){

		if(pokemonTeam.size() < 6){
			pokemonTeam.add(p);
		}else {
			System.out.println("Full party of 6!");
		}
	}

	public Pokemon getPokemon(int i){

		return pokemonTeam.get(i);
	}

	public void swapPokemon(){



	}

	public void removePokemon(){



	}

	public int nonFaintedTeam(){

		int nonFaintedCnt = 0;
		for(Pokemon p : pokemonTeam){
			if(p.getState() == 1){
				nonFaintedCnt++;
			}
		}
		return nonFaintedCnt;

	}

	public boolean isFaintedTeam(){

		boolean isFainted = true;

		for(Pokemon p : pokemonTeam){
			//System.out.println(p.getState() == 1);
			if(p.getState() == 1){
				isFainted = false;
			}
			//System.out.println(isFainted + " " + p.getName());
		}

		return isFainted;
	}

	public String toString(){

		String str = "";

		for(int i = 0; i < pokemonTeam.size(); i++){
			str = str + " (" + i + ") " + pokemonTeam.get(i).getName() + " ";
		}
		return str;
	}


}
