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
