package testy;

import static org.junit.Assert.*;

import java.lang.reflect.Method;

import bramki.Bramka;
import bramki.BramkaAnd;
import junit.framework.Assert;
import logika.Frame;

public class Test {

	@org.junit.Test
	public void testZamianyNotacjiInfixowejNaPrefixowa() {
		Frame f = new Frame();
		String wynik = f.infixNaPrefix("(a+(b*c)");
		assertEquals("+a*bc", wynik);		
	}
	

}
