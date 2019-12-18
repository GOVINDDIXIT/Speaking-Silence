package govind.sign_language.processing.preprocessing;

import org.opencv.android.CameraBridgeViewBase.CvCameraViewFrame;

import govind.sign_language.imaging.IFrame;

public interface IFramePreProcessor {

    IFrame preProcess(CvCameraViewFrame inputFrame);

}
