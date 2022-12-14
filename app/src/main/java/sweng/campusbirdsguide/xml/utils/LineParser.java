package sweng.campusbirdsguide.xml.utils;

import org.xmlpull.v1.XmlPullParser;

import sweng.campusbirdsguide.presentation.elements.LineElement;

public class LineParser extends ElementParser {
    private static final String FROM_X = "fromX";
    private static final String FROM_Y = "fromY";
    private static final String TO_X = "toX";
    private static final String TO_Y = "toY";
    private static final String THICKNESS = "thickness";

    public static LineElement parseLine(XmlPullParser xmlPullParser) {
        int thickness = Integer.parseInt(xmlPullParser.getAttributeValue(NAME_SPACE, THICKNESS));
        int fromX = Integer.parseInt(xmlPullParser.getAttributeValue(NAME_SPACE, FROM_X));
        int fromY = Integer.parseInt(xmlPullParser.getAttributeValue(NAME_SPACE, FROM_Y));
        int toX = Integer.parseInt(xmlPullParser.getAttributeValue(NAME_SPACE, TO_X));
        int toY = Integer.parseInt(xmlPullParser.getAttributeValue(NAME_SPACE, TO_Y));
        int colour = parseColour(xmlPullParser.getAttributeValue(NAME_SPACE, COLOUR));

        return new LineElement(thickness, fromX, fromY, toX, toY, colour);
    }
}
