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

package com.deepsky.lang.plsql.psi.impl.spec_func_call;

import com.deepsky.lang.common.PlSqlTokenTypes;
import com.deepsky.lang.plsql.SyntaxTreeCorruptedException;
import com.deepsky.lang.plsql.psi.types.TypeSpec;
import com.deepsky.lang.plsql.struct.Type;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;

public class RankFunction extends SpecFunctionCallBaseImpl {

    private final static TokenSet set = TokenSet.create(
            PlSqlTokenTypes.KEYWORD_RANK, PlSqlTokenTypes.KEYWORD_DENSE_RANK
    );

    public RankFunction(ASTNode astNode) {
        super(astNode);
    }

    @NotNull
    public Type getExpressionType() {
        return Type.NUMBER_TYPE;
    }

    @NotNull
    public String getFunctionName() {
        return getPsiFunctionName().getText();
    }

    @NotNull
    public PsiElement getPsiFunctionName() {
        return getNode().findChildByType(set).getPsi();
    }

}
