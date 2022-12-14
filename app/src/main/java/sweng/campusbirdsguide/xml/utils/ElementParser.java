package sweng.campusbirdsguide.xml.utils;

import android.graphics.Color;

import org.xmlpull.v1.XmlPullParser;

public abstract class ElementParser {
    protected static final String NAME_SPACE = null;

    // Position, common to many elements
    protected static final String X_COORDINATE = "xCoordinate";
    protected static final String Y_COORDINATE = "yCoordinate";

    // Colour, common to many elements
    protected static final String COLOUR = "colour";


    protected static int parseColour(String colour) {
        // TODO: Document magic numbers
        String formattedColour = "#" + colour.substring(7) + colour.substring(1, 7);
        return Color.parseColor(formattedColour);
    }
}
