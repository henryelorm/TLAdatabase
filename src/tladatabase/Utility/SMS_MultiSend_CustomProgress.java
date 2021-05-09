/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tladatabase.Utility;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javax.persistence.EntityManager;
import org.smslib.OutboundMessage;
import org.smslib.Service;
import tladatabase.Controllers.OptionsController;
import tladatabase.Entity.Student;
import tladatabase.EntityManager.StudentManager;
import tladatabase.MainPage;

/**
 *
 * @author Elorm
 */
public class SMS_MultiSend_CustomProgress {

    private final VBox vBox = new VBox();
    private final HBox hBox = new HBox();
    private final HBox hBox2 = new HBox();

    ProgressBar progress = new ProgressBar(0.01);

    public void loadCustomizeLoadProgress(EntityManager em, Stage primaryStage, String comment) {

        OptionStage ops = new OptionStage();

        primaryStage.setTitle("Sending Message");
        Group root = new Group();
        Text text = new Text("Sending: " + 0 + " / " + 0);

        Scene scene = new Scene(root, 363, 95);

        progress.setPrefWidth(300);
        hBox.getChildren().addAll(progress);
        hBox.setPadding(new Insets(25, 0, 0, 30));
        hBox.setSpacing(20);
        hBox2.getChildren().add(text);
        hBox2.setPrefWidth(500);
        hBox2.setPadding(new Insets(15, 0, 0, 32));
        vBox.getChildren().addAll(hBox, hBox2);

        StudentManager stm = new StudentManager();
        List<Student> student = stm.findAllStudents(em);

        root.getChildren().add(vBox);
        primaryStage.setScene(scene);
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.initStyle(StageStyle.UNIFIED);
        primaryStage.initOwner(MainPage.stage);
        primaryStage.setResizable(false);

        startSending(em, student, text, comment);

        primaryStage.showAndWait();

    }

    private void startSending(final EntityManager em, List<Student> student, Text text, String comment) {
        final Task<Void> task = new Task<Void>() {
            @Override
            public Void call() throws Exception {

                int i = 0;
                int size = FetchNumberForSMS.getPhoneNumbers(em).size();
                System.err.println(size);
                OutboundMessage msg = new OutboundMessage();
                if (size != 0) {
                    while (i < size) {

                        student.get(i).getPhoneNo();
                        Thread.sleep(400);

                        // OutboundMessage msg = new OutboundMessage(FetchNumberForSMS.getPhoneNumbers(em).get(i), comment);
                        msg.setRecipient(FetchNumberForSMS.getPhoneNumbers(em).get(i));
                        msg.setText(comment);
                        Service.getInstance().sendMessage(msg);
                        updateProgress(i + 1, size);
                        text.setText("Sending: " + (i + 1) + " / " + size);

                        ++i;
                    }
                    text.setText("Messages Status: " + msg.getMessageStatus() + ", Please Close Window");
                    System.out.println(msg.getMessageStatus());
                } else {

                    text.setText("No Numbers Found In This Class");
                }

                return null;
            }
        };

        progress.progressProperty().bind(task.progressProperty());
        new Thread(task).start();

    }

}
