package battleship;

import java.util.ArrayList;

public class SeaBoard {
    int cols = 10;
    String[] alphabet = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
    ArrayList<ShipInfo> ships = new ArrayList<>();
    public SeaBoard() {
        int cols = 10;
    }

    public void placeShip(Ship ship, int[] startCo, int[] endCo) {
        ShipInfo myShipInfo = new ShipInfo(ship,startCo, endCo );
        ships.add(myShipInfo);
    }
    public boolean isShip(int[] coordinate) {
        for(ShipInfo shipInfo: ships) {
            for (int[] part : shipInfo.parts) {
                if(part[0] == coordinate[0] && part[1] == coordinate[1]) {
                    return true;
                }
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
               if (isShip(new int[]{i, j})) {
                   System.out.print("O" + " ");
               }
               else {
                System.out.print("~" + " ");
                }
            }
            System.out.println();
        }
    }
}