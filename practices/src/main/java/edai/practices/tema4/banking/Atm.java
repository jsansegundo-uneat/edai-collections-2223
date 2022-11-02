package edai.practices.tema4.banking;

public class Atm {
    private final String name;

    public Atm(String name){
       this.name = name;
   }

   @Override
    public String toString(){
        return "ATM " + name;
   }
}
