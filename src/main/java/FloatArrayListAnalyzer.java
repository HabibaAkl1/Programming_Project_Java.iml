import java.util.ArrayList;
import java.util.Collections;

import java.util.ArrayList;
import java.util.Collections;

public class FloatArrayListAnalyzer extends ArrayList<Float> {
    public FloatArrayListAnalyzer(ArrayList<Float> data) {

    }

    public float getMinimum() {
        float min = 0;
        for (Float value : this) {
            if (value != null && value < min) {
                min = value;
            }
        }
        return min;
    }

    public float getMaximum() {
        float max = 0;
        for (Float value : this) {
            if (value != null && value > max) {
                max = value;
            }
        }
        return max;
    }
}


