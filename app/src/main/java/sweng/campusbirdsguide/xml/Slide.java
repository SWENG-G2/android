package sweng.campusbirdsguide.xml;

import java.util.ArrayList;

import lombok.Getter;

@Getter
public class Slide {
    private final int width, height;
    private final String title;

    private final ArrayList<PresentationElement> elements;

    public Slide(int width, int height, String title) {
        this.width = width;
        this.height = height;
        this.title = title;

        elements = new ArrayList<>();
    }

    public void addElement(PresentationElement presentationElement) {
        elements.add(presentationElement);
    }
}
