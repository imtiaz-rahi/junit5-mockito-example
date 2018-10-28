package com.github.imtiazrahi.junit5mock.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 
 * @author Imtiaz Rahi
 * @since 2018-10-29
 */
@Entity
@Data @NoArgsConstructor @RequiredArgsConstructor @Accessors(chain = true)
public class Address {
	@Id @GeneratedValue
	Long id;

	@NonNull
	String building, road;
	@NonNull
	String city, postcode, country;

	String area, district;
	String latitude, longitude;

}
