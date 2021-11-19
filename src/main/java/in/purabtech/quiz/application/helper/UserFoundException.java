package in.purabtech.quiz.application.helper;

public class UserFoundException extends Exception{
    public UserFoundException(){
        super("user is already present id db. try another username");
    }

    public UserFoundException(final String message) {
        super(message);
    }
}
