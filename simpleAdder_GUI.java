import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.text.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.event.*;
import javafx.scene.control.*;
import java.util.*;
import java.lang.Double.*;
import java.lang.String.*;
import java.lang.Object.*;

/**
 * Title: Assignment 2
 * Author: Alex Cortes
 * Date: September 16th 2014
 * 
 * A simple program that adds two doubles. It makes use of two text fields,
 * four texts and a button. If the input of the user is not a number, an 
 * error is given.
 * 
 * 
 */
public class simpleAdder_GUI extends Application 
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
    public void start(Stage primaryStage) 
    {
        //setting the stage, root and scene
        primaryStage.setTitle("AddMachine");
        Group root = new Group();
        Scene scene = new Scene(root, 350, 200);

        //the texts
        Text t1 = TextBuilder.create()
            .text("Enter a number:")
            .build();
        Text t2 = TextBuilder.create()
            .text("Enter another number:")
            .build();
        Text t3 = TextBuilder.create()
            .text("")
            .build();
        Text t4 = TextBuilder.create()
            .text("")
            .build();
        
        //the text fields
        final TextField op1 = TextFieldBuilder.create()
            .build();
        final TextField op2= TextFieldBuilder.create()
            .build();
         
        //the button    
        Button adder = ButtonBuilder.create()
            .layoutX(70)
            .layoutY(70)
            .text("Add!")
            .build();
        
        //add the button    
        root.getChildren().add(adder);

        //your vertical box
        VBox vb1 = VBoxBuilder.create()
            .padding(new Insets(10))     // space outside of this box
            .spacing(10)                 // space between box elements
            .alignment(Pos.CENTER)       // left-aligned by default
            .build();
        
        //your horizontal boxes
        HBox hb1 = HBoxBuilder.create()
            .spacing(50)
            .build();
        HBox hb2 = HBoxBuilder.create()
            .spacing(10)
            .build();
            
        EventHandler<ActionEvent> eh1 = new EventHandler<ActionEvent>()
            {
                @Override
                public void handle(ActionEvent event) 
                {
                    String num1 = op1.getText();
                    String num2 = op2.getText();
                    
                   //the try catch block. parseDouble throws two types of exceptions
                   //when any of the two exceptions are thrown an error code is given
                    try 
                    {
                        double operand1 = Double.parseDouble(num1);
                        double operand2 = Double.parseDouble(num2);
                    
                        double sum = operand1 + operand2;
                    
                        t3.setText(num1 + " plus " + num2 + " equals " + sum);
                        t4.setText("");
                   }
                   catch (NullPointerException noInput)
                   {
                       t3.setText(""); 
                       t4.setText("No input was given");
                   }
                   catch (NumberFormatException cantParse)
                   {
                       t3.setText("");
                       t4.setText("Unable to parse");
                   }
                }
            };    
        
        //add the action to the button    
        adder.setOnAction(eh1);
        
        //the layout of the program
        hb1.getChildren().addAll(t1, op1);
        hb2.getChildren().addAll(t2, op2);
        vb1.getChildren().addAll(hb1, hb2, adder, t3,t4);

        //show the program
        root.getChildren().add(vb1);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
