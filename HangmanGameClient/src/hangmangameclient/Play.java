package hangmangameclient;

import hangmangameserver.ServiceInterface;
import java.rmi.RemoteException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Play {

    private final Info info = new Info();
    
    public void run(ServiceInterface s){
        
        Scanner sc = new Scanner(System.in);
        String username;
        int option;
        String dashedWord;
        String guess;
        boolean win;
        int lifes;
        String usedLetters;
        
        try {
            info.cls();
            info.getIntro();
            info.pressKey();
            info.cls();
            
            System.out.println("\nWelcome to HANGMAN GAME, choose an username and let's get started!");
 
            System.out.println("\n(This field can't be empty)"); 
            System.out.print("Username: "); 
            username = sc.next();

            s.addPlayer(username);
            
            while(true){
                Scanner scop = new Scanner(System.in);
                
                try{
                    info.cls();
                    info.getMenu();
                    option = scop.nextInt();
            
                    switch (option) {
                        case 1: 
                            System.out.println("\nHello " + username + ", you have select the Single-player mode!"
                                             + "\nThe game will start in a second, get ready...");
                            TimeUnit.SECONDS.sleep(2);
                            while (true){
                                info.cls();
                                // GET THE DASHEDWORD
                                dashedWord = s.getWord();
                                System.out.println("\nCan you guess the word ?"
                                                    + "\n");
                                System.out.println(dashedWord);
    
                                while(true){
                                    // GET UPDATED VARIABLES
                                    win = s.getStatus();
                                    lifes =  s.getLifes();
                                    usedLetters = s.getUsedLetters();
                                    System.out.print("\nLifes: " +  lifes
                                                    + "\nUsed Letters: " + usedLetters);
                                    // IF WIN IS TRUE OR LIFES EQUALS TO ZERO, BREAK THE LOOP
                                    if (win || lifes == 0)
                                        break;
                
                                    System.out.print("\nGuess one letter (you can also try the word, but it may cost you): ");
                                    guess = sc.next().toUpperCase();
                    
                                    if (guess.equals("0")){
                                        System.out.print("\nAre you sure you want to quit the game? (Y/N): ");
                                        String yn = sc.next().toUpperCase();
                                        if (yn.equals("Y")){
                                            System.out.println("\nHope to see you soon!");
                                            TimeUnit.SECONDS.sleep(1);
                                            System.out.println("\nConnection ended");
                                            sc.close();
                                            System.exit(0);
                                        } else {
                                            // SEND THE GUESS AND UPDATE DAHSEDWORD
                                            System.out.println("\nThat's the spirit! Let's continue!");
                                            TimeUnit.SECONDS.sleep(1);
                                            dashedWord = s.guessWord(guess);
                                            info.cls();
                                            System.out.println("\nCan you guess the word ?"
                                                    + "\n");
                                            System.out.println(dashedWord);
                                        } 
                                    } else {
                                        // SEND THE GUESS AND UPDATE DAHSEDWORD
                                        dashedWord = s.guessWord(guess);
                                        info.cls();
                                        System.out.println("\nCan you guess the word ?"
                                                    + "\n");
                                        System.out.println(dashedWord);
                                    }
                                }
            
                                if(lifes != 0){
                                    info.getWin();
                                } else {
                                    info.getLose();
                                }  
                            
                                System.out.print("\nYou want to try again? (Y/N): ");
                                String yon = sc.next().toUpperCase();
                                if(yon.equals("Y")){
                                    System.out.println("\nThat's the spirit! Good luck!");
                                } else {
                                    break;
                                }
                            }
                            break;
                
                        case 2:
                            System.out.println("\nHello " + username + ", you have select the Multiplayer mode!"
                                                + "\nUnfortunately this mode is not available in this version!");
                            TimeUnit.SECONDS.sleep(2);
                            break;
                
                        case 0:
                            System.out.print("\nAre you sure you want to quit the game? (Y/N): ");
                            String yn = sc.next().toUpperCase();
                            if (yn.equals("Y")){
                                System.out.println("\nHope to see you soon!");
                                System.out.println("\nConnection ended");
                                sc.close();
                                System.exit(0);
                            } else {
                                System.out.println("\nThat's the spirit! Choose a new option!");
                                TimeUnit.SECONDS.sleep(2);
                            } 
                            break;
                    
                        default:
                            System.out.println("\nYou've choose an invalid option! Please try again!");
                            TimeUnit.SECONDS.sleep(2);
                    }
                } catch (InputMismatchException ex){
                    System.out.println("\nYou've input an invalid character! Please try again!");
                    TimeUnit.SECONDS.sleep(2);
                    ex.printStackTrace();
                }
            } 
        } catch (InterruptedException | RemoteException ex) {
            ex.printStackTrace();
        }
    }
}
