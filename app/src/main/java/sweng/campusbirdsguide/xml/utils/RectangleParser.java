package sweng.campusbirdsguide.xml.utils;

import org.xmlpull.v1.XmlPullParser;

import sweng.campusbirdsguide.presentation.elements.RectangleElement;

public class RectangleParser  extends ElementParser {
    private static final String WIDTH = "width";
    private static final String HEIGHT = "height";
    private static final String BORDER_WIDTH = "borderWidth";
    private static final String BORDER_COLOUR = "borderColour";

    public static RectangleElement parseRectangle(XmlPullParser xmlPullParser) {
        int width = parseInt(xmlPullParser.getAttributeValue(NAME_SPACE, WIDTH));
        int height = parseInt(xmlPullParser.getAttributeValue(NAME_SPACE, HEIGHT));
        int colour  = parseColour(xmlPullParser.getAttributeValue(NAME_SPACE, COLOUR));
        int borderWidth = parseInt(xmlPullParser.getAttributeValue(NAME_SPACE, BORDER_WIDTH));
        int borderColour = parseColour(xmlPullParser.getAttributeValue(NAME_SPACE, BORDER_COLOUR));
        int x = parseInt(xmlPullParser.getAttributeValue(NAME_SPACE, X_COORDINATE));
        int y = parseInt(xmlPullParser.getAttributeValue(NAME_SPACE, Y_COORDINATE));

        return new RectangleElement(width, height, colour, borderWidth, borderColour, x, y);
    }
}
