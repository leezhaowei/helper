package com.zwli.research.thread.deadlock1.beans;

public class Account implements Comparable<DollarAmount> {

    private DollarAmount balance;

    /**
     * DOC zwli Comment method "getBalance".
     *
     * @return
     */
    public DollarAmount getBalance() {
        return balance;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(DollarAmount o) {
        return balance.compareTo(o);
    }

    /**
     * DOC zwli Comment method "debit".
     *
     * @param amount
     */
    public void debit(DollarAmount amount) {
        // TODO
    }

    /**
     * DOC zwli Comment method "credit".
     *
     * @param amount
     */
    public void credit(DollarAmount amount) {
        // TODO Auto-generated method stub

    }

}
