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

import java.util.*;

public class MonomorphicVisitor extends BaseTypeVisitor<MonomorphicChecker> {

    // VARIABLE NAME -> TYPE OF FIRST ASSIGNMENT
    private final Map<String, String> types;

    public MonomorphicVisitor(MonomorphicChecker checker, CompilationUnitTree root) {
        super(checker, root);
        types = new HashMap<String, String>();
    }

    @Override
    public Void visitAssignment(AssignmentTree node, Void p) {
  /*    
   *    System.out.println("Assign: " + node);
        System.out.println("Assign: " + node.getClass());
        System.out.println("Assign: " + atypeFactory.getReceiverType(node));
        */
        checkUse(node.getVariable().toString(), getTypeName(node.getExpression()), node);
        /*
         * ExpressionTree variable = node.getVariable();
        System.out.println(rhs.getClass());
        System.out.println(variable.getClass());
*/
        return super.visitAssignment(node, p);
    }

    private String getTypeName(ExpressionTree constructor) {
        if (constructor instanceof NewClassTree) {
            NewClassTree cons = (NewClassTree) constructor;
            return cons.getIdentifier().toString();
        }
        throw new RuntimeException("we suck");
    }

    @Override
    protected void checkAssignability(AnnotatedTypeMirror varType, Tree varTree) {
        if (varTree instanceof VariableTree && isMonomorph(varType)) {
            VariableTree initialisation = (VariableTree) varTree;
            String variableName = initialisation.getName().toString();
            String className = getTypeName(initialisation.getInitializer());
            registerDeclaration(variableName, className);
            /*
            System.out.println("assignability: " + varTree + "]");
            System.out.println("assignability: " + varTree.getClass());
            System.out.println("assignability type: " + varType);
            */
        }
        super.checkAssignability(varType, varTree);
    }

    // TODO: fix
    private boolean isMonomorph(AnnotatedTypeMirror type) {
        return type.toString().contains("@Monomorph");
    }

    private void registerDeclaration(String variableName, String typeName) {
        types.put(variableName, typeName);
    }

    private void checkUse(String variableName, String typename, Object node) {
        String monomorphicType = types.get(variableName);
        if (monomorphicType != null && !typename.equals(monomorphicType)) {
            checker.report(Result.failure("an instance of "
                + typename + " is being assigned to " + variableName + " but only " + monomorphicType + " should be assigned due to @Monomorphic"), node);
        }
    }

}

