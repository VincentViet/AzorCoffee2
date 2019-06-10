package com.azor;

import com.azor.home.HomeView;
import com.azor.login.LoginView;
import com.azor.main.MainView;
import com.azor.manager.ManagerView;
import com.azor.manager.add.AddView;
import com.azor.manager.food.FoodView;
import com.azor.model.Account;
import com.azor.user.password.PasswordView;
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
        public static final int FOOD = 0;
        public static final int ADD = 1;
        public static final int PASSWORD = 2;
        public static final int MANAGER = 3;
        public static final int USER = 4;
        public static final int HOME = 5;
        public static final int MAIN = 6;
        static final int LOGIN = 7;
    }

    private static Stage primaryStage;
    private static Account currentAuth;

    private static ArrayList<ViewTuple> viewTuples = new ArrayList<>();

    public static void main(String[] args) {
        viewTuples.add(FluentViewLoader.fxmlView(FoodView.class).load());
        viewTuples.add(FluentViewLoader.fxmlView(AddView.class).load());
        viewTuples.add(FluentViewLoader.fxmlView(PasswordView.class).load());
        viewTuples.add(FluentViewLoader.fxmlView(ManagerView.class).load());
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
