// ============================================================================
//
// Copyright (C) 2006-2013 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package com.zwli.thread.deadlock1.beans;

/**
 * created by zwli on May 17, 2013 Detailled comment
 * 
 */
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
