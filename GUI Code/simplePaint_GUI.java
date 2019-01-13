import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.text.*;
import javafx.event.*; 
import javafx.scene.input.*;
import javafx.scene.shape.*;
import javafx.scene.paint.Color;
import java.util.*;

/**
 * Title: simplePaint_GUI
 * Author: Alex Cortes
 * Date: September 16th 2014
 * 
 * A simple program that "paints" when you drag your mouse. To clear the 
 * drawing pad, simply press backspace. To change the color to red or blue
 * press the 'r' or 'b'key respectively.
 * 
 */
public class simplePaint_GUI extends Application 
{
    /**
     *   Runs the application.
     *   Note: Application is a Process, only one allowed per VM.
     *   Reset the Java Virtual Machine in BlueJ after each run.
     */
    public static void main(String[] args) 
    {
        launch(args); // start the program
    }
    
   
    //create a color object that represents the color of the circle
    Color circColor = Color.BLACK;
    @Override
    public void start(Stage theStage) 
    {
        theStage.setTitle("Paint!");                    //The title of the stage

        Group root = new Group();                       //add a root to the stage
        Scene theScene = new Scene(root, 400, 400);     //add a scene to the root

        // this variable must be declared as final so inner classes can access it
        final Text message = TextBuilder.create()
            .layoutX(30)
            .layoutY(30)
            .text("Drag the mouse to paint!")
            .build();
            
        root.getChildren().add(message);               //add the message to the root
        
        
        // event listener (using anonymous inner class)
        theScene.setOnMouseDragged( 
            new EventHandler<MouseEvent>()
            {
                @Override
                public void handle(MouseEvent event) 
                {
                  //save the x and y position of the mouse to variables
                  double x = event.getX();
                  double y = event.getY();
                  
                  //create a circle where the position changes as the mouse moves
                  final Circle circ = CircleBuilder.create()
                  .centerX(x)
                  .centerY(y)
                  .radius(10)
                  .fill(circColor)
                  .build();
                
                  //add the circle to the scene
                  root.getChildren().add(circ);
                 }
             }
            
        );
        
        
        theScene.setOnKeyPressed(
            new EventHandler<KeyEvent>()
            {
                @Override
                public void handle (KeyEvent event)
                {
                   //convert the key code to a string
                   String input = event.getCode().toString();
                        
                   //compare the string to Back_Space, R, and B. A different
                   //action occurs depending on the case.
                   if (input == "BACK_SPACE")
                   {
                       root.getChildren().clear();
                   }
                   else if (input == "R")
                   {
                       circColor = Color.RED;
                   }
                   else if (input == "B")
                   {
                       circColor = Color.BLUE;
                   }
                        
                }
            }
        ); 
        
        //add the scene to the stage and show it
        theStage.setScene(theScene);
        theStage.show();
    }
}
