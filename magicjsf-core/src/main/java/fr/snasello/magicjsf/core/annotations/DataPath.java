package fr.snasello.magicjsf.core.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import fr.snasello.magicjsf.core.query.DataJoinType;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface DataPath {

	String path();
	
	DataJoinType joinType() default DataJoinType.NONE;
}
