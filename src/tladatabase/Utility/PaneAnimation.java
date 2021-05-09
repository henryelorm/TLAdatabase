/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tladatabase.Utility;

import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import tladatabase.Entity.Student;

/**
 *
 * @author Elorm
 */
public class PaneAnimation {

    Animation reducePane;

    public void stackPaneAnimation(StackPane stackPane, TableView<Student> table) {
        System.err.println("hfdhf");
        stackPane.setPrefWidth(100);
        
        
        reducePane = new Transition() {
            { 
                     setCycleDuration(Duration.millis(250));
            }

            @Override
            protected void interpolate(double frac) {

                final double size = stackPane.getWidth() * (1.0 - frac);
                System.out.println(size);
                stackPane.setPrefWidth(100);
                 
            }
        };

    }

}
