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
		List<Address> list = createAddressList();
		when(service.searchByPostcode("W148EZ")).thenReturn(list);
		when(service.searchByPostcode("W10 0HN")).thenReturn(list.subList(0, 3));
		when(service.getSum(6, 4)).then(invocation -> Integer.valueOf("10"));
	}

	@Test
	void testSearchByPostcode() {
		List<Address> list = service.searchByPostcode("W148EZ");
		assertThat(list).hasSize(4);
		assertThat(list).contains(new Address("21", "Russell Gardens", "London", "W14 8EZ", "UK"), atIndex(0));
		Address ob = list.get(2);
		assertAll("AddressCheck",
				() -> assertEquals("Russell Gardens", ob.getRoad()),
				() -> assertEquals("9a", ob.getBuilding())
				);
	}

	@Test
	void testSearchByPostcodeW100HN() {
		List<Address> list = service.searchByPostcode("W10 0HN");
		assertThat(list).hasSize(3);
		assertThat(list).contains(new Address("21", "Russell Gardens", "London", "W14 8EZ", "UK"), atIndex(0));
//		Address ob = list.get(1);
//		assertAll("AddressCheck",
//				() -> assertEquals("Russell Gardens", ob.getRoad()),
//				() -> assertEquals("9a", ob.getBuilding())
//				);
	}

	@Test
	void testSearchByWrongPostcode() {
		List<Address> list = service.searchByPostcode("NOTFOUND");
		assertThat(list).hasSize(0);
	}

	@Test
	void testSearch() {
		assertTrue(true);
	}

	@Test
	void testSum() {
		assertEquals(10, service.getSum(7, 3));
	}

	private List<Address> createAddressList() {
		return Arrays.asList(
				new Address("21", "Russell Gardens", "London", "W14 8EZ", "UK").setId(1001L),
				new Address("8", "Russell Gardens", "London", "W14 8EZ", "UK").setId(1002L),
				new Address("9a", "Russell Gardens", "London", "W14 8EZ", "UK").setId(1003L),
				new Address("9b", "Russell Gardens", "London", "W14 8EZ", "UK").setId(1004L)
				);
	}
}
