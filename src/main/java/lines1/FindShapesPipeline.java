package lines1;

import org.opencv.core.Mat;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.vision.VisionPipeline;

class FindShapesPipeline implements VisionPipeline {
    public Mat m;

    public FindShapesPipeline()
    {
        NetworkTableInstance.getDefault().startClient("rpi4", 2345);
    } 
    @Override
    public void process(Mat image) {
        FindShapes.Result result = FindShapes.processImage(image);
        m = result.display;
        //System.out.println(result.contours.length);
        if (result.target != null) {
            double x = result.target[0];
            double xc = m.cols() / 2;
            double alpha = 30 * (x - xc) / xc;
            System.out.println("t=" + result.target[0] + "," + result.target[1]);
            System.out.println(alpha + " Degrees");
            NetworkTableInstance.getDefault().getEntry("/angle").forceSetDouble(-alpha);
        }
    }
}
