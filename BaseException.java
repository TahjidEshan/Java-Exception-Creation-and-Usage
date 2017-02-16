//package main.java.com.bracu.lab02.exceptionset;

public class BaseException extends Exception {

  private String message = null;

  public BaseException( ) {
    super( );
  }

  public BaseException( String message, Throwable cause ) {
    super( message, cause );
  }

  public BaseException( String message ) {
    super( message );
  }

  public BaseException( Throwable cause ) {
    super( cause );
  }

}
