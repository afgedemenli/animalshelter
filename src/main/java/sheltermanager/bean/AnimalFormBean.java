package sheltermanager.bean;

public class AnimalFormBean {

	private String name;

	private Integer age;

	private Integer sponsor_id;

	private String species;

	public AnimalFormBean(String name, Integer age, Integer sponsor_id, String species) {
		this.name = name;
		this.age = age;
		this.sponsor_id = sponsor_id;
		this.species = species;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getSponsor_id() {
		return sponsor_id;
	}

	public void setSponsor_id(Integer sponsor_id) {
		this.sponsor_id = sponsor_id;
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}
}
