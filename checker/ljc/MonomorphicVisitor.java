package ljc;

import java.util.*;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.*;
import javax.lang.model.type.TypeKind;
import javax.lang.model.util.ElementFilter;
import javax.tools.Diagnostic.Kind;

import checkers.compilermsgs.quals.CompilerMessageKey;
import checkers.igj.quals.Immutable;
import checkers.igj.quals.ReadOnly;
import checkers.nullness.NullnessChecker;
import checkers.quals.DefaultQualifier;
import checkers.quals.Unused;
import checkers.source.Result;
import checkers.source.SourceVisitor;
import checkers.types.*;
import checkers.types.AnnotatedTypeMirror.AnnotatedArrayType;
import checkers.types.AnnotatedTypeMirror.AnnotatedDeclaredType;
import checkers.types.AnnotatedTypeMirror.AnnotatedExecutableType;
import checkers.types.AnnotatedTypeMirror.AnnotatedPrimitiveType;
import checkers.types.AnnotatedTypeMirror.AnnotatedTypeVariable;
import checkers.types.AnnotatedTypeMirror.AnnotatedWildcardType;
import checkers.types.visitors.AnnotatedTypeScanner;
import checkers.util.*;

import checkers.basetype.BaseTypeVisitor;

import com.sun.source.tree.*;
import com.sun.source.util.SourcePositions;
import com.sun.source.util.TreePath;
import com.sun.source.util.TreeScanner;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeInfo;

public class MonomorphicVisitor extends BaseTypeVisitor<MonomorphicChecker> {

    public MonomorphicVisitor(MonomorphicChecker checker, CompilationUnitTree root) {
        super(checker, root);
        System.out.println("initializing visitor");
    }

    @Override
    public Void visitAssignment(AssignmentTree node, Void p) {
        System.out.println(node);
        return super.visitAssignment(node, p);
    }

}
