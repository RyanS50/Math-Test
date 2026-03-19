import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.event.*;
import java.io.*;
import javafx.application.*;
import javafx.scene.*;
import javafx.scene.image.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.stage.*;
import java.util.*;
import javafx.geometry.Pos;
import java.nio.file.*;

//important constants
interface Numbers {
  final int SIZE = 400; //size of display
  final int NUMQ= 15; //nuber of questions in each section
  final int NUMI= 11; //number of integral questions
  final int NUMDE= 4; //number of derivitve questions
  final int ALG_TOPIC= 5; //number of questions for each algerbra topic
}


public class practiceTest extends Application implements Numbers  {
   String type = ""; //will keep track of if it's calculus/algerbra
   @Override
   public void start(Stage stage) throws FileNotFoundException, IOException {
     Question q = new Question();
     Response r = new Response();

     //create all the buttons
     Button aButton= makeButton("a");
     Button bButton = makeButton("b");
     Button cButton= makeButton("c");
     Button dButton = makeButton("d");
     Button nextButton = makeButton("Next");
     nextButton.setVisible(false);  //hide the next button
     Button calcButton= makeButton("Calculus");
     Button algButton= makeButton("Algerbra");

     Label intro = new Label("Would you like Calculus or Algerbra");
     Label l = new Label("");  //will display right/wrong

     //creating the HBox/VBoxes for displaying everything
     HBox hbox1 = new HBox();
     HBox hbox2 = new HBox();
     HBox options = new HBox();
     VBox vbox = new VBox();
     VBox temp = new VBox();

     //Setting the space between the nodes of the Box panes
     hbox1.setSpacing(5);
     hbox2.setSpacing(5);
     options.setSpacing(100);
     vbox.setSpacing(5);

     //Adding all the nodes to the boxes
     hbox1.getChildren().addAll( aButton, bButton);
     hbox2.getChildren().addAll(cButton, dButton);
     options.getChildren().addAll(calcButton, algButton);
     temp.getChildren().addAll(intro, options);

     //setting margins for each button
     hbox1.setMargin(aButton, new Insets(20,20,10,20));
     hbox1.setMargin(bButton, new Insets(20,20,10,20));
     hbox2.setMargin(cButton, new Insets(20,20,10,20));
     hbox2.setMargin(dButton, new Insets(20,20,10,20));
     temp.setMargin(intro, new Insets(100, 75, 100, 75));

     //makes something happen when you click a button
     aButton.setOnAction(aEvent -> {
       if (nextButton.isVisible()) {} //if you can move on, buttons don't do anything
       else {
         Boolean answer= false; //create a boolean for if you were right/wrong
           answer = r.check('a', q.questionNum); //check answer
           if (answer) { //if you were right
             l.setText("Correct");
             nextButton.setVisible(true); //show the next button
             progress(q); //marks that you got a question in a topic right
           } else {
             if(r.attempt>2) { //you get two chances before being shown the right answer
               l.setText("Nope. The right answer was " + r.correctA(q.questionNum));
               nextButton.setVisible(true);
               r.wrong++;
             } else {
               l.setText("Not Quite");
             }
           }
       }
      });
     bButton.setOnAction(bEvent -> {
       if (nextButton.isVisible()) {}
       else {
         Boolean answer= false;
           answer = r.check('b', q.questionNum);
           if (answer) {
             l.setText("Correct");
             nextButton.setVisible(true);
             progress(q);
           } else {
               if(r.attempt>2) {
                 l.setText("Nope. The right answer was " + r.correctA(q.questionNum));
                 nextButton.setVisible(true);
                 r.wrong++;
             } else {
                 l.setText("Not Quite");
             }
           }
       }
      });
     cButton.setOnAction(cEvent -> {
       if (nextButton.isVisible()) {}
       else {
         Boolean answer= false;
           answer = r.check('c', q.questionNum);
           if (answer) {
             l.setText("Correct");
             nextButton.setVisible(true);
             progress(q);
           } else {
               if(r.attempt>2) {
                 l.setText("Nope. The right answer was " + r.correctA(q.questionNum));
                 nextButton.setVisible(true);
                 r.wrong++;
             } else {
                 l.setText("Not Quite");
             }
           }
       }
      });
     dButton.setOnAction(dEvent -> {
       if (nextButton.isVisible()) {}
       else {
         Boolean answer= false;
           answer = r.check('d', q.questionNum);
           if (answer) {
             l.setText("Correct");
             nextButton.setVisible(true);
             progress(q);
           } else {
               if(r.attempt>2) {
                 l.setText("Nope. The right answer was " + r.correctA(q.questionNum));
                 nextButton.setVisible(true);
                 r.wrong++;
             } else {
                 l.setText("Not Quite");
             }
           }
       }
      });
     nextButton.setOnAction(next -> {
        l.setText(""); //reset right/wrong label
        q.nextQ(type); //get the next question
        r.attempt = 1; //reset attempt counter
        VBox newV = new VBox();
        //gets the question image and displays it
        try {
          newV.setSpacing(5);
          newV.getChildren().addAll(q.getQImage(), hbox1, hbox2, l, nextButton);
          Scene scene = new Scene(newV, SIZE, SIZE);
          stage.setScene(scene);
          stage.show();
          nextButton.setVisible(false);
          //if no more questions, shows the results
        } catch(FileNotFoundException e) {
          Label results = new Label(r.getStats());
          results.setFont(new Font(50));
          newV.getChildren().add(results);
          Scene review = new Scene(newV, SIZE, SIZE);
          stage.setScene(review);
          stage.show();
        }
      });
     calcButton.setOnAction(calc -> {
        type="C"; //sets the type of question
        //does the same thing as the next button
        q.nextQ(type);
        r.attempt = 1;
        try {
          VBox newV = new VBox();
          newV.setSpacing(5);
          newV.getChildren().addAll(q.getQImage(), hbox1, hbox2, l, nextButton);
          Scene scene = new Scene(newV, SIZE, SIZE);
          stage.setScene(scene);
          stage.show();
          nextButton.setVisible(false);
        } catch(FileNotFoundException e) {
          IO.println("Calc button error");
        }
      });
     algButton.setOnAction(alg ->{
        type="A"; //sets the question type
        //does the same thing as next button
        q.nextQ(type);
        r.attempt = 1;
        try {
          VBox newV = new VBox();
          newV.setSpacing(5);
          newV.getChildren().addAll(q.getQImage(), hbox1, hbox2, l, nextButton);
          Scene scene = new Scene(newV, SIZE, SIZE);
          stage.setScene(scene);
          stage.show();
          nextButton.setVisible(false);
        } catch(FileNotFoundException e) {
          IO.println("Alg button error");
        }
      });

     //displays the scene
     Scene scene = new Scene(temp, SIZE, SIZE);
     stage.setTitle("Math Test");
     stage.setScene(scene);
     stage.show();
   }
   // makes properly sized button
   public Button makeButton(String s) {
     Button button = new Button(s);
     button.setPrefWidth(150);
     return button;
  }

   //keeps track of what topics you get right and marks them of the list of questions
   public void progress(Question q) {
     if (q.questionNum>0 && q.questionNum<=NUMI) { //checks if in integral range
       q.integrals[q.questionNum-1] = true; //marks one value the integrals right array as true
       //keeps repeating that process
     } else if(q.questionNum>NUMI && q.questionNum<=NUMI+NUMDE) {
       q.deriv[q.questionNum-NUMI-1] =true;
     } else if(q.questionNum>NUMI+NUMDE && q.questionNum<=NUMI+NUMDE+ALG_TOPIC) {
       q.factors[q.questionNum%15-1]=true;
     } else if(q.questionNum>NUMI+NUMDE+ALG_TOPIC && q.questionNum<=NUMI+NUMDE+2*ALG_TOPIC) {
       q.imaginary[q.questionNum%15-ALG_TOPIC-1]=true;
     } else {
       q.domain[q.questionNum%16-2*ALG_TOPIC]=true;
     }
   }
}

class Question implements Numbers {
  public static int questionNum; //which question is being asked
  private static Boolean[] asked; //which questions have been asked already
  private Random rand;
  public static Boolean[] integrals; //keeps track of if you get integrals right
  public static Boolean[] deriv; //keeps track of if you get derivitves right
  public static Boolean[] factors; //keeps track of if you get factoring right
  public static Boolean[] imaginary; //keeps track of if you get imaginary numbers right
  public static Boolean[] domain; //keeps track of if you get domains right
  public static Boolean[] arr;  //will be a tempory copy of on of the topic arrays

  public Question() {
    this.asked = new Boolean[NUMQ];
    Arrays.fill(asked, false);
    this.integrals = new Boolean[NUMI];
    Arrays.fill(integrals, false);
    this.deriv= new Boolean[NUMDE];
    Arrays.fill(deriv, false);
    this.factors= new Boolean[ALG_TOPIC];
    Arrays.fill(factors, false);
    this.imaginary= new Boolean[ALG_TOPIC];
    Arrays.fill(imaginary, false);
    this.domain= new Boolean[ALG_TOPIC];
    Arrays.fill(domain, false);
    this.questionNum= 0; //just a value to start with, not a possible question number
    rand = new Random();
  }
  //sets the questionNum to the next question
  public void nextQ(String type) {
    String topic = ""; //will hold what topic it is
    int start= 0; //will mark the start of the topic
    int end = 0; //will mark end of topic
    //calculus goes from question 1-15
    if (type.equals("C")) {
      start = 1;
      end = 15;
      //algerbra goes from 16-30
    } else {
      start = 16;
      end= 30;
    }
    //checks what range the question is to determine the topic
    if (questionNum>0 && questionNum<=NUMI) {
      arr = Arrays.copyOf(integrals, NUMI);
      topic = "integrals";
    } else if (questionNum>NUMI && questionNum<=NUMI+NUMDE) {
      arr = Arrays.copyOf(deriv, NUMDE);
      topic = "deriv";
    } else if(questionNum>NUMI+NUMDE && questionNum<=NUMI+NUMDE+ALG_TOPIC) {
      arr = Arrays.copyOf(factors, ALG_TOPIC);
      topic = "factors";
    } else if(questionNum>NUMI+NUMDE+ALG_TOPIC && questionNum<=NUMI+NUMDE+2*ALG_TOPIC) {
      arr = Arrays.copyOf(imaginary, ALG_TOPIC);
      topic = "imaginary";
    } else {
      arr = Arrays.copyOf(domain, ALG_TOPIC);
      topic = "domain";
    }
    //counts how many questions of this topic you got right
    int temp = 0;
    for(int i=0; i<arr.length; i++) {
      if (arr[i]) {
        temp++;
      }
    }
    //if more than half, marks the remaing questions in that topic as not to be asked again
    if (temp>arr.length/2) {
      switch(topic) {
        case "integrals":
          Arrays.fill(this.asked, 0, NUMI-1, true);
          break;
        case "deriv":
          Arrays.fill(this.asked, NUMI, NUMI+NUMDE-1, true);
          break;
        case "factors":
          Arrays.fill(this.asked, 0, 4, true);
          break;
        case "imaginary":
          Arrays.fill(this.asked, 5, 9, true);
          break;
        case "domain":
          Arrays.fill(this.asked, 10, 14, true);
          break;
      }
    }
    if (Arrays.asList(asked).contains(false)) { //checks if there is another question to be asked
      if (type.equals("A")) { //checks if algerbra is the type
        //if so makes a random number in the appropriate range
        do {
          this.questionNum = rand.nextInt(end-start+1)+start;
        } while (asked[questionNum%(NUMQ+1)]); //checks if the question has already been asked
        asked[questionNum%(NUMQ+1)] = true; //marks the question as asked
      } else { //not algerbra means calculus
        do {
          this.questionNum = rand.nextInt(end-start+1)+start;
        } while (asked[questionNum-1]); //checks if question has been asked
        asked[questionNum-1] = true; //marks question as asked
      }
    } else { //if out of questions, gives garbage question number
      this.questionNum = -1;
    }
  }
  //gets the question image file and returns the image
  public ImageView getQImage() throws FileNotFoundException {
    Image questionPrompt = new Image(new FileInputStream("./Questions/" +this.questionNum));
    ImageView prompt = new ImageView(questionPrompt);
    prompt.setFitWidth(SIZE);
    prompt.setPreserveRatio(true);
    return prompt;
  }
}

class Response {
  public String key; //the answer key
  public static int attempt; //keeps track of the attempt number per question
  public static int right; //keeps track of how many were right
  public static int wrong; //keeps track of how many you got wrong

  public Response() {
    try {
      key = Files.readString(Path.of("./Answers/answer.txt")); //gets answer key
    } catch (IOException e) {
      IO.println("Response error, missing answer.txt file");
    }
    attempt = 1;
    right=0;
    wrong = 0;
  }

  //checks if the answer was right
  public Boolean check(char ch, int x) {
    int k=0; //makes the answer lookup possible for multidigit question numbers
    if (x<10) {
      k=2; //if single digit, answer is two ahead
    } else {
      k=3; //if two digit then the answer is three ahead
    }
    //gets the answer from the key and checks it against the answer given
    if(key.charAt(key.indexOf(String.valueOf(x))+k) == ch) {
      right++;
      return true;
    } else {
      attempt++; //increases attempt count for this question
      return false;
    }
  }

  //gets the right answer from the key
  public char correctA(int x) {
    int k=0;
    if (x<10) {
      k=2;
    } else {
      k=3;
    }
    return key.charAt(key.indexOf(String.valueOf(x))+k);
  }

  //shows your results
  public String getStats() {
    RationalNumber stats = new RationalNumber(this.right, (this.wrong+this.right));
    return stats.toString();
  }
}

class RationalNumber {
  public int numerator;
  public int denominator;

  public RationalNumber(int numerator, int denominator) {
    if (denominator == 0) {
      throw new IllegalArgumentException("The denominator cannot be zero");
    }
    this.numerator = numerator;
    this.denominator = denominator;
  }

  //creates the 0/1 rational number
  public RationalNumber() {
    numerator = 0;
    denominator = 1;
  }

  //returns the denominator
  public int getDenominator() {
    return denominator;
  }

  //returns the numerator
  public int getNumerator() {
    return numerator;
  }

  //returns a string representation
  public String toString() {
    if (denominator == 1) { // omits denominators of 1
      return "" + numerator;
    }
    return numerator + "/" + denominator;
  }

  //adds this rational number by another
  public void add(RationalNumber x) {
    if (this.denominator == x.getDenominator()) {
      this.numerator += x.getNumerator(); //if denominators are the same, just add numerators
    } else {
      //get common denomiator by multiplying the two denominators together and add
      this.numerator *= x.getDenominator();
      int temp = x.getNumerator() * this.denominator;
      this.numerator += temp;
      this.denominator *= x.getDenominator();
    }
    simplify();
  }

  //subtracts this rational number by another
  public void subtract(RationalNumber x) {
    if (this.denominator == x.getDenominator()) {
      this.numerator -= x.getNumerator(); //if denomiators are equal, subtract numerators
    } else {
      //get common denomiator by multiplying the two denomiators together
      this.numerator *= x.getDenominator();
      int temp = x.getNumerator() * this.denominator;
      this.numerator -= temp;
      this.denominator *= x.getDenominator();
    }
    simplify();
  }

  //multiplies this rational number by another
  public void multiply(RationalNumber x) {
    //just multiply the numerators together and the denominators together
    this.numerator *= x.getNumerator();
    this.denominator *= x.getDenominator();
    simplify();
  }

  //divides this rational number by another
  public void divide(RationalNumber x) {
    //division is multiplication of reciprocal
    //so numerator time denomiator and vice versa
    this.numerator *= x.getDenominator();
    this.denominator *= x.getNumerator();
    simplify();
  }

  public double toPercent(){
    return (this.numerator/this.denominator*100);
  }


  //simplifies the rational number
  private void simplify() {
    //loops through all number less than half of the larger value
    for (int i=1; i<= Math.abs(denominator)/2 || i<= Math.abs(numerator)/2; i++) {
      if (numerator%i ==0 && denominator%i ==0) { //checks if both are multiples of i
        //divide top and bottom by common factor
        numerator /=i;
        denominator /=i;
      }
    }
    //moves minus sign up to numerator and simplifies two negatives
    if (denominator<0) {
      denominator= Math.abs(denominator);
      numerator *= -1;
    }
  }
}
