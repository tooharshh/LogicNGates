package project.logicgatesimulator;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.geometry.Point2D;
import java.util.Stack;
import java.util.Objects;

public class CircuitHistory {
    private Stack<CircuitState> undoStack;
    private Stack<CircuitState> redoStack;
    private Pane pane;

    public CircuitHistory(Pane pane) {
        this.undoStack = new Stack<>();
        this.redoStack = new Stack<>();
        this.pane = pane;
    }

    public void saveState() {
        CircuitState state = captureCurrentState();
        undoStack.push(state);
        redoStack.clear();
    }

    public void undo() {
        if (undoStack.size() > 1) {
            CircuitState currentState = undoStack.pop();
            redoStack.push(currentState);
            
            CircuitState previousState = undoStack.peek();
            restoreState(previousState);
        }
    }

    public void redo() {
        if (canRedo()) {
            CircuitState nextState = redoStack.pop();
            undoStack.push(nextState);
            restoreState(nextState);
        }
    }

    public boolean canUndo() {
        return undoStack.size() > 1;
    }

    public boolean canRedo() {
        return !redoStack.isEmpty();
    }

    private CircuitState captureCurrentState() {
        CircuitState state = new CircuitState();
        
        for (Node node : pane.getChildren()) {
            if (node instanceof ImageView) {
                ImageView imageView = (ImageView) node;
                Component component = (Component) imageView.getUserData();
                if (component != null) {
                    String type = component.getClass().getSimpleName();
                    String imageUrl = imageView.getImage().getUrl();
                    CircuitState.ComponentData data = new CircuitState.ComponentData(
                        type,
                        imageView.getLayoutX(),
                        imageView.getLayoutY(),
                        component.input1,
                        component.input2,
                        imageUrl
                    );
                    state.addComponent(data);
                }
            } else if (node instanceof Line) {
                Line line = (Line) node;
                Wire wire = (Wire) line.getUserData();
                if (wire != null) {
                    CircuitState.WireData data = new CircuitState.WireData(
                        wire.sp.getX(),
                        wire.sp.getY(),
                        wire.ep.getX(),
                        wire.ep.getY()
                    );
                    state.addWire(data);
                }
            }
        }
        
        return state;
    }

    private void restoreState(CircuitState state) {
        pane.getChildren().clear();
        Wire.allWires.clear();
        Wire.points.clear();
        
        for (CircuitState.ComponentData compData : state.getComponents()) {
            try {
                Image image = new Image(compData.imageUrl);
                ImageView imageView = new ImageView(image);
                imageView.setLayoutX(compData.x);
                imageView.setLayoutY(compData.y);
                imageView.setPreserveRatio(true);
                
                Component component = null;
                
                switch (compData.type) {
                    case "ANDgate":
                        component = new ANDgate();
                        imageView.setFitHeight(60);
                        imageView.setFitWidth(100);
                        break;
                    case "ORgate":
                        component = new ORgate();
                        imageView.setFitHeight(60);
                        imageView.setFitWidth(118);
                        break;
                    case "NOTgate":
                        component = new NOTgate();
                        imageView.setFitHeight(60);
                        imageView.setFitWidth(100);
                        break;
                    case "NANDgate":
                        component = new NANDgate();
                        imageView.setFitHeight(60);
                        imageView.setFitWidth(100);
                        break;
                    case "NORgate":
                        component = new NORgate();
                        imageView.setFitHeight(60);
                        imageView.setFitWidth(118);
                        break;
                    case "XORgate":
                        component = new XORgate();
                        imageView.setFitHeight(60);
                        imageView.setFitWidth(118);
                        break;
                    case "XNORgate":
                        component = new XNORgate();
                        imageView.setFitHeight(60);
                        imageView.setFitWidth(118);
                        break;
                    case "Toggle":
                        component = new Toggle();
                        imageView.setFitHeight(30);
                        imageView.setFitWidth(30);
                        break;
                    case "Probe":
                        component = new Probe();
                        imageView.setFitHeight(30);
                        imageView.setFitWidth(30);
                        break;
                }
                
                if (component != null) {
                    component.gateImageView = imageView;
                    component.gateImage = image;
                    component.input1 = compData.input1;
                    component.input2 = compData.input2;
                    imageView.setUserData(component);
                    
                    component.enableEventListeners(pane);
                    pane.getChildren().add(imageView);
                }
            } catch (Exception e) {
                System.err.println("Error restoring component: " + e.getMessage());
            }
        }
        
        for (CircuitState.WireData wireData : state.getWires()) {
            try {
                Wire wire = new Wire();
                wire.sp = new Point2D(wireData.startX, wireData.startY);
                wire.ep = new Point2D(wireData.endX, wireData.endY);
                
                Line line = new Line(wireData.startX, wireData.startY, wireData.endX, wireData.endY);
                line.setStrokeWidth(2);
                line.setUserData(wire);
                wire.wireLine = line;
                
                Wire.points.add(wire.sp);
                Wire.points.add(wire.ep);
                Wire.allWires.add(wire);
                
                pane.getChildren().add(line);
                
                Component comp = new Component() {
                    @Override
                    public boolean getOutputValue() {
                        return false;
                    }
                };
                comp.enableEventListeners(pane);
            } catch (Exception e) {
                System.err.println("Error restoring wire: " + e.getMessage());
            }
        }
        
        Wire.simulator();
    }
}
