package sample;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Commercial extends Entries {
    private String cn;

    public Commercial(){

    }
    public Commercial(int acc, boolean acctype, String cn, String addr, int ref, double ec, boolean subs, String dueDate, double rate, double total){
        super(acc, acctype, addr, ref, ec, subs, dueDate, rate, total);
        this.cn = cn;
    }

    public String getCn() {
        return cn;
    }

    public void setCn(String cn) {
        this.cn = cn;
    }

    public static ArrayList<Entries> getEntries(){
        ArrayList<Entries> list = new ArrayList<Entries>();
        try {
            File file = new File("ComEntries.dat");
            Scanner input = new Scanner(file);
            for(int count = 0; input.hasNext(); count++){
                Commercial c = new Commercial();
                c.setAcc(input.nextInt());
                c.setAcctype(input.nextBoolean());
                input.nextLine(); //Skip rest of the line
                c.setCn(input.nextLine());
                c.setAddr(input.nextLine());
                c.setRef(input.nextInt());
                c.setEc(input.nextDouble());
                c.setSubs(input.nextBoolean());
                c.setDueDate(input.next());
                c.setRate(input.nextDouble());
                c.setTotal(input.nextDouble());
                list.add(c);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public String getAccName(){
        return this.cn;
    }

    /*
     * Function to write Array to file
     */
    public static void writeBack(ArrayList<Entries> entries){
        try {
            File out = new File("ComEntries.dat");
            PrintWriter output = new PrintWriter(out);
            String type;

            for(int i = 0; i < entries.size(); i++) {
                Commercial c = (Commercial) entries.get(i);
                output.println(c.getAcc());
                output.println(c.isAcctype());
                output.println(c.getCn());
                output.println(c.getAddr());
                output.println(c.getRef());
                output.println(c.getEc());
                output.println(c.isSubs());
                output.println(c.getDueDate());
                output.println(c.getRate());
                output.println(c.getTotal());
            }
            output.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
