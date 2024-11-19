package find_your_house.handler;



public class DateNotValidException extends Exception{

    private String message;
    public DateNotValidException(String message){
        this.message=message;
    }
}
