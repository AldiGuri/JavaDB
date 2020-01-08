import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class File_IO {
    //Shkruan ne file duke i shtuar te dhena pa fshire tekstin e me perparshem
    public void AppendToFile(String text,String filePath){
        try{
            FileWriter fw=new FileWriter(new File(filePath),true);
            fw.write(text);
            fw.close();
        }catch (FileNotFoundException e){System.out.println("File_IO Missing");}
        catch (IOException i){System.out.println("Failed to read file.");}
    }


    //Shkruan ne file duke i bere overwrite
    public void WriteToFile(String text,String filePath) {
        try{
            FileOutputStream os=new FileOutputStream(filePath);
            byte[] stringBytes=text.getBytes();
            os.write(stringBytes);
            os.close();
        }catch(IOException e){System.out.println("Failed to write to file");}
    }


    //Lexon file dhe kthen nje liste me rreshtat
    public String ReadFromFile(String filePath){
        //Krijojme liste per te ruajtur rreshtat e file
        String lines = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line;

            //Shtojme rreshtat ne liste nje nga nje
            while ((line=br.readLine()) !=null){
                lines += line +'\n';
            }
        }catch (FileNotFoundException e){System.out.println("File_IO Missing");}
        catch (IOException i){System.out.println("Failed to read file.");}
        return lines;
    }
}
