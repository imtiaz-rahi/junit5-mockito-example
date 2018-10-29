package com.github.imtiazrahi.junit5mock;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Condition;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.github.imtiazrahi.junit5mock.bean.Address;
import com.github.imtiazrahi.junit5mock.repo.AddressRepository;

/**
 * http://www.vogella.com/tutorials/Mockito/article.html
 * https://www.baeldung.com/mockito-junit-5-extension
 * https://www.baeldung.com/junit-5-runwith
 * https://www.baeldung.com/junit-5-migration
 * https://www.baeldung.com/mockito-annotations
 * @author Imtiaz Rahi
 * @since 2018-10-29
 */
@ExtendWith({ SpringExtension.class, MockitoExtension.class })
@MockitoSettings(strictness = Strictness.WARN)
@SpringBootTest
class AddressRepositoryTest {

	@Mock private AddressRepository repo;
	private static final List<String> CITIES = Arrays.asList("London", "Ilford", "Dhaka", "Paris");
	public static final Condition<String> isLONDON = new Condition<>(CITIES::contains, "London");

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		List<Address> list = createAddressList();
		when(repo.findByPostcode("W148EZ")).thenReturn(list);
		when(repo.findById(1001L)).thenReturn(Optional.ofNullable(list.get(0)));
		when(repo.findById(1002L)).thenReturn(Optional.ofNullable(list.get(1)));
		when(repo.findById(1003L)).thenReturn(Optional.ofNullable(list.get(2)));
		when(repo.findById(1004L)).thenReturn(Optional.ofNullable(list.get(3)));
		when(repo.findById(1005L)).thenReturn(Optional.ofNullable(null));
	}

	@DisplayName("Parameterized test with long value source")
	@ParameterizedTest
	@ValueSource(longs = { 1001L, 1002L, 1003L, 1004L })
	public void findById(Long id) {
		assertEquals("Russell Gardens", repo.findById(id).get().getRoad(), () -> "Failed for id " + id);
	}

	@Test
	public void testFindByPostcodeSuccess() {
		List<Address> list = repo.findByPostcode("W148EZ");
		assertThat(list).hasSize(4);
	}

	/* https://github.com/joel-costigliola/assertj-examples/blob/master/assertions-examples/src/test/java/org/assertj/examples/OptionalAssertionsExamples.java */
	@Test
	public void testFindByPostcode1001() {
		Optional<Address> ob = repo.findById(1001L);
		assertEquals("21", ob.get().getBuilding());
		assertThat(ob).isPresent()
		.containsInstanceOf(Address.class)
		.hasValueSatisfying(ad -> {
			assertThat(ad.getCountry()).isEqualTo("UK");
			assertThat(ad.getCity()).isIn("London", "Dhaka");
			assertThat(ad.getCity()).is(anyOf(isLONDON));
		});
		verify(repo).findById(1001L);
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
