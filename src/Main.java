import java.util.Scanner;

public class Main {
     // Naranjan ipinibida letri ya asmaute dauenin:
     // Berba zuzenan letra horren kopuru - Idatzixeko berbian letra horren kopuru = x
     // Idatzitxeko berbik zemat bider dauke hori letri leko zuzenin = y
     // x-y bider ahalda agertu hori letri naranjan, hortik aurra gorrixin
     //

    //kontau zemat bider dauen letri asmau barik
    public static boolean calculateOrange(String[] word, String[] guess, String letter,int index){
        int amount=0;
        boolean allow=false;
        for (int i=0; i<word.length;i++){
            if ((word[i].equals(letter))&&(!guess[i].equals(letter))){
                amount++;
            }
            //only allow orange if current letter is <=th appearance of that letter
            //0tik index-1era amount baño letter gitxiaua badaz
            int letterUntilIndex=0;
            if (index>0){
                for (int j=0; j<index-1;j++){
                    if(guess[j].equals(letter)){
                        letterUntilIndex++;
                    }
                }
            }

                if (letterUntilIndex<amount){
                    allow=true;
                }

        }
        return allow;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Word to guess: ");
        String word = scanner.nextLine();
        String[] correctLetters = word.split("");
        //System.out.println("How many guesses?");
        //int guessAmount = scanner.nextInt();
        int guessAmount=6;
        //scanner.nextLine();//leftover line
        //without checking word length nor if word is valid
        int j=0;
        boolean correct=false;
        while (j<guessAmount&&!correct){
            System.out.print("Guess number "+(j+1)+":");
            String guess = scanner.nextLine();
            String[] letters = guess.split("");
            for(int i=0; i<letters.length; i++){
                if(correctLetters[i].equals(letters[i])){
                    System.out.print("✅");
                }else{
                    if (word.contains(letters[i])){
                         boolean orangeAvailable = calculateOrange(correctLetters, letters, letters[i],i);
                        if (orangeAvailable){
                            System.out.print("❓");
                        }else{
                            System.out.print("❌");
                        }
                    }else{
                        System.out.print("❌");

                    }
                }
            }
            System.out.println("");
            if(guess.equals(word)){
                correct=true;
            }
            j++;
        }
        if(correct){
            System.out.println("Congratulations!");
        }else{
            System.out.println("Nice try!");
        }

        //System.out.println("❌");  Red square 🟥
        //System.out.println("✅"); Green square 🟩
        //System.out.println("❓");  Orange square 🟧
    }
}