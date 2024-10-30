package lumCode.processingGameBase;

import processing.core.PApplet;
import processing.core.PConstants;

import static processing.core.PApplet.lerpColor;
import static processing.core.PConstants.RGB;

public class Colors {
    /*
     * Processing colors described as hex values, such that; 0x|AA|RR|GG|BB
     */

    public static final int BLACK = 0xFF000000;
    public static final int D_GREY = 0xFF3F3F3F;
    public static final int GREY = 0xFF7F7F7F;
    public static final int L_GREY = 0xFFBFBFBF;
    public static final int WHITE = 0xFFFFFFFF;

    public static final int D_BLUE = 0xFF00007F;
    public static final int BLUE = 0xFF0000FF;
    public static final int L_BLUE = 0xFF7F7FFF;

    public static final int D_GREEN = 0xFF007F00;
    public static final int GREEN = 0xFF00FF00;
    public static final int L_GREEN = 0xFF7FFF7F;

    public static final int D_RED = 0xFF7F0000;
    public static final int RED = 0xFFFF0000;
    public static final int L_RED = 0xFFFF7F7F;

    public static final int D_MAGENTA = 0xFF7F007F;
    public static final int MAGENTA = 0xFFFF00FF;
    public static final int L_MAGENTA = 0xFFFF7FFF;

    public static final int D_CYAN = 0xFF007F7F;
    public static final int CYAN = 0xFF00FFFF;
    public static final int L_CYAN = 0xFF7FFFFF;

    public static final int D_YELLOW = 0xFF7F7F00;
    public static final int YELLOW = 0xFFFFFF00;
    public static final int L_YELLOW = 0xFFFFFF7F;

    public static final int D_TURQUOISE = 0xFF007F4F;
    public static final int TURQUOISE = 0xFF00FF9F;
    public static final int L_TURQUOISE = 0xFF3FFF7F;

    public static final int D_PURPLE = 0xFF4F007F;
    public static final int PURPLE = 0xFF9F00FF;
    public static final int L_PURPLE = 0xFF7F3FFF;

    public static final int D_ORANGE = 0xFF7F4F00;
    public static final int ORANGE = 0xFFFF9F00;
    public static final int L_ORANGE = 0xFFFF7F3F;

    public static final int D_LIME = 0xFF4F7F00;
    public static final int LIME = 0xFF9FFF00;
    public static final int L_LIME = 0xFF7FFF3F;

    public static final int D_NAVY = 0xFF004F7F;
    public static final int NAVY = 0xFF009FFF;
    public static final int L_NAVY = 0xFF3F7FFF;

    public static final int D_PINK = 0xFF7F004F;
    public static final int PINK = 0xFFFF009F;
    public static final int L_PINK = 0xFFFF3F7F;

    public static int mix(int c1, int c2, double percentage) {
        return PApplet.lerpColor(c1, c2, (float) percentage, PConstants.RGB);
    }

    public static int fromRGB(byte R, byte G, byte B) {
        return Main.instance().color(R, B, G);
    }

    public static int fromRGBAlpha(byte R, byte G, byte B, byte A) {
        return Main.instance().color(R, B, G, A);
    }
}
