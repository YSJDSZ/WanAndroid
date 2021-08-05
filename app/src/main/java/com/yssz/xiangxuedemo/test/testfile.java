package com.yssz.xiangxuedemo.test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class testfile {
    public static void main(String args[]) {
        try {
            FileReader read = new FileReader("D:/111/catalina.out");
            BufferedReader br = new BufferedReader(read);
            String row;

            int rownum = 1;

            int fileNo = 1;
            FileWriter fw = new FileWriter("D:/111/catalina"+fileNo +".out");
            while ((row = br.readLine()) != null) {
                rownum ++;
                fw.append(row + "\r\n");

                if((rownum / 4641830) > (fileNo - 1)){
                    fw.close();
                    fileNo ++ ;
                    fw = new FileWriter("D:/111/catalina"+fileNo +".out");
                }
            }
            fw.close();
            System.out.println("rownum="+rownum+";fileNo="+fileNo);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
