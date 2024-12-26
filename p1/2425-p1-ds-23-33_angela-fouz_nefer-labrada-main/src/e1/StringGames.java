package e1;

public class StringGames {

    public static String bestCharacters(String str1, String str2) {
        if (str1.length() != str2.length()) {
            throw new IllegalArgumentException("Cadenas de distinta longitud");
        }

        // guardo puntuaciones de cada string en un array de dos elementos
        int[] points = {0, 0}; //
        int[][] counts = new int[2][3]; // counts[0] para str1, counts[1] para str2: [minúsculas, mayúsculas, dígitos]

        for (int i = 0; i < str1.length(); i++) {
            counts[0][0] += Character.isLowerCase(str1.charAt(i)) ? 1 : 0; // minúsculas str1
            counts[0][1] += Character.isUpperCase(str1.charAt(i)) ? 1 : 0; // mayúsculas str1
            counts[0][2] += Character.isDigit(str1.charAt(i)) ? 1 : 0; // dígitos str1

            counts[1][0] += Character.isLowerCase(str2.charAt(i)) ? 1 : 0; // minúsculas str2
            counts[1][1] += Character.isUpperCase(str2.charAt(i)) ? 1 : 0; // mayúsculas str2
            counts[1][2] += Character.isDigit(str2.charAt(i)) ? 1 : 0; // dígitos str2
        }

        // comparamos categorias en un array de 3 elementos
        for (int j = 0; j < 3; j++) {
            if (counts[0][j] > counts[1][j]) points[0]++;
            else if (counts[1][j] > counts[0][j]) points[1]++;
        }

        return (points[0] > points[1]) ? str1 : (points[1] > points[0]) ? str2 : str1; // En caso de empate, devolver str1
    }

    public static int crossingWords(String str1, String str2) {
        // Calculamos el numeros de caracteres en comun
        int sameCharacters = 0;
        for (int i = 0; i < str1.length(); i++) {
            char currentChar = str1.charAt(i);
            for (int j = 0; j < str2.length(); j++) {
                if (currentChar == str2.charAt(j)) {
                    sameCharacters++;
                }
            }
        }
        return sameCharacters;
    }

    public static String wackyAlphabet(String str1, String alphabet) {
        // Aquí metí, además, que mirase q no hubiesen repeticiones y que estuviesen en minuscula
        if (alphabet.length() != 26 || !alphabet.chars().allMatch(Character::isLowerCase) ||
                alphabet.chars().distinct().count() != 26) {
            throw new IllegalArgumentException("El segundo string debe contener todas las letras del alfabeto sin repetición ni otros caracteres.");
        }
        int[] charOrder = new int[26];

        // Llenamos el arreglo con los índices de las letras en str2
        for (int i = 0; i < alphabet.length(); i++) {
            charOrder[alphabet.charAt(i) - 'a'] = i;
        }

        char[] charArray = str1.toCharArray();

        for (int i = 0; i < charArray.length - 1; i++) {
            for (int j = i + 1; j < charArray.length; j++) {
                if (charOrder[charArray[i] - 'a'] > charOrder[charArray[j] - 'a']) {
                    char temp = charArray[i];
                    charArray[i] = charArray[j];
                    charArray[j] = temp;
                }
            }
        }
        return new String(charArray);
    }
}
