package com.bookapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bookapp.bean.Book;
import com.bookapp.exception.AuthorNotFoundException;
import com.bookapp.exception.BookNotFoundException;
import com.bookapp.exception.CategoryNotFoundException;

public class BookImpl implements BookInter{

	@Override
	public void addBook(Book book) {
	
		Connection connection = ModelDAO.openConnection();
		String sql = "insert into book values(?,?,?,?,?)";
		PreparedStatement st=null;
		try {
			st = connection.prepareStatement(sql);
			st.setString(1, book.getTitle());
			st.setString(2, book.getAuthor());
			st.setString(3, book.getCategory());
			st.setInt(4, book.getBookId());
			st.setInt(5, book.getPrice());
			System.out.println("book added");
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			st.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
				
				try {
					if(st!=null)
					
					st.close();
					if(connection!=null)
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				
		}
	}

	@Override
	public boolean deleteBook(int bookid) throws BookNotFoundException {
		// TODO Auto-generated method stub
		
		boolean flag= false;
		Connection connection = ModelDAO.openConnection();
		String sql ="delete from book where bookid=?";
		PreparedStatement st=null;
		try {
			st = connection.prepareStatement(sql);
			st.setInt(1, bookid);
			flag= true;
			st.execute();
			if(!flag) {
				throw new BookNotFoundException("invalid");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			
			
			try {
				if(st!=null)
				
				st.close();
				if(connection!=null)
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return flag;
	}

	@Override
	public Book getBookById(int bookid) throws BookNotFoundException {
		// TODO Auto-generated method stub
		
	     boolean flag= false;
	     
		String sql ="select * from book where bookid=?";
		Connection connection = ModelDAO.openConnection();
		PreparedStatement st=null;
		
		Book book = new Book();
	
		try {
			
		
			st=connection.prepareStatement(sql);
			st.setInt(1, bookid);
			ResultSet rs = st.executeQuery();
			 
			while(rs.next()) {
				flag = true;
				book.setTitle(rs.getString(1));
				book.setAuthor(rs.getNString(2));
				book.setCategory(rs.getString(3));
				book.setBookid(rs.getInt(4));
				book.setPrice(rs.getInt(5));
				
			}
			if(!flag) {
				throw new BookNotFoundException("invalid id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return book;
		
	}

	@Override
	public boolean updateBook(int bookid, int price) throws BookNotFoundException {
		// TODO Auto-generated method stub
		boolean flag= false;
		String sql="update book set price=? where bookid=?";
		Connection connection = ModelDAO.openConnection();
		PreparedStatement st=null;
		try {
			flag= true;
			st = connection.prepareStatement(sql);
			st.setInt(1, price);
			st.setInt(2, bookid);
			flag= true;
			int result=st.executeUpdate();
			if(result==0) {
				flag= false;
				System.out.println("jss");
				throw new BookNotFoundException("invalid");
			}
			else
				flag= true;
		} catch (SQLException | BookNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		finally {
			
			
			try {
				if(st!=null)
				
				st.close();
				if(connection!=null)
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return flag;
		
	}

	@Override
	public List<Book> getAllBooks() {
		
		// TODO Auto-generated method stub
		List<Book> getallbooks = new ArrayList<>();
		Connection connection = ModelDAO.openConnection();
		PreparedStatement st=null;
		String sql = "select * from book";
		try {
			st = connection.prepareStatement(sql);
			
			ResultSet rs=st.executeQuery();
			boolean flag = false;
			 while(rs.next()) {
				 flag =true;
				Book book = new Book();
				book.setTitle(rs.getString(1));
				book.setAuthor(rs.getString(2));
				book.setCategory(rs.getString(3));
				book.setBookid(rs.getInt(4));
				book.setPrice(rs.getInt(5));
				
				getallbooks.add(book);	
				
			 }if(!flag) {
				 throw new BookNotFoundException("invalid booK name");
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BookNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 finally {
			 try {
					if(st!=null)
					
					st.close();
					if(connection!=null)
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			return getallbooks;
	}
	

	

	@Override
	public List<Book> getBookbyAuthor(String author) throws AuthorNotFoundException{
		// TODO Auto-generated method stub
		List<Book> bookAuthour = new ArrayList<>();
		Connection connection = ModelDAO.openConnection();
		PreparedStatement st=null;
		String sql = "select * from book where author=?";
		try {
			st = connection.prepareStatement(sql);
			st.setString(1, author);
			ResultSet rs=st.executeQuery();
			boolean flag = false;
			 while(rs.next()) {
				 flag= true;
				Book book = new Book();
				book.setTitle(rs.getString(1));
				book.setAuthor(rs.getString(2));
				book.setCategory(rs.getString(3));
				book.setBookid(rs.getInt(4));
				book.setPrice(rs.getInt(5));
				
				bookAuthour.add(book);	
				
			 }if(!flag) {
				 throw new AuthorNotFoundException("invalid authour name");
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 finally {
			 try {
					if(st!=null)
					
					st.close();
					if(connection!=null)
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			return bookAuthour;
	}

	@Override
	public List<Book> getBookbycategory(String category) throws CategoryNotFoundException{
		// TODO Auto-generated method stub
		List<Book> bookCategory = new ArrayList<>();
		Connection connection = ModelDAO.openConnection();
		PreparedStatement st=null;
		String sql = "select * from book where category=?";
		try {
			st = connection.prepareStatement(sql);
			st.setString(1, category);
			ResultSet rs=st.executeQuery();
			boolean flag = false;
			 while(rs.next()) {
				 flag = true;
				Book book = new Book();
				book.setTitle(rs.getString(1));
				book.setAuthor(rs.getString(2));
				book.setCategory(rs.getString(3));
				book.setBookid(rs.getInt(4));
				book.setPrice(rs.getInt(5));
				
				bookCategory.add(book);	
				
			 }if(!flag) {
				throw new CategoryNotFoundException("invalid category");
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 finally {
			 try {
					if(st!=null)
					
					st.close();
					if(connection!=null)
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		return bookCategory;
		
	}
	

	

	

}
