package sweng.campusbirdsguide.xml.utils;

import org.xmlpull.v1.XmlPullParser;

import sweng.campusbirdsguide.presentation.elements.VideoElement;

public class VideoParser extends ElementParser {
    public static VideoElement parseVideo(XmlPullParser xmlPullParser) {
        String url = xmlPullParser.getAttributeValue(NAME_SPACE, URL);
        int width = parseInt(xmlPullParser.getAttributeValue(NAME_SPACE, WIDTH));
        int height = parseInt(xmlPullParser.getAttributeValue(NAME_SPACE, HEIGHT));
        int x = parseInt(xmlPullParser.getAttributeValue(NAME_SPACE, X_COORDINATE));
        int y = parseInt(xmlPullParser.getAttributeValue(NAME_SPACE, Y_COORDINATE));
        boolean loop = Boolean.parseBoolean(xmlPullParser.getAttributeValue(NAME_SPACE, LOOP));

        return new VideoElement(url, width, height, x, y, loop);
    }
}
