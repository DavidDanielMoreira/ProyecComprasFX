package principal;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Principal extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/formMenuPrincipal.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/css/estilos.css").toExternalForm());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("MENU PRINCIPAL");
        stage.show();
        stage.setOnCloseRequest(e->{
            Platform.exit();
            System.exit(0);
        });
    }
    public static void main(String[] args){
        launch(args);
    }
}
