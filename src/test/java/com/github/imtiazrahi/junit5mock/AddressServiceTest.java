package com.github.imtiazrahi.junit5mock;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.github.imtiazrahi.junit5mock.bean.Address;

/**
 * https://www.baeldung.com/mockito-junit-5-extension
 * https://www.baeldung.com/junit-5-runwith
 * https://www.baeldung.com/junit-5-migration
 * @author Imtiaz Rahi
 * @since 2018-10-29
 */
@ExtendWith({ SpringExtension.class, MockitoExtension.class })
@ContextConfiguration(classes = { Junit5MockApplication.class })
@MockitoSettings(strictness = Strictness.WARN)
class AddressServiceTest {

	@Mock private AddressService service;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		when(service.searchByPostcode("W148EZ")).thenReturn(createAddressList());
	}

	@Test
	void testSearchByPostcode() {
		List<Address> list = service.searchByPostcode("W148EZ");
		assertThat(list).hasSize(4);
		assertThat(list).contains(new Address("21", "Russell Gardens", "London", "W14 8EZ", "UK"), atIndex(0));
	}

	@Test
	void testSearch() {
		assertTrue(true);
	}

	private List<Address> createAddressList() {
		return Arrays.asList(
				new Address("21", "Russell Gardens", "London", "W14 8EZ", "UK"),
				new Address("8", "Russell Gardens", "London", "W14 8EZ", "UK"),
				new Address("9a", "Russell Gardens", "London", "W14 8EZ", "UK"),
				new Address("9b", "Russell Gardens", "London", "W14 8EZ", "UK")
				);
	}
}
