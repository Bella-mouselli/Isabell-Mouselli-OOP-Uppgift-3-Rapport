import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;


    public class LuffarSchack {
       private static Scanner in; // Denna tar in användarens input. privat instansvariabler/ resten är lokal variabler.
       private static String[] board; // Spelbrädet
       private static String turn; //Vems tur det är X eller 0

        /**
         * Vi börjar med att ta input från användaren
         * Vi bestämmer även med instansvariabeln board vem som börjar spelet
         * Visar välkomstmeddelande i terminalen för spelstart
         * Try/catch för att kunna hantera fel inmatningar från användare te.x fel ogiltigt nr/ fel nr eller bokstav.
         *if/board utger vilken ruta i brädet som kommer att fyllas efter användarens input
         * if/winner utger vinnaren för spelet eller om det blev oavgjort
         */
        public static void tur() { //klassmetod
            in = new Scanner(System.in); // input från användaren
            board = new String[9];
            turn = "X"; //Vem som börjar spelet
            String winner = null; //vinnaren
            FillEmptyBoard();

            System.out.println("Välkommen till spelet Luffarschack");
            System.out.println("--------------------------------");// Mellanrum för välkommen och spelbrädet
            Pboard();
            System.out.println("X börjar spelet. Var vänlig välj en siffra mellan 1-9");

            while (winner == null) {
                int numInput;
                try {
                    numInput = in.nextInt();
                    if (!(numInput > 0 && numInput <= 9)) { // om spelaren väljer ett nummer utanför 1-9 dyker ett felmeddelande upp
                        System.out.println("Ogiltigt nummer, välj ett nytt nummer igen:");
                        continue; // spelet fortsätter efter felmeddelandet
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Ogiltigt nummer, välj ett nytt nummer igen::");// Felmeddelande vid fel tecken utöver 1-9/tex bokstäver
                    continue;
                }
                if (board[numInput-1].equals(String.valueOf(numInput))) { // array börjar alltid med 0, input av nummer på brädan, tex 6=7 på brädan.
                    board[numInput-1] = turn;
                    if (turn.equals("X")) { // X börjar
                        turn = "O"; // Sen 0´s tur
                    } else {
                        turn = "X";
                    }
                    Pboard();
                    winner = Winner();
                } else {
                    System.out.println("Denna ruta är redan tagen, vänlig välj ett nytt nummer");
                    continue;
                }
            }
            if (winner.equalsIgnoreCase("Oavgjort")) { // oavgjort om alla rutor är upptagna och ingen fått 3 i rad
                System.out.println("Det blev oavgjort. Tack för att du spelade.");
            } else {
                System.out.println("Grattis! " + winner + " har vunnit! Tack för att du spelade"); // annars vinner den som fått 3 i rad
            }
        }

        /**
         *
         * @return ger tillbaka värdet X eller 0 när X/0 har uppstått 3 gånger i rad, vinnaren utses då.
         * Array för rutor i brädet 1-9, array börjar alltid med 0 +1
         * switch statement hjälper programmet att räkna ut alla möjiga cenarion för eventuella vinter/oavgjort
         *
         */
        static String Winner() {
            for (int a = 0; a < 8; a++) { //Storleken på spelbrädet 1-9 rutor/ räkna alltid med +1
                String line = null;
                switch (a) { // Räknar ut alla möjliga cenarion för vinst eventuellt oavgjort på brädspelet
                    case 0:
                        line = board[0] + board[1] + board[2];
                        break;
                    case 1:
                        line = board[3] + board[4] + board[5];
                        break;
                    case 2:
                        line = board[6] + board[7] + board[8];
                        break;
                    case 3:
                        line = board[0] + board[3] + board[6];
                        break;
                    case 4:
                        line = board[1] + board[4] + board[7];
                        break;
                    case 5:
                        line = board[2] + board[5] + board[8];
                        break;
                    case 6:
                        line = board[0] + board[4] + board[8];
                        break;
                    case 7:
                        line = board[2] + board[4] + board[6];
                        break;
                }
                if (line.equals("XXX")) { //Checkar vinnaen som har tre symboler i rad.
                    return "X";
                } else if (line.equals("OOO")) {
                    return "O";
                }
            }

            for (int a = 0; a < 9; a++) {
                if (Arrays.asList(board).contains(String.valueOf(a+1))) {
                    break;
                }
                else if (a == 8) return "Oavgjort";
            }

            System.out.println(turn + " Tur, välj ett nummer att placera " + turn + " i:");
            return null;
        }

        /**
         * Utgör design fasen för brädspelet
         * Vi deklarerar värdet för varje ruta i brädspelet
         * Värdet för varje ruta har vi sedan med i våran array i Winner
         * Printar ut hela brädet i terminalen
         */
        static void Pboard() { // Brädspelets design// printa ut brädan/ ger array värdet för varje ruta
            System.out.println("/---|---|---\\");
            System.out.println("| " + board[0] + " | " + board[1] + " | " + board[2] + " |");
            System.out.println("|-----------|");
            System.out.println("| " + board[3] + " | " + board[4] + " | " + board[5] + " |");
            System.out.println("|-----------|");
            System.out.println("| " + board[6] + " | " + board[7] + " | " + board[8] + " |");
            System.out.println("/---|---|---\\");
        }

        /**
         * Loop för varje ruta i brädspelet
         * 1-9
         * När loopen nåt nummer 9 är brädspelet fullt
         * Vinnaren utses
         */
        static void FillEmptyBoard() { // Loopa igenom 9 gånger tills brädet är fullt, då utses vinnaren.
            for (int a = 0; a < 9; a++) {
                board[a] = String.valueOf(a+1);
            }
        }
    }

