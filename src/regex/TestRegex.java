package regex;
import org.junit.Test;
public class TestRegex {
	@Test
	public void testRegex() {
		String content = "122";
		System.out.println(content.matches(RegexCommon.DIGITAL_NOT_NEGATIVE));
	}
}
