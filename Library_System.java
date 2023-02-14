import java.io.*;
import java.util.*;
import java.text.*;
import java.text.ParseException;

public class Library_System {

    //clear screen function
    static void clrscr(){
        System.out.print("\033[H\033[2J");  
        System.out.flush(); 
    }

    //prints all books
    static void all_Books() throws IOException{
        //file reader
        File books = new File("BookLib.txt");
        BufferedReader readbook = new BufferedReader(new FileReader(books));
        //s will receive the content of the reader
        String b;

        //used for looping
        String trigger = "loop";

        //scanner for inputs
        Scanner input = new Scanner(System.in);
         
        //cont > content of the string s as we split is into an array
        String[] bookcont;

        while(trigger == "loop")
        {
            //Table Head
            System.out.printf("_______________________________________________________________________________________________\n");
            System.out.printf("|| %-5s | %-25s|\t%20s |\t%10s |\t%12s ||\n", "No.","Book Title", "Author", "Category", "Status");
            System.out.printf("||=======+==========================+========================+=============+=================||\n");

            //prints the content of lines while theres still content to read
            while( (b = readbook.readLine()) != null ){
                //splits the line when it encounter the parameter string
                bookcont = b.split(" > ");

                //used printf to create a format in printing
                System.out.printf("|| %-5s | %-25s|\t%20s |\t%10s |\t%12s ||\n", bookcont[0], bookcont[1], bookcont[2], bookcont[3], bookcont[4]);
                System.out.printf("||-------+--------------------------+------------------------+-------------+-----------------||\n");
            }

            System.out.print("\n[B] Back\t[A] Available Books\t[U] Unvailable\nChoice:");
            String choice = input.next();

            if( choice.equalsIgnoreCase("X") ){
                trigger = "end loop";

            }
            else if( choice.equalsIgnoreCase("A") ){

            }
            else if( choice.equalsIgnoreCase("B") ){

            }
        }//end of trigger

        
    }//end of all_Books


    static void catprint(String cat) throws IOException{
        //reads book BookLib.txt
        File books = new File("BookLib.txt");
        BufferedReader readBook = new BufferedReader(new FileReader(books));
        //receive the content of the reader
        String s;

        //bookcont > content of the string s as we split is into an array
        String[] bookcont;

        //table head
        System.out.printf("____________________________________________________________________________________________\n");
        System.out.printf("|| %-5s | %-25s|\t%20s |\t%10s |\t%10s ||\n", "No.","Book Title", "Author", "Category", "Status");
        System.out.printf("||=======+==========================+========================+=============+===============||\n");

        while( (s = readBook.readLine()) != null){
            bookcont = s.split(" > ");

            if( (bookcont[4].equalsIgnoreCase("available")) && bookcont[3].equalsIgnoreCase(cat)){
                System.out.printf("|| %-5s | %-25s|\t%20s |\t%10s |\t%10s ||\n", bookcont[0], bookcont[1], bookcont[2], bookcont[3], bookcont[4]);
                System.out.printf("||-------+--------------------------+------------------------+-------------+---------------||\n");
            }       
        }
    }//end of catprint


    static int borrow_Books(String searchkey) throws IOException, ParseException{

        //File Reader
        File books = new File("BookLib.txt");
        
        BufferedReader brBooks = new BufferedReader(new FileReader(books));
        
        String b;

        //needed many scanner to avoid bugs
        Scanner input1 = new Scanner(System.in);
        Scanner input2 = new Scanner(System.in);
        Scanner input3 = new Scanner(System.in);

        //used for updating the BooksLib.txt
        ArrayList<String> arrayBook = new ArrayList<String>();

        String[] bookcont;

        int confirm = 0;
        

        while( (b = brBooks.readLine()) != null ){
            if( b.contains(searchkey) ){

                //if the book being search was already borrowed
                if( b.contains("Unavailable") ){
                    System.out.println("BOOK IS UNAVAILABLE");
                    System.out.println("Press Enter Key...");
                    String key = input1.nextLine();
                    if(key == ""){
                        //clrscr();
                        return 1;
                    }
                }

                bookcont = b.split(" > ");
                System.out.println("Title: " + bookcont[0]);
                System.out.println("Author: " + bookcont[1]);

                //enter date the book is being borrowed
                System.out.printf("Date Borrow: "); String Bdate = input1.next();
                //converts String to Datetime format
                SimpleDateFormat sdf = new SimpleDateFormat("mm-dd-yyyy");
                Calendar c = Calendar.getInstance();
                Date dateB = sdf.parse(Bdate);

                //sets dateB as time and adds 5 days
                c.setTime(dateB);
                c.add(Calendar.DAY_OF_MONTH, 5);
                Date dateD = c.getTime(); String Ddate = sdf.format(dateD);
                System.out.println("Due Date: "+ Ddate);
                
                //name of the borrower
                System.out.flush();
                System.out.printf("Name: "); String name = input2.nextLine();

                System.out.printf("\n[A]Confirm\t[B]Cancel\nChoose:");
                String CC = input3.next();
                
                //confirms borrowing
                if(CC.equalsIgnoreCase("A")){
                    File logs = new File("LogLib.txt");
                    BufferedWriter bwLogs = new BufferedWriter(new FileWriter(logs, true));
                    System.out.println("Confirmed");
                    confirm = 1;

                    //adds a new log
                    bwLogs.write(name+" > "+bookcont[0]+" > "+bookcont[1]+" > "+bookcont[2]+" > "+Bdate+" > "+Ddate+" > "+"To Return"+" > "+null+"\n");
                    bwLogs.close();

                    //changes status off the borrowed book in BookLib.txt
                    String availtag = "Available";
                    String notavailtag = "Unavailable";
                    arrayBook.add(b.replace(availtag,notavailtag));
                }
                else{
                    confirm = 0;
                }
            }//end of if contains searchkey
            else{
                arrayBook.add(b);
            }

        }//end of while for readBooks

        if( confirm == 1){
            FileWriter writer = new FileWriter(books);
            for(int i=0; i<arrayBook.size(); i++){
                writer.append(arrayBook.get(i));
                writer.append("\n");
            }
                writer.close();

                System.out.println("Transaction Complete!!!");
                System.out.println("Press Enter Key...");
                String key = input1.nextLine();
                if(key == ""){
                    //clrscr();
                    return 1;
                }

                return 1;
        }
        else{
            return 0;
        }
    }


    static void borrow_menu()throws IOException, ParseException{

        String trigger = "loop";
        Scanner input = new Scanner(System.in);
        String searchkey;

        while(trigger == "loop")
        {
            
            System.out.println("=====BORROW=====\n");
            System.out.println("Search Title or Pick a Category:\n");
            System.out.println("[>M]Math\t[>S]Science\t[>L]Law\n[>H]History\t[>T]Language\t[>C]Cancel\n");
            System.out.println("Choose or Enter Title/Book number: "); searchkey = input.next();
            clrscr();

            if(searchkey.equalsIgnoreCase(">M")){
                catprint("MATH");
            }
            else if(searchkey.equalsIgnoreCase(">S")){
                catprint("SCIENCE");
            }
            else if(searchkey.equalsIgnoreCase(">L")){
                catprint("LAW");
            }
            else if(searchkey.equalsIgnoreCase(">H")){
                catprint("HISTORY");
            }
            else if(searchkey.equalsIgnoreCase(">T")){
                catprint("LANGUAGE");
            }
            else if(searchkey.equalsIgnoreCase(">C")){
                trigger="end";
            }
            else{
                if( borrow_Books(searchkey) == 1){
                    trigger = "end";
                } 
            }

        }// and of trigger

    }//end of borrow_menu

    static void return_book_list()throws IOException, ParseException{

        File logs = new File("LogLib.txt");
        BufferedReader brLogs = new BufferedReader(new FileReader(logs));
        String l;
        String[] logcont;

        System.out.printf("____________________________________________________________________________________________________________________________________________________________________________\n");
            System.out.printf("|| %-25s\t| %-5s|  %-25s\t| %-25s\t| %-12s\t| %-12s\t|%15s| %15s ||\n", "Name","no.", "Title", "Author", "Borrow Date", "Due Date", "Status","Return Date");
            System.out.printf("||==============================+======+================================+===============================+===============+===============+===============+=================||\n");
            
            
            while( (l = brLogs.readLine()) != null){
                logcont = l.split(" > ");
                if(logcont[6].equalsIgnoreCase("To Return")){
                                                // name | no | title | author | borrow date | due date | status | return date
                    System.out.printf("|| %-25s\t| %-5s|  %-25s\t| %-25s\t| %-12s\t| %-12s\t|%15s| %15s ||\n", logcont[0], logcont[1], logcont[2], logcont[3], logcont[4], logcont[5],logcont[6], logcont[7]);
                    System.out.printf("||------------------------------+------+--------------------------------+-------------------------------+---------------+---------------+---------------+-----------------||\n");
                }
            }

    }

    static void return_book() throws IOException, ParseException{

        //File Reader
        File books = new File("BookLib.txt");
        File logs = new File("LogLib.txt");
        BufferedReader brBooks = new BufferedReader(new FileReader(books));
        BufferedReader brLogs = new BufferedReader(new FileReader(logs));
        BufferedReader brLogs2 = new BufferedReader(new FileReader(logs));
        //b receives brBooks l receives bwlogs
        String b, l;

        Scanner input1 = new Scanner(System.in);
        Scanner input2 = new Scanner(System.in);

        //for File Writer
        ArrayList<String> arrbook = new ArrayList<String>();
        ArrayList<String> arrlogs = new ArrayList<String>();

        //receives an array from split
        String[] logcont; 

        //old and new status and variables to change return date
        //String oldStat, newStat, oldret, newret;
        
        int confirm=0; String keyword;

        String trigger = "return"; 

        while( trigger == "return" )
        {
            return_book_list();
            
            /* 
            System.out.printf("____________________________________________________________________________________________________________________________________________________________________________\n");
            System.out.printf("|| %-25s\t| %-5s|  %-25s\t| %-25s\t| %-12s\t| %-12s\t|%15s| %15s ||\n", "Name","no.", "Title", "Author", "Borrow Date", "Due Date", "Status","Return Date");
            System.out.printf("||==============================+======+================================+===============================+===============+===============+===============+=================||\n");
            
            
            while( (l = brLogs.readLine()) != null){
                logcont = l.split(" > ");
                if(logcont[6].equalsIgnoreCase("To Return")){
                                                // name | no | title | author | borrow date | due date | status | return date
                    System.out.printf("|| %-25s\t| %-5s|  %-25s\t| %-25s\t| %-12s\t| %-12s\t|%15s| %15s ||\n", logcont[0], logcont[1], logcont[2], logcont[3], logcont[4], logcont[5],logcont[6], logcont[7]);
                    System.out.printf("||------------------------------+------+--------------------------------+-------------------------------+---------------+---------------+---------------+-----------------||\n");
                }
            }*/
            
            System.out.print("[>C]Cancel\nEnter Student Name/Book Title/Book no.\nEnter: ");
            keyword = input1.nextLine();
            if(keyword.equalsIgnoreCase(">C")){
                trigger = "end";
            }

            while( (l = brLogs2.readLine()) != null ){

                if( l.contains(keyword) ){

                    if( l.contains("Returned") ){
                        System.out.println("BOOK ALREADY RETURNED");
                        String key = input1.nextLine();
                        if(key == ""){
                            clrscr();
                            return;
                        }
                    }

                    logcont = l.split(" > ");

                    
                    System.out.println("Name: " + logcont[0]);
                    System.out.println("Book No. : " + logcont[1]);
                    System.out.println("Title: " + logcont[2]);
                    System.out.println("Author: " + logcont[3]);
                    System.out.println("Date Borrow: " + logcont[4]);
                    System.out.println("Due Date: " + logcont[5]);
                    System.out.printf("Return Date: "); String dateR = input1.nextLine();
                    SimpleDateFormat sdf = new SimpleDateFormat("mm-dd-yyyy");
                    Date dueDate = sdf.parse(logcont[5]);
                    Date retDate = sdf.parse(dateR);

                    if(dueDate.compareTo(retDate) < 0){
                        long dif = retDate.getTime() - dueDate.getTime();
                        long daysdif = (dif / (1000*60*60*24)) % 365;
                        System.out.println("BOOK OVERDUE: "+daysdif);
                        System.out.println("Penalty: " + (daysdif * 10) );
                    }

                    System.out.print("\n[A]Confirm\t[B]Cancel\nChoose:");
                    String CC = input2.nextLine();

                    if( CC.equalsIgnoreCase("A") ){
                        System.out.println("Confirmed");
                        
                        confirm = 1;
                        arrlogs.add( l.replace("To Return", "Returned") );
                        arrlogs.add( l.replace("null" , dateR) );

                    }

                }//end of contains keyword
                else{
                    arrlogs.add(l);
                }//if not then still the same

            }//brLogs reader end
            

            if(confirm==1){

                while( (b = brBooks.readLine()) != null ){
                    if( b.contains(keyword) ){
                        arrbook.add(b.replace("Unavailable","Available"));
                    }
                    else{
                        arrbook.add(b);
                    }
    
                }

                FileWriter Wbook = new FileWriter(books);
                FileWriter Wlogs = new FileWriter(logs);
                int i;
                for(i=0; i< arrbook.size(); i++){
                    Wbook.append(arrbook.get(i));
                    Wbook.append("\n");
                }
                for(i=0; i< arrlogs.size(); i++){
                    Wlogs.append(arrlogs.get(i));
                    Wlogs.append("\n");
                }
                Wbook.close(); Wlogs.close();

                System.out.println("Logs Updated");
                trigger = "end loop";
            }


        }//end of while

    }//end of retrun_book


    //prints all logs with a "Returned" status
    static void retlog() throws IOException{
        String trigger = "viewLogs";
        Scanner input = new Scanner(System.in);
        File logs = new File("LogLib.txt");
        BufferedReader br = new BufferedReader(new FileReader(logs));
        String s; String[] brCont;

        while(trigger == "viewLogs")
        {
            clrscr();
            System.out.printf("____________________________________________________________________________________________________________________________________________________________________________\n");
            System.out.printf("|| %-25s\t| %-5s|  %-25s\t| %-25s\t| %-12s\t| %-12s\t|%15s| %15s ||\n", "Name","no.", "Title", "Author", "Borrow Date", "Due Date", "Status","Return Date");
            System.out.printf("||==============================+======+================================+===============================+===============+===============+===============+=================||\n");
            

            while( (s = br.readLine()) != null ){
                brCont = s.split(" > ");
                if(brCont[6].equalsIgnoreCase("Returned")){
                    System.out.printf("|| %-25s\t| %-5s|  %-25s\t| %-25s\t| %-12s\t| %-12s\t|%15s| %15s ||\n", brCont[0], brCont[1], brCont[2], brCont[3], brCont[4], brCont[5],brCont[6], brCont[7]);
                    System.out.printf("||------------------------------+------+--------------------------------+-------------------------------+---------------+---------------+---------------+-----------------||\n");
                }
            }

            System.out.print("\n[B] Back\t[A] All Logs\t[T] To Return\nChoice:");
            String choice = input.next();

            if(choice.equalsIgnoreCase("B")){
                br.close();
                trigger = "end";
            }
            else if(choice.equalsIgnoreCase("A")){
                br.close();
                viewLogs();
                trigger = "end";
            }
            else if(choice.equalsIgnoreCase("T")){
                br.close();
                toretlog();
                trigger = "end";
            }
        
        }
    }

    //prints all logs with a "To Return" Status
    static void toretlog() throws IOException{
        String trigger = "viewLogs";
        Scanner input = new Scanner(System.in);
        File logs = new File("LogLib.txt");
        BufferedReader br = new BufferedReader(new FileReader(logs));
        String s; String[] brCont;

        while(trigger == "viewLogs")
        {
            clrscr();
            System.out.printf("____________________________________________________________________________________________________________________________________________________________________________\n");
            System.out.printf("|| %-25s\t| %-5s|  %-25s\t| %-25s\t| %-12s\t| %-12s\t|%15s| %15s ||\n", "Name","no.", "Title", "Author", "Borrow Date", "Due Date", "Status","Return Date");
            System.out.printf("||==============================+======+================================+===============================+===============+===============+===============+=================||\n");
            

            while( (s = br.readLine()) != null ){
                brCont = s.split(" > ");
                if(brCont[6].equalsIgnoreCase("To Return")){
                    System.out.printf("|| %-25s\t| %-5s|  %-25s\t| %-25s\t| %-12s\t| %-12s\t|%15s| %15s ||\n", brCont[0], brCont[1], brCont[2], brCont[3], brCont[4], brCont[5],brCont[6], brCont[7]);
                    System.out.printf("||------------------------------+------+--------------------------------+-------------------------------+---------------+---------------+---------------+-----------------||\n");
                }
            }

            System.out.print("\n[B] Back\t[A] All Logs\t[R] To Return\nChoice:");
            String choice = input.next();

            if(choice.equalsIgnoreCase("B")){
                br.close();
                trigger = "end";
            }
            else if(choice.equalsIgnoreCase("A")){
                br.close();
                viewLogs();
                trigger = "end";
            }
            else if(choice.equalsIgnoreCase("R")){
                br.close();
                retlog();
                trigger = "end";
            }
            
        }

    }



    //Prints all Logs
    static void viewLogs() throws IOException{
        String trigger = "viewLogs";
        Scanner input = new Scanner(System.in);
        File logs = new File("LogLib.txt");
        BufferedReader br = new BufferedReader(new FileReader(logs));
        String s; String[] brCont;

        while(trigger == "viewLogs")
        {
            clrscr();
            System.out.printf("____________________________________________________________________________________________________________________________________________________________________________\n");
            System.out.printf("|| %-25s\t| %-5s|  %-25s\t| %-25s\t| %-12s\t| %-12s\t|%15s| %15s ||\n", "Name","no.", "Title", "Author", "Borrow Date", "Due Date", "Status","Return Date");
            System.out.printf("||==============================+======+================================+===============================+===============+===============+===============+=================||\n");
            

            while( (s = br.readLine()) != null ){
                brCont = s.split(" > ");
                System.out.printf("|| %-25s\t| %-5s|  %-25s\t| %-25s\t| %-12s\t| %-12s\t|%15s| %15s ||\n", brCont[0], brCont[1], brCont[2], brCont[3], brCont[4], brCont[5],brCont[6], brCont[7]);
                System.out.printf("||------------------------------+------+--------------------------------+-------------------------------+---------------+---------------+---------------+-----------------||\n");
                
            }

            System.out.print("\n[B] Back\t[R] Returned\t[T] To Return\nChoice:");
            String choice = input.next();

            if(choice.equalsIgnoreCase("B")){
                br.close();
                trigger = "end";
            }
            else if(choice.equalsIgnoreCase("R")){
                br.close();
                retlog();
                trigger = "end";
            }
            else if(choice.equalsIgnoreCase("T")){
                br.close();
                toretlog();
                trigger = "end";
            }
        
        }

    }


     //prints Main Menu
     static void MainMenu() throws IOException, ParseException{
        
        Scanner input = new Scanner(System.in);
        String trigger = "start";
        while(trigger=="start")
        {
            clrscr();
            System.out.println("=====THE LIBRARY=====");
            System.out.print("[A] All Books\n[B] Borrow\n[R] Return\n[L] Logs\n[X] Exit\nChoose: ");
            String choice = input.next();
            

            if(choice.equalsIgnoreCase("A")){ //All Books
                all_Books();
            }
            else if(choice.equalsIgnoreCase("B")){ //Borrow
                borrow_menu();
            }
            else if(choice.equalsIgnoreCase("R")){ //Return
                return_book();
            }
            else if(choice.equalsIgnoreCase("L")){ //Logs
                viewLogs();
            }
            else if(choice.equalsIgnoreCase("X")){ //Exit
                trigger = "end";
            }
        }// end of while(trigger=="start") *** ends when string is changed
        
    }
    
    
    public static void main(String[] args) throws IOException, ParseException{
        
        MainMenu();
    }
}
