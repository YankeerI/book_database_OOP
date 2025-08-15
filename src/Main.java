import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Library library = new Library();                    
        //library.loadFromFile("Atlantida.txt");        
        //library.loadFromJsonFile("books.json");
        library.loadFromJsonFile("knjige.json");
        Scanner scanner = new Scanner(System.in);             
        boolean running = true;                               


        while (running) {
            LibraryMainMenu.mainMenu();                                                        

            int choice = scanner.nextInt();                                      
            scanner.nextLine();                                                  

            switch (choice) {
                case 1:
                    LibraryMainMenu.handleAllBooks(library);
                    break;
                case 2:
                    LibraryMainMenu.handleAddBook(scanner,library);
                    break;

                case 3:
                    LibraryMainMenu.handleDeleteBook(scanner, library);
                    break;

                case 4:
                    LibraryMainMenu.handleUpdateBook(scanner, library);
                    break;

                case 5:
                    LibraryMainMenu.handleSearchByAuthor(scanner, library);
                    break;

                case 6:
                    LibraryMainMenu.handleSearchByGenre(scanner, library);
                break;

                case 7:
                    LibraryMainMenu.handleFileSaving(scanner, library);
                break;

                case 8:
                     LibraryMainMenu.handleFileLoading(scanner, library);
                break;
                case 9:
                    LibraryMainMenu.handleLoadFromJson(scanner, library);
                    break;
                case 10:
                    LibraryMainMenu.handleSaveToJson(scanner,library);
                    break;
                case 0:
                    running = false;
                    System.out.println("Exiting program.");
                    break;
                default:
                   System.out.println("Invalid choice. Try again.");
                   break;
                    }
            }

        }



    }



