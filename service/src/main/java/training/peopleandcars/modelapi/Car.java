package training.peopleandcars.modelapi;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.*;


import javax.annotation.Generated;

/**
 * Car
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-08-22T19:22:29.543238700-05:00[America/Mexico_City]")
@Entity
@Table(schema = "registry", name ="car")
public class Car {

  @JsonProperty("vin")
  @Id
  private String vin;

  @JsonProperty("brand")
  @Column(name="brand")
  private String brand;

  @JsonProperty("color")
  @Column(name="color")
  private String color;

  @JsonProperty("model")
  @Column(name="model")
  private String model;

  @JsonProperty("year")
  @Column(name="year")
  private Integer year;

  public Car(){}
  public Car(String vin, String brand, String model, Integer year, String color) {
    this.vin = vin;
    this.brand = brand;
    this.color = color;
    this.model = model;
    this.year = year;
  }

  public Car vin(String vin) {
    this.vin = vin;
    return this;
  }

  /**
   * Get vin
   * @return vin
  */
  @NotNull 
  public String getVin() {
    return vin;
  }

  public void setVin(String vin) {
    this.vin = vin;
  }

  public Car brand(String brand) {
    this.brand = brand;
    return this;
  }

  /**
   * Get brand
   * @return brand
  */
  
  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public Car color(String color) {
    this.color = color;
    return this;
  }

  /**
   * Get color
   * @return color
  */
  
  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public Car model(String model) {
    this.model = model;
    return this;
  }

  /**
   * Get model
   * @return model
  */
  
  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public Car year(Integer year) {
    this.year = year;
    return this;
  }

  /**
   * Get year
   * @return year
  */
  
  public int getYear() {
    return year;
  }

  public void setYear(Integer year) {
    this.year = year;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Car car = (Car) o;
    return Objects.equals(this.vin, car.vin) &&
        Objects.equals(this.brand, car.brand) &&
        Objects.equals(this.color, car.color) &&
        Objects.equals(this.model, car.model) &&
        Objects.equals(this.year, car.year);
  }

  @Override
  public int hashCode() {
    return Objects.hash(vin, brand, color, model, year);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Car {\n");
    sb.append("    vin: ").append(toIndentedString(vin)).append("\n");
    sb.append("    brand: ").append(toIndentedString(brand)).append("\n");
    sb.append("    color: ").append(toIndentedString(color)).append("\n");
    sb.append("    model: ").append(toIndentedString(model)).append("\n");
    sb.append("    year: ").append(toIndentedString(year)).append("\n");
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

