package sweng.campusbirdsguide.xml.slide;

public class SlideFactory {
    public static Slide createSlide(String type, int width, int height, String title) {
        switch (type) {
            case "bird":
                return new BirdSlide(width, height, title);
            default:
            case "basic":
                return new BasicSlide(width, height, title);
        }
    }
}
