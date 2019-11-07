package juego;

import static org.junit.Assert.*;

import org.junit.Test;

public class CuatroEnLineaTest {

	@Test
	public void CuatroHorizontal() {
		CuatroEnLinea juego = new CuatroEnLinea(7, 7, "Gian", "Mauro");
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(1);
		assertEquals("Gian", juego.obtenerGanador());
	}

	@Test
	public void CuatroEnDiagonal() {
		CuatroEnLinea juego = new CuatroEnLinea(7, 7, "Gian", "Mauro");
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
		assertEquals("Gian", juego.obtenerGanador());
	}

	@Test
	public void ComprobarEmpate() {
		CuatroEnLinea juego = new CuatroEnLinea(4, 4, "Gian", "Mauro");
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
	public void ColumnaNoEstaEnElIntervalo() {
		CuatroEnLinea juego = new CuatroEnLinea(4, 4, "Gian", "Mauro");
		juego.soltarFicha(5);
	}

	@Test(expected = Error.class)
	public void FilaNoEstaEnElIntervalo() {
		CuatroEnLinea juego = new CuatroEnLinea(4, 4, "Gian", "Mauro");
		juego.obtenerCasillero(5, 4);
		juego.obtenerCasillero(4, 5);
	}
	@Test
	public void test() {
		CuatroEnLinea juego = new CuatroEnLinea(7, 7, "Gian", "Mauro");
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(2);
		juego.soltarFicha(3);
		assertEquals(false, juego.hayGanador());
	}
	@Test (expected = Error.class)
	public void CrearJuegoConMenosDeCuatroFilasOColumnas() {
		CuatroEnLinea juego = new CuatroEnLinea(3, 7, "Gian", "Mauro");


	}
	@Test 
	public void cuatroEnLineaEnDiagnalIzquierda() {
		
	
		CuatroEnLinea juego = new CuatroEnLinea(7, 4, "Mauro", "Gian");

		juego.soltarFicha(1); // mauro
		juego.soltarFicha(1); // gian
		juego.soltarFicha(1); // mauro
		juego.soltarFicha(1); // gian
		juego.soltarFicha(1); // mauro
		juego.soltarFicha(2); // gian
		juego.soltarFicha(2); // mauro
		juego.soltarFicha(2);// gian
		juego.soltarFicha(3); // mauro
		juego.soltarFicha(3);// gian
		juego.soltarFicha(1);// mauro
		juego.soltarFicha(4);// gian
		
		
		assertEquals("Gian", juego.obtenerGanador());
		
		
	}

	@Test 
	public void cuatroEnLineaEnVertical() {
		
	
		CuatroEnLinea juego = new CuatroEnLinea(7, 4, "Mauro", "Gian");

		juego.soltarFicha(1); // mauro
		juego.soltarFicha(2); // gian
		juego.soltarFicha(1); // mauro
		juego.soltarFicha(2); // gian
		juego.soltarFicha(1); // mauro
		juego.soltarFicha(2); // gian
		juego.soltarFicha(1); // mauro
		
		
		assertEquals("Mauro", juego.obtenerGanador());
		
	}
	@Test (expected = Error.class)
	public void noGanoPrimero() {
		
	
		CuatroEnLinea juego = new CuatroEnLinea(7, 4, "Mauro", "Gian");

		juego.soltarFicha(1); // mauro
		juego.soltarFicha(2); // gian
		juego.soltarFicha(1); // mauro
		juego.soltarFicha(2); // gian
		juego.soltarFicha(1); // mauro
		juego.soltarFicha(2); // gian
		juego.soltarFicha(1); // mauro, ya gano.
		juego.soltarFicha(2); // gian
		
		
		assertEquals("Gian", juego.obtenerGanador());;
		
	}	

	

}
