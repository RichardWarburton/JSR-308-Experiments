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

@TypeQualifiers({ Monomorph.class })
// @SupportedLintOptions({"nulltest", "uninitialized", "advancedchecks"})
public class MonomorphicChecker extends BaseTypeChecker {


    // TODO: This lint option should only be temporary, until all checks are implemented correctly.
    public static final boolean ADVANCEDCHECKS_DEFAULT = false;

    //protected AnnotationMirror NONNULL, NULLABLE, PRIMITIVE;

    @Override
    public void initChecker(ProcessingEnvironment processingEnv) {
        super.initChecker(processingEnv);
        System.out.println("initializing checker");
        //AnnotationUtils annoFactory = AnnotationUtils.getInstance(env);
        //NULLABLE = annoFactory.fromClass(Nullable.class);
    }


/*
    class NullnessTypeHierarchy extends TypeHierarchy {

        public NullnessTypeHierarchy(BaseTypeChecker checker,
                QualifierHierarchy qualifierHierarchy) {
            super(checker, qualifierHierarchy);
        }

        @Override
        protected boolean isSubtypeAsTypeArgument(AnnotatedTypeMirror sub, AnnotatedTypeMirror sup) {
            // @Primitive and @NonNull are interchangeable
            if (sub.getEffectiveAnnotations().contains(PRIMITIVE) &&
                    sup.getEffectiveAnnotations().contains(NONNULL)) {
                return true;
            }
            return super.isSubtypeAsTypeArgument(sub, sup);
        }

    }
*/
    /*
     * TODO: it's ugly that this method cannot be in the TypeHierarchy, as these methods
     * are final there. Try to refactor this.
     */
//    @Override
//    public boolean isSubtype(AnnotatedTypeMirror sub, AnnotatedTypeMirror sup) {
        // @Primitive and @NonNull are interchangeable
        /*
         * if (sub.getEffectiveAnnotations().contains(PRIMITIVE) &&
                sup.getEffectiveAnnotations().contains(NONNULL)) {
            return true;
        }
        */
        //return super.isSubtype(sub, sup);
    //}

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
