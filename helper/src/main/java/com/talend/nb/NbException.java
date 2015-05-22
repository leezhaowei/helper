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
package com.talend.nb;

/**
 * created by zwli on Apr 11, 2014 Detailled comment
 */
public class NbException extends Exception {

    private static final long serialVersionUID = -2120144760326255212L;

    public NbException() {
        super();
    }

    public NbException(String message, Throwable cause) {
        super(message, cause);
    }

    public NbException(String message) {
        super(message);
    }

    public NbException(Throwable cause) {
        super(cause);
    }

}
