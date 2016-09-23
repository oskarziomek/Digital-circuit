package testy;

import static org.junit.Assert.*;

import java.util.ArrayList;

import bramki.Bramka;
import logika.Frame;
import logika.ParserWyrazenia;
import logika.Wyliczalne;
import logika.Zmienna;

public class Test {
	
	@org.junit.Test
	public void zamianaNotacjiInfixowejNaPrefixowa() {
		Frame f = new Frame();
		assertEquals("+a*bc", f.infixNaPrefix("(a+(b*c)"));		
	}
	
	@org.junit.Test(expected = java.util.EmptyStackException.class)
	public void infixNaPrefixZlyFormat() {
		Frame f = new Frame();
		assertEquals("+a*bc", f.infixNaPrefix("(((a+(b*c)"));		
	}
	
	@org.junit.Test
	public void tworzenieZmiennej() {
		Zmienna z;
		z = new Zmienna('a');
		assertNotNull(z);		
	}
	
	@org.junit.Test
	public void ustawianieWartosciLogicznej() {
		Zmienna z = new Zmienna('x');
		z.setWartoscLogiczna(true);
		assertSame(true, z.Wylicz());		
	}
	
	@org.junit.Test
	public void pobieranieZmiennej() {
		Zmienna z;
		z = new Zmienna('a');
		assertEquals('a', z.getSymbol());		
	}
	
	@org.junit.Test
	public void ustalenieWartosci1wejsciaBramki() {
		Bramka br = new Bramka();
		Zmienna z = new Zmienna('x');
		Wyliczalne wl = z;
		br.setWejscie1(wl);
		assertSame(wl, br.getWejscie1());		
	}
	
	@org.junit.Test
	public void ustalenieWartosci2wejsciaBramki() {
		Bramka br = new Bramka();
		Zmienna z = new Zmienna('x');
		Wyliczalne wl = z;
		br.setWejscie2(wl);
		assertSame(wl, br.getWejscie2());		
	}
	
	@org.junit.Test
	public void czytaniePoprawnegoPliku() {
		Frame f = new Frame();
		assertNotNull(f.czytajWyrazenieZPliku("wyrazenie.txt"));	
	}
	
	@org.junit.Test
	public void czytanieBlednegoPliku() {
		Frame f = new Frame();
		assertNull(f.czytajWyrazenieZPliku("wyrazenie_blad.txt"));	
	}
	
	@org.junit.Test (expected = NullPointerException.class)
	public void czytanieNieistniejacegoPliku() {
		Frame f = new Frame();
		assertNotNull(f.czytajWyrazenieZPliku("wyrazenie_brak.txt"));	
	}
	
	
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
	
	@org.junit.Test
	public void zamianaFalse() {
		Frame f = new Frame();
		String str = "[true, false, false, false]";
		str = f.zamienFalse(str);		
		assertFalse(str.contains("false"));
	}
	
	@org.junit.Test
	public void zamianaTrue() {
		Frame f = new Frame();
		String str = "[true, false, false, false]";
		str = f.zamienTrue(str);		
		assertTrue(!str.contains("true"));
	}
	
	// Wykrywanie operacji

	@org.junit.Test
	public void wykrywanieOperacjiOr() {
		ParserWyrazenia pw = new ParserWyrazenia();
		assertTrue(pw.znakJestOperacja('+'));
	}

	@org.junit.Test
	public void wykrywanieOperacjiAnd() {
		ParserWyrazenia pw = new ParserWyrazenia();
		assertTrue(pw.znakJestOperacja('*'));
	}
	
	@org.junit.Test
	public void wykrywanieOperacjiNot() {
		ParserWyrazenia pw = new ParserWyrazenia();
		assertTrue(pw.znakJestOperacja('-'));
	}
		
	@org.junit.Test
	public void wykrywanieOperacjiXor() {
		ParserWyrazenia pw = new ParserWyrazenia();
		assertTrue(pw.znakJestOperacja('^'));
	}
	
	@org.junit.Test
	public void wykrywanieOperacjiNor() {
		ParserWyrazenia pw = new ParserWyrazenia();
		assertTrue(pw.znakJestOperacja('%'));
	}
	
	@org.junit.Test
	public void wykrywanieOperacjiNand() {
		ParserWyrazenia pw = new ParserWyrazenia();
		assertTrue(pw.znakJestOperacja('#'));
	}
	
	// Tworzenie wêz³ów
	
	@org.junit.Test
	public void tworzenieWezlaOr() {
		ParserWyrazenia pw = new ParserWyrazenia();
		Wyliczalne a = pw.parsuj("+ab");
		assertEquals("class bramki.BramkaOr", a.getClass().toString());		
	}
	
	@org.junit.Test
	public void tworzenieWezlaAnd() {
		ParserWyrazenia pw = new ParserWyrazenia();
		Wyliczalne a = pw.parsuj("*ab");
		assertEquals("class bramki.BramkaAnd", a.getClass().toString());			
	}
	
	@org.junit.Test
	public void tworzenieWezlaNot() {
		ParserWyrazenia pw = new ParserWyrazenia();
		Wyliczalne a = pw.parsuj("-a");
		assertEquals("class bramki.BramkaNot", a.getClass().toString());			
	}
	
	@org.junit.Test
	public void tworzenieWezlaXor() {
		ParserWyrazenia pw = new ParserWyrazenia();
		Wyliczalne a = pw.parsuj("^ab");
		assertEquals("class bramki.BramkaXor", a.getClass().toString());			
	}
	
	@org.junit.Test
	public void tworzenieWezlaNor() {
		ParserWyrazenia pw = new ParserWyrazenia();
		Wyliczalne a = pw.parsuj("%ab");
		assertEquals("class bramki.BramkaNor", a.getClass().toString());			
	}
	
	@org.junit.Test
	public void tworzenieWezlaNand() {
		ParserWyrazenia pw = new ParserWyrazenia();
		Wyliczalne a = pw.parsuj("#ab");
		assertEquals("class bramki.BramkaNand", a.getClass().toString());			
	}
		


}
