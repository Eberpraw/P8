package PACKAGE_NAME.src.main.project.classes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.Console;
import java.io.IOException;

public class StoryController1_1 extends StoryController1 {
    @Override
    public void initialize() {
        super.initialize();
        if (getStoryDescriptionLabel2() != null) {
            getTitleLabel().setText(getStories().get(getCurrentStory()).getTitle());
            getStoryDescriptionLabel2().setText(getStories().get(getCurrentStory()).getStoryDescription2());
        }
    }

    public boolean isStory1_1() {
        return true;
    }

    public void toTask(ActionEvent event) throws IOException {
        StoryProgress.setStory1Completed(true);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../scenes/Task.fxml"));
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

    public void backAgain(ActionEvent event) throws IOException {
        Parent newSceneParent = FXMLLoader.load(getClass().getResource("../scenes/Story1.fxml"));
        Scene newScene = new Scene(newSceneParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }

    // Returns the current story object
    public Story getCurrentStoryObject() {
        return getStories().get(getCurrentStory());
    }

    @FXML
    private void playAudio() {
        // Create an instance of Audio
        Audio audio = new Audio("audio_files/kill_me.mp3");

        // Create an instance of AudioController and play the audio
        AudioController.playAudio(audio);
    }
}
