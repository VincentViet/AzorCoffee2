package com.azor;

import com.azor.login.LoginView;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;

public class AzorCoffee extends Application {

    public static final class layout{
//        public static final int main = 0;
        static final int login = 0;
    }

    private static Stage primaryStage;

    private static ArrayList<ViewTuple> viewTuples = new ArrayList<>();

    public static void main(String[] args) {
        viewTuples.add(FluentViewLoader.fxmlView(LoginView.class).load());

        launch(args);
    }

    @Override
    public void start(Stage stage) {
        primaryStage = stage;

        setScene(layout.login);

        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    public static void setScene(int id){
        primaryStage.setScene(new Scene(viewTuples.get(id).getView()));
    }

    public static void close(){
        Platform.exit();
    }

    public static void setStagePosition(double x, double y){
        primaryStage.setX(x);
        primaryStage.setY(y);
    }

}
