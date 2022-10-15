import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {
        System.out.println("Hello world!");
        File file = new File("parametrs.txt");
        ArrayList<String> arrayList = new ArrayList<>();
        Main.readfile(file, arrayList);
        System.out.println(arrayList);

        String[] wordArray = {};
        wordArray = arrayList.toArray(new String[arrayList.size()]);
        int A = Integer.parseInt(wordArray[0]);
        int S = Integer.parseInt(wordArray[1]);
        int S0 = Integer.parseInt(wordArray[2]);
        int tops = Integer.parseInt(wordArray[3]);
        int[] SPrev = new int[wordArray.length]; Arrays.fill(SPrev, -1);
        int[] SNext = new int[wordArray.length]; Arrays.fill(SNext, -1);
        int[] countPrev = new int[wordArray.length]; Arrays.fill(countPrev, 0);
        int[] countNext = new int[wordArray.length];
        int countLetters = 1;
        int IndexNext;
        int IndexPrev = 0;
        SPrev[0] = S0;
        int ans = 0;
        int[] fin = new int [wordArray.length]; Arrays.fill(fin, -1);
        int finIndex = 0;


        for(int i = 0; i < wordArray.length; ++i)
        {
            System.out.println(wordArray[i]);
        }
        Scanner in = new Scanner(System.in);
        String w = "";
        String w1 = in.nextLine();
        String w2 = "";
        w = w + w1 + w2;

        for(int i = 0; i < w.length(); ++i)
        {
            for(int j = i - 1; j>=0; --j)
            {
                if (j == 0)
                    countLetters++;
                else if (w.charAt(i) == w.charAt(j))
                {
                    break;
                }
            }
        }

        if (A >= countLetters)
        {
            for(int i = 0; i < w.length(); ++i)
            {
                IndexPrev = 0;
                IndexNext = 0;
                while(IndexPrev != SPrev.length)
                {
                    for(int k = 4 + tops; k < wordArray.length; ++k)
                    {
                        if(SPrev[IndexPrev] == Character.getNumericValue(wordArray[k].charAt(0)) && w.charAt(i) == wordArray[k].charAt(1))
                        {
                            SNext[IndexNext] = Character.getNumericValue(wordArray[k].charAt(2));
                            countNext[IndexNext] = countPrev[IndexPrev] + 1;
                            IndexNext++;
                            if(i == w.length() - 1)
                            {
                                fin[IndexNext - 1] = Character.getNumericValue(wordArray[k].charAt(2));
                            }
                        }
                        else if (SPrev[IndexPrev] == Character.getNumericValue(wordArray[k].charAt(0)) && Character.isDigit(wordArray[k].charAt(1)))
                        {
                            for(int p = 0; p < SPrev.length; ++p)
                            {
                                if (SPrev[p] == -1)
                                {
                                    SPrev[p] = Character.getNumericValue(wordArray[k].charAt(1));
                                    countPrev[p] = countPrev[IndexPrev] + 1;
                                    break;
                                }
                            }
                        }
                    }
                    IndexPrev++;
                }
                if (IndexNext == 0)
                {
                    System.out.println("we can't");
                    ans = 1;
                    break;
                }
                SPrev = new int[wordArray.length];
                countPrev = new int[wordArray.length];
                System.arraycopy(SNext, 0, SPrev, 0, SNext.length);
                System.arraycopy(countNext, 0, countPrev, 0, SNext.length);
                SNext = new int[wordArray.length]; Arrays.fill(SNext, -1);
                countNext = new int[wordArray.length];
            }
        }
        else
        {
            System.out.println("we can't");
            ans = 1;
        }
        if (ans == 0)
        {
            ans = 1;
            for(int i = 0; i < countPrev.length; ++i)
            {
                System.out.println(countPrev[i]);
                if(countPrev[i] <= S && countPrev[i] != 0)
                {
                    for(int j = 4; j < 4 + tops; ++j)
                    {
                        if(fin[i] == Integer.parseInt(wordArray[j]))
                            ans = 0;
                    }
                }
            }
        }
        if (ans == 0)
        {
            ans = 1;
            for(int i = 4; i < 4 + tops; ++i)
            {
                for(int j = 0; j <= tops; ++j)
                {
                    if(fin[j] == Integer.parseInt(wordArray[i]))
                        ans = 0;
                }

            }
        }
        if (ans == 0)
            System.out.println("oki");
        else
            System.out.println("we can't");
    }
    public static void readfile(File file, ArrayList<String> strings){
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String[] words = scanner.nextLine().split("[\\W]");
                for (String word: words) {
                    if(!word.equals("")){
                        strings.add(word.substring(0, Math.min(word.length(), 30)));
                    }
                }
            }
        } catch (FileNotFoundException e) {
                System.out.println("File not found!");
        }
    }
}