package tn.esprit.pibakcend.security.services;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tn.esprit.pibakcend.entities.Commentaire;
import tn.esprit.pibakcend.entities.Evenement;
import tn.esprit.pibakcend.entities.Publication;
import tn.esprit.pibakcend.entities.User;
import tn.esprit.pibakcend.Repository.CommentaireRepository;
import tn.esprit.pibakcend.Repository.PublicationRepository;
import tn.esprit.pibakcend.Repository.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.io.*;
import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentaireServiceImp implements ICommentaire{
    CommentaireRepository commentaireRepository;

    UserRepository userRepository;

    PublicationRepository publicationRepository;

    private TwillioSmsSender twillioSmsSender;

    @Override
    public Commentaire updateComm(Commentaire comm,Integer idComm) {
        Commentaire commentaire = commentaireRepository.findById(idComm).orElse(null);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<User> userOptional = userRepository.findByUsername(username);
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found"));
        comm.setIdComm(idComm);
        comm.setUser(user);
        comm.setPublication(commentaire.getPublication());

        return commentaireRepository.save(comm);
    }

    @Override
    public List<Commentaire> retrieveCommentaireByPubId(Integer idPub) {

        return commentaireRepository.findAll().stream().filter(x -> x.getPublication().getIdPub() == idPub).collect(Collectors.toList());
    }


    @Override
    public Commentaire assignCommentaireToPub(Commentaire comm, Integer idPub) throws IOException {

        Publication publication = publicationRepository.findById(idPub).orElse(null);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<User> userOptional = userRepository.findByUsername(username);
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found"));        if (publication != null && user != null) {
            comm.setPublication(publication);
            comm.setUser(user);
            commentaireRepository.save(comm);

            // Send SMS notification to the user who created the publication
            /*User pubUser = publication.getUser();
            if (pubUser != null) {
                String message = String.format("%s commented on your publication '%s'", user.getUsername(), publication.getTitrePub());
                SmsRequest smsRequest = new SmsRequest(pubUser.getTelNumber(), message);
                twillioSmsSender.sendSms(smsRequest);
            }*/

            // Check if comment is spam
            if (this.AntiSpam(comm)) {
                System.out.println("Comment is not spam.");
                return comm;
            } else {
                // Write the comment to a file in a specific directory
                try {
                    File file = new File("forum/spam/" + publication.getIdPub() + ".txt");
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    FileWriter writer = new FileWriter(file, true);
                    writer.write(String.format("%d %s %s\n", comm.getUser().getId(), comm.getContenuComm(), comm.getDateCreationComm()));
                    writer.close();
                    System.out.println("Comment is spam. Written to file: " + file.getAbsolutePath());
                } catch (IOException e) {
                    System.out.println("Failed to write comment to file: " + e.getMessage());
                }
                // Return null to indicate that the comment was not added to the publication
                return null;
            }
        }
        return null;
    }

    @Override
    public void deleteCommentaire(Integer idComm) throws AccessDeniedException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<User> userOptional = userRepository.findByUsername(username);
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Optional<Commentaire> optionalCommentaire = commentaireRepository.findById(idComm);
        if (optionalCommentaire.isPresent()) {
            Commentaire commentaire = optionalCommentaire.get();
            if (commentaire.getUser().equals(user)) {
                commentaireRepository.deleteById(idComm);
            } else {
                throw new AccessDeniedException("You do not have permission to delete this event");
            }
        } else {
            throw new EntityNotFoundException("Event with id " + idComm + " not found");
        }
    }

    @Override
    public Integer countByPublicationId(Integer idPub) {
        return commentaireRepository.countByPublicationId(idPub);
    }

    @Override
    public List<Commentaire> commentsUser(Long idUser) {

        List<Commentaire> comments = new ArrayList<Commentaire>();
	/*	if (cr != null)
			for (Comment c : cr.findAll()) {
				System.out.println("22222222222");
				if (c.getUser().getId() == idu)
					comments.add(c);
			}
		System.out.println("33333333333");*/
        return comments;
    }

    public boolean AntiSpam(Commentaire c) throws IOException {
        int p = 0;
        int links = 0;
        int userComments = 0;
        // Links
        String urlRegex = "((https?|ftp|gopher|telnet|file):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";
        Pattern pattern = Pattern.compile(urlRegex, Pattern.CASE_INSENSITIVE);
        Matcher urlMatcher = pattern.matcher(c.getContenuComm());

        while (urlMatcher.find()) {
            links++;
        }
        if (links <= 1)
            p += 2;
        else
            p--;

        System.out.println("P1=" + p);
        //  check is for the length of the comment's text. If it is less than 20 characters and there are no links,
        if (c.getContenuComm().length() < 20 && links < 1)
            p ++;
        else
            p--;

        System.out.println("P2=" + p);
        // Nb of comments
        if (c.getUser() != null)
            for (Commentaire comment : commentsUser(c.getUser().getId())) {

                if (comment.getPublication().getIdPub() == c.getPublication().getIdPub()) {
                    p++;
                }

            }

        System.out.println("P3=" + p);
        //  check searches for a spam word in a file named "forum/spam" followed by the publication ID and ".txt"
        if (c.getPublication() != null) {
            File spamF = new File("forum/spam" + c.getPublication().getIdPub() + ".txt");
            String[] wordss = null;

            FileReader frr = new FileReader(spamF);
            BufferedReader brr = new BufferedReader(frr);
            String strr;

            while ((strr = brr.readLine()) != null) {

                wordss = strr.split(" ");
                System.out.println("words[0=>"+wordss[0]+"\n");
                System.out.println("c.getUser().getId())="+c.getUser().getId());
                // Search for the word
                if (wordss[0].equals(Long.toString(c.getUser().getId()))) {
                    System.out.print("Spam exists");
                    p--;
                }


            }

        }
        System.out.println("P4=" + p);
        // check searches for bad words in a file named "forum/badWords.txt"
        File file = new File("forum/badWords.txt");
        String[] words = null;
        String[] bads = null;
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String str;

        while ((str = br.readLine()) != null) {

            words = str.split("\n");
            bads = c.getContenuComm().split(" ");
            for (String bad : bads) {
                for (String word : words) {
                    // Search for the word
                    if (word.equals(bad)) {
                        System.out.print("BAD=" + bad);
                        p=-2;
                    }
                }
            }
        }
        System.out.println("P5=" + p);
        System.out.println("c.getPost()=" + c.getPublication().getIdPub());
        // BodyUsed
        //check is to see if the exact same comment has already been made on the same publication
        Publication post=publicationRepository.findById(c.getPublication().getIdPub()).orElse(null);
        for (Commentaire comment : post.getCommentaires()) {

            if (comment.getContenuComm().equals(c.getContenuComm()) && comment.getIdComm() != c.getIdComm())
                p--;
        }
        System.out.println("P6=" + p);
        // random words
        //check looks for a sequence of five consecutive consonants in the comment's text.
        String cons = "bcdfghjklmnpqrstvwxz";
        char[] consonants = cons.toCharArray();
        int z = 0;
        char[] ch = c.getContenuComm().toLowerCase().toCharArray();
        for (int i = 0; i < c.getContenuComm().length(); i++) {
            boolean isConsonant = false;
            for (int j = 0; j < 20; j++) {
                if (ch[i] == consonants[j]) {
                    isConsonant = true;
                    break;
                }
            }
            if (isConsonant) {
                z++;
                if (z == 5)
                    break;
            } else {
                z = 0;
            }
        }
        if (z == 5)
            p--;
        else if (z == 0)
            p++;

        System.out.println("P7=" + p);

        if (p >= 1)
            return true;
        else

            return false;
    //final score of the comment is greater than or equal to 1, the method returns true, indicating that the comment is not spam. Otherwise, it returns false, indicating that the comment is spam.
    }
}

