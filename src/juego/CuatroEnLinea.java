package juego;

/**
 * Juego Cuatro en Lí­nea
 * 
 * Reglas:
 * 
 * ...
 *
 */
public class CuatroEnLinea {

	/**
	 * pre : 'filas' y 'columnas' son mayores o iguales a 4. post: empieza el
	 * juego entre el jugador que tiene fichas rojas, identificado como
	 * 'jugadorRojo' y el jugador que tiene fichas amarillas, identificado como
	 * 'jugadorAmarillo'. Todo el tablero está vacío.
	 * 
	 * @param filas
	 *            : cantidad de filas que tiene el tablero.
	 * @param columnas
	 *            : cantidad de columnas que tiene el tablero.
	 * @param jugadorRojo
	 *            : nombre del jugador con fichas rojas.
	 * @param jugadorAmarillo
	 *            : nombre del jugador con fichas amarillas.
	 */
	int filas;
	int columnas;
	String jugadorRojo;
	String jugadorAmarillo;
	Casillero[][] tablero;
	String turnoDeJugador = "Rojo";
	private boolean ganador = false;
	String jugadorGanador;

	public CuatroEnLinea(int filas, int columnas, String jugadorRojo, String jugadorAmarillo) {
		if (filas < 4 || columnas < 4) {
			throw new Error("Columnas y Filas deben ser mayores a 4");
		}
		tablero = new Casillero[filas][columnas];
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++) {
				tablero[i][j] = Casillero.VACIO;
			}
		}
		this.jugadorAmarillo = jugadorAmarillo;
		this.jugadorRojo = jugadorRojo;
		this.columnas = columnas;
		this.filas = filas;
	}

	/**
	 * post: devuelve la cantidad máxima de fichas que se pueden apilar en el
	 * tablero.
	 */
	public int contarFilas() {
		return filas;
	}

	/**
	 * post: devuelve la cantidad máxima de fichas que se pueden alinear en el
	 * tablero.
	 */
	public int contarColumnas() {
		return columnas;
	}

	/**
	 * pre : fila está en el intervalo [1, contarFilas()], columnas está en el
	 * intervalo [1, contarColumnas()]. post: indica qué ocupa el casillero en
	 * la posición dada por fila y columna.
	 * 
	 * @param fila
	 * @param columna
	 */
	public Casillero obtenerCasillero(int fila, int columna) {
		Casillero contenido = Casillero.VACIO;
		if (columnaEstaEnElIntervalo(columna) && filaEstaEnElIntervalo(fila)) {
			contenido = tablero[fila - 1][columna - 1];
		}
		return contenido;
	}

	/**
	 * pre : el juego no terminó, columna está en el intervalo [1,
	 * contarColumnas()] y aún queda un Casillero.VACIO en la columna indicada.
	 * post: deja caer una ficha en la columna indicada.
	 * 
	 * @param columna
	 */
	public void soltarFicha(int columna) {
		if (!hayGanador() && columnaEstaEnElIntervalo(columna)) {
			int turno = 0;
			columna--;
			for (int a = tablero.length - 1; a >= 0 && turno == 0; a--) {
				if (tablero[a][columna] == Casillero.VACIO
						&& turnoDeJugador == "Rojo") {
					tablero[a][columna] = Casillero.ROJO;
					turnoDeJugador = "Amarillo";
					turno++;

				}
				if (tablero[a][columna] == Casillero.VACIO
						&& turnoDeJugador == "Amarillo") {
					tablero[a][columna] = Casillero.AMARILLO;
					turnoDeJugador = "Rojo";
					turno++;

				}
				encontrarGanador();
			}
		}
	}

	/**
	 * post: indica si el juego terminó porque uno de los jugadores ganó o no
	 * existen casilleros vacíos.
	 */
	public boolean termino() {
		boolean resultado = false;

		if (hayGanador() || !hayEspacio()) {
			resultado = true;
		}

		return resultado;
	}

	/**
	 * post: indica si el juego terminó y tiene un ganador.
	 */
	public boolean hayGanador() {
		return ganador;
	}

	/**
	 * pre : el juego terminó. post: devuelve el nombre del jugador que ganó el
	 * juego.
	 */
	public String obtenerGanador() {
		return jugadorGanador;
	}

	/**
	 * post: indica si fila esta en el intervalo [1, contarFilas()]
	 */
	public boolean filaEstaEnElIntervalo(int fila) {
		if (fila >= 1 && fila <= contarFilas()) {
			return true;
		}
		if (fila < 1 || fila > contarFilas()) {
			throw new Error("Esta fila no existe.");
		}
		return false;
	}

	/**
	 * post: indica si columna esta en el intervalo [1, contarColumna()]
	 */
	public boolean columnaEstaEnElIntervalo(int columna) {
		if (columna >= 1 && columna <= contarColumnas()) {
			return true;
		}
		if (columna < 1 || columna > contarColumnas()) {
			throw new Error("Esta columna no existe.");
		}
		return false;
	}

	/**
	 * post: indica si hay espacio vacio en el tablero
	 */
	boolean hayEspacio() {
		boolean resultado = false;
		for (int i = 0; i < tablero.length && resultado == false; i++) {
			for (int j = 0; j < tablero[i].length && resultado == false; j++) {
				if (tablero[i][j] == Casillero.VACIO) {
					resultado = true;
				}

			}
		}
		return resultado;
	}

	/**
	 * post: indica si hay cuatro casilleros en linea con el mismo color
	 */
	private boolean hayCuatroSeguidos(Casillero color) {
		boolean resultado = false;
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++) {
				if (j + 3 < contarColumnas()) { // cuatro en linea horizontal
					if (tablero[i][j] == color && tablero[i][j + 1] == color
							&& tablero[i][j + 2] == color
							&& tablero[i][j + 3] == color) {
						resultado = true;
					}
				}
				if (i + 3 < contarFilas()) { // cuatro en linea vertical
					if (tablero[i][j] == color && tablero[i + 1][j] == color
							&& tablero[i + 2][j] == color
							&& tablero[i + 3][j] == color) {
						resultado = true;
					}
				}

				if (i + 3 < contarFilas() && j + 3 < contarColumnas()) { // cuatro en linea diagonal
					if (tablero[i][j] == color
							&& tablero[i + 1][j + 1] == color
							&& tablero[i + 2][j + 2] == color
							&& tablero[i + 3][j + 3] == color
							|| tablero[i][j + 3] == color
							&& tablero[i + 1][j + 2] == color
							&& tablero[i + 2][j + 1] == color
							&& tablero[i + 3][j] == color) {
						resultado = true;
					}

				}
			}
		}
		return resultado;
	}

	/**
	 * pre: El juego termino y se encontraron cuatro casilleros en linea del
	 * mismo color.
	 */
	private void encontrarGanador() {
		if (hayCuatroSeguidos(Casillero.ROJO)) {
			jugadorGanador = jugadorRojo;
			ganador = true;
		}
		if (hayCuatroSeguidos(Casillero.AMARILLO)) {
			jugadorGanador = jugadorAmarillo;
			ganador = true;
		}
	}

}