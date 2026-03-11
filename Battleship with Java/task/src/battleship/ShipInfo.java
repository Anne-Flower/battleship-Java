package battleship;

import java.util.Arrays;

public class ShipInfo {
    Ship ship;
    int[][] parts;

    private int[][] calculParts(int[] startCoord, int[] endCoord, int length) {
        int[][] parts = new int[length][2];
        int firstStart = startCoord[0];
        int secondStart = startCoord[1];
        int firstEnd = endCoord[0];
        int secondEnd = endCoord[1];
        if(firstStart == firstEnd) {
            int index = 0;
            for(int i = secondStart; i <= secondEnd; i++) {
                int[] currentCoords = {firstStart, i};
                parts[index] = currentCoords;
                index++;
            }
        }
        else {
            int index = 0;
            for(int i = firstStart; i <= firstEnd; i++) {
                int[] currentCoords = {i, secondStart};
                parts[index] = currentCoords;
                index++;
            }
        }
        return parts;
    }

    private int[][] calculPartsAround(int[][] parts, int length) {
        int[][] partsAround = new int[length][2];
        for(int[] part : parts) {
            if (part[0] >= 0 && part[0] <= 10) {

            }
        }
        return partsAround;
    }

    public ShipInfo(Ship ship, int[] startCoord, int[] endCoord) {
        this.ship = ship;
        this.parts = calculParts(startCoord, endCoord, ship.getCells());
    }


}
