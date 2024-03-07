package Backend;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class NotificationAlert {

    public static void display(Notification notification) {
        Stage window = new Stage();

        // Set up the window
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Notification");
        window.setMinWidth(250);

        // Create the components
        Label messageLabel = new Label(notification.getMessage());
        Label statusLabel = new Label(notification.isRequestAccepted() ? "Request Accepted" : "Request Declined");
        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> window.close());

        // Layout
        VBox layout = new VBox(10);
        layout.getChildren().addAll(messageLabel, statusLabel, closeButton);
        layout.setAlignment(Pos.CENTER);

        // Set scene and show window
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}
