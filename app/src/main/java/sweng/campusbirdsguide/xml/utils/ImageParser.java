package sweng.campusbirdsguide.xml.utils;

import org.xmlpull.v1.XmlPullParser;

import sweng.campusbirdsguide.presentation.elements.ImageElement;

public class ImageParser extends ElementParser {
    public static ImageElement parseImage(XmlPullParser xmlPullParser) {
        String url = xmlPullParser.getAttributeValue(NAME_SPACE, URL);
        int width = parseInt(xmlPullParser.getAttributeValue(NAME_SPACE, WIDTH));
        int height = parseInt(xmlPullParser.getAttributeValue(NAME_SPACE, HEIGHT));
        int x = parseInt(xmlPullParser.getAttributeValue(NAME_SPACE, X_COORDINATE));
        int y = parseInt(xmlPullParser.getAttributeValue(NAME_SPACE, Y_COORDINATE));

        return new ImageElement(url, width, height, x, y);
    }
}
