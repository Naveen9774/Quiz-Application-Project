package Com.destination.JFS.Projectday42;

import java.util.Scanner;

public class QuizeApplicaton{
	// Candidate details
    static String candidateName;
    static boolean isReady = false;

    // Lifelines and usage status
    static boolean lifeline50_50Used = false;
    static boolean audiencePhoneUsed = false;
    static boolean skipQuestionUsed = false;

    // Scoring variables
    static int score = 0;

    // Questions and options
    static String[] questions = {
            "Who is the Deputy CM of Andhra Pradesh?",
            "Who wrote the Indian national anthem 'Jana Gana Mana'?",
            "What is the capital city of India?",
            "Which is the longest river in India?",
            "Which sport is India's national game?",
            "Which is the largest company in India by market capitalization?",
            "In which year did India gain independence from British rule?",
            "What is the longest river in the world?",
            "Which hill station is located in the Western Ghats of Karnataka?",
            "What are the industry hit movie made by the Deputy CM?"
    };

    // Correct answers
    static String[] correctAnswers = {
            "PawanKalyan", "Rabindranath Tagore", "New Delhi", "Ganga", "Hockey", "Tata Consultancy Services (TCS)", "1947", "Nile River", "Coorg (Kodagu)",
            "Gabbar Singh"
    };

    // Options for each question
    static String[][] options = {
            {"PawanKalyan", "Yash", "Puneeth", "Rajinikanth"},
            {"Lata Mangeshkar", "Bankim Chandra Chattopadhyay", "Rabindranath Tagore", "Subhas Chandra Bose"},
            {"Mumbai", "New Delhi", "Mumbai", "Chennai"},
            {"Ganga", "Godavari", " Yamuna", "Brahmaputra"},
            {"Football", " Kabaddi", "Hockey", "Cricket"},
            {"Reliance Industries", "Tata Consultancy Services (TCS)", "Infosys", "HDFC Bank"},
            {"1947", "1950", "1970", "1980"},
            {"Amazon River", "Nile River", "Mississippi River", "Yangtze River"},
            {"Nandi Hills", "Coorg (Kodagu)", "Chikmagalur", "Agumbe"},
            {"Gabbar Singh" ,"Tholi Prema","Thammudu","Badhri"}
    };

    // Money amounts for each question
    static int[] amounts = {1000, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000, 10000};

// Main method
public static void main(String[] args) {
	Scanner scanner = new Scanner(System.in);

    // Collect candidate details
    System.out.println("Welcome to the Quiz Application!");
    System.out.print("Please tell me your name: ");
    candidateName = scanner.nextLine();
    System.out.println("=============================================");
    // Display rules of the game
    System.out.println("Rules of the game:");
    System.out.println("1. You will be asked 10 questions.");
    System.out.println("2. Each question has 4 options.");
    System.out.println("3. You can use 3 lifelines: 50-50, Audience Phone, and Skip the Question.");
    System.out.println("4. Each lifeline can only be used once.");
    System.out.println("5. If you answer a question wrong, the game ends.");
    System.out.println("6. You will earn money for every correct answer.");
    System.out.println("===============================================================");
    // Display the chart of amounts
    System.out.println("Amount chart:");
    for (int i = 0; i < amounts.length; i++) {
        System.out.println("Question " + (i + 1) + ": " + amounts[i] + " rupees");
    }
    System.out.println("====================================================================");
    // Ask if candidate is ready
    System.out.print("Are you ready to take the quiz? (Yes/No): ");
    String readyResponse = scanner.nextLine();
    if (readyResponse.equalsIgnoreCase("Yes")) {
        isReady = true;
    }

    if (isReady) {
        // Start quiz
        for (int i = 0; i < questions.length; i++) {
            askQuestion(i, scanner);
        }

        // Display final score
        System.out.println("Congratulations, " + candidateName + "!");
        System.out.println("Your final score is: " + score + " rupees.");
    } else {
        System.out.println("Maybe next time!");
    }

    // Close scanner
    scanner.close();
}

// Method to ask questions
public static void askQuestion(int questionIndex, Scanner scanner) {
    System.out.println("Question " + (questionIndex + 1) + ": " + questions[questionIndex]);
    for (int i = 0; i < options[questionIndex].length; i++) {
        System.out.println((i + 1) + ". " + options[questionIndex][i]);
    }
    
    // Ask for lifeline option
    System.out.print("Enter your answer (1-4) or press 5 to use a lifeline: ");
    String answer = scanner.nextLine().trim();
   
    if (answer.equals("5")) {
        useLifeline(questionIndex, scanner);
    } else {
        checkAnswer(answer, questionIndex);
    }
    System.out.println("==========================================================");

}


// Method to use a lifeline
public static void useLifeline(int questionIndex, Scanner scanner) {
    System.out.println("Available Lifelines: ");
    System.out.println("1. 50-50");
    System.out.println("2. Audience Phone");
    System.out.println("3. Skip the Question");

    System.out.print("Choose your lifeline (1-3): ");
    String lifelineChoice = scanner.nextLine().trim();

    switch (lifelineChoice) {
        case "1":
            if (lifeline50_50Used) {
                System.out.println("You have already used the 50-50 lifeline.");
            } else {
                lifeline50_50Used = true;
                use50_50(questionIndex, scanner);
            }
            break;
        case "2":
            if (audiencePhoneUsed) {
                System.out.println("You have already used the Audience Phone lifeline.");
            } else {
                audiencePhoneUsed = true;
                useAudiencePhone(questionIndex);
            }
            break;
        case "3":
            if (skipQuestionUsed) {
                System.out.println("You have already used the Skip lifeline.");
            } else {
                skipQuestionUsed = true;
                System.out.println("You skipped this question.");
                score += amounts[questionIndex];  // Skip question, no deduction of money
            }
            break;
        default:
            System.out.println("Invalid option, please choose again.");
    }
}

// 50-50 lifeline implementation
public static void use50_50(int questionIndex, Scanner scanner) {
    System.out.println("50-50 Lifeline: The two incorrect options will be removed.");
    int correctAnswerIndex = getCorrectAnswerIndex(questionIndex);
    int incorrectAnswer1, incorrectAnswer2;

    // Find two incorrect options
    do {
        incorrectAnswer1 = (int) (Math.random() * 4);
    } while (incorrectAnswer1 == correctAnswerIndex);

    do {
        incorrectAnswer2 = (int) (Math.random() * 4);
    } while (incorrectAnswer2 == correctAnswerIndex || incorrectAnswer2 == incorrectAnswer1);

    System.out.println("Remaining options:");
    System.out.println("1. " + options[questionIndex][correctAnswerIndex]);
    System.out.println("2. " + options[questionIndex][incorrectAnswer1]);

    System.out.print("Enter your answer (1 or 2): ");
    String answer = scanner.nextLine().trim();
    checkAnswer(answer, questionIndex);
}

// Audience phone lifeline implementation
public static void useAudiencePhone(int questionIndex) {
    System.out.println("Audience Phone Lifeline: The audience suggests that the answer is " + options[questionIndex][getCorrectAnswerIndex(questionIndex)]);
}

// Check answer method
public static void checkAnswer(String answer, int questionIndex) {
    int correctAnswerIndex = getCorrectAnswerIndex(questionIndex);
    if (answer.equals(String.valueOf(correctAnswerIndex + 1))) {
        score += amounts[questionIndex];
        System.out.println("Correct! You earned " + amounts[questionIndex] + " rupees.");
    } else {
        System.out.println("Wrong answer! The correct answer was: " + options[questionIndex][correctAnswerIndex]);
        System.out.println("Game Over! Your total earnings: " + score + " rupees.");
        System.exit(0);
    }
}

// Get the index of the correct answer
public static int getCorrectAnswerIndex(int questionIndex) {
    for (int i = 0; i < options[questionIndex].length; i++) {
        if (options[questionIndex][i].equalsIgnoreCase(correctAnswers[questionIndex])) {
            return i;
        }
    }
    return -1;  // Error if the correct answer is not found (should never happen)
    }
}



