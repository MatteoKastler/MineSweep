package mineSweep;

public class EmptyField extends Field {
	private int bombsCnt;
	public EmptyField(boolean flag, boolean open) {
		super(flag, open);
	}
	public String toString() {
		return bombsCnt==0?" ":Integer.toString(bombsCnt);
	}
	public int getBombsCnt() {
		return bombsCnt;
	}
	public void setBombsCnt(int bombsCnt) {
		this.bombsCnt = bombsCnt;
	}

}
