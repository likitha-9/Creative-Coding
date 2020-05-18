import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application {

	static FlowPane flow = new FlowPane();
	Button addEvent = new Button("Add Event"), delEvent = new Button("Delete Event"),
			editEvent = new Button("Edit Event");

	@Override
	public void start(Stage mainStage) throws Exception {
		Scene scene = new Scene(flow, 280, 500);

		flow.getChildren().add(addButtons());

		addEvent.setOnMouseClicked(e -> {
			AddEvent.addEvent();
		});

		mainStage.setTitle("To-Do List");
		mainStage.setScene(scene);
		mainStage.setResizable(false);// disables maximizing of window
		mainStage.show();
	}

	public HBox addButtons() {
		HBox hbox = new HBox(10); // sets spacing=10
		hbox.setPadding(new Insets(20));
		hbox.getChildren().addAll(addEvent, delEvent, editEvent);
		return hbox;
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}