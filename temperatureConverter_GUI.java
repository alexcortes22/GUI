package AllProjects;

import javafx.application.*;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.image.*;
import java.text.*;

import javafx.geometry.*;

import javafx.beans.binding.*;
import javafx.beans.property.*;
import javafx.beans.value.*;

/**
 * Title: temperatureConverter_GUI
 * Author: Alex Cortes
 * Date: October 2nd 2014
 * 
 * A program that converts the temperature from Fahrenheit to Celsius. The Fahrenheit value
 * is taken from the slider in the program.
 * 
 */
public class temperatureConverter_GUI extends Application 
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
    public void start(Stage theStage) 
    {
        theStage.setTitle("Hello World");

        Group root = new Group();
        Scene theScene = new Scene(root);
        
        //the background color
        LinearGradient red = LinearGradientBuilder.create()
            .stops(
                new Stop(0, Color.rgb(191, 28, 28)),
                new Stop(1, Color.rgb(112, 155, 252))
                )
            .startX(0).startY(0)
            .endX(0).endY(1)
            .proportional(true)
            .build();

        //set the color
        theScene.setFill( red );
        
        //create the main box
        VBox mainBox = VBoxBuilder.create()
            .padding(new Insets(10))     // space outside of this box
            .spacing(10)                 // space between box elements
            .alignment(Pos.CENTER)       // left-aligned by default
            .build();

        root.getChildren().add(mainBox);

        // code -------------------------------------------------
        final Text t1 = TextBuilder.create()
            .text("Select the temperature in fahrenheit")
            .build();
            
        mainBox.getChildren().addAll(t1);   
        
        //the slider
        Slider tempSlider = SliderBuilder.create()
            .prefWidth(400)
            .prefHeight(500)
            .min(-50)
            .max(250)
            .value(0)
            .majorTickUnit(10)
            .minorTickCount(10)
            .showTickLabels(true)
            .showTickMarks(true)
            .snapToTicks(true)
            .orientation (Orientation.VERTICAL)
            .build();
            
        //the text the shows the temperature in fahrenheit    
        final Text fahrenheit = TextBuilder.create()
            .text (" ")
            .build();
           
        mainBox.getChildren().addAll(fahrenheit, tempSlider);
        
        //the text that shows the temperature in celsius
        final Text celsiusText = TextBuilder.create()
            .text(" ")
            .build();
        
        mainBox.getChildren().add(celsiusText);
            
        //binding the slider value to the fahrenheit text
        StringBinding convertTemp = new StringBinding()
        {
            {
                super.bind(tempSlider.valueProperty());
            }
            
            @Override
            protected String computeValue() 
            {
                double d = Math.round(tempSlider.getValue());
                return d + " ";
            }
        };
        fahrenheit.textProperty().bind(convertTemp);
        
        //binding the slider value to the celsius text
        StringBinding convertCelsius = new StringBinding()
        {
            {
                super.bind(tempSlider.valueProperty());
            }
            
            @Override
            protected String computeValue()
            {
                String celsius;
                double d = Math.round(tempSlider.getValue());
                
                DecimalFormat df = new DecimalFormat("#.##");
                double celsius2 = ((d - 32) * 5)/9.0 ;
                
                celsius = (df.format(celsius2) + " ");
                
                return "The temperature in celsius is: " + celsius;
            }
        };
        celsiusText.textProperty().bind(convertCelsius);
        
        //the images of the stages of water
        final Image question = new Image ("pictures2/question.png");
        final Image ice = new Image ("pictures2/ice.png");
        final Image water = new Image ("pictures2/water.png");
        final Image steam = new Image ("pictures2/steam.png");
        
        final ImageView waterView = new ImageView (question);
        mainBox.getChildren().add(waterView);
        
        //when the slider value changes, the image in the imageView changes.
        tempSlider.valueProperty().addListener( 
            new ChangeListener()
            {
                public void changed(ObservableValue o, Object oldValue, Object newValue)
                {
                    Double sliderVal = (Double)newValue;
                    if (sliderVal <= 32) {
                        waterView.setImage(ice);
                    }
                    else if (sliderVal > 32 && sliderVal < 212) {
                        waterView.setImage(water);
                    } else {
                        waterView.setImage(steam);
                    }
                    
                }            
            });
 
         
        // code -------------------------------------------------
        
        theStage.setScene(theScene);
        theStage.show();
    }
}
