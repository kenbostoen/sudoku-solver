package com.example.sudokusolver.controllers;

import com.example.sudokusolver.SudokuDTO;
import com.example.sudokusolver.services.SudokuSolverService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class SudokuSolverController {

    private SudokuSolverService service;
    SudokuSolverController(SudokuSolverService service) {
        this.service = service;
    }
    @PostMapping("/solvesudoku")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<int[][]> greeting(@RequestBody() SudokuDTO sudokuDTO) {
        return new ResponseEntity<>(service.startSolving(sudokuDTO.getSudoku()), HttpStatus.OK);
	}
}