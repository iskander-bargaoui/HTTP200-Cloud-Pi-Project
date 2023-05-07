package tn.esprit.pibakcend.security.services;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import lombok.AllArgsConstructor;
import okhttp3.*;
import opennlp.tools.stemmer.PorterStemmer;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pibakcend.Repository.FeedbackRepository;
import tn.esprit.pibakcend.entities.Feedback;
import tn.esprit.pibakcend.entities.Feedback;
import tn.esprit.pibakcend.entities.Profile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
@Service
@AllArgsConstructor
public class FeedbackServiceImpl implements tn.esprit.pibakcend.Service.IFeedback {
    FeedbackRepository feedbackRepository;
    @Override
    public Feedback addFeedback(Feedback Feedback) throws IOException {
            String message = Feedback.getMessage();
          //  if (containsBadWord(message)) {
           //     message = sanitizeMessage(message);
             String final_msg =Badwords(message);
            Feedback.setMessage(final_msg);

        return feedbackRepository.save(Feedback);
    }

    @Override
    public Feedback updateFeedback(Feedback Feedback) throws IOException {
        String message = Feedback.getMessage();
       // if (containsBadWord(message)) {
        //    message = sanitizeMessage(message);
        //}
            String final_msg =Badwords(message);
            Feedback.setMessage(final_msg);
        Feedback.setUpdatedDate(new Date());
        return feedbackRepository.save(Feedback);
    }
    public String Badwords(String text) throws IOException {
    OkHttpClient client = new OkHttpClient().newBuilder().build();
    String censoredContent="";
        MediaType mediaType = MediaType.parse("text/plain");
    RequestBody body = RequestBody.create(mediaType, text);

    Request request = new Request.Builder()
            .url("https://api.apilayer.com/bad_words?censor_character=*")
            .addHeader("apikey", "Am3jC9Y1rNWLQLHDYr9xwrxyTsAHrqH4")
            .method("POST", body)
        .build();
        Response response = client.newCall(request).execute();
        String patternString = "\"censored_content\": \"(.*?)\"";
        Pattern pattern = Pattern.compile(patternString);
        String input = response.body().string();
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            censoredContent = matcher.group(1);
            System.out.println(censoredContent);
        }
        return censoredContent;
    }
    @Override
    public Feedback retrieveFeedbackById(Integer id) {
        return feedbackRepository.findById(id).orElse(null);
    }

    @Override
    public List<Feedback> retrieveAllFeedback() {
        return feedbackRepository.findAll();
    }

    @Override
    public void deleteFeedback(Integer id) {
        feedbackRepository.deleteById(id);
    }

    @Override
    public List<Feedback> findByProfile(Profile profile) {
        return feedbackRepository.findByProfile(profile);
    }

    @Override
    public List<Feedback> findByRating(int rating) {
        return feedbackRepository.findByRating(rating);
    }

    @Override
    public List<Feedback> findTopByRating (Profile profile) {
        List<Feedback> feedbackList = feedbackRepository.findByProfile(profile);
        Collections.sort(feedbackList, (f1, f2) -> Double.compare(f2.getRating(), f1.getRating()));
        int limit=3;
        List<Feedback> topRatedFeedback = feedbackList.stream().limit(limit).collect(Collectors.toList());
        return topRatedFeedback;
    }
   /*
    public String sanitizeMessage(String message) {
        String sanitizedMessage = message;
        for (String badWord : BAD_WORDS) {
            String regex = "\\b" + Pattern.quote(badWord) + "\\b";
            StringBuilder asterisks = new StringBuilder();
            for (int i = 0; i < badWord.length(); i++) {
                asterisks.append("*");
            }
            sanitizedMessage = sanitizedMessage.replaceAll("(?i)" + regex, asterisks.toString());
        }
        return sanitizedMessage;
    }


    private boolean containsBadWord(String message) {
        for (String badWord : BAD_WORDS) {
            if (message.toLowerCase().contains(badWord)) {
                return true;
            }
        }
        return false;
    }
*/

    @Override
    public Double getAverageRatingByProfile(Profile profile) {
        List<Feedback> feedbackList = feedbackRepository.findByProfile(profile);
        Double averageRating = feedbackList.stream()
                .mapToDouble(Feedback::getRating)
                .average()
                .orElse(Double.NaN);
        return averageRating;
    }




}
