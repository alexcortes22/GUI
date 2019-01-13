import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.event.*;
import javafx.scene.control.*;
import javafx.scene.text.*;
import javafx.scene.layout.*;
import javafx.geometry.*;

// JavaFX documentation: http://docs.oracle.com/javafx/2/api/

/**
 * @author Lee Stemkoski
 */
public class DialogBoxes extends Application 
{
    /**
     *   runs the application.
     *   Note: Application is a Process, only one allowed per VM.
     */
    public static void main(String[] args) 
    {
        launch(args);
    }

    @Override
    public void start(final Stage theStage) 
    {
        theStage.setTitle("Hello World");

        Group root = new Group();
        Scene theScene = new Scene(root);

        VBox mainBox = VBoxBuilder.create()
            .padding(new Insets(10))     // space outside of this box
            .spacing(10)                 // space between box elements
            .alignment(Pos.CENTER)       // left-aligned by default
            .build();

        root.getChildren().add(mainBox);

        Button infoButton = ButtonBuilder.create()
            .text("Show Information")
            .onAction( new EventHandler<ActionEvent>()
                {
                    @Override
                    public void handle(ActionEvent event) 
                    {
                        Dialogs.showInformationDialog(null, "Hello, world!", "Title");
                    }
                })
            .build();

        Button inputButton = ButtonBuilder.create()
            .text("Get User Input")
            .onAction( new EventHandler<ActionEvent>()
                {
                    @Override
                    public void handle(ActionEvent event) 
                    {
                        String s = Dialogs.showInputDialog(null, "Type something!", "Title");
                        System.out.println("You entered: " + s);
                    }
                })
            .build(); 
            
        mainBox.getChildren().addAll( infoButton, inputButton );

        theStage.setScene(theScene);
        theStage.show();
    }

}
