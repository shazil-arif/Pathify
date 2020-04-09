package com.cas2XB3.group8;

import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.BeforeClass;
import org.junit.Test;

public class ParseCoordTests {
	
	@BeforeClass
	public static void setUpBeforeClass() {
		try {
			ParseCoord.readCoord();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetLineCount() {
		assertEquals(1070376, ParseCoord.getLineCount());
	}

}
