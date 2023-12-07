package Utility;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;


public class APIs_Utils {

	 public static void saveResponseToFile(String fileName, String content) {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(content);
            System.out.println("Response saved to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



	 public static String generateRandomStringTest() {
		    int length = 12; // Desired length of the random string
		    String generatedString = generateRandomString(length);
		    System.out.println("Generated String: " + generatedString);
		    return generatedString;
		}

		public static String generateRandomString(int length) {
		    String characters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		    Random random = new Random();
		    StringBuilder sb = new StringBuilder(length);

		    // Ensure the first two characters are "02"
		    sb.append("02");

		    for (int i = 2; i < length; i++) {
		        int index = random.nextInt(characters.length());
		        sb.append(characters.charAt(index));
		    }

		    return sb.toString();
		}


//	  public static String generateRandomStringTest() {
//	        int length = 12; // Desired length of the random string
//	        String generatedString = generateRandomString(length);
//	        System.out.println("Generated String: " + generatedString);
//	        return generatedString;
//	    }
//
//	    public static String generateRandomString(int length) {
//	        String characters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
//	        Random random = new Random();
//	        StringBuilder sb = new StringBuilder(length);
//
//	        // Ensure the first character is a number
//	        sb.append(characters.charAt(random.nextInt(9) + 1)); // Ensure the first character is a number (1-9)
//
//	        for (int i = 1; i < length; i++) {
//	            int index = random.nextInt(characters.length());
//	            sb.append(characters.charAt(index));
//	        }
//
//	        return sb.toString();
//	    }



}
