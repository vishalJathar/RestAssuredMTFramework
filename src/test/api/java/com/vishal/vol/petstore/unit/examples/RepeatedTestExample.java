package com.vishal.vol.petstore.unit.examples;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;

import com.vishal.vol.petstore.TestTags;

@Tag(TestTags.UNIT_TESTS)
public class RepeatedTestExample {

	@RepeatedTest(3)
	void repeatedTest() {
		// ...
	}

}
