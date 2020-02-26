package com.example.sudokusolver.services;

import org.springframework.stereotype.Service;

@Service
public class SudokuSolverService {

    private int[][] globalBoard = new int[9][9];

    public int[][] startSolving(int[][] board) {
        globalBoard = board;
        solveField(0, 0);
        return globalBoard;
    }

    private boolean solveField(final int row, final int col) {
        int[] cell = findNextUnassignedLocation(row, col);
        if (cell == null) {
            System.out.println("solved");
            return true;
        }
        int newRow = cell[0];
        int newCol = cell[1];

        for (int possibleNumber = 1; possibleNumber < 10; possibleNumber++) {
            boolean isPossible = this.checkField(newRow, newCol, possibleNumber);
            if (isPossible) {
                globalBoard[newRow][newCol] = possibleNumber;
                if (solveField(newRow, newCol)) {
                    return true;
                } else {
                    globalBoard[newRow][newCol] = 0;
                }
            }
        }
        return false;
    }

    private int[] findNextUnassignedLocation(final int row, final int col) {
        for (int rowIndex = 0; rowIndex < globalBoard.length; rowIndex++) {
            for (int columnIndex = 0; columnIndex < globalBoard[rowIndex].length; columnIndex++) {
                    if (globalBoard[rowIndex][columnIndex] == 0) {
                        int[] indexes = new int[2];
                        indexes[0] = rowIndex;
                        indexes[1] = columnIndex;
                        return indexes;
                    }
            }
        }
        return null;
    }

    private boolean checkField(int rowIndex, int columnIndex, int fieldNumber) {
        return this.checkRow(rowIndex, fieldNumber) && this.checkColumn(columnIndex, fieldNumber)
                && this.checkSquare(rowIndex, columnIndex, fieldNumber);
    }

    private boolean checkRow(int rowIndex, int possibleNumber) {
        for (int field : globalBoard[rowIndex]) {
            if (field == possibleNumber) {
                return false;
            }
        }
        return true;
    }

    private boolean checkColumn(int columnIndex, int possibleNumber) {
        for (int[] row : globalBoard) {
            if (row[columnIndex] == possibleNumber) {
                return false;
            }
        }
        return true;
    }

    private boolean checkSquare(int origRow, int origCol, int possibleNumber) {
        double startRow = Math.floor(origRow / 3) * 3;
        double startCol = Math.floor(origCol / 3) * 3;
        for (int row = 0; row < globalBoard.length; row++) {
            for (int col = 0; col < globalBoard[row].length; col++) {
                if (Math.floor(row / 3) * 3 == startRow && Math.floor(col / 3) * 3 == startCol) {
                    if (globalBoard[row][col] == possibleNumber) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}