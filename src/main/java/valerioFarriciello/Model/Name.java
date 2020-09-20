package valerioFarriciello.Model;

import javax.persistence.Embeddable;

/**
 * @author Valerio Farriciello
 * <p>Class used to store the full name of Manager and Player<br></p>
 *
 */
@Embeddable
public class Name implements Comparable<Object>{

	private String firstName;
	private String middleName;
	private String lastName;
	
	

	public Name(String firstName, String middleName, String lastName) {
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
	}

	//DEFAULT CONSTRUCTOR FOR JPA
	public Name() {
		this.firstName = "default-fname";
		this.middleName = "default-mname";
		this.lastName = "default-lname";
	}
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Override
	public String toString() {
		if(this.middleName != null) {
			return this.firstName + " " + this.middleName + " " + this.lastName;
		}
		return this.firstName + " " + this.lastName;
	}

	/**
	 *<p>It checks each single attribute starting from the first name to the last name. I'm using the compareTo<br>
	 *method that is already present in the String class</p>
	 */
	@Override
	public int compareTo(Object o) {
		Name nameToCompare = (Name)o;
		//compare the first name
		return (this.firstName.compareTo(nameToCompare.firstName) != 0 ? this.firstName.compareTo(nameToCompare.firstName)  :
			//if the result is 0 then compare the middle name
			(this.middleName.compareTo(nameToCompare.middleName) != 0 ? this.middleName.compareTo(nameToCompare.middleName) :
				//if the result is still 0 then compare the last name
				(this.lastName.compareTo(nameToCompare.lastName))));
	}
	
}