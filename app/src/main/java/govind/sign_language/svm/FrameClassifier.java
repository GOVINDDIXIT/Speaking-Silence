package govind.sign_language.svm;

import android.util.Log;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.TermCriteria;
import org.opencv.ml.SVM;

import java.io.File;
import java.util.Iterator;

import govind.sign_language.imaging.IFrame;
import govind.sign_language.processing.IFrameProcessor;

import static org.opencv.core.Core.meanStdDev;

public class FrameClassifier implements IFrameProcessor {

    private SVM svm;
    private IFrame workingFrame;
    private MatOfPoint features;
    private LetterClass result;
    private Mat flatFeatures, results;

    public FrameClassifier(File xmlFile){

        flatFeatures = new Mat();
        results = new Mat();
        svm = SVM.create();

        svm.setType(SVM.C_SVC);
        svm.setKernel(SVM.RBF);

        svm.setTermCriteria(new TermCriteria(TermCriteria.MAX_ITER, 100, 1e-6));

        svm = SVM.load(xmlFile.getAbsolutePath());

    }

    @Override
    public IFrame process(IFrame inputFrame) {
        workingFrame = inputFrame;
        classify();

        return workingFrame;
    }

    private void classify(){

        flattenFeatures();
        flatFeatures.convertTo(flatFeatures, CvType.CV_32F);

        float response = svm.predict(flatFeatures);

        result = LetterClass.getLetter((int)response);
        workingFrame.setLetterClass(result);

        Log.d("DEBUG", "LETTER CLASS: " + result);
    }

    private boolean isEligibleToClassify() {

        if (!workingFrame.getFeatures().isEmpty()) {
            Iterator<MatOfPoint> allMatOfPoint = workingFrame.getFeatures().iterator();
            while (allMatOfPoint.hasNext()) {
                features = allMatOfPoint.next();
                return true;
            }
        }
        return false;
    }

    private void flattenFeatures(){

        flatFeatures = workingFrame.getHogDesc();
        flatFeatures = flatFeatures.reshape(1,1);

    }
}
