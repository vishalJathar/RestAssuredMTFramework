package com.vishal.vol.petstore.unit.examples;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.temporal.ChronoUnit;
import java.util.EnumSet;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import com.vishal.vol.petstore.TestTags;

@Tag(TestTags.UNIT_TESTS)
public class EnumSourceExample {

	@ParameterizedTest
	@EnumSource(names = { "DAYS", "HOURS" })
	void testWithEnumSourceInclude(ChronoUnit unit) {
		assertTrue(EnumSet.of(ChronoUnit.DAYS, ChronoUnit.HOURS).contains(unit));
	}

}
