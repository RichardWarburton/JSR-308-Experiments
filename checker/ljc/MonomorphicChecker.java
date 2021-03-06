package ljc;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.AnnotationMirror;

import checkers.basetype.BaseTypeChecker;
import checkers.nullness.quals.*;
import checkers.quals.TypeQualifiers;
import checkers.quals.PolyAll;
import checkers.source.*;
import checkers.types.*;
import checkers.util.AnnotationUtils;
import com.sun.source.tree.CompilationUnitTree;

@TypeQualifiers({ Monomorph.class })
// @SupportedLintOptions({"nulltest", "uninitialized", "advancedchecks"})
public class MonomorphicChecker extends BaseTypeChecker {


    private AnnotationMirror MONOMORPH;

    @Override
    public void initChecker(ProcessingEnvironment processingEnv) {
        super.initChecker(processingEnv);
        AnnotationUtils annoFactory = AnnotationUtils.getInstance(env);
        MONOMORPH = annoFactory.fromClass(Monomorph.class);
    }

    @Override
    public boolean isSubtype(AnnotatedTypeMirror subtype, AnnotatedTypeMirror supertype) {
        return super.isSubtype(subtype, supertype) && checkTypeEquivalence(subtype, supertype);
    }

    private boolean checkTypeEquivalence(AnnotatedTypeMirror subtype, AnnotatedTypeMirror supertype) {
        /*System.out.println(" subtyping " );
        System.out.println(subtype);
        System.out.println(subtype.getClass());
        System.out.println(supertype);
        System.out.println(supertype.getClass());
        */
        if (subtype.getEffectiveAnnotations().contains(MONOMORPH)) {
            return subtype.getCopy(false).equals(supertype.getCopy(false));
        }

        return true;
    }

    /*@Override
    protected SourceVisitor<?, ?> createSourceVisitor(CompilationUnitTree root) {
        return new MonomorphicVisitor(this, root);
    }*/

    /*
     * TODO: actually use the MultiGraphQH and incorporate rawness.
    @Override
    protected MultiGraphQualifierHierarchy.MultiGraphFactory createQualifierHierarchyFactory() {
        return new MultiGraphQualifierHierarchy.MultiGraphFactory(this);
    }

    @Override
    protected QualifierHierarchy createQualifierHierarchy() {
        return new NullnessQualifierHierarchy((MultiGraphQualifierHierarchy)super.createQualifierHierarchy());
    }

    private final class NullnessQualifierHierarchy extends MultiGraphQualifierHierarchy {
        public NullnessQualifierHierarchy(MultiGraphQualifierHierarchy hierarchy) {
            super(hierarchy);
        }
    }
    */
}
