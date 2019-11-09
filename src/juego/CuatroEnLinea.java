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
	
	private String jugadorRojo;
	private String jugadorAmarillo;
	private Casillero[][] tablero;
	private String turnoDeJugador = "Rojo";
	private boolean ganador = false;
	private String jugadorGanador;

	public CuatroEnLinea(int filas, int columnas, String jugadorRojo, String jugadorAmarillo) {
		if (filas < 4 || columnas < 4) {
			throw new Error("Columnas y Filas deben ser mayores a 4");
		}
		this.tablero = new Casillero[filas][columnas];
		for (int i = 0; i < this.tablero.length; i++) {
			for (int j = 0; j < this.tablero[i].length; j++) {
				this.tablero[i][j] = Casillero.VACIO;
			}
		}
		this.jugadorAmarillo = jugadorAmarillo;
		this.jugadorRojo = jugadorRojo;
	}

	/**
	 * post: devuelve la cantidad máxima de fichas que se pueden apilar en el
	 * tablero.
	 */
	public int contarFilas() {
		return this.tablero.length;
	}

	/**
	 * post: devuelve la cantidad máxima de fichas que se pueden alinear en el
	 * tablero.
	 */
	public int contarColumnas() {
		return this.tablero[0].length;
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
		if (filaEstaEnElIntervalo(fila) && columnaEstaEnElIntervalo(columna)) {
			return this.tablero[fila - 1][columna - 1];
		} else  {
			throw new Error("La ubicacion (fila, columna) no existe.");
		}
	}

	/**
	 * pre : el juego no terminó, columna está en el intervalo [1,
	 * contarColumnas()] y aún queda un Casillero.VACIO en la columna indicada.
	 * post: deja caer una ficha en la columna indicada.
	 * 
	 * @param columna
	 */
	public void soltarFicha(int columna) {
		if (condicionesSoltarFicha(columna)) {
			int turno = 0;
			for (int i = this.tablero.length - 1; i >= 0 && turno == 0; i--) {
				if (this.tablero[i][columna - 1] == Casillero.VACIO && this.turnoDeJugador == "Rojo") {
					this.tablero[i][columna - 1] = Casillero.ROJO;
					this.turnoDeJugador = "Amarillo";
					turno++;

				}
				if (this.tablero[i][columna - 1] == Casillero.VACIO && this.turnoDeJugador == "Amarillo") {
					this.tablero[i][columna - 1] = Casillero.AMARILLO;
					this.turnoDeJugador = "Rojo";
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
		return hayGanador() || !hayEspacio();
	}

	/**
	 * post: indica si el juego terminó y tiene un ganador.
	 */
	public boolean hayGanador() {
		return this.ganador;
	}

	/**
	 * pre : el juego terminó. post: devuelve el nombre del jugador que ganó el
	 * juego.
	 */
	public String obtenerGanador() {
		return this.jugadorGanador;
	}

	/**
	 * post: indica si fila esta en el intervalo [1, contarFilas()]
	 */
	private boolean filaEstaEnElIntervalo(int fila) {
		return fila >= 1 && fila <= contarFilas();
	}

	/**
	 * post: indica si columna esta en el intervalo [1, contarColumna()]
	 */
	private boolean columnaEstaEnElIntervalo(int columna) {
		return (columna >= 1) && (columna <= contarColumnas());
	}

	/**
	 * post: indica si columna esta en el intervalo [1, contarColumna()]
	 */
	private boolean condicionesSoltarFicha(int columna) {
		if (termino()) {
			throw new Error("El juego ha terminado.");
		} else if (!columnaEstaEnElIntervalo(columna)) {
			throw new Error("Esta columna no existe.");
		} else if (!hayEspacioEnColumna(columna)) {
			throw new Error("No hay más espacio en la columna.");
		} else {
			return true;
		}
	}
	
	/**
	 * post: indica si hay espacio vacio en el tablero
	 */
	private boolean hayEspacio() {
		boolean resultado = false;
		for (int i = 0; i < this.tablero.length && resultado == false; i++) {
			for (int j = 0; j < this.tablero[i].length && resultado == false; j++) {
				if (this.tablero[i][j] == Casillero.VACIO) {
					resultado = true;
				}

			}
		}
		return resultado;
	}
	
	/**
	 * post: indica si hay espacio vacio en la columna
	 */
	private boolean hayEspacioEnColumna(int columna) {
		boolean resultado = false;
		for (int i = this.tablero.length - 1; i >= 0 && resultado == false; i--) {
			if (this.tablero[i][columna - 1] == Casillero.VACIO) {
				resultado = true;
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
				if (j + 3 < contarColumnas()) {
					if (cuatroEnLineaHorizontal(i, j, color)) {
						resultado = true;
					}
				}
				if (i + 3 < contarFilas()) { // cuatro en linea vertical
					if (cuatroEnLineaVertical(i, j, color)) {
						resultado = true;
					}
				}

				if (i + 3 < contarFilas() && j + 3 < contarColumnas()) { // cuatro en linea diagonal
					if (cuatroEnLineaDiagonal(i, j, color)) {
						resultado = true;
					}

				}
			}
		}
		return resultado;
	}
	
	/**
	 * post: Devuelve si hay 4 fichas del mismo color de manera horizontal
	 */
	private boolean cuatroEnLineaHorizontal(int fila, int columna, Casillero color) {
		return this.tablero[fila][columna] == color 
			&& this.tablero[fila][columna + 1] == color
			&& this.tablero[fila][columna + 2] == color
			&& this.tablero[fila][columna + 3] == color;
	}

	/**
	 * post: Devuelve si hay 4 fichas del mismo color de manera vertical
	 */
	private boolean cuatroEnLineaVertical(int fila, int columna, Casillero color) {
		return this.tablero[fila][columna] == color 
			&& this.tablero[fila + 1][columna] == color
			&& this.tablero[fila + 2][columna] == color
			&& this.tablero[fila + 3][columna] == color;
	}
	
	/**
	 * post: Devuelve si hay 4 fichas del mismo color de manera diagonal
	 */
	private boolean cuatroEnLineaDiagonal(int fila, int columna, Casillero color) {
		return (this.tablero[fila][columna] == color
			&& this.tablero[fila + 1][columna + 1] == color
			&& this.tablero[fila + 2][columna + 2] == color
			&& this.tablero[fila + 3][columna + 3] == color)
			|| (this.tablero[fila][columna + 3] == color
			&& this.tablero[fila + 1][columna + 2] == color
			&& this.tablero[fila + 2][columna + 1] == color
			&& this.tablero[fila + 3][columna] == color);
	}
	
	/**
	 * pre: El juego termino y se encontraron cuatro casilleros en linea del
	 * mismo color.
	 */
	private void encontrarGanador() {
		if (hayCuatroSeguidos(Casillero.ROJO)) {
			this.jugadorGanador = this.jugadorRojo;
			this.ganador = true;
		}
		if (hayCuatroSeguidos(Casillero.AMARILLO)) {
			this.jugadorGanador = this.jugadorAmarillo;
			this.ganador = true;
		}
	}

}