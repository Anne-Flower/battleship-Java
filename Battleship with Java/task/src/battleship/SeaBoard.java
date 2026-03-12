package battleship;

import java.util.ArrayList;

public class SeaBoard {
    int cols = 10;
    String[] alphabet = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
    ArrayList<ShipInfo> ships = new ArrayList<>();
    ArrayList<int[]> missiles = new ArrayList<>();

    public SeaBoard() {
        int cols = 10;
    }


    public ValidationResult placeShip(Ship ship, int[] startCo, int[] endCo) {
        ShipInfo myShipInfo = new ShipInfo(ship, startCo, endCo);
        for (int[] part : myShipInfo.parts) {
            if (isAroundShip(part)) {
                return new ValidationResult(false, "Error! You placed it too close to another one. Try again:");
            }
        }
        ships.add(myShipInfo);
        return new ValidationResult(true);
    }

    private boolean coordEqual(int[] coordA, int[] coordB) {
        if (coordA[0] == coordB[0] && coordA[1] == coordB[1]) {
            return true;
        }
        return false;
    }

    public boolean placeMissile(int[] coord) {
        missiles.add(coord);
        if (isShip(coord)) {
            return true;
        }
        return false;
    }

    private int[][] getSurroundingCoordinates(int[] target) {
        int[][] listOfSurroundingCoordinate = new int[4][2];
        listOfSurroundingCoordinate[0] = new int[]{target[0] + 1, target[1]};
        listOfSurroundingCoordinate[1] = new int[]{target[0] - 1, target[1]};
        listOfSurroundingCoordinate[2] = new int[]{target[0], target[1] + 1};
        listOfSurroundingCoordinate[3] = new int[]{target[0], target[1] - 1};

        return listOfSurroundingCoordinate;
    }

    private boolean isAroundCoordinate(int[] target, int[] baseCoordinate) {
        int[][] list = getSurroundingCoordinates(target);
        for (int[] e : list) {
            if (coordEqual(e, baseCoordinate)) {
                return true;
            }
        }
        return false;
    }


    public boolean isAroundShip(int[] coordinate) {
        for (ShipInfo shipInfo : ships) {
            for (int[] part : shipInfo.parts) {
                if (coordEqual(part, coordinate)) {
                    return true;
                }
                if (isAroundCoordinate(part, coordinate)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isShip(int[] coordinate) {
        for (ShipInfo shipInfo : ships) {
            for (int[] part : shipInfo.parts) {
                if (part[0] == coordinate[0] && part[1] == coordinate[1]) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isMissile(int[] coord) {
        for(int[] missile : missiles ) {
            if (coordEqual(missile, coord)) {
                return true;
            }
        }
        return false;
    }

    public void display() {
        System.out.print("  ");
        for (int i = 0; i < cols; i++) {
            System.out.print(i + 1 + " ");
        }
        System.out.println();

        for (int i = 0; i < cols; i++) {
            System.out.print(alphabet[i] + " ");
            for (int j = 0; j < cols; j++) {
                int[] coord = new int[]{i, j};
                if (isShip(coord)) {
                    if (isMissile(coord)) {
                        System.out.print("X" + " ");
                    } else {
                        System.out.print("O" + " ");
                    }
                } else if (isMissile(coord)) {
                    System.out.print("M" + " ");
                } else {
                    System.out.print("~" + " ");
                }
            }
            System.out.println();
        }
    }
}