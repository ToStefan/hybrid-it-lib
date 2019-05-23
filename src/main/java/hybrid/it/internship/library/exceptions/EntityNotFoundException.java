package hybrid.it.internship.library.exceptions;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(Long id){
        super("Entity with ID: " + id.toString() + " not found!");
    }
}
