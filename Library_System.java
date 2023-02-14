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
    static void all_books() throws IOException{
        //file reader
        File books = new File("BookLib.txt");
        BufferedReader readbook = new BufferedReader(new FileReader(books));
        //s will receive the content of the reader
        String b;

        //scanner for inputs
        Scanner input = new Scanner(System.in);
         
        //cont > content of the string s as we split is into an array
        String[] bookcont;

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
        
    }//end of all_Books

    static void avail_books() throws IOException{
        //file reader
        File books = new File("BookLib.txt");
        BufferedReader readbook = new BufferedReader(new FileReader(books));
        //s will receive the content of the reader
        String b;

        //scanner for inputs
        Scanner input = new Scanner(System.in);
         
        //cont > content of the string s as we split is into an array
        String[] bookcont;

        //Table Head
        System.out.printf("_______________________________________________________________________________________________\n");
        System.out.printf("|| %-5s | %-25s|\t%20s |\t%10s |\t%12s ||\n", "No.","Book Title", "Author", "Category", "Status");
        System.out.printf("||=======+==========================+========================+=============+=================||\n");

        //prints the content of lines while theres still content to read
        while( (b = readbook.readLine()) != null ){
            //splits the line when it encounter the parameter string
            bookcont = b.split(" > ");

            if( bookcont[4].equalsIgnoreCase("Available") ){
                //used printf to create a format in printing
                System.out.printf("|| %-5s | %-25s|\t%20s |\t%10s |\t%12s ||\n", bookcont[0], bookcont[1], bookcont[2], bookcont[3], bookcont[4]);
                System.out.printf("||-------+--------------------------+------------------------+-------------+-----------------||\n");
            }

            
        }

    }

    static void unavail_books() throws IOException{
        //file reader
        File books = new File("BookLib.txt");
        BufferedReader readbook = new BufferedReader(new FileReader(books));
        //s will receive the content of the reader
        String b;

        //scanner for inputs
        Scanner input = new Scanner(System.in);
         
        //cont > content of the string s as we split is into an array
        String[] bookcont;

        //Table Head
        System.out.printf("_______________________________________________________________________________________________\n");
        System.out.printf("|| %-5s | %-25s|\t%20s |\t%10s |\t%12s ||\n", "No.","Book Title", "Author", "Category", "Status");
        System.out.printf("||=======+==========================+========================+=============+=================||\n");

        //prints the content of lines while theres still content to read
        while( (b = readbook.readLine()) != null ){
            //splits the line when it encounter the parameter string
            bookcont = b.split(" > ");

            if( bookcont[4].equalsIgnoreCase("Unavailable") ){
                //used printf to create a format in printing
                System.out.printf("|| %-5s | %-25s|\t%20s |\t%10s |\t%12s ||\n", bookcont[0], bookcont[1], bookcont[2], bookcont[3], bookcont[4]);
                System.out.printf("||-------+--------------------------+------------------------+-------------+-----------------||\n");
            }

            
        }
    }

    static void catprint(String cat, String catsort) throws IOException{
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

            //prints all books in the selected category
            if( bookcont[3].equalsIgnoreCase(cat) && catsort.equalsIgnoreCase("O")){
                System.out.printf("|| %-5s | %-25s|\t%20s |\t%10s |\t%10s ||\n", bookcont[0], bookcont[1], bookcont[2], bookcont[3], bookcont[4]);
                System.out.printf("||-------+--------------------------+------------------------+-------------+---------------||\n");
            }
            //prints all available books in the selected category
            else if( bookcont[3].equalsIgnoreCase(cat) && catsort.equalsIgnoreCase("A") && bookcont[4].equalsIgnoreCase("Available") ){

                System.out.printf("|| %-5s | %-25s|\t%20s |\t%10s |\t%10s ||\n", bookcont[0], bookcont[1], bookcont[2], bookcont[3], bookcont[4]);
                System.out.printf("||-------+--------------------------+------------------------+-------------+---------------||\n");
            } 
            //prints all unavailable books in the selected category
            else if( bookcont[3].equalsIgnoreCase(cat) && catsort.equalsIgnoreCase("U") && bookcont[4].equalsIgnoreCase("Unavailable") ){
                System.out.printf("|| %-5s | %-25s|\t%20s |\t%10s |\t%10s ||\n", bookcont[0], bookcont[1], bookcont[2], bookcont[3], bookcont[4]);
                System.out.printf("||-------+--------------------------+------------------------+-------------+---------------||\n");
            }  
        }
    }//end of catprint

    static void view_books()throws IOException{
        
        Scanner input = new Scanner(System.in);
        Scanner input2 = new Scanner(System.in);

        //the function starts by printing all books
        String choice = "O"; String catsort;

        String trigger = "loop";

        while( trigger == "loop" )
        {
    
            if( choice.equalsIgnoreCase("O") ){
                all_books();
            }
            else if( choice.equalsIgnoreCase("X") ){
                return;
            }
            else if( choice.equalsIgnoreCase("A") ){
                avail_books();
            }
            else if( choice.equalsIgnoreCase("U") ){
                unavail_books();
            }
            else if( choice.equalsIgnoreCase("M") ){
                //will ask if the user only wants to see a more specific content
                System.out.printf("\n[O]All Math\t[A]Available Books\t[U]Unavailable Books\nChoice: ");
                catsort = input2.next();
                catprint("MATH", catsort);
            }
            else if( choice.equalsIgnoreCase("S") ){
                System.out.printf("\n[O]All Math\t[A]Available Books\t[U]Unavailable Books\nChoice: ");
                catsort = input2.next();
                catprint("SCIENCE", catsort);
            }
            else if( choice.equalsIgnoreCase("L") ){
                System.out.printf("\n[O]All Math\t[A]Available Books\t[U]Unavailable Books\nChoice: ");
                catsort = input2.next();
                catprint("LAW", catsort);
            }
            else if( choice.equalsIgnoreCase("H") ){
                System.out.printf("\n[O]All Math\t[A]Available Books\t[U]Unavailable Books\nChoice: ");
                catsort = input2.next();
                catprint("HISTORY", catsort);
            }
            else if( choice.equalsIgnoreCase("T") ){
                System.out.printf("\n[O]All Math\t[A]Available Books\t[U]Unavailable Books\nChoice: ");
                catsort = input2.next();
                catprint("LANGUAGE", catsort);
            }
            

            System.out.printf("\n[O]All Books\t[A]Available Books\t[U]Unavailable Books\n");
            System.out.println("========= CATEGORIES =========");
            System.out.printf("[M]MATH\t\t[S]SCIENCE\t[L]LAW\n[H]HISTORY\t[T]LANGUAGE\t[X]Back\nChoice:");

            choice = input.next();

        }

        

    }



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
                System.out.println("Title:\t" + bookcont[0]);
                System.out.println("Author:\t" + bookcont[1]);

                //enter date the book is being borrowed
                System.out.printf("Date Borrow (mm-dd-yyyy): "); String Bdate = input1.next();
                //converts String to Datetime format
                SimpleDateFormat sdf = new SimpleDateFormat("mm-dd-yyyy");
                Calendar c = Calendar.getInstance();
                Date dateB = sdf.parse(Bdate);

                //sets dateB as time and adds 5 days
                c.setTime(dateB);
                c.add(Calendar.DAY_OF_MONTH, 5);
                Date dateD = c.getTime(); String Ddate = sdf.format(dateD);
                System.out.println("Due Date (mm-dd-yyyy): "+ Ddate);
                
                //name of the borrower
                System.out.flush();
                System.out.printf("Name:\t"); String name = input2.nextLine();

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
            //updates BookLib.txt
            FileWriter writer = new FileWriter(books);
            for(int i=0; i<arrayBook.size(); i++){
                writer.append(arrayBook.get(i));
                writer.append("\n");
            }
                writer.close();

                System.out.println("Transaction Complete!!!");
                System.out.println("Press Enter Key...");
                String key = input1.nextLine();
                //only accepts enter key
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
            System.out.println("[M]Math\t\t[S]Science\t[L]Law\n[H]History\t[T]Language\t[C]Cancel\n");
            System.out.println("Choose or Enter Title/Book number: "); searchkey = input.next();
            clrscr();

            //if-else statements for specific categories
            if(searchkey.equalsIgnoreCase("M")){
                catprint("MATH", "A");
            }
            else if(searchkey.equalsIgnoreCase("S")){
                catprint("SCIENCE", "A");
            }
            else if(searchkey.equalsIgnoreCase("L")){
                catprint("LAW", "A");
            }
            else if(searchkey.equalsIgnoreCase("H")){
                catprint("HISTORY", "A");
            }
            else if(searchkey.equalsIgnoreCase("T")){
                catprint("LANGUAGE", "A");
            }
            else if(searchkey.equalsIgnoreCase("C")){
                trigger="end";
            }
            else{
                if( borrow_Books(searchkey) == 1){
                    trigger = "end";
                } 
            }

        }// and of trigger

    }//end of borrow_menu

    //baka dagdagan ko wait...

    //return a book
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
            //prints all "To Return" books
            toret_log();
            
            System.out.print("[C]Cancel\nEnter Student Name/Book Title/Book no.\nEnter: ");
            keyword = input1.nextLine();
            if(keyword.equalsIgnoreCase("C")){
                trigger = "end";
            }

            //reads a line in the textfile till it reaches the end of the file
            while( (l = brLogs2.readLine()) != null ){

                //if the string "l" contains the keyword
                if( l.contains(keyword) ){

                    //if the book is already returned
                    if( l.contains("Returned") ){
                        System.out.println("BOOK ALREADY RETURNED");
                        String key = input1.nextLine();
                        if(key == ""){
                            clrscr();
                            return;
                        }
                    }

                    //splits string "l" using a designated separator 
                    logcont = l.split(" > ");

                    //Prints info from logs
                    System.out.println("Name:\t" + logcont[0]);
                    System.out.println("Book No.:\t " + logcont[1]);
                    System.out.println("Title:\t" + logcont[2]);
                    System.out.println("Author:\t" + logcont[3]);
                    System.out.println("Date Borrow (mm-dd-yyyy): " + logcont[4]);
                    System.out.println("Due Date (mm-dd-yyyy): " + logcont[5]);

                    //takes the return date
                    System.out.printf("Return Date (mm-dd-yyyy): "); String dateR = input1.nextLine();
                    SimpleDateFormat sdf = new SimpleDateFormat("mm-dd-yyyy");
                    Date dueDate = sdf.parse(logcont[5]);
                    Date retDate = sdf.parse(dateR);

                    //compares the Due date and the return date
                    //if its later than the due date then day*10 pesos penaly 
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
                        //updates the log
                        arrlogs.add( l.replace("To Return", "Returned").replace("null" , dateR) );

                    }

                }//end of contains keyword
                else{
                    arrlogs.add(l);
                }//if not then still the same

            }//brLogs reader end
            

            if(confirm==1){

                //updates the book's status
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

    //Prints all Logs
    static void all_logs() throws IOException{
        String trigger = "viewLogs";
        Scanner input = new Scanner(System.in);
        File logs = new File("LogLib.txt");
        BufferedReader br = new BufferedReader(new FileReader(logs));
        String s; String[] brCont;

        clrscr();
        System.out.printf("ALL==================================================================================ALL=================================================================================ALL\n\n");
        System.out.printf("____________________________________________________________________________________________________________________________________________________________________________\n");
        System.out.printf("|| %-25s\t| %-5s|  %-25s\t| %-25s\t| %-12s\t| %-12s\t|%15s| %15s ||\n", "Name","no.", "Title", "Author", "Borrow Date", "Due Date", "Status","Return Date");
        System.out.printf("||==============================+======+================================+===============================+===============+===============+===============+=================||\n");
            

        while( (s = br.readLine()) != null ){
            brCont = s.split(" > ");
            System.out.printf("|| %-25s\t| %-5s|  %-25s\t| %-25s\t| %-12s\t| %-12s\t|%15s| %15s ||\n", brCont[0], brCont[1], brCont[2], brCont[3], brCont[4], brCont[5],brCont[6], brCont[7]);
            System.out.printf("||------------------------------+------+--------------------------------+-------------------------------+---------------+---------------+---------------+-----------------||\n");
        }//end of printing

    }

    //prints all logs with a "Returned" status
    static void ret_log() throws IOException{
        String trigger = "viewLogs";
        Scanner input = new Scanner(System.in);
        File logs = new File("LogLib.txt");
        BufferedReader br = new BufferedReader(new FileReader(logs));
        String s; String[] brCont;

        clrscr();
        System.out.printf("RETUNED============================================================================RETURNED=========================================================================RETURNED\n\n");
        System.out.printf("____________________________________________________________________________________________________________________________________________________________________________\n");
        System.out.printf("|| %-25s\t| %-5s|  %-25s\t| %-25s\t| %-12s\t| %-12s\t|%15s| %15s ||\n", "Name","no.", "Title", "Author", "Borrow Date", "Due Date", "Status","Return Date");
        System.out.printf("||==============================+======+================================+===============================+===============+===============+===============+=================||\n");
            

            while( (s = br.readLine()) != null ){
                brCont = s.split(" > ");
                if(brCont[6].equalsIgnoreCase("Returned")){
                    System.out.printf("|| %-25s\t| %-5s|  %-25s\t| %-25s\t| %-12s\t| %-12s\t|%15s| %15s ||\n", brCont[0], brCont[1], brCont[2], brCont[3], brCont[4], brCont[5],brCont[6], brCont[7]);
                    System.out.printf("||------------------------------+------+--------------------------------+-------------------------------+---------------+---------------+---------------+-----------------||\n");
                }
            }//end of printing
        
    }

    //prints all logs with a "To Return" Status
    static void toret_log() throws IOException{
        String trigger = "viewLogs";
        Scanner input = new Scanner(System.in);
        File logs = new File("LogLib.txt");
        BufferedReader br = new BufferedReader(new FileReader(logs));
        String s; String[] brCont;


        clrscr();
        System.out.printf("TO RETURN==========================================================================TO RETURN=======================================================================TO RETURN\n\n");
        System.out.printf("____________________________________________________________________________________________________________________________________________________________________________\n");
        System.out.printf("|| %-25s\t| %-5s|  %-25s\t| %-25s\t| %-12s\t| %-12s\t|%15s| %15s ||\n", "Name","no.", "Title", "Author", "Borrow Date", "Due Date", "Status","Return Date");
        System.out.printf("||==============================+======+================================+===============================+===============+===============+===============+=================||\n");
            

        while( (s = br.readLine()) != null ){
            brCont = s.split(" > ");
            if(brCont[6].equalsIgnoreCase("To Return")){
                System.out.printf("|| %-25s\t| %-5s|  %-25s\t| %-25s\t| %-12s\t| %-12s\t|%15s| %15s ||\n", brCont[0], brCont[1], brCont[2], brCont[3], brCont[4], brCont[5],brCont[6], brCont[7]);
                    System.out.printf("||------------------------------+------+--------------------------------+-------------------------------+---------------+---------------+---------------+-----------------||\n");
            }
        }//end of printing

    }


    //menu for logs
    static void view_logs()throws IOException{
        Scanner input = new Scanner (System.in);
        //function starts by printing all logs
        String choice = "O";

        String trigger = "loop";

        while( trigger == "loop" )
        {
            clrscr();
    
            if( choice.equalsIgnoreCase("O")){
                all_logs();
            }
            else if( choice.equalsIgnoreCase("X")){
                return;
            }
            else if( choice.equalsIgnoreCase("T")){
                toret_log();
            }
            else if( choice.equalsIgnoreCase("R")){
                ret_log();
            }
            

            System.out.printf("\n[O]All Logs\t[X]Back\n[T]To Return\t[R]Returned\nChoice:");
            choice = input.next();

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
                view_books();
            }
            else if(choice.equalsIgnoreCase("B")){ //Borrow
                borrow_menu();
            }
            else if(choice.equalsIgnoreCase("R")){ //Return
                return_book();
            }
            else if(choice.equalsIgnoreCase("L")){ //Logs
                view_logs();
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
