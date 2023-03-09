package sweng.campusbirdsguide.xml.utils;

import org.xmlpull.v1.XmlPullParser;

import sweng.campusbirdsguide.presentation.elements.TextElement;

public class TextParser extends ElementParser {
    private static final String FONT_SIZE = "fontSize";
    private static final String FONT_NAME = "fontName";

    public static TextElement parseText(XmlPullParser xmlPullParser) {
        int xCoordinate = parseInt(xmlPullParser.getAttributeValue(NAME_SPACE, X_COORDINATE));
        int yCoordinate = parseInt(xmlPullParser.getAttributeValue(NAME_SPACE, Y_COORDINATE));
        int width = parseInt(xmlPullParser.getAttributeValue(NAME_SPACE, WIDTH));
        int height = parseInt(xmlPullParser.getAttributeValue(NAME_SPACE, HEIGHT));
        int fontSize = parseInt(xmlPullParser.getAttributeValue(NAME_SPACE, FONT_SIZE));
        String font = xmlPullParser.getAttributeValue(NAME_SPACE, FONT_NAME);
        long timeOnScreen = parseTimeOnScreen(xmlPullParser.getAttributeValue(NAME_SPACE, TIME_ON_SCREEN));
        int colour = parseColour(xmlPullParser.getAttributeValue(NAME_SPACE, COLOUR));

        return new TextElement(font, fontSize, colour, xCoordinate, yCoordinate, width, height, timeOnScreen);
    }
}
