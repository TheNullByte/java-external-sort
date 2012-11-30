
import java.io.*;
import java.util.Arrays;



public class app4
{
    static int tracker;
    static int counter = 0;
    public static void intoChunks(String filename) throws FileNotFoundException, IOException
    {
       int i = 1;
       tracker = 1;
       BufferedReader br = new BufferedReader(new InputStreamReader(new DataInputStream(new FileInputStream(filename))));
       PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("merge"+i+".txt", true)));
       String str;
       
       while((str = br.readLine()) != null)
       {
           pw.println(str);
           if(i % 10 == 0)
           {
               tracker++;
               pw.close();
               pw = new PrintWriter(new BufferedWriter(new FileWriter("merge"+tracker+".txt")));
           }
           i++;
       }
       
       
       pw.close();
        if((i - 1) % 10 == 0)
       {
           File file = new File("merge"+tracker+".txt");
           file.delete();
           tracker -= 1;
       }
        if(tracker % 2 != 0)
        {
            File file = new File("merge"+(tracker+1)+".txt");
            file.createNewFile();
            tracker++;
        }
    }
    public static void sortChunks(String filename) throws FileNotFoundException, IOException
    {
        intoChunks(filename);
        for(int i = 1; i <= tracker; i++)
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(new DataInputStream(new FileInputStream("merge"+i+".txt"))));
            
            String str;
            int[] temp = new int[10];
            int j = 0;
            for(int h = 0; h < 10; h++)
            {
                str = br.readLine();
                if(str != null)temp[j] = Integer.parseInt(str);
                j++;
            }
            //System.out.println(Arrays.toString(temp) + "merge"+i+".txt");System.exit(0);
            Arrays.sort(temp);
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("merge"+i+".txt")));
            for(int k = 0; k < j; k++)
            {
                if(temp[k] != 0)pw.println(temp[k]);
            }
            pw.close();
            br.close();
        }
        
    }
     public static void mix(String File1, String File2, String out) throws FileNotFoundException, IOException
    {
        BufferedReader br1 = new BufferedReader(new InputStreamReader(new DataInputStream(new FileInputStream(File1))));
        BufferedReader br2 = new BufferedReader(new InputStreamReader(new DataInputStream(new FileInputStream(File2))));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(out)));
        String str1 = br1.readLine();
        String str2 = br2.readLine();
        while(str1 != null && str2 != null)
        {
            if(Integer.parseInt(str1) < Integer.parseInt(str2)){
            pw.println(str1);
            str1 = br1.readLine();
            }
            else
            {
                pw.println(str2);
                str2 = br2.readLine();
            }
        }
        while(str1 == null && str2 != null)
        {
            pw.println(str2);
            str2 = br2.readLine();
        }
        while(str2 == null && str1 != null)
        {
            pw.println(str1);
            str1 = br1.readLine();
        }
        pw.close();
    }
    public static void mix(String File1, String File2) throws FileNotFoundException, IOException
    {
        BufferedReader br1 = new BufferedReader(new InputStreamReader(new DataInputStream(new FileInputStream(File1))));
        BufferedReader br2 = new BufferedReader(new InputStreamReader(new DataInputStream(new FileInputStream(File2))));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("merge"+tracker+".txt")));
        String str1 = br1.readLine();
        String str2 = br2.readLine();
        while(str1 != null && str2 != null)
        {
            if(Integer.parseInt(str1) < Integer.parseInt(str2)){
            pw.println(str1);
            str1 = br1.readLine();
            }
            else
            {
                pw.println(str2);
                str2 = br2.readLine();
            }
        }
        while(str1 == null && str2 != null)
        {
            pw.println(str2);
            str2 = br2.readLine();
        }
        while(str2 == null && str1 != null)
        {
            pw.println(str1);
            str1 = br2.readLine();
        }
        pw.close();
    }
    
    public static void merge(String File) throws FileNotFoundException, IOException
    {
        sortChunks(File);
        //int temp = tracker;
        tracker++;
        //tracker = 1;
        //mix("merge1.txt", "merge2.txt","merge5.txt");
        //mix("merge3.txt","merge4.txt","merge6.txt");
        //mix("merge5.txt","merge6.txt","merge7.txt");
        for(int i = 1; i < tracker; i+=2)
        {
            File f = new File("merge"+(i+1)+".txt");
            if(f.exists()){
            mix("merge"+i+".txt", "merge"+(i+1)+".txt", "merge"+tracker+".txt");
            tracker++;
            }
            else
            {
                System.out.println("Finished Sorting. File Name is merge"+i+".txt");
                System.exit(0);
            }
        }
    }
    public static void main(String...args) throws FileNotFoundException, IOException
    {
        merge("numbers.txt");
        
    }
}