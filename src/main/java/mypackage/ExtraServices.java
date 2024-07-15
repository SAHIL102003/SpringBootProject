package mypackage;

import com.asprise.ocr.Ocr;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.util.CoreMap;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
public class ExtraServices {

	public String GetTextFromImage() {
		Ocr.setUp(); // one time setup
		Ocr ocr = new Ocr(); // create a new OCR engine
		ocr.startEngine("eng", Ocr.SPEED_FASTEST); // English
		String s = ocr.recognize(new File[] {new File("C:\\Users\\CIIT\\OneDrive\\Desktop\\images\\dd.jpg")},
		  Ocr.RECOGNIZE_TYPE_ALL, Ocr.OUTPUT_FORMAT_PLAINTEXT); // PLAINTEXT | XML | PDF | RTF
		System.out.println("Result: " + s);
		ocr.stopEngine();
		return s;
	}
	
	
	
	 public   String getSentiment(String text, StanfordCoreNLP pipeline) {
	        // Create an Annotation object with the input text
	        Annotation annotation = new Annotation(text);

	        // Run all the NLP annotators on the text
	        pipeline.annotate(annotation);

	        // Extract the sentiment from the annotation
	        CoreMap sentence = annotation.get(CoreAnnotations.SentencesAnnotation.class).get(0);
	        String sentiment = sentence.get(SentimentCoreAnnotations.SentimentClass.class);

	        return sentiment;
	    }
}
