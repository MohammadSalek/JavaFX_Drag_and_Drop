package visuals;

import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.Cursor;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Controller {

    private double sceneX, sceneY;
    private double shapeX, shapeY;
    private double distanceX, distanceY;
    @FXML private Rectangle source;
    @FXML private Rectangle dest;
    @FXML private Rectangle borderRect;

    @FXML
    private void initialize() {
        dragAndDropHandler(source);
        Bounds boundsInSceneSource = source.localToScene(source.getBoundsInLocal());
        Bounds boundsInSceneDest = dest.localToScene(dest.getBoundsInLocal());
        distanceX = boundsInSceneDest.getMinX() - boundsInSceneSource.getMinX();
        distanceY = boundsInSceneDest.getMinY() - boundsInSceneSource.getMinY();
    }

    private void dragAndDropHandler(Rectangle rect) {
        rect.setOnMouseEntered(event -> rect.setCursor(Cursor.HAND));
        rect.setOnMousePressed(event -> {
            System.out.println("Mouse Pressed!");
            sceneX = event.getSceneX();
            sceneY = event.getSceneY();
            shapeX = ((Rectangle) event.getSource()).getX();
            shapeY = ((Rectangle) event.getSource()).getY();
            rect.setMouseTransparent(true);
            rect.setEffect(new DropShadow(20.0, Color.BLACK));
            event.setDragDetect(true);
        });
        rect.setOnMouseDragged(event -> {
//            System.out.println("Mouse Dragged!");
            double offsetX = event.getSceneX() - sceneX;
            double offsetY = event.getSceneY() - sceneY;
            double newShapeX = shapeX + offsetX;
            double newShapeY = shapeY + offsetY;
            ((Rectangle) event.getSource()).setX(newShapeX);
            ((Rectangle) event.getSource()).setY(newShapeY);
            rect.setCursor(Cursor.CLOSED_HAND);
            rect.setEffect(new DropShadow(20.0, Color.BLUEVIOLET));
            event.setDragDetect(false);
            if (source.getBoundsInParent().contains(dest.getBoundsInParent())) {
                source.setX(distanceX);
                source.setY(distanceY);
            }
        });
        rect.setOnMouseReleased(event -> {
            System.out.println("Mouse Released!");
            rect.setMouseTransparent(false);
            rect.setEffect(null);
        });
    }
}
