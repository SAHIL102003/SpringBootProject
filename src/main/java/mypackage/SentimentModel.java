package mypackage;

public class SentimentModel {

	private String sentence;
	private int sentimentInt;
	private String sentimentName;
	public SentimentModel(String sentence, int sentimentInt, String sentimentName) {
		super();
		this.sentence = sentence;
		this.sentimentInt = sentimentInt;
		this.sentimentName = sentimentName;
	}
	public SentimentModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getSentence() {
		return sentence;
	}
	public void setSentence(String sentence) {
		this.sentence = sentence;
	}
	public int getSentimentInt() {
		return sentimentInt;
	}
	public void setSentimentInt(int sentimentInt) {
		this.sentimentInt = sentimentInt;
	}
	public String getSentimentName() {
		return sentimentName;
	}
	public void setSentimentName(String sentimentName) {
		this.sentimentName = sentimentName;
	}
}
