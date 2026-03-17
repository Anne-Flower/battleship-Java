package battleship;

import java.util.Scanner;

import static battleship.Main.*;

public class Player {
    String name;
    SeaBoard seaBoard;

    public Player(String name) {
        this.name = name;
        this.seaBoard = new SeaBoard();
    }


    public static void placeShipPrompt(Ship ship) {
        System.out.println();
        System.out.format("Enter the coordinates of the %s (%d cells):\n", ship.getNameShip(), ship.getCells());
    }

    public static ValidationResult isValidShipCoordinates(String startCoordinate, String endCoordinate, Ship ship) {
        if (!isValidCoordinate(startCoordinate) || !isValidCoordinate(endCoordinate)) {
            return new ValidationResult(false, "Error! Wrong ship location! Try again:");
        }
        int[] start = parseStringCoordinate(startCoordinate);
        int[] end = parseStringCoordinate(endCoordinate);

        if (start[0] != end[0] && start[1] != end[1]) {
            return new ValidationResult(false, "Error! Wrong ship location! Try again:");
        }
        if (ship.getCells() != calculateLength(start, end)) {
            String errorString = String.format("Error! Wrong length of the %s! Try again:", ship.getNameShip());
            return new ValidationResult(false, errorString);
        }
//        System.out.println(calculateLength(start, end));
        return new ValidationResult(true);

    }

    public void placeShips(Ship[] shipsToPlace) {
        Scanner scanner = new Scanner(System.in);
        for (Ship ship : shipsToPlace) {
            ValidationResult coordResult = new ValidationResult(false);
            ValidationResult placeResult = new ValidationResult(false);

            while (!coordResult.isValid || !placeResult.isValid) {
                placeShipPrompt(ship);
                String startCoordinate = scanner.next();
                String endCoordinate = scanner.next();
                coordResult = isValidShipCoordinates(startCoordinate, endCoordinate, ship);
                if (coordResult.isValid) {
                    int[] startInt = parseStringCoordinate(startCoordinate);
                    int[] endInt = parseStringCoordinate(endCoordinate);
                    if (endInt[0] < startInt[0] || endInt[1] < startInt[1]) {
                        int[] tempCoord = startInt;
                        startInt = endInt;
                        endInt = tempCoord;
                    }
                    placeResult = this.seaBoard.placeShip(ship, startInt, endInt);
                    if (placeResult.isValid) {
                        this.seaBoard.display(false);
                    } else {
                        System.out.println(placeResult.error);
                    }
                } else {
                    System.out.println(coordResult.error);
                }
            }

        }
    }
}
