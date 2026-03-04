package battleship;


import java.util.Scanner;
import java.util.HashMap;


public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Ship[] playerOneShips = {
                new Ship(5, "Aircraft Carrier"),
                new Ship(4, "Battleship"),
                new Ship(3, "Submarine"),
                new Ship(3, "Cruiser"),
                new Ship(2, "Destroyer")
        };

        for(int i = 0; i < playerOneShips.length; i++) {
            placeShipPrompt(playerOneShips[i]);
            String startCoordinate = scanner.next();
            String endCoordinate = scanner.next();
        }

    }
    
    public static void placeShipPrompt(Ship ship) {
        System.out.format("Enter the coordinates of the %s (%d cells):\n", ship.name, ship.cells);
    }

    public static int[] parseStringCoordinate(String stringCoordinate) {
        HashMap<String, Integer> letterMapping = new HashMap<>();
        letterMapping.put("A", 0);
        letterMapping.put("B", 1);
        letterMapping.put("C", 2);
        letterMapping.put("D", 3);
        letterMapping.put("E", 4);
        letterMapping.put("F", 5);
        letterMapping.put("G", 6);
        letterMapping.put("H", 7);
        letterMapping.put("I", 8);
        letterMapping.put("J", 9);

        String letter = String.valueOf(stringCoordinate.charAt(0));
        int number = Integer.parseInt(stringCoordinate.substring(1));
        int letterDico = letterMapping.get(letter);
        int[] coordinate = {letterDico, number};
        return coordinate;
    }

    public static boolean isInteger(String num) {
        String nums = "0123456789";
        String[] numInArray = num.split("");
        for (String digit : numInArray) {
            if (!nums.contains(digit)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isValidCoordinate(String coordinate) {
        String alphabet = "ABCDEFGHIJ";
        String secondElement = coordinate.substring(1);

        String[] coordinateInArray = coordinate.split("");
        if (!alphabet.contains(coordinateInArray[0])) {
            return false;
        }
        if (!isInteger(secondElement)) {
            return false;
        }

        if (Integer.parseInt(secondElement) > 10) {
            return false;
        }
        if (Integer.parseInt(secondElement) < 0) {
            return false;
        }

        return true;
    }

    public static int calculateLength(int[] start, int[] end) {
        if (start[0] == end[0]) {
            int calcul = Math.abs(start[1] - end[1]);
            return calcul + 1;
        }
        if (start[1] == end[1]) {
            int calcul = Math.abs(start[0] - end[0]);
            return calcul + 1;
        }
        throw new RuntimeException("invalid coordinates");
    }

    public static boolean isValidShipCoordinates(String startCoordinate, String endCoordinate, Ship ship) {
        if (!isValidCoordinate(startCoordinate) ||  !isValidCoordinate(endCoordinate)) {
            return false;
        }
        int[] start = parseStringCoordinate(startCoordinate);
        int[] end = parseStringCoordinate(endCoordinate);

        if (start[0] != end[0] && start[1] != end[1]) {
            return false;
        }
        if(ship.cells != calculateLength(start, end)) {
            return false;
        }
//        System.out.println(calculateLength(start, end));
        return true;

    }
    

}
