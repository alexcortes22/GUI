import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.event.*;
import javafx.scene.control.*;
import javafx.scene.text.*;
import javafx.scene.layout.*;
import javafx.scene.input.*;

import javafx.scene.shape.*;
import javafx.scene.paint.*;
import javafx.scene.effect.*;
import javafx.scene.image.*;

import javafx.geometry.*;


/**
 * Name: rockPaperScissors_GUI
 * Author: Alex Cortes
 * 
 * This program is a basic rock paper and scissors game. There
 * are 3 buttons in which the user chooses between the these 
 * choices. The program also displays your choice and the
 * computer's choice.
 * 
 */
public class rockPaperScissors_GUI extends Application 
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
        theStage.setTitle("Rock/Paper/Scissors");
        theStage.getIcons().add(new Image("pictures/rps.png"));

        Group root = new Group();
        Scene theScene = new Scene(root, 700, 700);
        
        //the background color, a mixture of two colors
        LinearGradient red = LinearGradientBuilder.create()
            .stops(
                new Stop(0, Color.rgb(164, 162, 162)),
                new Stop(1, Color.rgb(191, 28, 28))
            )
            .startX(0).startY(0)
            .endX(0).endY(1)
            .proportional(true)
            .build();

        //set the color
        theScene.setFill( red );

        VBox mainBox = VBoxBuilder.create()
            .padding(new Insets(50))     
            .spacing(10)                 
            .alignment(Pos.CENTER)       
            .build();

        mainBox.prefWidthProperty().bind( theStage.widthProperty() );
        
        root.getChildren().add(mainBox);

        //the images which will be loaded unto buttons
        final Image rock = new Image("pictures/rock.png");
        final Image paper = new Image("pictures/paper.png");
        final Image scissors = new Image ("pictures/scissors.png");
        final Image enemy = new Image("pictures/enemy.png");
        final Image user = new Image("pictures/user.png");
        final Image penny = new Image("pictures/tails.png");
        
        //the user and the computer, here the choices are displayed
        final ImageView badGuy = new ImageView(enemy);
        final ImageView goodGuy = new ImageView(user);
        
        //the font style in the program
        String styleData = ""
            + " -fx-font-family: Chalkduster;             "
            + " -fx-font-style:  normal;            "  // italic/normal
            + " -fx-font-weight: normal;              "  // bold/normal
            + " -fx-font-size:   20px;              "
            + " -fx-text-fill:   rgb(38, 142, 73); ";
        
        //the label that gives the result of the game
        final Label result = LabelBuilder.create()
            .text("Rock, Paper or Scissors?")
            .style( styleData )
            .build();
            
        //what happens when you press the rock?     
        Button rockButton = ButtonBuilder.create()
            .text("Rock")
            .graphic( new ImageView(rock))
            .contentDisplay( ContentDisplay.TOP)
            .style( styleData )
            .onAction( new EventHandler<ActionEvent>()
                {
                    @Override
                    public void handle(ActionEvent event) 
                    {
                        int user = 0;
                        
                        //A random number generator between 0 and 2
                        int comp = (int)Math.floor(Math.random() * 3);
                        goodGuy.setImage(rock);
                        
                        if (comp == 0)
                        {
                            badGuy.setImage(rock);
                            result.setText("You both chose Rock, Its a tie!");
                        }
                        else if (comp == 1)
                        { 
                            badGuy.setImage(paper);
                            result.setText("Rock is covered by paper. You lose.");
                        }
                        else if (comp == 2)
                        {
                            badGuy.setImage(scissors);
                            result.setText("Rock smashes scissors. You win!");
                        }   
                    }
                })
            .build();

        //what happens when you press the paper?
        Button paperButton = ButtonBuilder.create()
            .text("Paper")
            .graphic( new ImageView(new Image("pictures/paper.png")) )
            .contentDisplay (ContentDisplay.TOP)
            .style( styleData )
            .onAction( new EventHandler<ActionEvent>()
                {
                    @Override
                    public void handle(ActionEvent event) 
                    {
                        int user = 1;
                        int comp = (int)Math.floor(Math.random() * 3);
                        goodGuy.setImage(paper);
                        
                        if (comp == 0)
                        {
                            badGuy.setImage(rock);
                            result.setText("Paper covers rock. You win!");
                        }
                        else if (comp == 1)
                        { 
                            badGuy.setImage(paper);
                            result.setText("You both chose paper. Its a tie!");
                        }
                        else if (comp == 2)
                        {
                            badGuy.setImage(scissors);
                            result.setText("Paper is cut by scissors. You lose.");
                        }   
                    }
                })
            .build();
           
        //what happens when you press the scissors?
        Button scissorButton = ButtonBuilder.create()
            .text("Scissors")
            .graphic( new ImageView(new Image("pictures/scissors.png"))) 
            .contentDisplay (ContentDisplay.TOP)
            .style( styleData )
            .onAction( new EventHandler<ActionEvent>()
                {
                    @Override
                    public void handle(ActionEvent event) 
                    {
                        int user = 2;
                        int comp = (int)Math.floor(Math.random() * 3);
                        goodGuy.setImage(scissors);
                        
                        if (comp == 0)
                        {
                            badGuy.setImage(rock);
                            result.setText("Scissors are smashed by rock. You lose.");
                        }
                        else if (comp == 1)
                        { 
                            badGuy.setImage(paper);
                            result.setText("Scissors cut paper. You win!");
                        }
                        else if (comp == 2)
                        {
                            badGuy.setImage(scissors);
                            result.setText("You both chose scissors. Its a tie!");
                        }   
                    }
                })
            .build();

        //the first row which contains the buttons
        HBox hb1 = HBoxBuilder.create()
             .spacing(20)
             .build();
             
        //the row that contains the user and computer choices     
        HBox hb2 = HBoxBuilder.create()
            .spacing(50)
            .build();
            
        hb1.getChildren().addAll(rockButton, paperButton, scissorButton);
        hb2.getChildren().addAll(goodGuy, badGuy);

        mainBox.getChildren().addAll(hb1, hb2, result);

        theStage.setScene(theScene);
        theStage.show();
    }
}
