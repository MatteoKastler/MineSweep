public class EmptyField extends Field {
	private int bombsCnt;
	public EmptyField(boolean flag, boolean open) {
		super(flag, open);
	}
	public String toString() {
		if(flag) return "F";
		if(open) {
			return bombsCnt==0?"_":Integer.toString(bombsCnt);
		}
		return " ";
	}
	public int getBombsCnt() {
		return bombsCnt;
	}
	public void setBombsCnt(int bombsCnt) {
		this.bombsCnt = bombsCnt;
	}

}
