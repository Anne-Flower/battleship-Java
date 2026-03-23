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

    public SeaBoard.MissileResult placeMissile(SeaBoard targetBoard) {
        //todo : placer un missile d un joueur, sur le board de l'autre joueur

        SeaBoard.MissileResult myResult= SeaBoard.MissileResult.START;
        Scanner scanner = new Scanner(System.in);
        String shot = scanner.next();
        int[] shotCoord = parseStringCoordinate(shot);
        myResult = targetBoard.placeMissile(shotCoord);
        return myResult;


//        SeaBoard.MissileResult myResult1 = SeaBoard.MissileResult.START;
//       while (SeaBoard.MissileResult.ALL_SHIPS_SUNK != myResult1) {
//           Scanner scanner = new Scanner(System.in);
//           String shot = scanner.next();
//           int[] shotCoord = parseStringCoordinate(shot);
//           myResult = player1.seaBoard.placeMissile(shotCoord);
//           targetBoard.display(true);
//           switch (myResult) {
//               case MISSILE_HIT:
//                   System.out.println("You hit a ship! Try again:");
//                   break;
//               case MISSILE_MISSED:
//                   System.out.println("You missed. Try again:");
//                   break;
//               case SHIP_SUNK:
//                   System.out.println("You sank a ship! Specify a new target:");
//                   break;
//               case ALL_SHIPS_SUNK:
//                   System.out.println("You sank the last ship. You won. Congratulations!");
//                   break;
//           }
//       }
    }

//    public static void displayGamePlayers(Player player1, Player player2) {
//        SeaBoard.MissileResult myResult1 = SeaBoard.MissileResult.START;
//        while (SeaBoard.MissileResult.ALL_SHIPS_SUNK != myResult1) {
//            Scanner scanner = new Scanner(System.in);
//            String shot = scanner.next();
//            int[] shotCoord = parseStringCoordinate(shot);
//            myResult = player1.seaBoard.placeMissile(shotCoord);
//            player1.seaBoard.display(true);
//            switch (myResult) {
//                case MISSILE_HIT:
//                    System.out.println("You hit a ship! Try again:");
//                    break;
//                case MISSILE_MISSED:
//                    System.out.println("You missed. Try again:");
//                    break;
//                case SHIP_SUNK:
//                    System.out.println("You sank a ship! Specify a new target:");
//                    break;
//                case ALL_SHIPS_SUNK:
//                    System.out.println("You sank the last ship. You won. Congratulations!");
//                    break;
//            }
//            player1.seaBoard.display(false);
//        }
//    }
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

    public void yourTurn(){
        System.out.format("%s, it's your turn:", this.name);
    }

    public void placeShips(Ship[] shipsToPlace) {
        System.out.format("%s, place your ships on the game field", this.name);
        System.out.println();
        this.seaBoard.display(false);
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
