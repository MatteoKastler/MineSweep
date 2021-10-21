package mineSweep;

public class Field {
	private boolean flag, open;
		
	
	public Field(boolean flag, boolean open) {
		this.flag = flag;
		this.open = open;
	}


	public boolean isFlag() {
		return flag;
	}


	public void setFlag(boolean flag) {
		this.flag = flag;
	}


	public boolean isOpen() {
		return open;
	}


	public void setOpen(boolean open) {
		this.open = open;
	}
	

}
