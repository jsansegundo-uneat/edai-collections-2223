package edai.practices.tema4.supermarket;

public class CheckoutPoint {
    private Integer currentClient = -1;
    private boolean isFree = true;

    public Integer getCurrentClient() {
        return currentClient;
    }

    public void setCurrentClient(Integer currentClient) {
        this.currentClient = currentClient;
        isFree = false;
    }

    public boolean isFree(){
        return isFree;
    }

    public void complete(){
        isFree = true;
    }
}
