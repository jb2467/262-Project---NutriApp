package unitXX;

import java.util.ArrayList;
import java.util.HashMap;

import Backend.*;
import Backend.Adapter.HandlerCSV;
import Backend.Adapter.JSONHandler;
import Backend.Adapter.NewDatatypeHandler;
import Backend.Adapter.XMLHandler;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HelloFX extends Application {
    Stage window;
    Scene login, createUser, homepage, history, meal;
    Button loginButton, homepageButton, mealButton, workoutButton, goalButton, historyButton, ingredientsButton,
            fileButton;
    User user;
    Goal goal;
    Guest guest;
    History user_history;
    NewDatatypeHandler handler;
    Ingredient i = new Ingredient();

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("NutriApp");
        login = loginScene();
        primaryStage.setScene(login);
        window = primaryStage;

        primaryStage.show();
    }

    public Scene loginScene() {
        GridPane login_layout = new GridPane();
        login_layout.setPadding(new Insets(150, 10, 10, 135));
        login_layout.setVgap(8);
        login_layout.setHgap(10);

        // User name and Password Label
        Label user_name_label = new Label("User Name:");
        GridPane.setConstraints(user_name_label, 0, 0);
        login_layout.getChildren().add(user_name_label);

        Label pass_label = new Label("Password:");
        GridPane.setConstraints(pass_label, 0, 1);
        login_layout.getChildren().add(pass_label);

        // TextFields for UserName and Password
        TextField user_name_input = new TextField();
        user_name_input.setPromptText("User Name");
        GridPane.setConstraints(user_name_input, 1, 0);
        login_layout.getChildren().add(user_name_input);

        TextField pass_input = new TextField();
        pass_input.setPromptText("Password");
        GridPane.setConstraints(pass_input, 1, 1);
        login_layout.getChildren().add(pass_input);

        // Button for creating new user
        loginButton = new Button("Login");
        GridPane.setConstraints(loginButton, 1, 2);
        login_layout.getChildren().add(loginButton);

        Button guestButton = new Button("Guest");
        GridPane.setConstraints(guestButton, 1, 4);
        login_layout.getChildren().add(guestButton);

        Button create_user = new Button("Create Account");
        GridPane.setConstraints(create_user, 1, 3);
        login_layout.getChildren().add(create_user);

        Scene loginScene = new Scene(login_layout, 600, 550);

        guestButton.setOnAction(f -> {
            guest = new Guest();
            Scene guestHomePage = GuestHome();
            window.setScene(guestHomePage);
        });

        create_user.setOnAction(x -> {
            Scene createUserScene = createUserScene();
            window.setScene(createUserScene);
        });

        loginButton.setOnAction(e -> AlertBox.display("Error", "Invalid Username and Password"));

        // TO MAKE THIS LOGIN FUNCTION WORK WE NEED A JSON FILE THAT HAS STORED USERS TO
        // CHECK FROM.
        // loginButton.setOnAction(e -> {
        // user = new User(user_name_input.getText(), pass_input.getText());
        // createUser = createUserScene();
        // window.setScene(createUser);
        // });
        return loginScene;
    }

    public Scene createUserScene() {
        // Code for the login page UI below
        GridPane login_layout = new GridPane();
        login_layout.setPadding(new Insets(150, 10, 10, 135));
        login_layout.setVgap(8);
        login_layout.setHgap(10);

        // Name Label, Height Label, Weight Label, Birthday Label, Goal Label, UserName
        // laber, Password Label
        Label name_label = new Label("Name:");
        GridPane.setConstraints(name_label, 0, 0);
        login_layout.getChildren().add(name_label);

        Label height_label = new Label("Height:");
        GridPane.setConstraints(height_label, 0, 1);
        login_layout.getChildren().add(height_label);

        Label weight_label = new Label("Weight:");
        GridPane.setConstraints(weight_label, 0, 2);
        login_layout.getChildren().add(weight_label);

        Label target_weight_label = new Label("Target Weight:");
        GridPane.setConstraints(target_weight_label, 0, 3);
        login_layout.getChildren().add(target_weight_label);

        Label average_calorie_label = new Label("Average Calorie:");
        GridPane.setConstraints(average_calorie_label, 0, 4);
        login_layout.getChildren().add(average_calorie_label);

        Label birthdate_label = new Label("Birthday:");
        GridPane.setConstraints(birthdate_label, 0, 5);
        login_layout.getChildren().add(birthdate_label);

        // Label goal_label = new Label("Goal:");
        // GridPane.setConstraints(goal_label,0,6);
        // login_layout.getChildren().add(goal_label);

        Label user_name_label = new Label("Username:");
        GridPane.setConstraints(user_name_label, 0, 7);
        login_layout.getChildren().add(user_name_label);

        Label password_label = new Label("Password:");
        GridPane.setConstraints(password_label, 0, 8);
        login_layout.getChildren().add(password_label);

        // Name input, Height input, Weight input, Birthday input

        TextField name_input = new TextField();
        name_input.setPromptText("FIRST LAST");
        GridPane.setConstraints(name_input, 1, 0);
        login_layout.getChildren().add(name_input);

        TextField height_input = new TextField();
        height_input.setPromptText("Centimeter (Decimal)");
        GridPane.setConstraints(height_input, 1, 1);
        login_layout.getChildren().add(height_input);

        TextField weight_input = new TextField();
        weight_input.setPromptText("LBS (Decimal)");
        GridPane.setConstraints(weight_input, 1, 2);
        login_layout.getChildren().add(weight_input);

        TextField target_weight_input = new TextField();
        target_weight_input.setPromptText("LBS (Decimal)");
        GridPane.setConstraints(target_weight_input, 1, 3);
        login_layout.getChildren().add(target_weight_input);

        TextField average_calorie_input = new TextField();
        average_calorie_input.setPromptText("Average Daily Calories");
        GridPane.setConstraints(average_calorie_input, 1, 4);
        login_layout.getChildren().add(average_calorie_input);

        TextField birthdate_input = new TextField();
        birthdate_input.setPromptText("MM/DD/YYYY");
        GridPane.setConstraints(birthdate_input, 1, 5);
        login_layout.getChildren().add(birthdate_input);

        // ChoiceBox<String> goalType = new ChoiceBox<>();
        // //getItems returns the observable list objects
        // goalType.setValue("Lose Weight");
        // goalType.getItems().addAll("Gain Weight","Maintain Weight","Lose Weight");
        // GridPane.setConstraints(goalType,1,6);
        // login_layout.getChildren().add(goalType);

        TextField user_name_input = new TextField();
        user_name_input.setPromptText("Username");
        GridPane.setConstraints(user_name_input, 1, 7);
        login_layout.getChildren().add(user_name_input);

        TextField password_input = new TextField();
        password_input.setPromptText("Password");
        GridPane.setConstraints(password_input, 1, 8);
        login_layout.getChildren().add(password_input);

        // Button for creating new user
        loginButton = new Button("Create User");
        GridPane.setConstraints(loginButton, 1, 9);
        login_layout.getChildren().add(loginButton);
        Scene loginScene = new Scene(login_layout, 600, 550);

        loginButton.setOnAction(e -> {

            try {
                if ((target_weight_input.getText().length() == 0) || (average_calorie_input.getText().length() == 0) ||
                        (name_input.getText().length() == 0) || (height_input.getText().length() == 0)
                        || (weight_input.getText().length() == 0) ||
                        (birthdate_input.getText().length() == 0) || (user_name_input.getText().length() == 0)
                        || (password_input.getText().length() == 0)) {
                    AlertBox.display("Error", "Must Fill All Inputs!");
                } else {
                    String goalType = "";
                    if (Double.parseDouble(target_weight_input.getText()) > Double.parseDouble(weight_input.getText())
                            &&
                            Double.parseDouble(target_weight_input.getText())
                                    - Double.parseDouble(weight_input.getText()) > 5) {
                        goalType = "Gain Weight";
                    } else if (Double.parseDouble(target_weight_input.getText()) < Double
                            .parseDouble(weight_input.getText()) &&
                            Double.parseDouble(target_weight_input.getText())
                                    - Double.parseDouble(weight_input.getText()) < -5) {
                        goalType = "Lose Weight";
                    } else {
                        goalType = "Maintain Weight";
                    }
                    goal = new Goal(Double.parseDouble(target_weight_input.getText()),
                            Integer.parseInt(average_calorie_input.getText()), goalType);
                    user = new User(name_input.getText(), Double.parseDouble(height_input.getText()),
                            Double.parseDouble(weight_input.getText()), birthdate_input.getText(), goal,
                            user_name_input.getText(), password_input.getText());
                    user.setName(name_input.getText());
                    user.setHeight(Double.parseDouble(height_input.getText()));
                    user.setWeight(Double.parseDouble(weight_input.getText()));
                    user.setBirthDate(birthdate_input.getText());

                    user_history = new History();
                    homepage = homeScene();
                    window.setScene(homepage);
                }
            } catch (Exception d) {
                AlertBox.display("Error", "Invalid input for birthdate. Please use this format \"##/##/####\"");
            }
        });

        return loginScene;
    }

    public Scene setUserScene() {
        // Code for the edit page UI below
        GridPane login_layout = new GridPane();
        login_layout.setPadding(new Insets(150, 10, 10, 135));
        login_layout.setVgap(8);
        login_layout.setHgap(10);

        // Name Label, Height Label, Weight Label, Birthday Label
        Label name_label = new Label("Name:");
        GridPane.setConstraints(name_label, 0, 0);
        login_layout.getChildren().add(name_label);

        Label height_label = new Label("Height:");
        GridPane.setConstraints(height_label, 0, 1);
        login_layout.getChildren().add(height_label);

        Label weight_label = new Label("Weight:");
        GridPane.setConstraints(weight_label, 0, 2);
        login_layout.getChildren().add(weight_label);

        Label birthdate_label = new Label("Birthday:");
        GridPane.setConstraints(birthdate_label, 0, 3);
        login_layout.getChildren().add(birthdate_label);

        Label team_label = new Label("Teams:");
        GridPane.setConstraints(team_label, 0, 4);
        login_layout.getChildren().add(team_label);

        // Name input, Height input, Weight input, Birthday input

        TextField name_input = new TextField();
        // name_input.setPromptText("FIRST LAST");
        name_input.setText(user.getName());
        GridPane.setConstraints(name_input, 1, 0);
        login_layout.getChildren().add(name_input);

        TextField height_input = new TextField();
        // height_input.setPromptText("Centimeter (Decimal)");
        height_input.setText(Double.toString(user.getHeight()));
        GridPane.setConstraints(height_input, 1, 1);
        login_layout.getChildren().add(height_input);

        TextField weight_input = new TextField();
        // weight_input.setPromptText("LBS (Decimal)");
        weight_input.setText(Double.toString(user.getWeight()));
        GridPane.setConstraints(weight_input, 1, 2);
        login_layout.getChildren().add(weight_input);

        TextField birthdate_input = new TextField();
        // birthdate_input.setPromptText("MM/DD/YYYY");
        birthdate_input.setText(user.getBirthDate().toString());
        GridPane.setConstraints(birthdate_input, 1, 3);
        login_layout.getChildren().add(birthdate_input);

        ChoiceBox<String> teamType = new ChoiceBox<>();
        // getItems returns the observable list objects
        teamType.setValue("Team 1");
        teamType.getItems().addAll("Team 1", "Team 2", "Team 3");
        GridPane.setConstraints(teamType, 1, 4);
        login_layout.getChildren().add(teamType);

        // Buttons for going to home page
        Button done = new Button("Done");

        done.setOnAction(e -> {
            user.setName(name_input.getText());
            user.setHeight(Double.parseDouble(height_input.getText()));
            user.setWeight(Double.parseDouble(weight_input.getText()));
            user.setBirthDate(birthdate_input.getText());
            Scene newHomeScene = homeScene();
            homepage = newHomeScene;
            window.setScene(homepage);
        });

        GridPane.setConstraints(done, 1, 5);
        login_layout.getChildren().add(done);
        Scene loginScene = new Scene(login_layout, 600, 550);
        return loginScene;
    }

    public Scene setGoalScene() {

        GridPane goal_layout = new GridPane();
        goal_layout.setPadding(new Insets(150, 10, 10, 135));
        goal_layout.setVgap(8);
        goal_layout.setHgap(10);

        Label target_weight_label = new Label("Target Weight:");
        GridPane.setConstraints(target_weight_label, 0, 1);
        goal_layout.getChildren().add(target_weight_label);

        Label average_calorie_label = new Label("Average Calorie:");
        GridPane.setConstraints(average_calorie_label, 0, 2);
        goal_layout.getChildren().add(average_calorie_label);

        Label goal_label = new Label("Goal:");
        GridPane.setConstraints(goal_label, 0, 3);
        goal_layout.getChildren().add(goal_label);

        // Name input, Height input, Weight input, Birthday input

        TextField target_weight_input = new TextField();
        // target_weight_input.setPromptText("LBS (Decimal)");
        target_weight_input.setText(Double.toString(goal.getTargetWeight()));
        GridPane.setConstraints(target_weight_input, 1, 1);
        goal_layout.getChildren().add(target_weight_input);

        TextField average_calorie_input = new TextField();
        // average_calorie_input.setPromptText("Average Daily Calories");
        average_calorie_input.setText(Integer.toString(goal.getAverageCalorie()));
        GridPane.setConstraints(average_calorie_input, 1, 2);
        goal_layout.getChildren().add(average_calorie_input);

        ChoiceBox<String> goalType = new ChoiceBox<>();
        // getItems returns the observable list objects
        goalType.setValue("Lose Weight");
        goalType.getItems().addAll("Lose Weight", "Maintain Weight", "Gain Weight");
        GridPane.setConstraints(goalType, 1, 3);
        goal_layout.getChildren().add(goalType);

        // Buttons for going to home page
        Button done = new Button("Done");

        done.setOnAction(e -> {
            goal.setTargetWeight(Double.parseDouble(target_weight_input.getText()));
            goal.setAverageCalorie(Integer.parseInt(average_calorie_input.getText()));
            goal.setGoal(goalType.getValue());
            Scene newHomeScene = homeScene();
            homepage = newHomeScene;
            window.setScene(homepage);
        });

        GridPane.setConstraints(done, 1, 5);
        goal_layout.getChildren().add(done);
        Scene goalScene = new Scene(goal_layout, 600, 550);
        return goalScene;
    }

    public Scene GuestHome() {
        GridPane guest_homepage_layout = new GridPane();

        guest_homepage_layout.setPadding(new Insets(150, 10, 10, 165));
        guest_homepage_layout.setVgap(8);
        guest_homepage_layout.setHgap(8);

        Text userTitle = new Text();
        userTitle.setText(" Name: " + guest.getGuestName() + " ");

        Button create_user = new Button("Create Account");

        Button view_ingredients = new Button("View Ingredients");

        create_user.setOnAction(g -> {
            Scene CreateUser = createUserScene();
            window.setScene(CreateUser);
        });

        // GOT TO FINISH THIS FOR GUESTS TO VIEW INGREDIENTS
        // view_ingredients.setOnAction(e -> {

        // });

        VBox userBox = new VBox(20, userTitle);

        // VBox uBox = new VBox(20, goalButton);

        userBox.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        GridPane.setConstraints(userBox, 1, 0);
        guest_homepage_layout.getChildren().add(userBox);

        GridPane.setConstraints(create_user, 1, 1);
        guest_homepage_layout.getChildren().add(create_user);

        GridPane.setConstraints(view_ingredients, 2, 1);
        guest_homepage_layout.getChildren().add(view_ingredients);

        // *_____________________Logout button____________________________
        Button logout = new Button("Logout");
        GridPane.setConstraints(logout, 1, 2);
        guest_homepage_layout.getChildren().add(logout);

        logout.setOnAction(v -> {
            Scene logout_scene = loginScene();
            window.setScene(logout_scene);
        });

        Scene guest_homeScene = new Scene(guest_homepage_layout, 600, 550);

        return guest_homeScene;
    }

    public Scene homeScene() {
        GridPane homepage_layout = new GridPane();

        homepage_layout.setPadding(new Insets(150, 10, 10, 165));
        homepage_layout.setVgap(8);
        homepage_layout.setHgap(8);

        // #region User
        /* _______________________User Box___________________________ */

        /* Title */
        Text userTitle = new Text();
        String userTitleText;
        // userTitleText = "Rafael Gilboa"; //this.getName()
        userTitleText = user.getName();
        userTitle.setText(" Name: " + userTitleText + " ");

        /* Content */
        Text height = new Text();
        String heightText;
        // heightText = "5\"11"; //this.getHeight()
        heightText = Double.toString(user.getHeight());
        height.setText(" Height: " + heightText + " cm ");

        Text weight = new Text();
        String weightText;
        // weightText = "168 lbs"; //this.getWeight()
        weightText = Double.toString(user.getWeight());
        weight.setText(" Weight: " + weightText + " lbs ");

        Text bday = new Text();
        String bdayText;
        // bdayText = "08/21/2004"; //this.parseBday()
        bdayText = user.getBirthDate().toString();
        bday.setText(" Birth date: " + bdayText + " ");

        Text team = new Text();
        String teamText;
        // bdayText = "08/21/2004"; //this.parseBday()
        teamText = user.getTeam();
        team.setText(" Team: " + teamText + " ");

        VBox userBlock = new VBox(20, height, weight, bday, team);

        goalButton = new Button("Change User");

        goalButton.setOnAction(g -> {
            Scene editUser = setUserScene();
            window.setScene(editUser);
        });

        VBox userBox = new VBox(20, userTitle, userBlock);

        VBox uBox = new VBox(20, goalButton);

        // Button userButton = new Button(userGroup)
        userBox.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        GridPane.setConstraints(userBox, 1, 0);
        homepage_layout.getChildren().add(userBox);

        GridPane.setConstraints(uBox, 1, 1);
        homepage_layout.getChildren().add(uBox);
        // #endregion

        // #region Goal
        /* _______________________Goal Box___________________________ */

        /* Title */
        Text goalTitle = new Text();
        String goalTitleText;
        goalTitleText = " Goal:";
        goalTitle.setText(goalTitleText);

        /* Content */
        Text dailyCalTarget = new Text();
        String dailyCalTargetText;
        // dailyCalTargetText = "3500 calories/day"; //this.getdailyCalTarget()
        dailyCalTargetText = Integer.toString(goal.getDailyTargetCalorie());
        dailyCalTarget.setText(" Target Calorie: " + dailyCalTargetText + " ");

        Text weightGoal = new Text();
        String weightGoalText;
        weightGoalText = Double.toString(goal.getTargetWeight()); // this.getWeightGoal()
        weightGoal.setText(" Target Weight: " + weightGoalText + " lbs ");

        Text weightPlan = new Text();
        String weightPlanText;
        weightPlanText = " Plan: " + goal.getGoal() + " "; // this.getWeightPlan()
        weightPlan.setText(weightPlanText);

        VBox goalBlock = new VBox(20, dailyCalTarget, weightGoal, weightPlan);

        Button b = new Button("Goal Page");

        b.setOnAction(e -> {
            Scene newGoalScene = setGoalScene();
            window.setScene(newGoalScene);
        });

        VBox goalBox = new VBox(20, goalTitle, goalBlock);
        VBox Box = new VBox(20, b);

        goalBox.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        GridPane.setConstraints(goalBox, 2, 0);
        GridPane.setConstraints(Box, 2, 1);
        homepage_layout.getChildren().add(goalBox);
        homepage_layout.getChildren().add(Box);
        // #endregion

        // #region History
        /* _______________________History Box___________________________ */

        historyButton = new Button("View History");

        VBox hiBox = new VBox(20, historyButton);
        GridPane.setConstraints(hiBox, 1, 2);
        homepage_layout.getChildren().add(hiBox);

        // *_____________________Logout button____________________________
        Button logout = new Button("Logout");
        GridPane.setConstraints(logout, 1, 3);
        homepage_layout.getChildren().add(logout);

        logout.setOnAction(v -> {
            Scene logout_scene = loginScene();
            window.setScene(logout_scene);
        });

        historyButton.setOnAction(a -> {
            Scene HistScene = historyScene();
            window.setScene(HistScene);
        });
        // #endregion

        // #region Meal
        /* _______________________Meal Box___________________________ */

        mealButton = new Button("View Meal");

        VBox MealBox = new VBox(20, mealButton);
        GridPane.setConstraints(MealBox, 2, 2);
        homepage_layout.getChildren().add(MealBox);

        mealButton.setOnAction(e -> {
            Scene mScene = mealScene();
            window.setScene(mScene);
        });

        ingredientsButton = new Button("View ingredients");
        VBox ingredBox = new VBox(20, ingredientsButton);
        GridPane.setConstraints(ingredBox, 3, 3);
        homepage_layout.getChildren().add(ingredBox);

        ingredientsButton.setOnAction(e -> {
            Scene iScene = ingredientScene();
            window.setScene(iScene);
        });

        fileButton = new Button("View ingredients");
        VBox fileBox = new VBox(20, fileButton);
        GridPane.setConstraints(fileBox, 4, 3);
        homepage_layout.getChildren().add(fileBox);

        fileButton.setOnAction(e -> {
            Scene fileScene = fileScene();
            window.setScene(fileScene);
        });

        // #endregion

        // #region Workout
        /* _______________________Workout Box___________________________ */

        /* Title */
        Text workoutTitle = new Text();
        String workoutTitleText;
        workoutTitleText = "Workouts";
        workoutTitle.setText("Name: " + workoutTitleText);

        /* Content */
        Text recentWorkoutLength = new Text();
        String recentWorkoutLengthText;

        ArrayList<Workout> workoutCopy = new ArrayList<>();

        // recentWorkoutLength.setText("Height: "+recentWorkoutLengthText+" cm");

        // Text = new Text();
        // String weightText;
        // //weightText = "168 lbs"; //this.getWeight()
        // weightText = Double.toString(user.getWeight());
        // weight.setText("Weight: "+weightText+" lbs");

        // Text bday = new Text();
        // String bdayText;
        // //bdayText = "08/21/2004"; //this.parseBday()
        // bdayText = user.getBirthDate().toString();
        // bday.setText("Birth date: "+bdayText);

        // VBox userBlock = new VBox(20, height, weight, bday);

        // goalButton = new Button("Change User");

        // VBox userBox = new VBox(20, userTitle, userBlock, goalButton);
        // // Button userButton = new Button(userGroup)
        // userBox.setBorder(new Border(new BorderStroke(Color.BLACK,
        // BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        // GridPane.setConstraints(userBox,1,0);
        // homepage_layout.getChildren().add(userBox);

        // #endregion

        // #region History
        // TODO HISTORY UI

        // #endregion

        Scene homeScene = new Scene(homepage_layout, 600, 550);

        return homeScene;
    }

    public Scene mealScene() {
        GridPane meal_layout = new GridPane();
        meal_layout.setPadding(new Insets(150, 10, 10, 115));
        meal_layout.setVgap(8);
        meal_layout.setHgap(10);

        Label shopping_list = new Label("Shopping Cart:");
        GridPane.setConstraints(shopping_list, 0, 0);
        meal_layout.getChildren().add(shopping_list);

        ListView<String> list = new ListView<String>();
        ObservableList<String> items = FXCollections.observableArrayList();
        list.setItems(items);
        list.setPrefWidth(100);
        list.setPrefHeight(70);
        GridPane.setConstraints(list, 0, 1);
        meal_layout.getChildren().add(list);

        // Name Label, Height Label, Weight Label, Birthday Label
        Label name_label = new Label("Name:");
        GridPane.setConstraints(name_label, 2, 0);
        meal_layout.getChildren().add(name_label);

        Label height_label = new Label("Recipe:");
        GridPane.setConstraints(height_label, 2, 1);
        meal_layout.getChildren().add(height_label);

        // Name input, Height input, Weight input, Birthday input

        TextField name_input = new TextField();
        name_input.setPromptText("NAME");
        GridPane.setConstraints(name_input, 3, 0);
        meal_layout.getChildren().add(name_input);

        TextField recipe_input = new TextField();
        recipe_input.setPromptText("NAME");
        GridPane.setConstraints(recipe_input, 3, 1);
        meal_layout.getChildren().add(recipe_input);

        // Button for creating new user

        mealButton = new Button("input");
        GridPane.setConstraints(mealButton, 3, 2);
        meal_layout.getChildren().add(mealButton);

        Button backButton = new Button("Back");
        GridPane.setConstraints(backButton, 2, 3);
        meal_layout.getChildren().add(backButton);

        backButton.setOnAction(e -> {
            Scene newScene = homeScene();
            window.setScene(newScene);
        });

        Scene mealScene = new Scene(meal_layout, 500, 500);

        return mealScene;
    }

    public Scene ingredientScene() {
        i.parseIngredientCSV();
        HashMap<String, Ingredient> ingredMap = Ingredient.getDataBase();
        ListView<String> listView = new ListView<>();
        ObservableList<String> items = FXCollections.observableArrayList(ingredMap.keySet());
        listView.setItems(items);

        VBox vbox = new VBox(listView);

        Scene iScene = new Scene(vbox, 300, 400);

        return iScene;
    }

    public Scene fileScene() {

        GridPane add_file = new GridPane();
        add_file.setPadding(new Insets(150, 10, 10, 135));
        add_file.setVgap(8);
        add_file.setHgap(10);
        ChoiceBox<String> optionChoiceBox = new ChoiceBox<>();

        optionChoiceBox.getItems().addAll("Export", "Import");

        ChoiceBox<String> fileChoice = new ChoiceBox<>();

        Label file_name = new Label("File:");
        GridPane.setConstraints(file_name, 0, 0);
        add_file.getChildren().add(file_name);
        fileChoice.getItems().addAll("JSON", "CSV", "XML");

        StringProperty choice1String = new SimpleStringProperty("");
        StringProperty choice2String = new SimpleStringProperty("");
        fileChoice.getSelectionModel().selectedItemProperty()
                .addListener((v, oldValue, newValue) -> choice1String.set(newValue));
        optionChoiceBox.getSelectionModel().selectedItemProperty()
                .addListener((v, oldValue, newValue) -> choice2String.set(newValue));
        if (choice1String.toString().equals("XML")) {
            handler = new XMLHandler(null, null, null);
        } else if (choice1String.toString().equals("JSON")) {
            handler = new JSONHandler(null, null, null);
        } else {
            handler = new HandlerCSV(null, null, null);
        }

        Button newfileButton = new Button("Export/Import your new file!");
        newfileButton.setOnAction(e -> function_name(choice2String, file_name.getText()));
        return null;
    }

    private void function_name(StringProperty choice2String, String fileName) {
        if (choice2String.toString().equals("Export")) {
            handler.print(fileName, null);
        } else {
            handler.parse(fileName, null);
        }
    }

    public Scene historyScene() {
        // History scene layout
        GridPane history_layout = new GridPane();
        history_layout.setPadding(new Insets(10, 10, 10, 10));
        history_layout.setVgap(8);
        history_layout.setHgap(10);

        Label history_label = new Label("Meal History:");
        GridPane.setConstraints(history_label, 0, 0);
        history_layout.getChildren().add(history_label);

        // history list view
        ListView<String> historyList = new ListView<String>();
        ObservableList<String> historyItems = FXCollections.observableArrayList("Meal 1", "Meal 2", "Meal 3");
        historyList.setItems(historyItems);
        GridPane.setConstraints(historyList, 0, 1, 3, 1);
        history_layout.getChildren().add(historyList);

        // Clearing the history
        Button clearHistory = new Button("Clear History");
        GridPane.setConstraints(clearHistory, 1, 2);
        clearHistory.setOnAction(e -> historyItems.clear());
        history_layout.getChildren().add(clearHistory);

        // Details about the meal
        Button detailsButton = new Button("Details");
        GridPane.setConstraints(detailsButton, 2, 2);
        detailsButton.setOnAction(e -> {
            if (historyList.getSelectionModel().getSelectedItem() != null) {
                String selectedMeal = historyList.getSelectionModel().getSelectedItem();
                System.out.println("Details for " + selectedMeal);
            }
        });
        history_layout.getChildren().add(detailsButton);

        Button backButton = new Button("Back");
        GridPane.setConstraints(backButton, 0, 2);
        backButton.setOnAction(e -> {
            Scene newScene = homeScene();
            window.setScene(newScene);
        });
        history_layout.getChildren().add(backButton);
        return new Scene(history_layout, 500, 500);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
