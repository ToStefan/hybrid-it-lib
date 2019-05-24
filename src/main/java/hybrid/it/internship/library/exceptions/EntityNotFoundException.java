package hybrid.it.internship.library.exceptions;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String id){
        super("Entity with ID: " + id + " not found!");
    }
}
