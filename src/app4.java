
import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class app4 {

    static int tracker;
    static int counter = 0;
    static final int ARRAY_SIZE = 10;
    static long startTime;

    public static void intoChunks(String filename) throws FileNotFoundException, IOException {
        int i = 1;
        tracker = 1;
        try {
            FileInputStream thenumbers = new FileInputStream(filename);
            BufferedReader br = new BufferedReader(new InputStreamReader(new DataInputStream(new FileInputStream(filename))));
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("merge" + i + ".txt", true)));
            String str;
            int j = 0;
            int temp[] = new int[ARRAY_SIZE];
            while ((str = br.readLine()) != null) {
                if (i % ARRAY_SIZE == 0) {
                    temp[j] = Integer.parseInt(str);
                    Arrays.sort(temp);
                    for (int x = 0; x < ARRAY_SIZE; x++) {
                        pw.println(temp[x]);
                    }
                    tracker++;
                    pw.close();
                    pw = new PrintWriter(new BufferedWriter(new FileWriter("merge" + tracker + ".txt")));
                    j = 0;
                    i++;
                } else {
                    temp[j] = Integer.parseInt(str);
                    j++;
                    i++;
                }
            }
            Arrays.sort(temp);
            for (int y = 0; y < j; y++) {
                pw.println(temp[y]);
            }
            pw.close();
            if ((i - 1) % ARRAY_SIZE == 0) {
                File file = new File("merge" + tracker + ".txt");
                file.delete();
                tracker -= 1;
            }
            if (tracker % 2 != 0) {
                File file = new File("merge" + (tracker + 1) + ".txt");
                file.createNewFile();
                tracker++;
            }
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
        }
    }

    public static void mix(String File1, String File2, String out) throws FileNotFoundException, IOException {
        BufferedReader br1 = new BufferedReader(new InputStreamReader(new DataInputStream(new FileInputStream(File1))));
        BufferedReader br2 = new BufferedReader(new InputStreamReader(new DataInputStream(new FileInputStream(File2))));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(out)));
        String str1 = br1.readLine();
        String str2 = br2.readLine();
        while (str1 != null && str2 != null) {
            if (Integer.parseInt(str1) < Integer.parseInt(str2)) {
                pw.println(str1);
                str1 = br1.readLine();
            } else {
                pw.println(str2);
                str2 = br2.readLine();
            }
        }
        while (str1 == null && str2 != null) {
            pw.println(str2);
            str2 = br2.readLine();
        }
        while (str2 == null && str1 != null) {
            pw.println(str1);
            str1 = br1.readLine();
        }
        pw.close();
        br1.close();
        br2.close();
    }

    public static void merge(String File) throws FileNotFoundException, IOException {
        intoChunks(File);
        tracker++;
        for (int i = 1; i < tracker; i += 2) {
            File f = new File("merge" + (i + 1) + ".txt");
            if (f.exists()) {
                mix("merge" + i + ".txt", "merge" + (i + 1) + ".txt", "merge" + tracker + ".txt");
                tracker++;
                File f2 = new File("merge" + i + ".txt");
                File f3 = new File("merge" + (i + 1) + ".txt");
                if (f2.exists()) {
                    f2.delete();
                    f3.delete();
                }
            } else {
                long endTime = System.currentTimeMillis();
                System.out.println("It took " + i + " passes to sort this data and was sorted in " + ((endTime-startTime)/1000) + " seconds.");
                System.out.println("Finished Sorting. File Name is merge" + i + ".txt");
                System.exit(0);
            }
        }
    }

    public static void main(String... args) throws FileNotFoundException, IOException {
        while(true)
        {
            System.out.println("Please enter the textfile Location: ");
            String in = new Scanner(System.in).nextLine();
            startTime = System.currentTimeMillis();
            if(new File(in).exists())
            {
            merge(in);
            }
            else
            {
                System.out.println("Error, file not found. Please try again");
            }
        }
    }
}