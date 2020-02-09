package ui;

import jdk.net.SocketFlow;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.Layer;
import twitter4j.Status;

import java.awt.*;

public class MapMarkerAdvanced extends MapMarkerSimple {
    protected Color color;
    protected Image img;
    protected Status status;

    public MapMarkerAdvanced(Layer layer, Coordinate coord, Color color, Image img, Status s) {
        super(layer, coord);
        this.color = color;
        this.img = img;
        this.status = s;
        setBackColor(color);
    }

    @Override
    public Color getColor() {
        return color;
    }

    public Image getImg() {
        return this.img;
    }

    public Status getStatus(){ return status;}

    @Override
    public void paint(Graphics g, Point position, int radius) {

        final int size = radius * 10;
        final int imgSize = size/2;
        final int centeredX = position.x + radius + 2;
        final int centeredY = position.y + radius + 2;

        final int ovalPosX = position.x - radius;
        final int ovalPosY = position.y - radius;

        if (g instanceof Graphics2D && this.getBackColor() != null) {
            Graphics2D g2 = (Graphics2D) g;
            Composite oldComposite = g2.getComposite();
            g2.setComposite(AlphaComposite.getInstance(3));
            g2.setPaint(this.getBackColor());
            g.fillOval(ovalPosX, ovalPosY, size, size);
            g2.setComposite(oldComposite);
        }

        g.setColor(this.getColor());
        g.drawOval(ovalPosX, ovalPosY, size, size);
        if (this.getLayer() == null || this.getLayer().isVisibleTexts()) {
            this.paintText(g, position);
            g.drawImage(img, centeredX, centeredY, imgSize, imgSize,  null);
        }
    }
}
