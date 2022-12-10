package sweng.campusbirdsguide.xml;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import sweng.campusbirdsguide.presentation.elements.TextElement;
import sweng.campusbirdsguide.xml.utils.LineParser;
import sweng.campusbirdsguide.xml.utils.TextParser;


public class PresentationParser {
    private static final String NAME_SPACE = null;
    private static final String WIDTH = "width";
    private static final String HEIGHT = "height";
    private static final String PRESENTATION = "presentation";
    private static final String SLIDE = "slide";
    private static final String TEXT = "text";
    private static final String LINE = "line";
    private static final String TITLE = "title";


    private List<Slide> parsePresentation(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        List<Slide> slides = new ArrayList<>();

        Slide workingSlide = null;
        TextElement text = null;

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
                                text = TextParser.parseText(xmlPullParser);
                                workingSlide.addElement(text);
                            }
                            break;
                        }
                        case LINE:
                            if (workingSlide != null)
                                workingSlide.addElement(LineParser.parseLine(xmlPullParser));
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
