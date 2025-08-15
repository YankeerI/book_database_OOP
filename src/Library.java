import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Library {

    private final List<Book> books;

    public Library() {
        books = new ArrayList<>();
    }


    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book " + book.getTitle() + " added successfully");
    }

    public List<Book> getAllBooks() {
        return books;
    }

    public Book getBookById(int id) {
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            if (book.getId() == id) {
                return book;
            }

        }
        return null;
    }


    public Book deleteBookById(int id) {
        Book bookToDelete = getBookById(id);
        if (bookToDelete != null) {
            books.remove(bookToDelete);
            return bookToDelete;
        }
        return null;
    }


    public Book updateBook(int id, String newTitle, String newAuthor, int newPages, String newGenre, String newPublisher) {
        Book bookToUpdate = getBookById(id);
        if (bookToUpdate != null) {
            if (newTitle != null && !newTitle.isBlank())
                bookToUpdate.setTitle(newTitle);
            if (newAuthor != null && !newAuthor.isBlank())
                bookToUpdate.setAuthor(newAuthor);
            if (newPages > 0)
                bookToUpdate.setPages(newPages);
            if (newGenre != null && !newGenre.isBlank())
                bookToUpdate.setGenre(newGenre);
            if (newPublisher != null && !newPublisher.isBlank())
                bookToUpdate.setPublisher(newPublisher);

            System.out.println("Book '" + bookToUpdate.getTitle() + "' successfully updated");
            return bookToUpdate;
        }

        System.out.println("No book found");
        return null;
    }


    public List<Book> searchBookByGenre(String genre) {
        List<Book> result = new ArrayList<>();

        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            if (book.getGenre().equalsIgnoreCase(genre)) {
                result.add(book);
            }
        }

        return result;
    }


    public List<Book> searchBookByAuthor(String author) {
        List<Book> result = new ArrayList<>();

        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            if (book.getAuthor().equalsIgnoreCase(author)) {
                result.add(book);
            }
        }

        return result;
    }

        public void saveToFile (String fileName){
            try (FileWriter writer = new FileWriter(fileName)) {
                for (int i = 0; i < books.size(); i++) {
                    Book book = books.get(i);

                    writer.write(book.getId() + "," +
                            book.getTitle() + "," +
                            book.getAuthor() + "," +
                            book.getPages() + "," +
                            book.getGenre() + "," +
                            book.getPublisher() + "\n");
                }
                System.out.println(books.size() + " books saved to file successfully." + fileName);
            } catch (IOException e) {
                System.out.println("An error occurred while saving to file: " + e.getMessage());
            }


        }


        public void loadFromFile (String fileName){
            //books.clear();
            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 6) {
                        int id = Integer.parseInt(parts[0]);
                        String title = parts[1];
                        String author = parts[2];
                        int pages = Integer.parseInt(parts[3]);
                        String genre = parts[4];
                        String publisher = parts[5];

                        Book book = new Book(id, title, author, pages, genre, publisher);
                        books.add(book);
                    }
                }
                System.out.println(books.size() + " books successfully loaded from: " + fileName);
            } catch (FileNotFoundException e) {
                System.out.println("File not found. Please check the filename and try again.");

            } catch (IOException e) {
                System.out.println("Error reading file: " + e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format in file.");
            }


        }


        public Book findExactDuplicate (Book bookToCheck){
            for (int i = 0; i < books.size(); i++) {
                Book b = books.get(i);
                if (
                        b.getTitle().equalsIgnoreCase(bookToCheck.getTitle()) &&
                                b.getAuthor().equalsIgnoreCase(bookToCheck.getAuthor()) &&
                                b.getPublisher().equalsIgnoreCase(bookToCheck.getPublisher())) {
                    return b;
                }
            }
            return null;
        }


        public void saveToJsonFile (String filename){
            Gson gson = new Gson();
            String json = gson.toJson(this.getAllBooks());

            try (FileWriter writer = new FileWriter(filename)) {
                writer.write(json);
                System.out.println("Library saved to " + filename);
            } catch (IOException e) {
                System.out.println("Error saving library to file: " + e.getMessage());
            }


        }


        public void loadFromJsonFile (String filename){
            Gson gson = new Gson();
            try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
                Book[] booksArray = gson.fromJson(reader, Book[].class);
                for (int i = 0; i < booksArray.length; i++) {
                    addBook(booksArray[i]);
                }
                System.out.println("Library loaded from " + filename);
            } catch (IOException e) {
                System.out.println("Error loading library from file: " + e.getMessage());
            }


        }

    }


