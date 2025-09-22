import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ZombieCatcher{
    public static Scanner keyboardOBJ = new Scanner(System.in);

    public static boolean overlappingDayAndNightPeriods(int StartPeriod1, int EndPeriod1, int StartPeriod2, int EndPeriod2){
        return ((StartPeriod2 > EndPeriod1 && EndPeriod2 > StartPeriod1) || (StartPeriod2 > StartPeriod1 && EndPeriod1 > EndPeriod2));
    } 

    static boolean overlappingPeriods(int StartPeriod1, int EndPeriod1, int StartPeriod2, int EndPeriod2){
        return (StartPeriod1 < EndPeriod2 && StartPeriod2 < EndPeriod1);
    }

    //THE Two arguments are when the zombies are known to be within the venue
    static int getVisitors(int startTime, int Endtime){
        int quarantinedVisitors = 0;
        System.out.println("Enter the number of visitors:");
        int numberVisitors = keyboardOBJ.nextInt();


        for (int i = 0; i < numberVisitors; i++){

            System.out.println("Enter the visitor's name:");
            String visitorName = keyboardOBJ.next();

            System.out.println("Enter the arrival time:");
            int arrivalTime = keyboardOBJ.nextInt();

            System.out.println("Enter the departure time:");
            int depatureTime = keyboardOBJ.nextInt();
            

            boolean result = overlappingPeriods(startTime,Endtime,arrivalTime,depatureTime);

            if (result){
                System.out.println(visitorName + " needs to be quarantined.");
                quarantinedVisitors += 1;
            } else{
                System.out.println(visitorName + " does not need to be quarantined.");
            }
        }

        return quarantinedVisitors;

    }

    public static void main(String[] args){

        //this is the task to Q3 DO NOT REMOVE
        System.out.println("Enter the start time:");
        int userStartTime = keyboardOBJ.nextInt();

        System.out.println("Enter the end time:");
        int userEndTime = keyboardOBJ.nextInt();
        
        if (args.length == 0){
            System.out.println("Number of potential zombies: " + getVisitors(userStartTime, userEndTime));
        } else {

            
            //reading files
            int potentialZombies = 0;
            for (String arg : args){
                String filename = arg; //storing file name from arguments

                try {
                    File fileOBJ = new File(filename); //creates a file object
                    Scanner reader = new Scanner(fileOBJ); //reads the file
                    

                    while (reader.hasNext()){
                        String line = reader.nextLine();

                        String[] words = line.split(" "); //splits the sentence into individual words
                        
                        String name = words[0];
                        int arrival = Integer.parseInt(words[1]);
                        int depature = Integer.parseInt(words[2]);

                        boolean result = overlappingPeriods(userStartTime, userEndTime, arrival, depature);

                        if (result){
                            System.out.println(name + " needs to be quarantined.");
                            potentialZombies += 1;
                        }
                    }
                
                    
                    reader.close(); //closing the file
                    } catch (FileNotFoundException e) {
                    System.err.println("WARNING: " + filename + " not found.");
                }
            }
            System.out.println("Number of potential zombies: " + potentialZombies);
        }    
    }
}