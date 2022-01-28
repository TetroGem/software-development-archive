/*
 * Course: CS1021 - 021
 * Winter 2021
 * Lab 5 - Game Of Life
 * Name: Benjamin Singleton
 * Created: 01/12/2022
 * Modified: 01/17/2022
 */
package singletonb;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

/**
 * Driver class for the Application and Game of Life methods
 * @author singletonb
 */
public class Lab5 extends Application {
    private static final int WINDOW_SIZE = 600;
    private static LifeGrid lifeGrid;
    private static Label stats;
    private static LineChart<Number, Number> lineChart;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Game of Life");

        GridPane gridPane = new GridPane();
        final int gridSize = 500;
        lifeGrid = new LifeGrid(gridPane, gridSize, gridSize);

        createChart();

        Button stepButton = new Button("Next Step");
        stepButton.setOnAction(this::step);
        Button randomizeButton = new Button("Randomize");
        randomizeButton.setOnAction(this::randomizeGrid);
        Button clearButton = new Button("Clear");
        clearButton.setOnAction(this::clearGrid);
        stats = new Label();
        updateStats();

        final int buffer = 5;
        HBox buttonRow = new HBox(buffer);
        buttonRow.setAlignment(Pos.CENTER_LEFT);
        buttonRow.getChildren().addAll(stepButton, randomizeButton, clearButton, stats);

        HBox mainDisplays = new HBox(buffer);
        mainDisplays.getChildren().addAll(gridPane, lineChart);

        Pane rows = new VBox();
        rows.getChildren().addAll(mainDisplays, buttonRow);

        Scene scene = new Scene(rows);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Updates stats about the game, such as the cell populations and chart data
     */
    public static void updateStats() {
        if(lifeGrid != null) {
            stats.setText(String.format("Alive Cells: %d | Dead Cells: %d | Step: %d",
                    lifeGrid.countAliveCells(), lifeGrid.countCells() - lifeGrid.countAliveCells(),
                    lifeGrid.getStep()));
            updateChart(lifeGrid.getTotalPopulationData());
        }
    }

    private void clearGrid(ActionEvent actionEvent) {
        lifeGrid.clear();
    }

    private void randomizeGrid(ActionEvent actionEvent) {
        lifeGrid.randomize();
    }

    private void step(ActionEvent actionEvent){
        lifeGrid.iterate();
    }

    private static void createChart() {
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Step");
        yAxis.setLabel("Population");

        lineChart = new LineChart<Number, Number>(xAxis, yAxis);
        lineChart.setTitle("Total Population over Time");

        XYChart.Series<Number, Number> total = new XYChart.Series<>();
        total.setName("Total Population");
        lineChart.getData().add(total);
    }

    private static void updateChart(List<Integer> totalData) {
        XYChart.Series<Number, Number> total = lineChart.getData().get(0);
        if(totalData.size() < total.getData().size()) {
            total.getData().clear();
        }
        for(int step = total.getData().size(); step < totalData.size(); step++) {
            int population = totalData.get(step);
            total.getData().add(new XYChart.Data<>(step, population));
        }
    }
}
