package juego;

import org.junit.Assert;
import org.junit.Test;

public class CuatroEnLineaTest {

	
	@Test
	public void testContarColumnas() {
		CuatroEnLinea juego = new CuatroEnLinea(5, 7, "Santiago", "Josefina");
		
		Assert.assertEquals(7, juego.contarColumnas());
	}
	
	@Test
	public void testContarFilas() {
		CuatroEnLinea juego = new CuatroEnLinea(5, 7, "Santiago", "Josefina");
		
		Assert.assertEquals(5, juego.contarFilas());
	}
	
	@Test
	public void testObtenerCasilleroVacio() {
		CuatroEnLinea juego = new CuatroEnLinea(5, 7, "Santiago", "Josefina");
		
		Assert.assertEquals(Casillero.VACIO, juego.obtenerCasillero(5, 4));
	}
	
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
		
		Assert.assertEquals("Santiago", juego.obtenerGanador());
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
		
		Assert.assertEquals("Santiago", juego.obtenerGanador());
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
		
		Assert.assertEquals(null, juego.obtenerGanador());
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
	}
	
	
	@Test
	public void testNoHayGanador() {
		CuatroEnLinea juego = new CuatroEnLinea(7, 7, "Santiago", "Josefina");
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(2);
		juego.soltarFicha(3);
		
		Assert.assertEquals(false, juego.hayGanador());
	}
	
	
	@Test (expected = Error.class)
	public void testCrearJuegoConMenosDeCuatroFilas() {
		new CuatroEnLinea(3, 7, "Santiago", "Josefina");
	}
	
	
	@Test (expected = Error.class)
	public void testCrearJuegoConMenosDeCuatroColumnas() {
		new CuatroEnLinea(5, 3, "Santiago", "Josefina");
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
		
		Assert.assertEquals("Santiago", juego.obtenerGanador());
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
		
		Assert.assertEquals("Josefina", juego.obtenerGanador());
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
		
		Assert.assertEquals("Santiago", juego.obtenerGanador());;
	}	
	

}
