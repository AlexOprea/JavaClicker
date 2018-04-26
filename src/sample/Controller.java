package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable
{
   public static final String LC_CHECKBOX = "lcCheckbox";
   public static final String RC_CHECKBOX = "rcCheckbox";
   private static Robot robot = null;

   @FXML
   private Button startButton;

   @FXML
   private Button stopButton;

   private boolean lcClicked = false;
   private boolean rcClicked = false;

   private boolean loop = false;

   @Override
   public void initialize(URL location, ResourceBundle resources)
   {

   }

   @FXML
   private void handleStop(final ActionEvent event)
   {
      loop = false;
   }

   @FXML
   private void handleStart(final ActionEvent event) throws InterruptedException
   {
      try
      {
         robot = new Robot();
      }
      catch (AWTException e)
      {
         e.printStackTrace();
      }
      loop = true;
      while (loop)
      {
         if (lcClicked)
            click(MouseBtn.LEFT);
         if (rcClicked)
            click(MouseBtn.RIGHT);
         Thread.sleep(15*100);
      }
   }

   public static void click (MouseBtn mouseBtn)
   {
      Point point = MouseInfo.getPointerInfo().getLocation();
      robot.mouseMove(point.x, point.y);
      switch (mouseBtn)
      {
         case RIGHT:
            robot.delay(5);
            robot.mousePress(MouseEvent.BUTTON3_MASK);
            robot.mouseRelease(MouseEvent.BUTTON3_MASK);
            break;
         case LEFT:
            robot.delay(5);
            robot.mousePress(MouseEvent.BUTTON1_MASK);
            robot.mouseRelease(MouseEvent.BUTTON1_MASK);
            break;
         default: break;
      }
   }

   @FXML
   private void onSelectedCheckbox(ActionEvent event)
   {
      if (!(event.getSource() instanceof CheckBox))
      {
         return;
      }
      final CheckBox checkBox = (CheckBox)event.getSource();
      if (LC_CHECKBOX.equals(checkBox.getId()))
      {
         if (checkBox.isSelected())
            lcClicked = true;
         else
            lcClicked = false;
      }else if (RC_CHECKBOX.equals(checkBox.getId()))
      {
         if (checkBox.isSelected())
            rcClicked = true;
         else
            rcClicked = false;
      }else
      {
         return;
      }
   }

   private enum MouseBtn{
      LEFT,
      RIGHT
   }
}
