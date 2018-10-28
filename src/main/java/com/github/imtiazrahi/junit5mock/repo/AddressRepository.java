package com.github.imtiazrahi.junit5mock.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.github.imtiazrahi.junit5mock.bean.Address;

/**
 * 
 * @author Imtiaz Rahi
 * @since 2018-10-29
 */
@Repository
public interface AddressRepository extends CrudRepository<Address, Long> {

	public List<Address> findByPostcode(String postcode);

	public Optional<Address> findByPostcodeAndBuilding(String postcode, String building);
}
