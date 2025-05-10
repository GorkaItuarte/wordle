import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

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
                    if((guess[j].equals(letter))&&(!word[j].equals(letter))){
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
    private static Set<String> loadDictionary(String filePath) {
        Set<String> dictionary = new HashSet<>();
        try {
            // Leer todas las líneas del archivo y agregarlas al Set
            dictionary.addAll(Files.readAllLines(Paths.get(filePath)));
        } catch (IOException e) {
            System.out.println("Error al cargar el diccionario: " + e.getMessage());
        }
        return dictionary;
    }
    public static void main(String[] args) {
        // Cargar el diccionario
        Set<String> dictionary = loadDictionary("src/palabras_5_letras.txt");

        if (dictionary.isEmpty()) {
            System.out.println("El diccionario está vacío o no se pudo cargar.");
            return;
        }
        Random random = new Random();
        int numeroAleatorio = random.nextInt(10835);
        List<String> dictionaryList = new ArrayList<>(dictionary);
        String word = dictionaryList.get(numeroAleatorio - 1);
        Scanner scanner = new Scanner(System.in);
        /**
         * boolean wordValid=false;
         * String word="";
         *         Scanner scanner = new Scanner(System.in);
         *         while (!wordValid){
         *         scanner = new Scanner(System.in);
         *         System.out.print("Introduce una palabra: ");
         *         word = scanner.nextLine().toLowerCase();
         *         Random random = new Random();
         *         int numeroAleatorio = random.nextInt(10835);
         *             List<String> dictionaryList = new ArrayList<>(dictionary);
         *             word = dictionaryList.get(numeroAleatorio - 1);
         *         System.out.println(numeroAleatorio);
         *         // Verificar si la palabra es válida
         *         if (dictionary.contains(word)) {
         *             System.out.println("La palabra es válida.");
         *             wordValid=true;
         *         } else {
         *             System.out.println("La palabra no es válida.");
         *         }
         *         }
         */




        String[] correctLetters = word.split("");
        //System.out.println("How many guesses?");
        //int guessAmount = scanner.nextInt();
        int guessAmount=6;
        //scanner.nextLine();//leftover line
        //without checking word length nor if word is valid
        int j=0;
        boolean correct=false;
        while (j<guessAmount&&!correct){
            System.out.print("Intento número "+(j+1)+":");
            String guess = scanner.nextLine();
            if (dictionary.contains(guess)) {
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
            }else{
                System.out.println("La palabra no es válida");
            }
            }
        if(correct){
            System.out.println("Felicidades!");
        }else{
            System.out.println("Buen intento!");
            System.out.println("La palabra era: "+word);
        }

        //System.out.println("❌");  Red square 🟥
        //System.out.println("✅"); Green square 🟩
        //System.out.println("❓");  Orange square 🟧
    }
}