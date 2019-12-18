package govind.sign_language.processing.postprocessing;

import govind.sign_language.imaging.IFrame;

public interface IFramePostProcessor {

    IFrame postProcess(IFrame inputFrame);

}
