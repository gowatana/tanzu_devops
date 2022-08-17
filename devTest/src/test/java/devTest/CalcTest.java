package devTest;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class CalcTest {

	private Calc calc = new Calc();
	
	@Test
	public void addTest() {
		assertThat(calc.add(1, 2), is(3));
		assertThat(calc.add(1, 0), is(1));
		assertThat(calc.add(0, -1), is(-1));
		assertThat(calc.add(0, 0), is(0));
		assertThat(calc.add(-1, -2), is(-3));
	}
	
	@Test
	public void minusTest() {
		assertThat(calc.minus(2, 1), is(1));
		assertThat(calc.minus(1, 0), is(1));
		assertThat(calc.minus(0, 5), is(-5));
		assertThat(calc.minus(0, 0), is(0));
		assertThat(calc.minus(-1, -2), is(1));
	}
	
	@Test
	public void multiTest() {
		assertThat(calc.multi(2, 1), is(2));
		assertThat(calc.multi(-2, 2), is(-4));
		assertThat(calc.multi(1, 0), is(0));
		assertThat(calc.multi(0, -1), is(0));
		assertThat(calc.multi(0, 0), is(0));
		assertThat(calc.multi(-2, -2), is(4));
	}
	
	@Test
	public void divTest() {
		try {
			assertThat(calc.division(4, 2), is(2));
			assertThat(calc.division(-4, 2), is(-2));
			assertThat(calc.division(-4, -2), is(2));
		}catch (Exception e) {
			// Exceptionは発生しないはず
			fail();
		}
	}
	
//	@Rule
//	public ExpectedException expectedException = ExpectedException.none();
	
	@Test
	public void divZeroTest() {
//		expectedException.expect(Exception.class);
//		expectedException.expectMessage("0除算は行えません");
		try {
			calc.multi(0, -2);
		}catch (Exception e) {
			assertThat(e.getMessage(), equalTo("0除算は行えません"));
		}
	}
}
