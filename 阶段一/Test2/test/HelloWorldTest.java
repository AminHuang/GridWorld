import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class HelloWorldTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetStr() {
		HelloWorld he = new HelloWorld();
		he.setStr();
		assertEquals("hello world!",he.getStr());
		//fail("Not yet implemented");
	}

}
