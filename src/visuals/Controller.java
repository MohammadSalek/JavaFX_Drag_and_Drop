package visuals;

import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Controller {

    private double sceneX, sceneY;
    private double shapeX, shapeY;
    @FXML private Rectangle rect0;
    @FXML private Rectangle rect1;

    @FXML
    private void initialize() {
        dragAndDropHandler(rect0);
        dragAndDropHandler(rect1);
    }

    private void dragAndDropHandler(Rectangle rect) {
        rect.setOnMouseEntered(event -> rect.setCursor(Cursor.HAND));

        rect.setOnMousePressed(event -> {
            System.out.println("Mouse Pressed.");
            sceneX = event.getSceneX();
            sceneY = event.getSceneY();
            shapeX = ((Rectangle) event.getSource()).getTranslateX();
            shapeY = ((Rectangle) event.getSource()).getTranslateY();
            rect.setMouseTransparent(true);
            rect.setEffect(new DropShadow(20.0, Color.BLACK));
            event.setDragDetect(true);
        });

        rect.setOnMouseDragged(event -> {
//            System.out.println("Mouse Dragged.");
            double offsetX = event.getSceneX() - sceneX;
            double offsetY = event.getSceneY() - sceneY;
            double newShapeX = shapeX + offsetX;
            double newShapeY = shapeY + offsetY;
            ((Rectangle) event.getSource()).setTranslateX(newShapeX);
            ((Rectangle) event.getSource()).setTranslateY(newShapeY);
            rect.setCursor(Cursor.CLOSED_HAND);
            rect.setEffect(new DropShadow(20.0, Color.BLUEVIOLET));
            event.setDragDetect(false);
        });

        rect.setOnMouseReleased(event -> {
            System.out.println("Released!");
            rect.setMouseTransparent(false);
            rect.setEffect(null);
        });
    }

}
