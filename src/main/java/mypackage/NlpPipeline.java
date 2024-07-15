package mypackage;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations.SentimentAnnotatedTree;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;

public class NlpPipeline {
	 static StanfordCoreNLP pipeline;
	    public static void init() 
	    {
	        Properties props = new Properties();
	        props.setProperty("annotators", "tokenize, ssplit, parse, sentiment");
	        pipeline = new StanfordCoreNLP(props);
	    }
	    public static List<SentimentModel> estimatingSentiment(String text)
	    {
	   int sentimentInt;
	      String sentimentName; 
	      Annotation annotation = pipeline.process(text);
	      List<SentimentModel> lst=new ArrayList<SentimentModel>();
	      for(CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class))
	      {
	         Tree tree = sentence.get(SentimentAnnotatedTree.class);
	        sentimentInt = RNNCoreAnnotations.getPredictedClass(tree); 
	                sentimentName = sentence.get(SentimentCoreAnnotations.SentimentClass.class);
	                SentimentModel sm=new SentimentModel(sentence.toString(), sentimentInt, sentimentName);
	                lst.add(sm);
	     //   System.out.println(sentimentName + "\t" + sentimentInt + "\t" + sentence);
	      }
	      return lst;
	     }
}
