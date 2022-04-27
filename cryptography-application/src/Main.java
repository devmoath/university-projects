import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static FXMLLoader loader;

    @Override
    public void start(Stage primaryStage) throws Exception{
        loader = new FXMLLoader(getClass().getResource("sample\\main_sample.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        loader.setController(new Controller());
        primaryStage.setTitle("Cryptography Application");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}