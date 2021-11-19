package in.purabtech.quiz.application.helper;

public class UserNotFoundException extends Exception{

    public UserNotFoundException(){
        super("user not present id db.");
    }

    public UserNotFoundException(final String message) {
        super(message);
    }
}
