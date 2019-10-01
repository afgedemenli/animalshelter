package sheltermanager.bean;

public class SponsorFormBean {

	private String name;

	private String surname;

	private String phone;

	private String animals;

	public SponsorFormBean(String name, String surname, String phone, String animals) {
		this.name = name;
		this.surname = surname;
		this.phone = phone;
		this.animals = animals;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAnimals() {
		return animals;
	}

	public void setAnimals(String animals) {
		this.animals = animals;
	}
}
