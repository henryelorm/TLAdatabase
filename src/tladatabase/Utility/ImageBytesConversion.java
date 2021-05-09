/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tladatabase.Utility;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import tladatabase.Entity.Student;
import tladatabase.Entity.StudentImage;

/**
 *
 * @author Elorm
 */
public class ImageBytesConversion extends ChooseImage {

    public static void getImagebitsForStore(StudentImage stdi) throws IOException {

        if (getimage != null) {
            BufferedImage bimage = SwingFXUtils.fromFXImage(getimage, null);

            try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                ImageIO.write(bimage, "jpg", baos);
                baos.flush();
                byte[] imageInByte = baos.toByteArray();
                stdi.setImage(imageInByte);
            }

        } else {

            stdi.setImage(null);
        }
    }
    
    public static void getImagebitsForStoreEdit(StudentImage stdi,Image getTheImage) throws IOException {

        if (getTheImage != null) {
            BufferedImage bimage = SwingFXUtils.fromFXImage(getTheImage, null);

            try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                ImageIO.write(bimage, "jpg", baos);
                baos.flush();
                byte[] imageInByte = baos.toByteArray();
                stdi.setImage(imageInByte);
            }

        } else {

            stdi.setImage(null);
        }
    }

    public static void isFromBytestoImage(EntityManager em, ImageView imageView, String id) throws IOException {

        StudentImage stdi = em.find(StudentImage.class, id);

        if (stdi.getImage() != null) {
            ByteArrayInputStream bais = new ByteArrayInputStream(stdi.getImage());

            while (bais.available() > 0) {
                BufferedImage buffImage = ImageIO.read(bais);

                final Image image = SwingFXUtils.toFXImage(buffImage, null);

                imageView.setImage(image);

            }

        } else {

            Image i = new Image("tladatabase/Images/noImage.gif");

            imageView.setImage(i);
        }
    }

}
