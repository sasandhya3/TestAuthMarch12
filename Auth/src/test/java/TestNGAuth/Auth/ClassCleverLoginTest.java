package TestNGAuth.Auth;

import java.io.IOException;
import org.testng.annotations.Test;

public class ClassCleverLoginTest {

	@Test
	public void clasTest() throws InterruptedException, IOException {
		ClassLogin cl=new ClassLogin();
		cl.classCheck();
	}
	@Test
	public void cleverTest() throws InterruptedException, IOException {
		CleverLogin cl=new CleverLogin();
		cl.cleverCheck();
	}
}
