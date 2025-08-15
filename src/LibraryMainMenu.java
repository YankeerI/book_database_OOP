import java.util.List;
import java.util.Scanner;

public class LibraryMainMenu {

    public static void mainMenu(){                      
        System.out.println("\nLibrary Menu:");
        System.out.println("1. List all books");
        System.out.println("2. Add a book");
        System.out.println("3. Delete a book by ID");
        System.out.println("4. Update a book");
        System.out.println("5. Search book by writer");
        System.out.println("6. Search book by genre");
        System.out.println("7. Save a book to a file.");
        System.out.println("8. Load a book from a file");
        System.out.println("9. Load books from JSON file");
        System.out.println("10. Save books to a JSON file");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");

    }


    public static void handleAllBooks(Library library){
        List<Book> bookList = library.getAllBooks();
        if (bookList.isEmpty()) {                                      
            System.out.println("There are no books in the list");
        } else {
            for (int i = 0; i < bookList.size(); i++) {
                Book book = bookList.get(i);
                System.out.println(book);
            }
        }

    }

    public static void handleAddBook(Scanner scanner, Library library){
        System.out.print("Enter book ID: ");
        int id;                                            
        while (true) {
            String line = scanner.nextLine();
            try {
                id = Integer.parseInt(line);
                boolean idExists = false;                            
                List<Book> currentBooks = library.getAllBooks();
                for (int i = 0; i < currentBooks.size(); i++) {
                    if (currentBooks.get(i).getId() == id) {
                        idExists = true;                            
                        break;                                      
                    }      }
                if (idExists) {
                    System.out.print("This ID already exists. Please enter a different ID: ");
                    continue;                                                         
                }
                break;  

            } catch (NumberFormatException e) {
                System.out.print("Invalid number, please enter a valid integer: ");
            }
        }

        System.out.print("Enter a book title:");
        String title = "";                                
        while (true) {
            title = scanner.nextLine();
            if (title.trim().isBlank()) {
                System.out.print("Title cannot be empty. Please enter a valid title.");
            } else {
                break;
            }
        }

        System.out.print("Enter a writer's name: ");
        String author = "";                                
        while (true) {
            author = scanner.nextLine();
            if (author.trim().isBlank()) {
                System.out.print("Author cannot be empty. Please enter a valid name.");
            } else {
                break; 
            }
        }

        System.out.print("Enter number of pages: ");
        int numberPages;
        while (true) {
            String line = scanner.nextLine();
            if (line.trim().isBlank()) {                         
                System.out.println("Input cannot be blank.");
                continue;                                        
            }
            try {
                numberPages = Integer.parseInt(line);
                if (numberPages <= 0) {                          
                    System.out.println("Pages must be a positive number.");
                    continue;                                    
                }
                break;  
            } catch (NumberFormatException e) {
                System.out.print("Invalid number, please enter a valid integer.");
            }
        }

        System.out.print("Enter a genre: ");
        String genre = "";                                
        while (true) {
            genre = scanner.nextLine();
            if (genre.trim().isBlank()) {
                System.out.print("Genre cannot be empty. Please enter a valid genre.");
            } else {
                break; 
            }
        }

        System.out.print("Enter book publisher: ");
        String publisher = "";                               
        while (true) {
            publisher = scanner.nextLine();
            if (publisher.trim().isBlank()) {
                System.out.print("Publisher cannot be empty. Please enter a valid publisher.");
            } else {
                break;
            }
        }
        Book newBook = new Book(id, title, author, numberPages, genre, publisher);  
        Book duplicate = library.findExactDuplicate(newBook);                        
        if (duplicate != null) {
            System.out.println("That exact book already exists: " + duplicate.getTitle());
        } else{                                                                             
            library.addBook(newBook);
            }
    }


    public static void handleDeleteBook(Scanner scanner, Library library){
        System.out.print("Enter the ID of the book to delete: ");
        int idToDelete;
        try {
            idToDelete = Integer.parseInt(scanner.nextLine());                 
            Book deletedBook = library.deleteBookById(idToDelete);
            if (deletedBook != null) {
                System.out.println("Book '" + deletedBook.getTitle() + "' successfully deleted.");
            } else {
                System.out.println("No book found with this id.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid integer.");
        }

    }

    public static void handleUpdateBook(Scanner scanner, Library library){              
        System.out.println("Enter an id of a book you want to update");
        while (true) {
            int idInput;
            try {
                idInput = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid ID. Please enter a valid integer.");
                continue;

            }

            Book bookToUpdate = library.getBookById(idInput);
            if (bookToUpdate == null) {
                System.out.println("No book found with this ID.");
              continue;
            }

            String newTitle = null;
            String newAuthor = null;
            int newPages = -1;
            String newGenre = null;
            String newPublisher = null;

            while(true){
                System.out.println("\n Choose field to update:");
                System.out.println("1. Title");
                System.out.println("2. Author");
                System.out.println("3. Pages");
                System.out.println("4. Genre");
                System.out.println("5. Publisher");
                System.out.println("0. Finish updating");

               int choice = Integer.parseInt(scanner.nextLine());


                switch (choice) {
                    case 0:
                        System.out.println("Exit.");
                        return;                                                   
                    case 1:
                        while(true){                                             
                        System.out.print("Enter new title: ");
                        String title = scanner.nextLine();
                        if (title.isBlank())
                        {System.out.println("Title can't be empty.");
                          continue;}
                        else
                        {newTitle=title;
                          System.out.println("Title updated");
                           } break;}                                         
                        break;

                    case 2:
                        while(true){
                        System.out.print("Enter new author: ");
                        String author = scanner.nextLine();
                        if (author.isBlank())
                        {System.out.println("Author can't be empty.");
                       continue;}
                        else
                        {newAuthor=author;
                            System.out.println("Author updated");
                            } break;}
                        break;

                    case 3:
                        while (true) {
                            System.out.print("Enter new pages count: ");
                            String line = scanner.nextLine();
                            if (line.trim().isBlank()) {                         
                                System.out.println("Input cannot be blank.");
                                continue;                                        
                            }
                            try {
                                int numberPages = Integer.parseInt(line);
                                if (numberPages <= 0) {                          
                                    System.out.println("Pages must be a positive number.");
                                    continue;                                   
                                }
                                newPages=numberPages;
                                System.out.println("Pages updated.");
                                break;                                           
                            } catch (NumberFormatException e) {
                                System.out.print("Invalid number, please enter a valid integer.");

                            }}

                             break;

                    case 4:
                        while(true){
                        System.out.print("Enter new genre: ");
                        String genre = scanner.nextLine();
                        if (genre.isBlank())
                        {System.out.println("Genre can't be empty.");
                       continue;}
                        else {
                            newGenre=genre;
                            System.out.println("Genre updated");
                        } break;
                        } break;

                    case 5:
                        while(true){System.out.print("Enter new publisher: ");
                        String publisher = scanner.nextLine();
                        if (publisher.isBlank())
                        {System.out.println("Publisher can't be empty.");
                        continue;}
                        else {
                            newPublisher=publisher;
                            System.out.println("Publisher updated");
                        } break;
                       } break;

                    default:
                        System.out.println("Invalid choice.");
                        break;
                }
                Book updatedBook=library.updateBook(idInput, newTitle, newAuthor, newPages, newGenre, newPublisher);
                if (updatedBook != null) {
                    System.out.println("Successfully updated: " + updatedBook);
                }
            }

    }}


    public static void handleSearchByAuthor(Scanner scanner, Library library){

        System.out.println("Write an author of a book you are after.");
        String authorBook = scanner.nextLine();
        if (!authorBook.isBlank()) {                                          
            List<Book> authorSearch = library.searchBookByAuthor(authorBook);

            if (authorSearch.isEmpty()) {                                       
                System.out.println("No book found by that author.");
            } else {                                                       
                for (int i = 0; i < authorSearch.size(); i++) {
                    Book bookAuthor = authorSearch.get(i);
                    System.out.println(bookAuthor);
                }
            }
        } else {
            System.out.println("Please enter a writer's name.");
        }

    }

    public static void handleSearchByGenre(Scanner scanner, Library library){
       
        System.out.println("Write a genre you are interested in");
        String genreSearch = scanner.nextLine();
        if (!genreSearch.isBlank()) {                                         
            List<Book> genreLook = library.searchBookByGenre(genreSearch);

            if (genreLook.isEmpty()) {                                      
                System.out.println("No book found with that genre.");
            } else {                                                        
                for (int i = 0; i < genreLook.size(); i++) {
                    Book bookGenre = genreLook.get(i);
                    System.out.println(bookGenre);
                }
            }
        } else {
            System.out.println("Please enter a genre.");
        }
    }


    public static void handleFileSaving(Scanner scanner, Library library){
        System.out.print("Enter a name of a file in which the book will be saved: ");
        String fileName = scanner.nextLine();
        if (!fileName.isBlank()) {
            if (!fileName.endsWith(".txt")) {
                fileName = fileName + ".txt";                                
            }
            library.saveToFile(fileName);                                    
        } else {
            System.out.println("File name cannot be empty.");
        }
    }

    public static void handleFileLoading(Scanner scanner, Library library){
        System.out.print("Enter the file name to load books from: ");
        String loadFile = scanner.nextLine();
        if (!loadFile.isBlank()) {
            String name = loadFile;
            if (!name.toLowerCase().endsWith(".txt")) {                    
                name += ".txt";                                               
            }
            library.getAllBooks().clear();                                   
            library.loadFromFile(name);
            List<Book> allBooks = library.getAllBooks();
            for (int i = 0; i < allBooks.size(); i++) {                       
                System.out.println(allBooks.get(i));
            }
        } else {
            System.out.println("File name cannot be blank.");
        }
    }

    public static void handleLoadFromJson(Scanner scanner, Library library){
        System.out.print("Enter the JSON filename to load books from: ");
        String fileName = scanner.nextLine();
        if (!fileName.isBlank()){
            if (!fileName.toLowerCase().endsWith(".json")) {
                fileName += ".json";
            }
            library.getAllBooks().clear();
            library.loadFromJsonFile(fileName);
            List<Book> allBooks = library.getAllBooks();
            for (int i = 0; i < allBooks.size(); i++) {                       
                System.out.println(allBooks.get(i));
            }
        }else {
            System.out.println("Filename cannot be empty.");
        }

    }

    public static void handleSaveToJson(Scanner scanner, Library library){
        System.out.print("Enter a name of a json file in which the book will be saved: ");
        String fileName = scanner.nextLine();
        if (!fileName.isBlank()) {
            if (!fileName.endsWith(".json")) {
                fileName = fileName + ".json";                               
            }
            library.saveToJsonFile(fileName);                                    
        } else {
            System.out.println("File name cannot be empty.");
        }

    }













}
