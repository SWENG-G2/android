package sweng.campusbirdsguide.xml.utils;

import org.xmlpull.v1.XmlPullParser;

import sweng.campusbirdsguide.presentation.elements.AudioElement;
import sweng.campusbirdsguide.presentation.elements.TextElement;

public class AudioParser extends ElementParser {
    private static final String URL = "url";
    private static final String LOOP = "loop";

    public static AudioElement parseAudio(XmlPullParser xmlPullParser) {
        int xCoordinate = parseInt(xmlPullParser.getAttributeValue(NAME_SPACE, X_COORDINATE));
        int yCoordinate = parseInt(xmlPullParser.getAttributeValue(NAME_SPACE, Y_COORDINATE));
        String url = xmlPullParser.getAttributeValue(NAME_SPACE, URL);
        boolean loop = Boolean.parseBoolean(xmlPullParser.getAttributeValue(NAME_SPACE, LOOP));


        return new AudioElement(url, loop, xCoordinate, yCoordinate);
    }
}
