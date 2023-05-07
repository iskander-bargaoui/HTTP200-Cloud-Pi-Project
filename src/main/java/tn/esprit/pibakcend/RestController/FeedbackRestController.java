package tn.esprit.pibakcend.RestController;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import lombok.AllArgsConstructor;
import opennlp.tools.stemmer.PorterStemmer;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pibakcend.Service.IFeedback;
import tn.esprit.pibakcend.entities.Feedback;
import tn.esprit.pibakcend.entities.Profile;
import tn.esprit.pibakcend.security.services.IProfile;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.AccessDeniedException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class FeedbackRestController {
    IFeedback iFeedback;
    IProfile iProfile;
    @PostMapping("/addFeedback")
    public Feedback addFeedback(@RequestBody Feedback Feedback) throws IOException {
        return iFeedback.addFeedback(Feedback);
    }
    @GetMapping("/Feedbacks")
    public List<Feedback> retrieveAllFeedback(){
        return iFeedback.retrieveAllFeedback();

    }

    @GetMapping("/FeedbackBYID/{id}")
    public Feedback retrieveFeedbackById(@PathVariable("id") Integer id){
        return iFeedback.retrieveFeedbackById(id);

    }

    @PutMapping("/updateFeedback")
    public Feedback updateFeedback(@RequestBody Feedback Feedback) throws IOException {
        return iFeedback.updateFeedback(Feedback);
    }

    @DeleteMapping("/deleteFeedback/{id}")
    public void deleteFeedback(@PathVariable("id") Integer id){
        iFeedback.deleteFeedback(id);

    }

    @GetMapping("/feedbacks/byProfile/{username}")
    public List<Feedback> findByProfile(@PathVariable("username") String username) throws AccessDeniedException {
        Profile profile = iProfile.findByUsername(username);
        List<Feedback> feedbackList = iFeedback.findByProfile(profile);
        return feedbackList;
    }
    @GetMapping("/feedbacks/byRating/{rating}")
    public List<Feedback> findByRating(@PathVariable("rating") int rating) {
        List<Feedback> feedbackList = iFeedback.findByRating(rating);
        return feedbackList;
    }

    @GetMapping("/feedbacks/topRated")
    public List<Feedback> findTopByRating(Profile profile) {
        int limit = 3;
        List<Feedback> feedbackList = iFeedback.findTopByRating(profile);
        return feedbackList;
    }

    @GetMapping("/feedbacks/averageRating/{username}")
    public Double AverageRatingperFeedback(@PathVariable("username") String username) throws AccessDeniedException {
        Profile profile = iProfile.findByUsername(username);
        return iFeedback.getAverageRatingByProfile(profile);
    }
    @GetMapping(path="/feedbacks/sentiment/{username}", produces = MediaType.IMAGE_PNG_VALUE)
    public BufferedImage getFeedbackCountBySentiment(@PathVariable("username") String username) throws AccessDeniedException {
        List<Feedback> feedbackList =findByProfile(username);
        // Preprocess feedback data
        List<String> preprocessedMessages = feedbackList.stream()
                .map(feedback -> {
                    try {
                        return preprocessMessage(feedback.getMessage());
                    } catch (IOException e) {
                        e.printStackTrace();
                        return null; // or handle the exception in some other way
                    }
                })
                .collect(Collectors.toList());

        // Perform sentiment analysis on feedback data
        List<String> sentimentList = preprocessedMessages.stream()
                .map(this::performSentimentAnalysis)
                .collect(Collectors.toList());

        // Aggregate feedback data by sentiment
        Map<String, Long> feedbackCountBySentiment = IntStream.range(0, feedbackList.size())
                .boxed()
                .collect(Collectors.groupingBy(
                        sentimentList::get,
                        Collectors.counting()));

        // Create dataset for pie chart
        DefaultPieDataset dataset = new DefaultPieDataset();
        for (Map.Entry<String, Long> entry : feedbackCountBySentiment.entrySet()) {
            dataset.setValue(entry.getKey(), entry.getValue());
        }

        // Create pie chart
        JFreeChart chart = ChartFactory.createPieChart(
                "Feedback Count by Sentiment",
                dataset,
                true,
                true,
                false);

        // Convert chart to image
        BufferedImage chartImage = null;
        try {
            chartImage = chart.createBufferedImage(400, 400);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Return chart image as response
        return chartImage;
    }


    private String preprocessMessage(String message) throws IOException {
        // Preprocess feedback message using OpenNLP tools
        // ...
        InputStream modelIn = getClass().getResourceAsStream("/en-token.bin");
        TokenizerModel tokenModel = new TokenizerModel(modelIn);
        Tokenizer tokenizer = new TokenizerME(tokenModel);
        PorterStemmer stemmer = new PorterStemmer();

        Set<String> stopWords = new HashSet<>();
        stopWords.add("a");
        stopWords.add("an");
        stopWords.add("the");
        // Preprocess feedback message
        String[] tokens = tokenizer.tokenize(message);
        List<String> tokenList = Arrays.asList(tokens);
        List<String> filteredTokenList = tokenList.stream()
                .filter(token -> !stopWords.contains(token))
                .collect(Collectors.toList());
        List<String> stemmedTokenList = filteredTokenList.stream()
                .map(stemmer::stem)
                .collect(Collectors.toList());
        String preprocessedMessage = String.join(" ", stemmedTokenList);
        return preprocessedMessage;
    }
    private String performSentimentAnalysis(String preprocessedMessage) {
        // Perform sentiment analysis on preprocessed feedback message using Stanford CoreNLP tool
        // Initialize Stanford CoreNLP tools
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, parse, sentiment");
        props.setProperty("parse.model", "edu/stanford/nlp/models/lexparser/englishPCFG.ser.gz");
        props.setProperty("tokenize.language", "en");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        // Perform sentiment analysis on preprocessed message
        Annotation annotation = pipeline.process(preprocessedMessage);
        String sentiment = annotation.get(CoreAnnotations.SentencesAnnotation.class)
                .get(0)
                .get(SentimentCoreAnnotations.SentimentClass.class);

        return sentiment;
    }

    @PostMapping("/filter")
    public void filterContent(String text) throws IOException {
            iFeedback.Badwords("good worker");
    }







}
