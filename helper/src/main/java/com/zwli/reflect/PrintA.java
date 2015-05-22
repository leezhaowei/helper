// ============================================================================
//
// Copyright (C) 2006-2014 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package com.zwli.reflect;


/**
 * created by zwli on Mar 31, 2014
 * Detailled comment
 *
 */
public class PrintA implements IPrint {

    @Override
    public void print() {
        System.out.println("### " + this.getClass().getName());
    }
}
