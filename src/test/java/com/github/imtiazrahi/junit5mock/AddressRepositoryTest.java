package com.github.imtiazrahi.junit5mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.github.imtiazrahi.junit5mock.bean.Address;
import com.github.imtiazrahi.junit5mock.repo.AddressRepository;

/**
 * https://www.baeldung.com/mockito-junit-5-extension
 * https://www.baeldung.com/junit-5-runwith
 * https://www.baeldung.com/junit-5-migration
 * @author Imtiaz Rahi
 * @since 2018-10-29
 */
@ExtendWith({ SpringExtension.class, MockitoExtension.class })
@MockitoSettings(strictness = Strictness.WARN)
@SpringBootTest
class AddressRepositoryTest {

	@Mock private AddressRepository repo;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		when(repo.findByPostcode("W148EZ")).thenReturn(createAddressList());
		when(repo.findById(1001L)).thenReturn(Optional.ofNullable(createAddressList().get(0)));
		when(repo.findById(1005L)).thenReturn(Optional.ofNullable(null));
	}

	@Test
	public void testFindByPostcodeSuccess() {
		List<Address> list = repo.findByPostcode("W148EZ");
		assertThat(list).hasSize(4);
	}

	@Test
	public void testFindByPostcode1001() {
		Optional<Address> ob = repo.findById(1001L);
		assertEquals("21", ob.get().getBuilding());
		assertThat(ob).containsSame(createAddressList().get(0));
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
