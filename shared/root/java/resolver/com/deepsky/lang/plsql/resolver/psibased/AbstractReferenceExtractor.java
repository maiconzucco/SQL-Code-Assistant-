/*
 * Copyright (c) 2009,2010 Serhiy Kulyk
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *     1. Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     2. Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *
 * SQL CODE ASSISTANT PLUG-IN FOR INTELLIJ IDEA IS PROVIDED BY SERHIY KULYK
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL SERHIY KULYK BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.deepsky.lang.plsql.resolver.psibased;

import com.deepsky.lang.common.ResolveProvider;
import com.deepsky.lang.plsql.resolver.ResolveFacadeImpl;
import com.deepsky.lang.plsql.sqlIndex.AbstractSchema;
import com.deepsky.lang.plsql.tree.MarkupGeneratorEx2;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;


public abstract class AbstractReferenceExtractor extends ReferenceProcessor {

    protected AbstractSchema sindex;

    protected long parsingTime = 0;
    protected long treeProcessTime = 0;

    public AbstractReferenceExtractor(AbstractSchema sindex) {
        this.sindex = sindex;
    }

    public long getParsingTime(){
        return parsingTime;
    }

    public long getTreeProcessTime(){
        return treeProcessTime;
    }

    public void processContent(String content, String fileName) {

        long ms0 = System.currentTimeMillis();

        MarkupGeneratorEx2 generator = new MarkupGeneratorEx2(fileName);
        ASTNode root = generator.parse(content);

        PsiElement element = root.getPsi();
        ((ResolveProvider)element).setResolver(new ResolveFacadeImpl(sindex));

        long ms1 = System.currentTimeMillis();
        parsingTime = ms1-ms0;

        processTree(element);

        long ms2 = System.currentTimeMillis();
        treeProcessTime = ms2 - ms1;
    }

}