package xyz.krstic.restservices.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Address {

	private String street;
	private String suite;
	private String city;
	private String zipcode;
	private Geolocation geo;
	
}