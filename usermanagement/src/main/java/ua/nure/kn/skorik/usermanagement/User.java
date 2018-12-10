package ua.nure.kn.skorik.usermanagement;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4918621917952737269L;
	private Long id;
	private String firstName;
	private String lastName;
	private Date dateOfBirth;

	public User() {
	}

	/**
	 * Class constructor specifying User's id, first name, last name and date of
	 * birth
	 */
	public User(Long id, String firstName, String lastName, Date dateOfBirth) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
	}

	public User(String firstName, String lastName, Date dateOfBirth) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * Returns User's full name.
	 * 
	 * @return the string in the form of "lastName, firstName"
	 */
	public String getFullName() {
		StringBuilder builder = new StringBuilder();
		builder.append(lastName);
		builder.append(", ");
		builder.append(firstName);
		return builder.toString();
	}

	/**
	 * Returns User's age.
	 * 
	 * @return int User's age, which is computed according to the formula
	 *         current_year-year_of_birth
	 */
	public int getAge() {
		Calendar calendar = Calendar.getInstance();
		int currentYear = calendar.get(Calendar.YEAR);
		int currentMonth = calendar.get(Calendar.MONTH);
		int currentDay = calendar.get(Calendar.DAY_OF_MONTH);

		calendar.setTime(getDateOfBirth());
		int yearOfBirth = calendar.get(Calendar.YEAR);
		int monthOfBirth = calendar.get(Calendar.MONTH);
		int dayOfBirth = calendar.get(Calendar.DAY_OF_MONTH);

		int age = currentYear - yearOfBirth;

		if (currentMonth < monthOfBirth || (currentMonth == monthOfBirth && currentDay < dayOfBirth)) {
			age = age - 1;
		}

		return age;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		if (this.getId() == null && ((User) obj).getId() == null) {
			return true;
		}
		return this.getId().equals(((User) obj).getId());
	}

	@Override
	public int hashCode() {
		if (this.getId() == null) {
			return 0;
		}
		return this.getId().hashCode();
	}

}
