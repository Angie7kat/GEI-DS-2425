package e2;

public class KeyPad {

    private static char[] generarSecuencia(int tamano) {
        if (!(tamano > 36)){
            char[] secuencia = new char[tamano];
            int index = 0;

            // del 1 al 0 (10)
            for (char i = '1'; i <= '9' && index < tamano; i++) {
                secuencia[index++] = i;
            }
            if (index < tamano) {
                secuencia[index++] = '0';
            }

            // anade de la A a la Z
            for (char letra = 'A'; letra <= 'Z' && index < tamano; letra++) {
                secuencia[index++] = letra;
            }

            return secuencia;
        }
        throw new IllegalArgumentException("Tamano no puede ser mayor de 36.");
    }

    public static boolean isValidKeyPad(char[][] matrix) {

        if (matrix == null){
            return false;
        }

        // mira si teclado rectangular
        int numFilas = matrix.length;
        int numColumnas = matrix[0].length;
        for (int i = 1; i < numFilas; i++) {
            if (matrix[i] == null) return false;
            if (matrix[i].length != numColumnas) {
                return false;
            }
        }
        // mira si estamos en la esquina superior izquierda con un 1
        if (!(matrix[0][0] == '1')) {
            return false;
        }

        char[] secuencia = generarSecuencia(numFilas * numColumnas);

        int index = 0;
        boolean secuenciaPorFilas = true;
        for (char[] chars : matrix) {
            for (int columna = 0; columna < numColumnas; columna++) {
                if (chars[columna] != secuencia[index++]) {
                    secuenciaPorFilas = false;
                    break;
                }
            }
            if (!secuenciaPorFilas) {
                break;
            }
        }

        // verificar columnas y filas
        if (!secuenciaPorFilas) {
            index = 0;
            for (int columna = 0; columna < numColumnas; columna++) {
                for (char[] chars : matrix) {
                    if (chars[columna] != secuencia[index++]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static boolean areValidMovements(String[] movements) {
        if (movements == null) return false;

        for (String move : movements) {
            if (move == null || move.isEmpty()) return false;
            for (char ch : move.toCharArray()) {
                if ("UDLR".indexOf(ch) == -1) return false; // solo permite U, D, L, R
            }
        }
        return true;
    }

    public static String obtainCode(char[][] keyboard, String[] movements) {
        if (!isValidKeyPad(keyboard) || !areValidMovements(movements)) {
            throw new IllegalArgumentException("Teclado o movimientos inválidos");
        }

        StringBuilder code = new StringBuilder();
        int x = 0, y = 0; // esquina izquierda superior

        for (String move : movements) {
            for (char direction : move.toCharArray()) {
                switch (direction) {
                    case 'U': if (y > 0) y--; break;
                    case 'D': if (y < keyboard.length - 1) y++; break;
                    case 'L': if (x > 0) x--; break;
                    case 'R': if (x < keyboard[y].length - 1) x++; break;
                }
            }
            code.append(keyboard[y][x]); // añade la tecla actual después de moverse
        }
        return code.toString();
    }
}

