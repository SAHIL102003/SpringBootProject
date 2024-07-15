package mypackage;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by frkn on 16/01/2017.
 */

@Service
public class FaceDetectionService {


    private Resource faceResource = new ClassPathResource("haarcascades/haarcascade_frontalface_alt.xml");
    private Resource smilefaceResource = new ClassPathResource("haarcascades/haarcascade_smile.xml");
    private List<FaceEntity> faceEntities;
    private Mat image;
    public FaceDetectionService detectFace(MultipartFile file) throws IOException {
        faceEntities=new ArrayList<>();
        MatOfRect faceDetections = new MatOfRect();
        MatOfRect smileDetections = new MatOfRect();
        CascadeClassifier faceDetector = new CascadeClassifier(faceResource.getFile().getAbsolutePath());
        CascadeClassifier smilefaceDetector = new CascadeClassifier(smilefaceResource.getFile().getAbsolutePath());

        image = Imgcodecs.imdecode(new MatOfByte(file.getBytes()), Imgcodecs.CV_LOAD_IMAGE_UNCHANGED);
        faceDetector.detectMultiScale(image, faceDetections);

//        System.out.println(String.format("Detected %s faces", faceDetections.toArray().length));

//        for (Rect rect : faceDetections.toArray()) {
//            faceEntities.add(new FaceEntity(rect.x, rect.y, rect.width, rect.height, 0));
//        }


        Mat face = image.submat(faceDetections.toArray()[0]);
        smilefaceDetector.detectMultiScale(face, smileDetections);

	    for (Rect rect : smileDetections.toArray()) {
            faceEntities.add(new FaceEntity(rect.x, rect.y, rect.width, rect.height, 0));
	    }

        
        
        return this;
    }
    
    public void ProcessForSmileDetection(String imageValue) {
    	CascadeClassifier faceDetector = new CascadeClassifier(new File("haarcascades/haarcascade_frontalface_alt.xml").getAbsolutePath());
	    CascadeClassifier smileDetector = new CascadeClassifier(new File("haarcascades/haarcascade_smile.xml").getAbsolutePath());
	    Mat src = Imgcodecs.imread(imageValue); 
	    MatOfRect faceDetections = new MatOfRect();
	    MatOfRect smileDetections = new MatOfRect();

    
    }


    public List<FaceEntity> toList() {
        return faceEntities;
    }


    public byte[] toImage() {
        for (FaceEntity fc : faceEntities) {
            Imgproc.rectangle(image, new Point(fc.getX(), fc.getY()), new Point(fc.getX() + fc.getWidth(), fc.getY() + fc.getHeight()), new Scalar(0, 255, 0));
        }
        return mat2Image(image);
    }

    private byte[] mat2Image(Mat frame) {
        MatOfByte buffer = new MatOfByte();
        Imgcodecs.imencode(".jpg", frame, buffer);
        return buffer.toArray();
    }
    
    
    
    
    public String detectSmileOnFace(MultipartFile file) throws IOException {
        faceEntities=new ArrayList<>();
        MatOfRect faceDetections = new MatOfRect();
        MatOfRect smileDetections = new MatOfRect();
        CascadeClassifier faceDetector = new CascadeClassifier(faceResource.getFile().getAbsolutePath());
        CascadeClassifier smilefaceDetector = new CascadeClassifier(smilefaceResource.getFile().getAbsolutePath());

        image = Imgcodecs.imdecode(new MatOfByte(file.getBytes()), Imgcodecs.CV_LOAD_IMAGE_UNCHANGED);
        faceDetector.detectMultiScale(image, faceDetections);

//        System.out.println(String.format("Detected %s faces", faceDetections.toArray().length));

//        for (Rect rect : faceDetections.toArray()) {
//            faceEntities.add(new FaceEntity(rect.x, rect.y, rect.width, rect.height, 0));
//        }


        Mat face = image.submat(faceDetections.toArray()[0]);
        smilefaceDetector.detectMultiScale(face, smileDetections);

//	    for (Rect rect : smileDetections.toArray()) {
//            faceEntities.add(new FaceEntity(rect.x, rect.y, rect.width, rect.height, 0));
//	    }
	    
        
	    if(smileDetections.toArray().length>0)
	    {
	    	return "Smile";
	    }
	    else {
	    	return "Sad";
	    }
        
        

    }

}




//public FaceDetectionService detectFace(MultipartFile file) throws IOException {
//
//    faceEntities=new ArrayList<>();
//    MatOfRect faceDetections = new MatOfRect();
//    CascadeClassifier faceDetector = new CascadeClassifier(faceResource.getFile().getAbsolutePath());
//    CascadeClassifier faceDetector = new CascadeClassifier(smilefaceResource.getFile().getAbsolutePath());
//
//
//
//    image = Imgcodecs.imdecode(new MatOfByte(file.getBytes()), Imgcodecs.CV_LOAD_IMAGE_UNCHANGED);
//    faceDetector.detectMultiScale(image, faceDetections);
//
//    System.out.println(String.format("Detected %s faces", faceDetections.toArray().length));
//
//    for (Rect rect : faceDetections.toArray()) {
//        faceEntities.add(new FaceEntity(rect.x, rect.y, rect.width, rect.height, 0));
//    }
//
//    return this;
//}
