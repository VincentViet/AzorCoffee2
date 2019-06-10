package com.azor.chart;

import com.jfoenix.controls.JFXDatePicker;
import de.saxsys.mvvmfx.FxmlPath;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import java.time.LocalDate;
import java.util.Random;

@FxmlPath("/fxml/chart.fxml")
public class ChartView implements FxmlView<ChartViewModel> {

    @InjectViewModel
    private ChartViewModel viewModel;

    @FXML
    private LineChart<String, Number> lineChart;

    @FXML
    private JFXDatePicker datePicker;

    public void initialize() {
        datePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (oldValue == null || !newValue.getMonth().equals(oldValue.getMonth())){
                Random random = new Random();
                XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
                int day, month, year, dayOfMonth;
                day = newValue.getDayOfMonth();
                month = newValue.getMonthValue();
                year = newValue.getYear();
                switch (month){
                    case 1:
                    case 3:
                    case 5:
                    case 7:
                    case 8:
                    case 10:
                    case 12:
                        dayOfMonth = 31;
                        break;

                    case 4:
                    case 6:
                    case 9:
                    case 11:
                        dayOfMonth = 30;
                        break;
                    default:
                        dayOfMonth = 28;
                }
                int value = day + month + year;
                for (int i = 1; i <= dayOfMonth; i++){
                    series.getData().add(new XYChart.Data<>("" + i, random.nextInt(value)));
                }
                lineChart.getData().add(series);
            }
        });
        datePicker.setValue(LocalDate.now());
    }

    @FXML
    private void onClearChart(ActionEvent event){
        lineChart.getData().clear();
    }
}