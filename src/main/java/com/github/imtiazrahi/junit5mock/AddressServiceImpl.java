package com.github.imtiazrahi.junit5mock;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.imtiazrahi.junit5mock.bean.Address;
import com.github.imtiazrahi.junit5mock.repo.AddressRepository;

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

}
