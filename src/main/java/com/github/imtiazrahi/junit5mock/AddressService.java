package com.github.imtiazrahi.junit5mock;

import java.util.List;

import org.springframework.stereotype.Component;

import com.github.imtiazrahi.junit5mock.bean.Address;

/**
 * 
 * @author Imtiaz Rahi
 * @since 2018-10-29
 */
@Component
public interface AddressService {

	public List<Address> searchByPostcode(String postcode);

	public Address search(String postcode, String building);

	public int getSum(int a, int b);
}
