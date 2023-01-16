package sweng.campusbirdsguide.xml.utils;

import android.graphics.Color;

public abstract class ElementParser {
    protected static final String NAME_SPACE = null;

    // Common attributes, shared by multiple elements
    protected static final String X_COORDINATE = "xCoordinate";
    protected static final String Y_COORDINATE = "yCoordinate";
    protected static final String WIDTH = "width";
    protected static final String HEIGHT = "height";
    protected static final String COLOUR = "colour";
    protected static final String URL = "url";
    protected static final String LOOP = "loop";
    protected static final String RADIUS = "radius";
    protected static final String BORDER_WIDTH = "borderWidth";
    protected static final String BORDER_COLOUR = "borderColour";

    private static final int EXPECTED_COLOUR_STRING_LENGTH = 9;
    // Colour string is #AARRGGBB according to standard
    private static final int ALPHA_START_STRING_INDEX = 7;
    private static final int RGB_START_STRING_INDEX = 1;


    protected static int parseColour(String colour) {
        if (colour != null && colour.length() == EXPECTED_COLOUR_STRING_LENGTH) {
            String formattedColour = "#" + colour.substring(ALPHA_START_STRING_INDEX)
                    + colour.substring(RGB_START_STRING_INDEX, ALPHA_START_STRING_INDEX);
            return Color.parseColor(formattedColour);
        }
        return Color.TRANSPARENT;
    }

    protected static int parseInt(String stringValue) {
        try {
            return Integer.parseInt(stringValue);
        } catch (NumberFormatException numberFormatException) {
            return 0;
        }
    }
}
