
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BattleLoop b = new BattleLoop();
		b.startBattle();
		try{
		b.battle();
		}catch(Exception e){
			System.out.println("Bad input");
		}

	}

}
