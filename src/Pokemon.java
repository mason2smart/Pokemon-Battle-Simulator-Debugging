
public class Pokemon {

	private String name;
	private String[] type;
	private String nature;
	private int level;
	private int[] baseStats = new int[6];
	private int[] IVs = new int[6];
	private int[] EVs = new int[6];
	private int[] stats = new int[6];
	private int[] currentStats;
	private int natureVal = 1; //temp int, until implement nature

	private String[] strMoves;
	private Move[] moves;
//	private int HP;
//	private int attack;
//	private int defense;
//	private int spAttack;
//	private int spDefense;
//	private int speed;

	public Pokemon(String nam, String[] t, String nat, int lv, int hp, int atk, int def, int spA, int spD, int spe, int hpIV, int atkIV, int defIV, int spAIV, int spDIV, int speIV, int hpEV, int atkEV, int defEV, int spAEV, int spDEV, int speEV){

		name = nam;
		type = t;
		nature = nat;
		level = lv;

		baseStats[0] = hp;
		baseStats[1] = atk;
		baseStats[2] = def;
		baseStats[3] = spA;
		baseStats[4] = spD;
		baseStats[5] = spe;

		IVs[0] = hpIV;
		IVs[1] = atkIV;
		IVs[2] = defIV;
		IVs[3] = spAIV;
		IVs[4] = spDIV;
		IVs[5] = speIV;

		EVs[0] = hpEV;
		EVs[1] = atkEV;
		EVs[2] = defEV;
		EVs[3] = spAEV;
		EVs[4] = spDEV;
		EVs[5] = speEV;


		calculateStats();
		currentStats = stats;
	}

	public void calculateStats(){

		stats[0] = (int) (((2 * baseStats[0] + IVs[0] + ((double) EVs[0]/4)) * level)/100) + level + 10;

		for(int i = 1; i < stats.length; i++){

			stats[i] = (int) ((((2 * baseStats[i] + IVs[i] + ((double) EVs[i]/4)) * level)/100) + 5) * natureVal;
		}


	}

	public void calculate(){


	}

	public String getName(){

		return name;

	}

	public String[] getType(){

		return type;

	}

	public String getNature(){

		return nature;
	}

	public int getLevel(){

		return level;

	}

	public int getHP(){

		return stats[0];

	}

	public int getAttack(){

		return stats[1];

	}

	public int getDefense(){

		return stats[2];

	}

	public int getSpAttack(){

		return stats[3];

	}

	public int getSpDefense(){

		return stats[4];

	}

	public int getSpeed(){

		return stats[5];

	}

	public int getCurrentHP(){

		return currentStats[0];

	}

	public int getCurrentAtk(){

		return currentStats[1];

	}

	public int getCurrentDef(){

		return currentStats[2];

	}

	public int getCurrentSpAtk(){

		return currentStats[3];

	}

	public int getCurrentSpDef(){

		return currentStats[4];

	}

	public int getCurrentSpe(){

		return currentStats[5];

	}

	public void setCurrentHP(int currHP){

		currentStats[0] = currHP;
	}

	public void setMoves(String[] inMoves){

		strMoves = inMoves;
	}

	public void statsToString(){

		for(int i : stats){
		System.out.print(i + " ");}
		System.out.println();
	}
}
