package sweng.campusbirdsguide.xml.utils;

import org.xmlpull.v1.XmlPullParser;

import sweng.campusbirdsguide.presentation.elements.AudioElement;

public class AudioParser extends ElementParser {
    public static AudioElement parseAudio(XmlPullParser xmlPullParser) {
        int xCoordinate = parseInt(xmlPullParser.getAttributeValue(NAME_SPACE, X_COORDINATE));
        int yCoordinate = parseInt(xmlPullParser.getAttributeValue(NAME_SPACE, Y_COORDINATE));
        String url = xmlPullParser.getAttributeValue(NAME_SPACE, URL);
        boolean loop = Boolean.parseBoolean(xmlPullParser.getAttributeValue(NAME_SPACE, LOOP));


        return new AudioElement(url, loop, xCoordinate, yCoordinate);
    }
}
