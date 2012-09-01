package ljc;

import checkers.quals.*;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

@TypeQualifier
@SubtypeOf(Unqualified.class)
@Target({ElementType.TYPE_PARAMETER, ElementType.TYPE_USE})
public @interface Monomorph {}

