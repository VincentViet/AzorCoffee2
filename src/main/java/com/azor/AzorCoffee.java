package com.azor;

import com.azor.home.HomeView;
import com.azor.login.LoginView;
import com.azor.main.MainView;
import com.azor.model.Account;
import com.azor.user.UserView;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;

public class AzorCoffee extends Application {

    public static final class layout{
        public static final int USER = 0;
        public static final int HOME = 1;
        public static final int MAIN = 2;
        static final int LOGIN = 3;
    }

    private static Stage primaryStage;
    private static Account currentAuth;

    private static ArrayList<ViewTuple> viewTuples = new ArrayList<>();

    public static void main(String[] args) {
        viewTuples.add(FluentViewLoader.fxmlView(UserView.class).load());
        viewTuples.add(FluentViewLoader.fxmlView(HomeView.class).load());
        viewTuples.add(FluentViewLoader.fxmlView(MainView.class).load());
        viewTuples.add(FluentViewLoader.fxmlView(LoginView.class).load());

        launch(args);
    }

    @Override
    public void start(Stage stage) {
        primaryStage = stage;

        setScene(layout.LOGIN);

        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    public static void centerOnScreen(){
        primaryStage.centerOnScreen();
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

    public static Parent getView(int id){
        return viewTuples.get(id).getView();
    }

    public static Account getCurrentAuth() {
        return currentAuth;
    }

    public static void setCurrentAuth(Account currentAuth) {
        AzorCoffee.currentAuth = currentAuth;
    }

    public static void minimize(){
        primaryStage.setIconified(true);
    }
}
