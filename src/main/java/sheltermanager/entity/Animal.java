package sheltermanager.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "animal")
public class Animal {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Column
  private String name;

  @Column
  private String species;

  @Column
  private Integer age;

  @Column
  private Integer caretaker_id;

  @Column
  private Integer sponsor_id;

  public Animal() {
  }

  public Animal(String name, String species, Integer age) {
    this.name = name;
    this.species = species;
    this.age = age;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public String getSpecies() {
    return species;
  }

  public void setSpecies(String species) {
    this.species = species;
  }

  public Integer getCaretaker_id() {
    return caretaker_id;
  }

  public void setCaretaker_id(Integer caretaker_id) {
    this.caretaker_id = caretaker_id;
  }

  public Integer getSponsor_id() {
    return sponsor_id;
  }

  public void setSponsor_id(Integer sponsor_id) {
    this.sponsor_id = sponsor_id;
  }
}

