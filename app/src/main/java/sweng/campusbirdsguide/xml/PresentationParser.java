package sweng.campusbirdsguide.xml;

import android.graphics.Color;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import sweng.campusbirdsguide.presentation.elements.PresentationLine;
import sweng.campusbirdsguide.presentation.elements.PresentationText;


public class PresentationParser {
    private static final String NAME_SPACE = null;
    private static final String ID = "id";
    private static final String WIDTH = "width";
    private static final String HEIGHT = "height";
    private static final String X_COORDINATE = "xCoordinate";
    private static final String Y_COORDINATE = "yCoordinate";
    private static final String FROM_X = "fromX";
    private static final String FROM_Y = "fromY";
    private static final String TO_X = "toX";
    private static final String TO_Y = "toY";
    private static final String THICKNESS = "thickness";
    private static final String FONT_SIZE = "fontSize";
    private static final String FONT_NAME = "fontName";
    private static final String COLOUR = "colour";
    private static final String PRESENTATION = "presentation";
    private static final String SLIDE = "slide";
    private static final String TEXT = "text";
    private static final String LINE = "line";
    private static final String TITLE = "title";

    private int getColour(XmlPullParser xmlPullParser) {
        String originalColour = xmlPullParser.getAttributeValue(NAME_SPACE, COLOUR);
        // TODO: Document magic numbers
        String formattedColour = "#" + originalColour.substring(7) + originalColour.substring(1, 7);
        System.out.println(formattedColour + " " + Color.parseColor(formattedColour));
        return Color.parseColor(formattedColour);
    }

    private PresentationText parseText(XmlPullParser xmlPullParser) {
        int xCoordinate = Integer.parseInt(xmlPullParser.getAttributeValue(NAME_SPACE, X_COORDINATE));
        int yCoordinate = Integer.parseInt(xmlPullParser.getAttributeValue(NAME_SPACE, Y_COORDINATE));
        int fontSize = Integer.parseInt(xmlPullParser.getAttributeValue(NAME_SPACE, FONT_SIZE));
        String font = xmlPullParser.getAttributeValue(NAME_SPACE, FONT_NAME);

        int colour = getColour(xmlPullParser);

        return new PresentationText(font, fontSize, xCoordinate, yCoordinate, colour);
    }

    private PresentationLine parseLine(XmlPullParser xmlPullParser) {
        int thickness = Integer.parseInt(xmlPullParser.getAttributeValue(NAME_SPACE, THICKNESS));
        int fromX = Integer.parseInt(xmlPullParser.getAttributeValue(NAME_SPACE, FROM_X));
        int fromY = Integer.parseInt(xmlPullParser.getAttributeValue(NAME_SPACE, FROM_Y));
        int toX = Integer.parseInt(xmlPullParser.getAttributeValue(NAME_SPACE, TO_X));
        int toY = Integer.parseInt(xmlPullParser.getAttributeValue(NAME_SPACE, TO_Y));
        int colour = getColour(xmlPullParser);

        return new PresentationLine(thickness, fromX, fromY, toX, toY, colour);
    }

    private List<Slide> parsePresentation(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        List<Slide> slides = new ArrayList<>();

        Slide workingSlide = null;
        PresentationText text = null;

        xmlPullParser.require(XmlPullParser.START_TAG, NAME_SPACE, PRESENTATION);

        int eventType = xmlPullParser.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {

            switch (eventType) {
                case XmlPullParser.START_TAG:
                    switch (xmlPullParser.getName()) {
                        case SLIDE: {
                            String title = xmlPullParser.getAttributeValue(NAME_SPACE, TITLE);
                            int width = Integer.parseInt(xmlPullParser.getAttributeValue(NAME_SPACE, WIDTH));
                            int height = Integer.parseInt(xmlPullParser.getAttributeValue(NAME_SPACE, HEIGHT));

                            workingSlide = new Slide(width, height, title);
                            break;
                        }
                        case TEXT: {
                            if (workingSlide != null) {
                                text = parseText(xmlPullParser);
                                workingSlide.addElement(text);
                            }
                            break;
                        }
                        case LINE:
                            if (workingSlide != null)
                                workingSlide.addElement(parseLine(xmlPullParser));
                            break;
                        default:
                            break;
                    }
                    break;
                case XmlPullParser.END_TAG:
                    switch (xmlPullParser.getName()) {
                        case SLIDE:
                            slides.add(workingSlide);
                            workingSlide = null;
                            break;
                        case TEXT:
                            text = null;
                            break;
                        default:
                            break;
                    }
                    break;
                case XmlPullParser.TEXT:
                    String content = xmlPullParser.getText();
                    if (text != null && content != null)
                        text.setContent(content);
                    break;
                default:
                    break;
            }

            eventType = xmlPullParser.next();
        }

        return slides;
    }

    public List<Slide> parse(String input) throws XmlPullParserException, IOException {
        XmlPullParser xmlPullParser = Xml.newPullParser();
        xmlPullParser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
        xmlPullParser.setInput(new StringReader(input));
        xmlPullParser.nextTag();


        return parsePresentation(xmlPullParser);
    }
}
