package com.zwli.thread.deadlock1.beans;

public class DollarAmount implements Comparable<DollarAmount> {

    private Integer nextInt;

    /**
     * DOC zwli DollarAmount constructor comment.
     *
     * @param nextInt
     */
    public DollarAmount(Integer nextInt) {
        this.nextInt = nextInt;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(DollarAmount o) {
        return nextInt.compareTo(o.getNextInt());
    }

    public Integer getNextInt() {
        return this.nextInt;
    }

    public void setNextInt(Integer nextInt) {
        this.nextInt = nextInt;
    }

}
