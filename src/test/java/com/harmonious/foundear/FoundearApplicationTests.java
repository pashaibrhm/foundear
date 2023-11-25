package com.harmonious.foundear;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.modulith.core.ApplicationModules;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class FoundearApplicationTests {

	ApplicationModules modules = ApplicationModules.of(FoundearApplication.class);

	@Test
	void contextLoads() {
		assertThat(modules).isNotNull();
	}

	@Test
	void shouldBeCompliant() {
		modules.verify();
	}

}
