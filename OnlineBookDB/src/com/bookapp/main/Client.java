package com.bookapp.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.bookapp.bean.Book;
import com.bookapp.dao.BookImpl;
import com.bookapp.exception.AuthorNotFoundException;
import com.bookapp.exception.BookNotFoundException;
import com.bookapp.exception.CategoryNotFoundException;

public class Client {

	public static void main(String[] args) {
		
	
		BookImpl book = new BookImpl();
		int choice;
		Scanner sc = new Scanner(System.in);
		System.out.println("enter the choice\n "
				+ "1. Add Book \n"
				+ "2.Delete Book\n"
				+ "3.Update Book\n"
				+ "4.Show all Books\n"
				+ "5.Get Book by Authour\n"
				+ "6.Get Book By Category\n"
				+ "7.Get Book By ID\n"
				+ "");
		choice=sc.nextInt();
		 switch(choice){
		 case 1:			 
			 Book book1 = new Book();
			String title= sc.next();
			String author=sc.next();
			String category=sc.next();
			int bookid=sc.nextInt();
			int price=sc.nextInt();
			
			 book1.setTitle(title);
			 book1.setAuthor(author);
			 book1.setCategory(category);
			 book1.setBookid(bookid);
			 book1.setPrice(price);
			 
			 book.addBook(book1);
			 break;
			 
		 case 2:
			 System.out.println("enter the book id");
			 
			   bookid= sc.nextInt();
			   boolean val=false;
			   try {
				val=book.deleteBook(bookid);
				if(val)
					System.out.println("value deleted");
			} catch (BookNotFoundException e) {
				// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			}
			   break;
		 case 3:
			 System.out.println("enter the id");
			 int id =sc.nextInt();
			 System.out.println("enter the price");
			 int price1 =sc.nextInt();
			 boolean val1=false;
			 try {
				 
				val1 =book.updateBook(id, price1);
				if(val1)
					System.out.println("updated");
				
			} 
			 catch(BookNotFoundException e) {
				 System.out.println(e.getMessage());
			 }
			 break;
			 
		 case 4:
			 System.out.println("");
			 List<Book> getbook = new ArrayList<>();
			 getbook=book.getAllBooks();
			 for(Book b :getbook) {
				 System.out.println("Title :"+b.getTitle() + " , Author :"+b.getAuthor() + ", Category : "+b.getCategory()+ ", Bookid :"+b.getBookId() +", price : "+b.getPrice());
				 
			 }
			 
			 break;
		 case 5:
			
			 String authour;
			 System.out.println("enter authour name");
			 authour=sc.next();
			 
			  List<Book> getauthor = new ArrayList<>();
			  try {
				getauthor=book.getBookbyAuthor(authour);
				for(Book b :getauthor) {
					 System.out.println("Title : "+b.getTitle() + " , Author : "+b.getAuthor()  + " , Category : "+b.getCategory()+ " , Bookid : "+b.getBookId() +" , price : "+b.getPrice());
				}
			} catch (AuthorNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
			  
			 break;
		 case 6:
			 String category1;
			 System.out.println("enter the category name");
			 category1=sc.next();
          List<Book> getcategory = new ArrayList<>();
          try {
        	  getcategory = book.getBookbycategory(category1);
        	  
        	  for(Book b :getcategory) {
					 System.out.println("Title : "+b.getTitle() + " , Author : "+b.getAuthor()  + " , Category : "+b.getCategory()+ " , Bookid : "+b.getBookId() +" , price : "+b.getPrice());
				}
			} catch (CategoryNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
          break;
			  
		 case 7:
			 int bookid1;
			 System.out.println("enter the book id");
			 bookid1 = sc.nextInt();
			 Book book3 = new Book();
			 try {
				book3=book.getBookById(bookid1);
				System.out.println("Title : "+book3.getTitle() + " , Author : "+book3.getAuthor()  + " , Category : "+book3.getCategory()+ " , Bookid : "+book3.getBookId() +" , price : "+book3.getPrice());
				
			} catch (BookNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
			 break;
		 case 8:
			 System.out.println("enter correct choice");
			 break;
				 
		 }
	}

}
