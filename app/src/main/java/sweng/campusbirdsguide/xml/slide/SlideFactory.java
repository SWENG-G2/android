package sweng.campusbirdsguide.xml.slide;

public class SlideFactory {
    // Slide variants
    public static final String BIRD_SLIDE = "bird";
    public static final String DETAIL_SLIDE = "detail";
    public static final String BASIC_SLIDE = "basic";
    // Slide title, to differentiate hero slide from detail slides
    private static final String HERO_SLIDE = "heroSlide";

    public static Slide createSlide(String type, int width, int height, String title) {
        switch (type) {
            case BIRD_SLIDE:
                return new BirdSlide(width, height, title);
            case DETAIL_SLIDE:
                if (title.equals(HERO_SLIDE))
                    return new BasicSlide(width, height, title);
                return new DetailSlide(width, height, title);
            default:
            case BASIC_SLIDE:
                return new BasicSlide(width, height, title);
        }
    }
}
