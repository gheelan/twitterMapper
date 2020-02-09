package ui;

import javafx.scene.control.Tooltip;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.Layer;
import twitter4j.Status;
import util.Util;

import java.awt.*;

public class MapMarkerRectangle extends MapMarkerAdvanced {
    private static final String FONT_NAME = "Roboto";
    private static final int FONT_STYLE = Font.BOLD;
    private static final int FONT_SIZE = 13;
    private Font font = new Font(FONT_NAME, FONT_STYLE, FONT_SIZE);



    public MapMarkerRectangle(Layer layer, Coordinate coord, Color color, Image img, Status status) {
        super(layer, coord, color, img, status);
        new Util();
        this.img = Util.imageFromURL(status.getUser().getBiggerProfileImageURL());
    }

    @Override
    public void paint(Graphics g, Point position, int sides){

        final int rectangleHeight = sides * 20;
        final int rectangleWidth = sides * 200;
        final int rectanglePosX = position.x - sides;
        final int rectanglePosY = position.y - sides;

        final int imageWidth = rectangleWidth/8;
        final int imageHeight = rectangleHeight - 10;

        final int stringPosX = position.x + 150;
        final int stringPosY = position.y + 70;

        if (g instanceof Graphics2D && this.getBackColor() != null) {
            Graphics2D g2 = (Graphics2D) g;
            Composite oldComposite = g2.getComposite();
            g2.setComposite(AlphaComposite.getInstance(3));
            g2.setPaint(this.getBackColor());
            g.fillRect(rectanglePosX, rectanglePosY, rectangleWidth, rectangleHeight);
            g2.setComposite(oldComposite);
        }

        g.setColor(new Color(0xFA020202, true));
        g.drawRect(rectanglePosX, rectanglePosY, rectangleWidth, rectangleHeight);
        if (this.getLayer() == null || this.getLayer().isVisibleTexts()) {
            this.paintText(g, position);
            g.drawImage(img, position.x, position.y, imageWidth, imageHeight,  null);
            g.setFont(font);
            g.drawString(status.getText(), stringPosX, stringPosY);

        }

    }

}
