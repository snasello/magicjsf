package fr.snasello.magicjsf.core.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import fr.snasello.magicjsf.core.query.DataJoinType;
import fr.snasello.magicjsf.core.query.OrderType;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface DataPath {

	String path();
	
	DataJoinType join() default DataJoinType.NONE;
	
	OrderType order() default OrderType.NONE;
}
