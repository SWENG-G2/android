package sweng.campusbirdsguide.xml.utils;

import org.xmlpull.v1.XmlPullParser;

import sweng.campusbirdsguide.presentation.elements.CircleElement;
import sweng.campusbirdsguide.presentation.elements.RectangleElement;

public class CircleParser extends ElementParser {
    private static final String RADIUS = "radius";
    private static final String BORDER_WIDTH = "borderWidth";
    private static final String BORDER_COLOUR = "borderColour";

    public static CircleElement parseCircle(XmlPullParser xmlPullParser) {
        int radius = parseInt(xmlPullParser.getAttributeValue(NAME_SPACE, RADIUS));
        int colour  = parseColour(xmlPullParser.getAttributeValue(NAME_SPACE, COLOUR));
        int borderWidth = parseInt(xmlPullParser.getAttributeValue(NAME_SPACE, BORDER_WIDTH));
        int borderColour = parseColour(xmlPullParser.getAttributeValue(NAME_SPACE, BORDER_COLOUR));
        int x = parseInt(xmlPullParser.getAttributeValue(NAME_SPACE, X_COORDINATE));
        int y = parseInt(xmlPullParser.getAttributeValue(NAME_SPACE, Y_COORDINATE));

        return new CircleElement(radius, colour, borderWidth, borderColour, x, y);
    }
}
