package hybrid.it.internship.library.exception;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String info){
        super("Entity with identifier: " + info + " not found!");
    }
}
