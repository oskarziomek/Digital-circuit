package testy;

import static org.junit.Assert.*;

import java.util.ArrayList;

import bramki.Bramka;
import bramki.BramkaAnd;
import bramki.BramkaNand;
import bramki.BramkaNor;
import bramki.BramkaNot;
import bramki.BramkaOr;
import bramki.BramkaXor;
import logika.Frame;
import logika.ParserWyrazenia;
import logika.Wyliczalne;
import logika.Zmienna;

public class Test {
	
	// 1
	@org.junit.Test
	public void zamianaNotacjiInfixowejNaPrefixowa() {
		Frame f = new Frame();
		assertEquals("+a*bc", f.infixNaPrefix("(a+(b*c)"));		
	}
	
	// 2
	@org.junit.Test
	public void dostosowanieFormatuStringaPrzechowujacegoZmienne () {
		Frame f = new Frame();
		ArrayList<Zmienna> zmienne = new ArrayList<Zmienna>();
		Zmienna a = new Zmienna ('a');
		Zmienna b = new Zmienna ('b');
		Zmienna c = new Zmienna ('c');
		zmienne.add(a);
		zmienne.add(b);
		zmienne.add(c);
		assertEquals("[a, b, c]", f.sprawdzZmienne(zmienne));
	}
	
	
	// 4
	@org.junit.Test
	public void wykrywanieOperacjiOr() {
		ParserWyrazenia pw = new ParserWyrazenia();
		assertTrue(pw.znakJestOperacja('+'));
	}


	// 5
	@org.junit.Test
	public void wykrywanieOperacjiAnd() {
		ParserWyrazenia pw = new ParserWyrazenia();
		assertTrue(pw.znakJestOperacja('*'));
	}
	
	// 6
	@org.junit.Test
	public void wykrywanieOperacjiNot() {
		ParserWyrazenia pw = new ParserWyrazenia();
		assertTrue(pw.znakJestOperacja('-'));
	}
		
	// 7
	@org.junit.Test
	public void wykrywanieOperacjiXor() {
		ParserWyrazenia pw = new ParserWyrazenia();
		assertTrue(pw.znakJestOperacja('^'));
	}
	
	// 8
	@org.junit.Test
	public void wykrywanieOperacjiNor() {
		ParserWyrazenia pw = new ParserWyrazenia();
		assertTrue(pw.znakJestOperacja('%'));
	}
	
	// 9
	@org.junit.Test
	public void wykrywanieOperacjiNand() {
		ParserWyrazenia pw = new ParserWyrazenia();
		assertTrue(pw.znakJestOperacja('#'));
	}
	
	// 10
	@org.junit.Test
	public void tworzenieWezlaOr() {
		ParserWyrazenia pw = new ParserWyrazenia();
		Wyliczalne a = pw.parsuj("+ab");
		assertEquals("class bramki.BramkaOr", a.getClass().toString());		
	}
	
	// 11
	@org.junit.Test
	public void tworzenieWezlaAnd() {
		ParserWyrazenia pw = new ParserWyrazenia();
		Wyliczalne a = pw.parsuj("*ab");
		assertEquals("class bramki.BramkaAnd", a.getClass().toString());			
	}
	
	// 12
	@org.junit.Test
	public void tworzenieWezlaNot() {
		ParserWyrazenia pw = new ParserWyrazenia();
		Wyliczalne a = pw.parsuj("-a");
		assertEquals("class bramki.BramkaNot", a.getClass().toString());			
	}
	
	// 13
	@org.junit.Test
	public void tworzenieWezlaXor() {
		ParserWyrazenia pw = new ParserWyrazenia();
		Wyliczalne a = pw.parsuj("^ab");
		assertEquals("class bramki.BramkaXor", a.getClass().toString());			
	}
	
	// 14
	@org.junit.Test
	public void tworzenieWezlaNor() {
		ParserWyrazenia pw = new ParserWyrazenia();
		Wyliczalne a = pw.parsuj("%ab");
		assertEquals("class bramki.BramkaNor", a.getClass().toString());			
	}
	
	// 15
	@org.junit.Test
	public void tworzenieWezlaNand() {
		ParserWyrazenia pw = new ParserWyrazenia();
		Wyliczalne a = pw.parsuj("#ab");
		assertEquals("class bramki.BramkaNand", a.getClass().toString());			
	}
		


}
