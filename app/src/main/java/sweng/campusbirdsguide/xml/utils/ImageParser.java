package sweng.campusbirdsguide.xml.utils;

import org.xmlpull.v1.XmlPullParser;

import sweng.campusbirdsguide.presentation.elements.ImageElement;

public class ImageParser extends ElementParser {
    private static final String WIDTH = "width";
    private static final String HEIGHT = "height";

    private static final String URL = "url";
    public static ImageElement parseImage(XmlPullParser xmlPullParser) {
        String url = xmlPullParser.getAttributeValue(NAME_SPACE, URL);
        int width = Integer.parseInt(xmlPullParser.getAttributeValue(NAME_SPACE, WIDTH));
        int height = Integer.parseInt(xmlPullParser.getAttributeValue(NAME_SPACE, HEIGHT));
        int x = Integer.parseInt(xmlPullParser.getAttributeValue(NAME_SPACE, X_COORDINATE));
        int y = Integer.parseInt(xmlPullParser.getAttributeValue(NAME_SPACE, Y_COORDINATE));

        return new ImageElement(url, width, height, x, y);
    }
}
