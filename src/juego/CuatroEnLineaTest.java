package juego;

import static org.junit.Assert.*;

import org.junit.Test;

public class CuatroEnLineaTest {

	@Test
	public void testCuatroHorizontal() {
		CuatroEnLinea juego = new CuatroEnLinea(7, 7, "Santiago", "Josefina");
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(1);
		assertEquals("Santiago", juego.obtenerGanador());
	}

	
	@Test
	public void testCuatroEnDiagonal() {
		CuatroEnLinea juego = new CuatroEnLinea(7, 7, "Santiago", "Josefina");
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(2);
		juego.soltarFicha(3);
		juego.soltarFicha(3);
		juego.soltarFicha(4);
		juego.soltarFicha(3);
		juego.soltarFicha(6);
		juego.soltarFicha(4);
		juego.soltarFicha(4);
		juego.soltarFicha(4);
		assertEquals("Santiago", juego.obtenerGanador());
	}

	
	@Test
	public void testComprobarEmpate() {
		CuatroEnLinea juego = new CuatroEnLinea(4, 4, "Santiago", "Josefina");
		juego.soltarFicha(1);
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(2);
		juego.soltarFicha(3);
		juego.soltarFicha(4);
		juego.soltarFicha(3);
		juego.soltarFicha(4);
		juego.soltarFicha(4);
		juego.soltarFicha(3);
		juego.soltarFicha(4);
		juego.soltarFicha(3);
		assertEquals(null, juego.obtenerGanador());
	}


	@Test(expected = Error.class)
	public void testColumnaNoEstaEnElIntervalo() {
		CuatroEnLinea juego = new CuatroEnLinea(4, 4, "Santiago", "Josefina");
		juego.soltarFicha(5);
	}

	
	@Test(expected = Error.class)
	public void testFilaNoEstaEnElIntervalo() {
		CuatroEnLinea juego = new CuatroEnLinea(4, 4, "Santiago", "Josefina");
		juego.obtenerCasillero(5, 4);
		juego.obtenerCasillero(4, 5);
	}
	
	
	@Test
	public void testNoHayGanador() {
		CuatroEnLinea juego = new CuatroEnLinea(7, 7, "Santiago", "Josefina");
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(2);
		juego.soltarFicha(3);
		assertEquals(false, juego.hayGanador());
	}
	
	
	@Test (expected = Error.class)
	public void testCrearJuegoConMenosDeCuatroFilas() {
		CuatroEnLinea juego = new CuatroEnLinea(3, 7, "Santiago", "Josefina");
	}
	
	
	@Test (expected = Error.class)
	public void testCrearJuegoConMenosDeCuatroColumnas() {
		CuatroEnLinea juego = new CuatroEnLinea(5, 3, "Santiago", "Josefina");
	}
	
	
	@Test 
	public void testCuatroEnLineaDiagnalIzquierda() {
		CuatroEnLinea juego = new CuatroEnLinea(7, 4, "Josefina", "Santiago");

		juego.soltarFicha(1); // jose
		juego.soltarFicha(1); // santi
		juego.soltarFicha(1); // jose
		juego.soltarFicha(1); // santi
		juego.soltarFicha(1); // jose
		juego.soltarFicha(2); // santi
		juego.soltarFicha(2); // jose
		juego.soltarFicha(2); // santi
		juego.soltarFicha(3); // jose
		juego.soltarFicha(3); // santi
		juego.soltarFicha(1); // jose
		juego.soltarFicha(4); // santi
		
		
		assertEquals("Santiago", juego.obtenerGanador());
	}

	
	@Test 
	public void testCuatroEnLineaVertical() {
		CuatroEnLinea juego = new CuatroEnLinea(7, 4, "Josefina", "Santiago");

		juego.soltarFicha(1); // jose
		juego.soltarFicha(2); // santi
		juego.soltarFicha(1); // jose
		juego.soltarFicha(2); // santi
		juego.soltarFicha(1); // jose
		juego.soltarFicha(2); // santi
		juego.soltarFicha(1); // jose
		
		
		assertEquals("Josefina", juego.obtenerGanador());
	}
	
	
	@Test (expected = Error.class)
	public void testNoGanoPrimero() {
		CuatroEnLinea juego = new CuatroEnLinea(7, 4, "Josefina", "Santiago");

		juego.soltarFicha(1); // jose
		juego.soltarFicha(2); // santi
		juego.soltarFicha(1); // jose
		juego.soltarFicha(2); // santi
		juego.soltarFicha(1); // jose
		juego.soltarFicha(2); // santi
		juego.soltarFicha(1); // jose, ya gano.
		juego.soltarFicha(2); // santi
		
		
		assertEquals("Santiago", juego.obtenerGanador());;
	}	
	

}
