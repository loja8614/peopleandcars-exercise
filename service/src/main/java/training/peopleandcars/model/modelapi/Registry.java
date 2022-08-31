package training.peopleandcars.model.modelapi;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.annotation.Generated;
import javax.validation.constraints.NotNull;

/**
 * Registry
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-08-22T19:22:29.543238700-05:00[America/Mexico_City]")
public class Registry {

  @JsonProperty("id")
  private Integer id;

  @JsonProperty("People")
  private People people;

  @JsonProperty("Car")
  private Car car;

  public Registry(){}
  public Registry(Integer id, People people, Car car) {
    this.id = id;
    this.people = people;
    this.car = car;
  }

  public Registry id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  @Valid
  @NotNull
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Registry people(People people) {
    this.people = people;
    return this;
  }

  /**
   * Get people
   * @return people
  */
  @Valid
  @NotNull
  public People getPeople() {
    return people;
  }

  public void setPeople(People people) {
    this.people = people;
  }

  public Registry car(Car car) {
    this.car = car;
    return this;
  }

  /**
   * Get car
   * @return car
  */
  @Valid
  @NotNull
  public Car getCar() {
    return car;
  }

  public void setCar(Car car) {
    this.car = car;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Registry registry = (Registry) o;
    return Objects.equals(this.id, registry.id) &&
        Objects.equals(this.people, registry.people) &&
        Objects.equals(this.car, registry.car);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, people, car);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Registry {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    people: ").append(toIndentedString(people)).append("\n");
    sb.append("    car: ").append(toIndentedString(car)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

