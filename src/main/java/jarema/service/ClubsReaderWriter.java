package jarema.service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Jarema on 05.01.2017.
 */
public class ClubsReaderWriter {

    public Clubs read(int season){

        try(ObjectInputStream is = new ObjectInputStream(new FileInputStream("season"+season+".bin"))) {

            String line = (String) is.readObject();
            List<String> list = Arrays.asList(line.split(","));
            is.close();
            return new Clubs(list);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean write(int season, String str){

        String s = str.toUpperCase();
        if(s.split(",").length%2 != 0)
            return false;

        try(ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("season"+season+".bin"))) {

            os.writeObject(s);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    public static void main(String [] args){
        new ClubsReaderWriter().write(2016,"asdad");
        new ClubsReaderWriter().read(2016);

    }

}
