package project.logicgatesimulator;

import javafx.scene.Node;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CircuitState implements Serializable {
    private List<ComponentData> components;
    private List<WireData> wires;

    public CircuitState() {
        this.components = new ArrayList<>();
        this.wires = new ArrayList<>();
    }

    public void addComponent(ComponentData data) {
        components.add(data);
    }

    public void addWire(WireData data) {
        wires.add(data);
    }

    public List<ComponentData> getComponents() {
        return components;
    }

    public List<WireData> getWires() {
        return wires;
    }

    public static class ComponentData implements Serializable {
        public String type;
        public double x;
        public double y;
        public boolean input1;
        public boolean input2;
        public String imageUrl;

        public ComponentData(String type, double x, double y, boolean input1, boolean input2, String imageUrl) {
            this.type = type;
            this.x = x;
            this.y = y;
            this.input1 = input1;
            this.input2 = input2;
            this.imageUrl = imageUrl;
        }
    }

    public static class WireData implements Serializable {
        public double startX;
        public double startY;
        public double endX;
        public double endY;

        public WireData(double startX, double startY, double endX, double endY) {
            this.startX = startX;
            this.startY = startY;
            this.endX = endX;
            this.endY = endY;
        }
    }
}
