package PACKAGE_NAME.src.main.project.classes;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class StoryController {
    @FXML
    private Label titleLabel;
    @FXML
    private Label storyDescriptionLabel;

    private int currentStory = 0;

    private List<Story> stories = new ArrayList<>();

    public StoryController() {
        loadStoriesFromFile("src/main/project/scenes/Stories/Story.txt");
    }

    public List<Story> getStories() {
        return stories;
    }

    /**
     * @param filename This method reads the stories from a file and adds them to the stories list.
     *                 It seperates the title and the story description by a comma.
     */
    // This method reads the stories from a file and adds them to the stories ArrayList.
    private void loadStoriesFromFile(String filename) {

        // The method uses a try-with-resources statement to ensure that the BufferedReader is closed after it's no longer needed. This is a good practice to avoid resource leaks.
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            // While loop to read the file line by line
            while ((line = reader.readLine()) != null) {
                // Each line is split into parts using a plus (+) as the delimiter.
                // + is a special character in regular expressions, so it needs to be escaped with two backslashes (\\) to be treated as a literal plus sign.
                String[] parts = line.split("\\+");
                // The first part is considered the title, and the second part is considered the story description.
                String title = parts[0];
                String storyDescription = parts[1];

                // A new Story object is created using the extracted title and story description.
                Story story = new Story(title, storyDescription);

                // Then add the newly created story object to the "stories" ArrayList.
                stories.add(story);
            }
            // If any IOException occurs during the file reading process, it's caught and the error message is printed to the console.
            //  IOException is typically thrown when an error occurs while performing input or output operations, such as reading or writing to a file
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * if the story list is not empty, it sets the title and story description of the first story
     * in the list to the corresponding labels.
     */
    public void initialize() {
        if (!stories.isEmpty()) {
            titleLabel.setText(stories.get(currentStory).getTitle());
            storyDescriptionLabel.setText(stories.get(currentStory).getStoryDescription());
        }
    }
}