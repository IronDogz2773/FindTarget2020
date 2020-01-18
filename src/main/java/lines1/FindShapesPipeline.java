package lines1;

import org.opencv.core.Mat;
import edu.wpi.first.vision.VisionPipeline;

class FindShapesPipeline implements VisionPipeline {
    public Mat m;

    @Override
    public void process(Mat image) {
        FindShapes.Result result = FindShapes.processImage(image);
        m = result.display;
        System.out.println(result.contours.length);
    }
}