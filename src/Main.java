import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        editLog();
        File file = new File("src/assemble.txt");
        Scanner fileReader = new Scanner(file);

        String nextLine = "";

        while (fileReader.hasNextLine()){
            nextLine = fileReader.nextLine();
            String[] parsed = nextLine.split("[, ]" );



            int decimal = Integer.parseInt(switchCase(parsed),2);
            String hexStr = Integer.toString(decimal,16);

            write(hexStr);

            /*for (String s: parsed) {
                System.out.print(s  +" ");
            }
            System.out.println();*/
        }
    }

    public static void write(String message) {

        File f = new File("out.hex");//Path for log file
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(f, true));
            bw.append(message + " " +  "\n");
            System.out.println(message + " ");
            bw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void editLog() {
        File f= new File("out.hex");
        f.delete();
    }

    public static String switchCase(String[] parsed){
        String bin = "";
        switch (parsed[0]){
            case "ADD":
                bin += "1000";
                bin += registerToBin(parsed[1]);
                bin +=  registerToBin(parsed[2]);
                bin += "0";
                bin += "00";
                bin += registerToBin(parsed[3]);
            break;

            case "ADDI":
                bin += "1000";
                bin += registerToBin(parsed[1]);
                bin +=  registerToBin(parsed[2]);
                bin += "1";
                bin += immToBin(parsed[3] , 5);


            break;

            case "AND":

                bin += "1001";
                bin += registerToBin(parsed[1]);
                bin +=  registerToBin(parsed[2]);
                bin += "0";
                bin += "00";
                bin += registerToBin(parsed[3]);

            break;

            case "ANDI":

                bin += "1001";
                bin += registerToBin(parsed[1]);
                bin +=  registerToBin(parsed[2]);
                bin += "1";
                bin += immToBin(parsed[3], 5);

            break;

            case"ORI":

                bin += "1010";
                bin += registerToBin(parsed[1]);
                bin +=  registerToBin(parsed[2]);
                bin += "1";
                bin += immToBin(parsed[3], 5);

            break;

            case "JUMP":
                bin += "1011";
                bin += immToBin(parsed[1], 12);

            break;

            case "LD":

                bin += "1100";
                bin += registerToBin(parsed[1]);
                bin += immToBin(parsed[2], 9);

            break;

            case "ST":


                bin += "1101";
                bin += registerToBin(parsed[1]);
                bin += immToBin(parsed[2], 9);


            break;

            case "CMP":

                bin += "1110";
                bin += registerToBin(parsed[1]);
                bin += registerToBin(parsed[2]);
                bin += "000000";


            break;

            case "JE":

                bin += "0001";
                bin += immToBin(parsed[1], 12);

            break;

            case "JA":

                bin += "0010";
                bin += immToBin(parsed[1], 12);

            break;

            case "JB":

                bin += "0011";
                bin += immToBin(parsed[1], 12);

                break;

            case "JAE":

                bin += "0100";
                bin += immToBin(parsed[1], 12);

            break;

            case "JBE":

                bin += "0101";
                bin += immToBin(parsed[1], 12);

            break;

            default :
                System.out.println("Entered default");
            break;



        }
        return bin;
    }

    static String immToBin(String s, int length){

        String bin5 = "";
        String bin = Integer.toBinaryString(Integer.parseInt(s));

        if (Integer.parseInt(s) < 0)
            return bin.substring( bin.length() - length);

        for (int i = 0; i < length - bin.length(); i++) {
            if (Integer.parseInt(s) > 0){
                bin5 += "0";
            }
        }

        bin5 += bin;
        return bin5;
    }

    static    String  registerToBin(String s){
        String bin = "";
        switch(s){
            case "R0":
                bin = "000";
                break;
            case "R1":
                bin = "001";
                break;
            case "R2":
                bin = "010";
                break;
            case "R3":
                bin = "011";
                break;
            case "R4":
                bin = "100";
                break;
            case "R5":
                bin = "101";
                break;
            case "R6":
                bin = "110";
                break;
            case "R7":
                bin = "111";
                break;
        }
        return bin;
    }




}
