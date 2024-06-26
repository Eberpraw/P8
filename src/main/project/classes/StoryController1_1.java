package main.project.classes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;

public class StoryController1_1 extends StoryController1 {
    @FXML
    private TextArea storyDescriptionLabel2;

    @FXML
    private Button playAudioButton;

    @FXML
    private Button backStory1_1;

    @FXML
    private Button toTask;

    @Override
    public void initialize() {
        super.initialize(); // Call the initialize method from the superclass
        if (getStoryDescriptionLabel2() != null) {
            getTitleLabel().setText(getStories().get(getCurrentStory()).getTitle()); // Set the title of the story
            getStoryDescriptionLabel2().setText(getStories().get(getCurrentStory()).getStoryDescription2()); // Set the story description
        }

        Animations.buttonAnimation(backStory1_1); // Load Animation class for the button
        Animations.buttonAnimation(toTask);
        Animations.buttonAnimation(playAudioButton);
        storyProgress.resetCircles();
        storyProgress.fillCircle("Story1-1");

    }

    public void loadingIndicator() {
        toTask.setText("Loading..."); // Set the text of the button to "Loading..."
    }

    public boolean isStory1_1() { // This method checks if the current story is the first part of the first story
        return true;
    }

    // This method is called when the user clicks the "To Task" button
    public void toTask(ActionEvent event) throws IOException {
        loadingIndicator();
        // Stop the audio
        AudioController.stopAudio();
        // Set the first story as completed
        StoryProgress.setStory1Completed(true);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/scenes/Task.fxml"));
        Parent newSceneParent = loader.load();
        TaskController controller = loader.getController();

        // Create the Task and TaskDescription
        Task task1 = new Task();
        TaskDescription taskDescription1 = TaskDescription.createTaskDescription1();
        task1.setTaskDescription(taskDescription1);

        // Set the task in the controller
        controller.setTask(task1);

        Scene newScene = new Scene(newSceneParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }
    // This method is called when the user clicks the "Back" button
    public void backAgain(ActionEvent event) throws IOException {
        AudioController.stopAudio();
        Parent newSceneParent = FXMLLoader.load(getClass().getResource("/scenes/Story1.fxml"));
        Scene newScene = new Scene(newSceneParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }
    // This method is called when the user clicks the "Back" button back to the Library
    public void Story1_1ToLibrary(ActionEvent event) throws IOException {
        AudioController.stopAudio();
        Node node = (Node) event.getSource(); // Get the source of the event (the button)
        Stage stage = (Stage) node.getScene().getWindow(); // Get the stage from the button
        Scene scene = stage.getScene(); // Get the scene from the stage
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/scenes/Library.fxml")); // Load the FXML file
        Parent root = fxmlLoader.load(); // Load the FXML file into a Parent object
        scene.setRoot(root); // Set the scene root to the new FXML file
    }

    // Returns the current story object
    public Story getCurrentStoryObject() {
        return getStories().get(getCurrentStory());
    }

    @FXML
    private void playAudio() { // This method is called when the user clicks the "Play Audio" button
        if (!AudioController.isAudioPlaying()){
        // Create an instance of Audio
        Audio audio = new Audio("audio_files/Story1-part2.mp3");

        // Create an instance of AudioController and play the audio
        AudioController.playAudio(audio);
        } else {
            AudioController.stopAudio();
        }
    }
}
