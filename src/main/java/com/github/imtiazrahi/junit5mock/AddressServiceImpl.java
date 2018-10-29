package com.github.imtiazrahi.junit5mock;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.imtiazrahi.junit5mock.bean.Address;
import com.github.imtiazrahi.junit5mock.repo.AddressRepository;

/**
 * Default implementation of {@link AddressService} interface.
 * 
 * @author Imtiaz Rahi
 * @since 2018-10-28
 */
@Service
public class AddressServiceImpl implements AddressService {
	@Autowired private AddressRepository repo;

	@Override
	public List<Address> searchByPostcode(String postcode) {
		return repo.findByPostcode(postcode);
	}

	@Override
	public Address search(String postcode, String building) {
		return repo.findByPostcodeAndBuilding(postcode, building).orElseGet(null);
	}

	@Override
	public int getSum(int a, int b) {
		return a + b;
	}
}
