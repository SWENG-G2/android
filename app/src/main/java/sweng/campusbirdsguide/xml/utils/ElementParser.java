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


    protected static int getColour(XmlPullParser xmlPullParser) {
        String originalColour = xmlPullParser.getAttributeValue(NAME_SPACE, COLOUR);
        // TODO: Document magic numbers
        String formattedColour = "#" + originalColour.substring(7) + originalColour.substring(1, 7);
        return Color.parseColor(formattedColour);
    }
}
