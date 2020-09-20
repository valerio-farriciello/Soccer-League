package valerioFarriciello.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

/**
 * @author Valerio Farriciello
 * <p>Entity class Manager<br></p>
 *
 */
@Entity
public class Manager extends Person implements Comparable<Object>{
	@Id
	private String managerID;
	private String dateOfBirth;
	private int starRating;
	@Transient
	private Team team;
	
	public Manager(String managerID, Name name, String phone, String email, String dateOfBirth, int starRating) {
		super(name, phone, email);
		this.managerID = managerID;
		this.dateOfBirth = dateOfBirth;
		this.starRating = starRating;
		this.team = null;
	}
	
	//DEFAULT CONSTRUCTOR FOR JPA
	public Manager() {
		super(new Name("default-fname", "default-mname", "default-lname"), "default-phone", "default-email");		
		this.managerID = "default-ID";
		this.dateOfBirth = "default-DOB";
		this.starRating = -1;
		this.team = null;
	}

	
	

	public String getManagerID() {
		return managerID;
	}

	public void setManagerID(String managerID) {
		this.managerID = managerID;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public int getStarRating() {
		return starRating;
	}

	public void setStarRating(int starRating) {
		this.starRating = starRating;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	@Override
	public String toString() {
		String teamDetails = "Team: NO TEAM";
		if(this.team != null) {
			teamDetails = "Team: " + this.team.getName() + ".";
		}
		
		return this.getName().toString() + "- PhoneNumber: " + super.getPhone() + " - Email: " + super.getEmail() +" - DOB: " + this.dateOfBirth + ", Star rating: " +
	this.starRating + "/10." + teamDetails;
	}

	@Override
	public int compareTo(Object o) {
		return ((Manager)o).starRating - this.starRating;
	}

	
}
