import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("/Server.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        stage.setTitle("Server");
        stage.setScene(scene);
        stage.setX(50);
        stage.setY(50);
        stage.show();

        FXMLLoader fxmlLoader1=new FXMLLoader(getClass().getResource("/Client.fxml"));
        Scene scene1 = new Scene(fxmlLoader1.load());

        Stage stage1 = new Stage();
        stage1.setTitle("Client");
        stage1.setScene(scene1);
        stage1.setX(700);
        stage1.setY(50);
        stage1.show();
    }
}
