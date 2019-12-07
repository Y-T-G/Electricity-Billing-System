package sample;

import java.util.ArrayList;

public abstract class Entries {
    private int acc;
    private boolean acctype;
    private String addr;
    private int ref;
    private double ec;
    private boolean subs;
    private String dueDate;
    private double rate;
    private double total;

    public Entries(){

    }
    public Entries(int acc, boolean acctype, String addr,int ref, double ec, boolean subs, String dueDate, double rate, double total){
        this.acc = acc;
        this.acctype = acctype;
        this.addr = addr;
        this.ref = ref;
        this.ec = ec;
        this.subs = subs;
        this.dueDate = dueDate;
        this.rate = rate;
        this.total = total;
    }

    public int getAcc() {
        return acc;
    }

    public void setAcc(int acc) {
        this.acc = acc;
    }

    public boolean isAcctype() {
        return acctype;
    }

    public void setAcctype(boolean acctype) {
        this.acctype = acctype;
    }

    public int getRef() {
        return ref;
    }

    public void setRef(int ref) {
        this.ref = ref;
    }

    public double getEc() {
        return ec;
    }

    public void setEc(double ec) {
        this.ec = ec;
    }

    public boolean isSubs() {
        return subs;
    }

    public void setSubs(boolean subs) {
        this.subs = subs;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public static ArrayList<Entries> getEntries(){
        ArrayList<Entries> list;
        list = Residential.getEntries();
        list.addAll(Commercial.getEntries());

        //Return concatenated ArrayList
        return list;
    }

    public abstract String getAccName();
}
