package project.logicgatesimulator;

import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Wire extends Component{
    Pane pane;
    ImageView imageView;
    MouseEvent event;
    private ImageView startNode, endNode;
    private Component inNode, outNode;
    protected Line wireLine = new Line();
    private double startX, startY, endX, endY;
    private boolean listenersActive = true;
    protected Point2D sp , ep;
    private double startgateX, startgateY, endgateX, endgateY, gateWidth, gateHeight, mouseX, mouseY;
    protected static List<Point2D> points = new ArrayList<>();
    protected static List<Wire> allWires = new ArrayList<>();
    private boolean signalVal;

    public Wire() {
    }

    public Wire(MouseEvent e, Pane pane) {

        wireLine.setStrokeWidth(3);
        wireLine.setStroke(Color.BLACK);
        pane.getChildren().add(wireLine);
        this.wireLine.setUserData(this);

        wireLine.setOnMouseClicked(this::handleImageClicked);

        pane.getChildren().forEach(node -> {
            if (node instanceof ImageView imageView) {

                imageView.setOnMousePressed(event -> {
                    if (startNode == null && listenersActive) {

                        startgateX = imageView.getLayoutX();
                        startgateY = imageView.getLayoutY();
                        gateWidth = imageView.getFitWidth();
                        gateHeight = imageView.getFitHeight();

                        mouseX = event.getX() + startgateX;
                        mouseY = event.getY() + startgateY;

                        if (imageView.getImage().getUrl().contains("Images/AND_Gate.png") | imageView.getImage().getUrl().contains("Images/OR_Gate.png") | imageView.getImage().getUrl().contains("Images/NAND_Gate.png") | imageView.getImage().getUrl().contains("Images/NOR_Gate.png") | imageView.getImage().getUrl().contains("Images/XOR_Gate.png") | imageView.getImage().getUrl().contains("Images/XNOR_Gate.png")) {
                            if (mouseY > startgateY && mouseY < startgateY + gateHeight / 3) {
                                startX = startgateX;
                                startY = startgateY + gateHeight / 4;
                                sp = new Point2D(startX,startY);
                                if (!(points.contains(sp))){
                                    startNode = imageView;
                                    wireLine.setStartX(startX);
                                    wireLine.setStartY(startY);
                                    wireLine.setEndX(startX);
                                    wireLine.setEndY(startY);
                                    points.add(sp);
                                    pane.setCursor(Cursor.CROSSHAIR);
                                    if (imageView.getImage().getUrl().contains("Images/AND_Gate.png"))
                                        outNode = (ANDgate) imageView.getUserData();
                                    else if (imageView.getImage().getUrl().contains("Images/OR_Gate.png"))
                                        outNode = (ORgate) imageView.getUserData();
                                    else if (imageView.getImage().getUrl().contains("Images/NAND_Gate.png"))
                                        outNode = (NANDgate) imageView.getUserData();
                                    else if (imageView.getImage().getUrl().contains("Images/NOR_Gate.png"))
                                        outNode = (NORgate) imageView.getUserData();
                                    else if (imageView.getImage().getUrl().contains("Images/XOR_Gate.png"))
                                        outNode = (XORgate) imageView.getUserData();
                                    else if (imageView.getImage().getUrl().contains("Images/XNOR_Gate.png"))
                                        outNode = (XNORgate) imageView.getUserData();
                                }
                                else
                                    showWarning("More than one wire cannot be connected to the same input terminal!");
                            } else if (mouseY > startgateY + 2 * gateHeight / 3 && mouseY < startgateY + gateHeight) {
                                startX = startgateX;
                                startY = startgateY + 3 * gateHeight / 4;
                                sp = new Point2D(startX,startY);
                                if (!(points.contains(sp))){
                                    startNode = imageView;
                                    wireLine.setStartX(startX);
                                    wireLine.setStartY(startY);
                                    wireLine.setEndX(startX);
                                    wireLine.setEndY(startY);
                                    points.add(sp);
                                    pane.setCursor(Cursor.CROSSHAIR);
                                    if (imageView.getImage().getUrl().contains("Images/AND_Gate.png"))
                                        outNode = (ANDgate) imageView.getUserData();
                                    else if (imageView.getImage().getUrl().contains("Images/OR_Gate.png"))
                                        outNode = (ORgate) imageView.getUserData();
                                    else if (imageView.getImage().getUrl().contains("Images/NAND_Gate.png"))
                                        outNode = (NANDgate) imageView.getUserData();
                                    else if (imageView.getImage().getUrl().contains("Images/NOR_Gate.png"))
                                        outNode = (NORgate) imageView.getUserData();
                                    else if (imageView.getImage().getUrl().contains("Images/XOR_Gate.png"))
                                        outNode = (XORgate) imageView.getUserData();
                                    else if (imageView.getImage().getUrl().contains("Images/XNOR_Gate.png"))
                                        outNode = (XNORgate) imageView.getUserData();
                                }
                                else
                                    showWarning("More than one wire cannot be connected to the same input terminal!");
                            } else if (mouseX > startgateX && mouseX < startgateX + gateWidth) {
                                startX = startgateX + gateWidth;
                                startY = startgateY + gateHeight / 2;
                                startNode = imageView;
                                wireLine.setStartX(startX);
                                wireLine.setStartY(startY);
                                wireLine.setEndX(startX);
                                wireLine.setEndY(startY);
                                pane.setCursor(Cursor.CROSSHAIR);
                                if (imageView.getImage().getUrl().contains("Images/AND_Gate.png"))
                                    inNode = (ANDgate) imageView.getUserData();
                                else if (imageView.getImage().getUrl().contains("Images/OR_Gate.png"))
                                    inNode = (ORgate) imageView.getUserData();
                                else if (imageView.getImage().getUrl().contains("Images/NAND_Gate.png"))
                                    inNode = (NANDgate) imageView.getUserData();
                                else if (imageView.getImage().getUrl().contains("Images/NOR_Gate.png"))
                                    inNode = (NORgate) imageView.getUserData();
                                else if (imageView.getImage().getUrl().contains("Images/XOR_Gate.png"))
                                    inNode = (XORgate) imageView.getUserData();
                                else if (imageView.getImage().getUrl().contains("Images/XNOR_Gate.png"))
                                    inNode = (XNORgate) imageView.getUserData();
                            }
                        }
                        else if (imageView.getImage().getUrl().contains("Images/NOT_Gate.png")) {
                            if (mouseY > startgateY && mouseY < startgateY + gateHeight) {
                                if (mouseX > startgateX && mouseX < startgateX + gateWidth / 2) {
                                    startX = startgateX;
                                    startY = startgateY + gateHeight / 2;
                                    sp = new Point2D(startX,startY);
                                    if (!(points.contains(sp))){
                                        startNode = imageView;
                                        wireLine.setStartX(startX);
                                        wireLine.setStartY(startY);
                                        wireLine.setEndX(startX);
                                        wireLine.setEndY(startY);
                                        points.add(sp);
                                        pane.setCursor(Cursor.CROSSHAIR);
                                        outNode = (NOTgate) imageView.getUserData();
                                    }
                                    else
                                        showWarning("More than one wire cannot be connected to the same input terminal!");
                                } else if (mouseX > startgateX + gateWidth / 2 && mouseX < startgateX + gateWidth) {
                                    startX = startgateX + gateWidth;
                                    startY = startgateY + gateHeight / 2;
                                    startNode = imageView;
                                    wireLine.setStartX(startX);
                                    wireLine.setStartY(startY);
                                    wireLine.setEndX(startX);
                                    wireLine.setEndY(startY);
                                    pane.setCursor(Cursor.CROSSHAIR);
                                    inNode = (NOTgate) imageView.getUserData();
                                }
                            }
                        }
                        else if(imageView.getImage().getUrl().contains("Images/toggle-0.png") | imageView.getImage().getUrl().contains("Images/toggle-1.png")){
                            startNode = imageView;
                            startX = startgateX + gateWidth;
                            startY = startgateY + gateHeight / 2;
                            wireLine.setStartX(startX);
                            wireLine.setStartY(startY);
                            wireLine.setEndX(startX);
                            wireLine.setEndY(startY);
                            pane.setCursor(Cursor.CROSSHAIR);
                            inNode = (Toggle) imageView.getUserData();

                        }
                        else if(imageView.getImage().getUrl().contains("Images/probe-0.png") | imageView.getImage().getUrl().contains("Images/probe-1.png") | imageView.getImage().getUrl().contains("Images/probe.png")){

                            startX = startgateX;
                            startY = startgateY + gateHeight / 2;
                            sp = new Point2D(startX,startY);
                            if (!(points.contains(sp))) {
                                startNode = imageView;
                                wireLine.setStartX(startX);
                                wireLine.setStartY(startY);
                                wireLine.setEndX(startX);
                                wireLine.setEndY(startY);
                                points.add(sp);
                                pane.setCursor(Cursor.CROSSHAIR);
                                outNode = (Probe) imageView.getUserData();
                            }
                            else
                                showWarning("More than one wire cannot be connected to the same input terminal!");
                        }
                    }
                });

                imageView.setOnMouseDragged(event -> {
                    if (listenersActive) {
                        wireLine.setEndX(event.getSceneX());
                        wireLine.setEndY(event.getSceneY());

                    }
                });

                imageView.setOnMouseReleased(event -> {
                    if (event.getSource() instanceof ImageView && listenersActive && endNode == null && !imageView.equals(startNode)) {
                        this.imageView = imageView;
                        this.pane = pane;
                        this.event = event;
                        endgateX = imageView.getLayoutX();
                        endgateY = imageView.getLayoutY();
                        gateWidth = imageView.getFitWidth();
                        gateHeight = imageView.getFitHeight();

                        mouseX = event.getX() + endgateX;
                        mouseY = event.getY() + endgateY;

                        if (imageView.getImage().getUrl().contains("Images/AND_Gate.png") | imageView.getImage().getUrl().contains("Images/OR_Gate.png") | imageView.getImage().getUrl().contains("Images/NAND_Gate.png") | imageView.getImage().getUrl().contains("Images/NOR_Gate.png") | imageView.getImage().getUrl().contains("Images/XOR_Gate.png") | imageView.getImage().getUrl().contains("Images/XNOR_Gate.png")) {
                            if (mouseY > endgateY && mouseY < endgateY + gateHeight / 3) {
                                endX = endgateX;
                                endY = endgateY + gateHeight / 4;
                                ep = new Point2D(endX,endY);
                                if (!(points.contains(ep))){
                                    endNode = imageView;
                                    wireLine.setEndX(endX);
                                    wireLine.setEndY(endY);
                                    pane.setCursor(Cursor.DEFAULT);
                                    listenersActive = false;
                                    allWires.add(this);
                                    points.add(ep);
                                    if (imageView.getImage().getUrl().contains("Images/AND_Gate.png"))
                                        outNode = (ANDgate) imageView.getUserData();
                                    else if (imageView.getImage().getUrl().contains("Images/OR_Gate.png"))
                                        outNode = (ORgate) imageView.getUserData();
                                    else if (imageView.getImage().getUrl().contains("Images/NAND_Gate.png"))
                                        outNode = (NANDgate) imageView.getUserData();
                                    else if (imageView.getImage().getUrl().contains("Images/NOR_Gate.png"))
                                        outNode = (NORgate) imageView.getUserData();
                                    else if (imageView.getImage().getUrl().contains("Images/XOR_Gate.png"))
                                        outNode = (XORgate) imageView.getUserData();
                                    else if (imageView.getImage().getUrl().contains("Images/XNOR_Gate.png"))
                                        outNode = (XNORgate) imageView.getUserData();
                                    signalVal = inNode.getOutputValue();
                                    outNode.input1 = signalVal;

                                    imageView.setOnMousePressed(null);
                                    imageView.setOnMouseDragged(null);
                                    imageView.setOnMouseReleased(null);
                                    enableEventListeners(pane);
                                }
                                else
                                    showWarning("More than one wire cannot be connected to the same input terminal!");
                            } else if (mouseY > endgateY + 2 * gateHeight / 3 && mouseY < endgateY + gateHeight) {
                                endX = endgateX;
                                endY = endgateY + 3 * gateHeight / 4;
                                ep = new Point2D(endX,endY);
                                if (!(points.contains(ep))){
                                    endNode = imageView;
                                    wireLine.setEndX(endX);
                                    wireLine.setEndY(endY);
                                    pane.setCursor(Cursor.DEFAULT);
                                    listenersActive = false;
                                    allWires.add(this);
                                    points.add(ep);
                                    if (imageView.getImage().getUrl().contains("Images/AND_Gate.png"))
                                        outNode = (ANDgate) imageView.getUserData();
                                    else if (imageView.getImage().getUrl().contains("Images/OR_Gate.png"))
                                        outNode = (ORgate) imageView.getUserData();
                                    else if (imageView.getImage().getUrl().contains("Images/NAND_Gate.png"))
                                        outNode = (NANDgate) imageView.getUserData();
                                    else if (imageView.getImage().getUrl().contains("Images/NOR_Gate.png"))
                                        outNode = (NORgate) imageView.getUserData();
                                    else if (imageView.getImage().getUrl().contains("Images/XOR_Gate.png"))
                                        outNode = (XORgate) imageView.getUserData();
                                    else if (imageView.getImage().getUrl().contains("Images/XNOR_Gate.png"))
                                        outNode = (XNORgate) imageView.getUserData();
                                    signalVal = inNode.getOutputValue();
                                    outNode.input2 = signalVal;

                                    imageView.setOnMousePressed(null);
                                    imageView.setOnMouseDragged(null);
                                    imageView.setOnMouseReleased(null);
                                    enableEventListeners(pane);
                                }
                                else
                                    showWarning("More than one wire cannot be connected to the same input terminal!");
                            } else if (mouseX > endgateX && mouseX < endgateX + gateWidth) {
                                if(outNode == null)
                                    showWarning("Output of one node cannot be connected to the output of another node.");
                                else {
                                    endX = endgateX + gateWidth;
                                    endY = endgateY + gateHeight / 2;
                                    endNode = imageView;
                                    wireLine.setEndX(endX);
                                    wireLine.setEndY(endY);
                                    pane.setCursor(Cursor.DEFAULT);
                                    listenersActive = false;
                                    allWires.add(this);
                                    points.add(ep);
                                    if (imageView.getImage().getUrl().contains("Images/AND_Gate.png"))
                                        inNode = (ANDgate) imageView.getUserData();
                                    else if (imageView.getImage().getUrl().contains("Images/OR_Gate.png"))
                                        inNode = (ORgate) imageView.getUserData();
                                    else if (imageView.getImage().getUrl().contains("Images/NAND_Gate.png"))
                                        inNode = (NANDgate) imageView.getUserData();
                                    else if (imageView.getImage().getUrl().contains("Images/NOR_Gate.png"))
                                        inNode = (NORgate) imageView.getUserData();
                                    else if (imageView.getImage().getUrl().contains("Images/XOR_Gate.png"))
                                        inNode = (XORgate) imageView.getUserData();
                                    else if (imageView.getImage().getUrl().contains("Images/XNOR_Gate.png"))
                                        inNode = (XNORgate) imageView.getUserData();

                                    imageView.setOnMousePressed(null);
                                    imageView.setOnMouseDragged(null);
                                    imageView.setOnMouseReleased(null);
                                    enableEventListeners(pane);
                                }
                            }
                        }
                        else if (imageView.getImage().getUrl().contains("Images/NOT_Gate.png")) {
                            if (mouseY > endgateY && mouseY < endgateY + gateHeight) {
                                if (mouseX > endgateX && mouseX < endgateX + gateWidth / 2) {
                                    endX = endgateX;
                                    endY = endgateY + gateHeight / 2;
                                    ep = new Point2D(endX,endY);
                                    if (!(points.contains(ep))){
                                        endNode = imageView;
                                        wireLine.setEndX(endX);
                                        wireLine.setEndY(endY);
                                        pane.setCursor(Cursor.DEFAULT);
                                        listenersActive = false;
                                        allWires.add(this);
                                        points.add(ep);
                                        outNode = (NOTgate) imageView.getUserData();
                                        signalVal = inNode.getOutputValue();
                                        outNode.input1 = signalVal;

                                        imageView.setOnMousePressed(null);
                                        imageView.setOnMouseDragged(null);
                                        imageView.setOnMouseReleased(null);
                                        enableEventListeners(pane);
                                    }
                                    else
                                        showWarning("More than one wire cannot be connected to the same input terminal!");
                                } else if (mouseX > endgateX + gateWidth / 2 && mouseX < endgateX + gateWidth) {
                                    if(outNode == null)
                                        showWarning("Output of one node cannot be connected to the output of another node.");
                                    else {
                                        endX = endgateX + gateWidth;
                                        endY = endgateY + gateHeight / 2;
                                        endNode = imageView;
                                        wireLine.setEndX(endX);
                                        wireLine.setEndY(endY);
                                        pane.setCursor(Cursor.DEFAULT);
                                        listenersActive = false;
                                        allWires.add(this);
                                        inNode = (NOTgate) imageView.getUserData();

                                        imageView.setOnMousePressed(null);
                                        imageView.setOnMouseDragged(null);
                                        imageView.setOnMouseReleased(null);
                                        enableEventListeners(pane);
                                    }
                                }
                            }
                        }
                        else if(imageView.getImage().getUrl().contains("Images/toggle-0.png") | imageView.getImage().getUrl().contains("Images/toggle-1.png")){
                            if(outNode == null)
                                showWarning("Output of one node cannot be connected to the output of another node.");
                            else {
                                endNode = imageView;
                                endX = endgateX + gateWidth;
                                endY = endgateY + gateHeight / 2;
                                wireLine.setEndX(endX);
                                wireLine.setEndY(endY);
                                pane.setCursor(Cursor.DEFAULT);
                                listenersActive = false;
                                allWires.add(this);
                                inNode = (Toggle) imageView.getUserData();

                                imageView.setOnMousePressed(null);
                                imageView.setOnMouseDragged(null);
                                imageView.setOnMouseReleased(null);
                                enableEventListeners(pane);
                            }
                        }
                        else if(imageView.getImage().getUrl().contains("Images/probe-0.png") | imageView.getImage().getUrl().contains("Images/probe-1.png") | imageView.getImage().getUrl().contains("Images/probe.png")) {

                            endX = endgateX;
                            endY = endgateY + gateHeight / 2;
                            ep = new Point2D(endX, endY);
                            if (!(points.contains(ep))) {
                                endNode = imageView;
                                wireLine.setEndX(endX);
                                wireLine.setEndY(endY);
                                pane.setCursor(Cursor.DEFAULT);
                                listenersActive = false;
                                allWires.add(this);
                                points.add(ep);
                                outNode = (Probe) imageView.getUserData();
                                signalVal = inNode.getOutputValue();
                                outNode.input1 = signalVal;

                                imageView.setOnMousePressed(null);
                                imageView.setOnMouseDragged(null);
                                imageView.setOnMouseReleased(null);
                                enableEventListeners(pane);
                            }
                            else
                                showWarning("More than one wire cannot be connected to the same input terminal!");
                        }
                    }
                });

            }
        });
    }

    private void updateStartDimensions(MouseEvent e, ImageView imageView) {
        if (startNode != null && endNode != null) {
            double deltaX = imageView.getLayoutX() - startgateX;
            double deltaY = imageView.getLayoutY() - startgateY;
            startX += deltaX;
            startY += deltaY;
            wireLine.setStartX(startX);
            wireLine.setStartY(startY);
            startgateX = imageView.getLayoutX();
            startgateY = imageView.getLayoutY();
        }
    }
    private void updateEndDimensions(MouseEvent e, ImageView imageView) {
        if (startNode != null && endNode != null) {
            double deltaX = imageView.getLayoutX() - endgateX;
            double deltaY = imageView.getLayoutY() - endgateY;
            endX += deltaX;
            endY += deltaY;
            wireLine.setEndX(endX);
            wireLine.setEndY(endY);
            endgateX = imageView.getLayoutX();
            endgateY = imageView.getLayoutY();
        }
    }
    @Override
    void handleImageDragged(MouseEvent event){
        ImageView gateImageView = (ImageView) event.getSource();
        gateImageView.setLayoutX(event.getSceneX() - xOffset);
        gateImageView.setLayoutY(event.getSceneY() - yOffset);
        for (Wire wire : allWires) {
            if (wire.startNode == gateImageView) {
                int index = points.indexOf(wire.sp);
                wire.updateStartDimensions(event, gateImageView);
                wire.sp = new Point2D(wire.startX, wire.startY);
                if (index != -1)
                    points.set(index, wire.sp);
            }
            else if(wire.endNode == gateImageView){
                int index = points.indexOf(wire.ep);
                wire.updateEndDimensions(event, gateImageView);
                wire.ep = new Point2D(wire.endX, wire.endY);
                if (index != -1)
                    points.set(index, wire.ep);
            }
        }
    }
    @Override
    void handleImageReleased(MouseEvent event){
        ImageView gateImageView = (ImageView) event.getSource();
        gateImageView.setMouseTransparent(false);
        gateImageView.setVisible(true);
        gateImageView.setLayoutX(event.getSceneX() - xOffset);
        gateImageView.setLayoutY(event.getSceneY() - yOffset);
        for (Wire wire : allWires) {
            if (wire.startNode == gateImageView) {
                int index = points.indexOf(wire.sp);
                wire.updateStartDimensions(event, gateImageView);
                wire.sp = new Point2D(wire.startX, wire.startY);
                if (index != -1)
                    points.set(index, wire.sp);
            }
            else if(wire.endNode == gateImageView){
                int index = points.indexOf(wire.ep);
                wire.updateEndDimensions(event, gateImageView);
                wire.ep = new Point2D(wire.endX, wire.endY);
                if (index != -1)
                    points.set(index, wire.ep);
            }
        }
    }

    public boolean getOutputValue(){
        return input1;
    }

    public static void simulator() {

        for (Wire wire  : allWires) {
            if (wire.inNode instanceof Toggle) {
                if(wire.inNode.getOutputValue())
                    wire.wireLine.setStroke(Color.RED);
                else
                    wire.wireLine.setStroke(Color.YELLOW);
                if (wire.outNode instanceof NOTgate | wire.outNode instanceof Probe){
                    wire.outNode.input1 = wire.inNode.getOutputValue();
                }
                else{
                    if(Objects.equals(wire.sp, new Point2D(wire.startNode.getLayoutX(), (wire.startNode.getLayoutY() + wire.startNode.getFitHeight() / 4))) | Objects.equals(wire.ep, new Point2D(wire.endNode.getLayoutX(), (wire.endNode.getLayoutY() + wire.endNode.getFitHeight() / 4))))
                        wire.outNode.input1 = wire.inNode.getOutputValue();
                    else if(Objects.equals(wire.sp, new Point2D(wire.startNode.getLayoutX(), (wire.startNode.getLayoutY() + 3 * wire.startNode.getFitHeight() / 4))) | Objects.equals(wire.ep, new Point2D(wire.endNode.getLayoutX(), (wire.endNode.getLayoutY() + 3 * wire.endNode.getFitHeight() / 4))))
                        wire.outNode.input2 = wire.inNode.getOutputValue();
                }
            }
        }

        int count =0;
        for (Wire wire  : allWires) {
            if (! (wire.inNode instanceof Toggle | wire.outNode instanceof Probe) ) {
                if(wire.inNode.getOutputValue())
                    wire.wireLine.setStroke(Color.RED);
                else
                    wire.wireLine.setStroke(Color.YELLOW);
                if (wire.outNode instanceof NOTgate){
                    wire.outNode.input1 = wire.inNode.getOutputValue();
                }
                else{
                    if(Objects.equals(wire.sp, new Point2D(wire.startNode.getLayoutX(), (wire.startNode.getLayoutY() + wire.startNode.getFitHeight() / 4))) | Objects.equals(wire.ep, new Point2D(wire.endNode.getLayoutX(), (wire.endNode.getLayoutY() + wire.endNode.getFitHeight() / 4))))
                        wire.outNode.input1 = wire.inNode.getOutputValue();
                    else if(Objects.equals(wire.sp, new Point2D(wire.startNode.getLayoutX(), (wire.startNode.getLayoutY() + 3 * wire.startNode.getFitHeight() / 4))) | Objects.equals(wire.ep, new Point2D(wire.endNode.getLayoutX(), (wire.endNode.getLayoutY() + 3 * wire.endNode.getFitHeight() / 4))))
                        wire.outNode.input2 = wire.inNode.getOutputValue();
                }
                count++;
            }
        }
        for(int i=1; i<count;i++){
            for (Wire wire  : allWires) {
                if (! (wire.inNode instanceof Toggle | wire.outNode instanceof Probe) ) {
                    if(wire.inNode.getOutputValue())
                        wire.wireLine.setStroke(Color.RED);
                    else
                        wire.wireLine.setStroke(Color.YELLOW);
                    if (wire.outNode instanceof NOTgate){
                        wire.outNode.input1 = wire.inNode.getOutputValue();
                    }
                    else{
                        if(Objects.equals(wire.sp, new Point2D(wire.startNode.getLayoutX(), (wire.startNode.getLayoutY() + wire.startNode.getFitHeight() / 4))) | Objects.equals(wire.ep, new Point2D(wire.endNode.getLayoutX(), (wire.endNode.getLayoutY() + wire.endNode.getFitHeight() / 4))))
                            wire.outNode.input1 = wire.inNode.getOutputValue();
                        else if(Objects.equals(wire.sp, new Point2D(wire.startNode.getLayoutX(), (wire.startNode.getLayoutY() + 3 * wire.startNode.getFitHeight() / 4))) | Objects.equals(wire.ep, new Point2D(wire.endNode.getLayoutX(), (wire.endNode.getLayoutY() + 3 * wire.endNode.getFitHeight() / 4))))
                            wire.outNode.input2 = wire.inNode.getOutputValue();
                    }
                }
            }
        }
        for (Wire wire : allWires) {
            if (wire.outNode instanceof Probe) {
                if(wire.inNode.getOutputValue())
                    wire.wireLine.setStroke(Color.RED);
                else
                    wire.wireLine.setStroke(Color.YELLOW);
                wire.outNode.input1 = wire.inNode.getOutputValue();
                if(wire.outNode.input1)
                    wire.outNode.gateImageView.setImage(new Image(Objects.requireNonNull(Wire.class.getResource("Images/probe-1.png").toExternalForm())));
                else
                    wire.outNode.gateImageView.setImage(new Image(Objects.requireNonNull(Wire.class.getResource("Images/probe-0.png").toExternalForm())));
            }

        }
    }
    public void showWarning(String text){

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Be Alert!");
        alert.setHeaderText(null);
        alert.setContentText(text);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResource("Images/warning.jpg").toExternalForm())));
        alert.showAndWait();
    }
}
