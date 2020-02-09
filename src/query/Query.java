package query;

import filters.Filter;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.Layer;
import twitter4j.Status;
import ui.MapMarkerAdvanced;
import util.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

public class Query implements Observer {
    // The map on which to display markers when the query matches
    private final JMapViewer map;
    // Each query has its own "layer" so they can be turned on and off all at once
    private Layer layer;
    // The color of the outside area of the marker
    private final Color color;
    // The string representing the filter for this query
    private final String queryString;
    // The filter parsed from the queryString
    private final Filter filter;
    // The checkBox in the UI corresponding to this query (so we can turn it on and off and delete it)
    private JCheckBox checkBox;

    private Set<MapMarkerAdvanced> markers;

    public Color getColor() {
        return color;
    }
    public String getQueryString() {
        return queryString;
    }
    public Filter getFilter() {
        return filter;
    }
    public Layer getLayer() {
        return layer;
    }
    public JCheckBox getCheckBox() {
        return checkBox;
    }
    public void setCheckBox(JCheckBox checkBox) {
        this.checkBox = checkBox;
    }
    public void setVisible(boolean visible) {
        layer.setVisible(visible);
    }
    public boolean getVisible() { return layer.isVisible(); }

    public Query(String queryString, Color color, JMapViewer map) {
        this.queryString = queryString;
        this.filter = Filter.parse(queryString);
        this.color = color;
        this.layer = new Layer(queryString);
        this.map = map;
        this.markers = new HashSet<>();
    }

    @Override
    public String toString() {
        return "Query: " + queryString;
    }

    public void terminate() {
         setVisible(false);
         for (MapMarkerAdvanced m : markers){
             if(map.getMapMarkerList().contains(m)){
                 map.removeMapMarker(m);
             }
         }
    }

    @Override
    public void update(Observable o, Object arg) {
        if(filter.matches((Status) arg)){
            new Util();
            Coordinate c = Util.statusCoordinate((Status) arg);
            BufferedImage img = Util.imageFromURL(((Status) arg).getUser().getProfileImageURL());
            MapMarkerAdvanced m = new MapMarkerAdvanced(layer, c, getColor(), img, (Status) arg);
            map.addMapMarker(m);
            markers.add(m);

        }


    }
}

