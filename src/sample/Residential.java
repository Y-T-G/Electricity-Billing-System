package sample;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Residential extends Entries {
    private String fn;
    private String ln;

    public Residential(){
    }
    public Residential(int acc, boolean acctype, String fn, String ln, String addr, int ref, double ec, boolean subs, String dueDate, double rate, double total){
        super(acc, acctype, addr, ref, ec, subs, dueDate, rate, total);
        this.fn = fn;
        this.ln = ln;
    }

    public String getFn() {
        return fn;
    }

    public void setFn(String fn) {
        this.fn = fn;
    }

    public String getLn() {
        return ln;
    }

    public void setLn(String ln) {
        this.ln = ln;
    }

    public static ArrayList<Entries> getEntries(){
        ArrayList<Entries> list = new ArrayList<Entries>();
        try {
            File file = new File("ResEntries.dat");
            Scanner input = new Scanner(file);
            for(int count = 0; input.hasNext(); count++){
                Residential r = new Residential();
                //System.out.print("" + input.nextInt() + input.nextBoolean()+ input.next()+ input.next()+ input.next()+ input.nextInt()+ input.nextDouble()+ input.nextBoolean()+ input.next()+ input.nextDouble()+ input.nextDouble());
                r.setAcc(input.nextInt());
                r.setAcctype(input.nextBoolean());
                input.nextLine(); //Skip rest of line
                r.setFn(input.nextLine());
                r.setLn(input.nextLine());
                r.setAddr(input.nextLine());
                //System.out.print(input.nextLine());
                r.setRef(input.nextInt());
                r.setEc(input.nextDouble());
                r.setSubs(input.nextBoolean());
                r.setDueDate(input.next());
                r.setRate(input.nextDouble());
                r.setTotal(input.nextDouble());
                list.add(r);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
    @Override
    public String getAccName(){
        return this.fn + " " + this.ln;
    }

    /*
     * Function to write Array to file
     */
    public static void writeBack(ArrayList<Entries> entries){
        try {
            File out = new File("ResEntries.dat");
            PrintWriter output = new PrintWriter(out);
            String type;

            for(int i = 0; i < entries.size(); i++) {
                Residential r = (Residential) entries.get(i);
                output.println(r.getAcc());
                output.println(r.isAcctype());
                output.println(r.getFn());
                output.println(r.getLn());
                output.println(r.getAddr());
                output.println(r.getRef());
                output.println(r.getEc());
                output.println(r.isSubs());
                output.println(r.getDueDate());
                output.println(r.getRate());
                output.println(r.getTotal());
            }
            output.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
