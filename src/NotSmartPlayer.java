public class NotSmartPlayer {
	PlayerLists p;

	public int[] col= new int[10];
	public int total=0;

	public NotSmartPlayer(){
		p = new PlayerLists();
	}

	public int move(Chip[] chip) {
		int move = 9;
		move = p.move(chip);
		return move;
	}
}