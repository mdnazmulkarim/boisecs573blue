package org.boisestate.petrinet;

public class Marking{
	
	private String name;               //M0
	private String placeSequence;     //(P1,P2,P3,P4,P5)
	private String tokensNoSequence;  //(1,1,0,0,0)
	private String precedenceTransitionName;
	
	
	public Marking(String name) {
		this.setName(name);
		this.setPlaceSequence("");
		this.setTokenSequence("");
		this.setPrecedenceTransitionName("");
	}


	public Marking(String name, String placeSeq ,String tokenSeq) {
		this.setName(name);
		this.setPlaceSequence(placeSeq);
		this.setTokenSequence(tokenSeq);
		this.setPrecedenceTransitionName("");
	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getTokenSequence() {
		return tokensNoSequence;
	}


	public void setTokenSequence(String details) {
		this.tokensNoSequence = details;
	}
	
	public String getPlaceSequence() {
		return placeSequence;
	}


	public void setPlaceSequence(String placeSequence) {
		this.placeSequence = placeSequence;
	}
	
	public String getPrecedenceTransitionName() {
		return precedenceTransitionName;
	}


	public void setPrecedenceTransitionName(String precedenceTransitionName) {
		this.precedenceTransitionName = precedenceTransitionName;
	}


	@Override
	public String toString()
	{
		return name+":"+placeSequence+":"+tokensNoSequence+":"+precedenceTransitionName;
		
	}
	
}