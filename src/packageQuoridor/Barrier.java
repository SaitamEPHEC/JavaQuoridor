package packageQuoridor;

public class Barrier {
	private int posX1;
	private int posX2;
	private int posY1;
	private int posY2;
	
	public Barrier() {
		super();
	}

	public Barrier(int posX1, int posX2, int posY1, int posY2) {
		super();
		this.posX1 = posX1;
		this.posX2 = posX2;
		this.posY1 = posY1;
		this.posY2 = posY2;
	}

	public int getPosX1() {
		return posX1;
	}

	public int getPosX2() {
		return posX2;
	}

	public int getPosY1() {
		return posY1;
	}

	public int getPosY2() {
		return posY2;
	}	
}
