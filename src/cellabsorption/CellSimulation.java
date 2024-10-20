package cellabsorption;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Point;

import java.awt.Color;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("SameParameterValue")
public class CellSimulation {

    private CanvasWindow canvas;
    private Random rand = new Random();
    private List<Cell> cells;

    public static void main(String[] args) {
        new CellSimulation();
    }

    public CellSimulation() {
        canvas = new CanvasWindow("Cell Absorption", 800, 800);
        populateCells();

        //noinspection InfiniteLoopStatement
        while (true) {
            Point canvasCenter = new Point(canvas.getWidth() / 2.0, canvas.getHeight() / 2.0);
            for (Cell cell: cells) {
                cell.moveAround(canvasCenter);
            }
            handleCellInteraction();
            canvas.draw();
            canvas.pause(10);
        }
    }

    private void populateCells() {
        cells = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            double size = rand.nextInt(5) + 2;
        Cell cell = new Cell(
            rand.nextDouble() * (canvas.getWidth() - size),
            rand.nextDouble() * (canvas.getWidth() - size),
            size,
            Color.getHSBColor(rand.nextFloat(), rand.nextFloat() * 0.5f + 0.1f, 1));
        cells.add(cell);
        canvas.add(cell.getShape());
        }
    }
    
    private void handleCellInteraction() {
        for (int i = 0; i < cells.size(); i++) {
            Cell cell1 = cells.get(i);
            for (int r = i + 1; r < cells.size(); r++) {
                Cell cell2 = cells.get(r);
                cell1.interactWith(cell2);
            }
        }
    }  
}
