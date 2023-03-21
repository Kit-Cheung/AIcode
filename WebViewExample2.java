import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WebViewExample2 extends Application {

    @Override
    public void start(Stage stage) {
        WebView webView = new WebView();
        webView.getEngine().load("https://www.example.com");

        Button saveButton = new Button("Save");
        saveButton.setOnAction(event -> {
            String htmlContent = webView.getEngine().getDocument().toString();
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save HTML File");
            fileChooser.setInitialFileName("page.html");
            File selectedFile = fileChooser.showSaveDialog(stage);
            if (selectedFile != null) {
                try (FileWriter fileWriter = new FileWriter(selectedFile)) {
                    fileWriter.write(htmlContent);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        Scene scene = new Scene(new VBox(webView, saveButton), 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
