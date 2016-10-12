package server.home.model;

public class Lights {
	
	private String kichent;
	private int bedroom;
	
	
	public Lights(String kichent, int bedroom) {
		super();
		this.kichent = kichent;
		this.bedroom = bedroom;
	}


	public Lights() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getKichent() {
		return kichent;
	}


	public void setKichent(String kichent) {
		this.kichent = kichent;
	}


	public int getBedroom() {
		return bedroom;
	}


	public void setBedroom(int bedroom) {
		this.bedroom = bedroom;
	}


	@Override
	public String toString() {
		return "Lights [kichent=" + kichent + ", bedroom=" + bedroom + "]";
	}
	
	

}
